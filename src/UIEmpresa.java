import entities.Funcionario;
import entities.Gerente;
import entities.Secretario;
import entities.Vendedor;
import enums.Cargo;
import repository.ICrud;

import java.lang.reflect.Field;
import java.util.*;

public class UIEmpresa {
  private ICrud empresa;

  public UIEmpresa(ICrud empresa) {
    this.empresa = empresa;
  }

  public void start() {
    Scanner sc = new Scanner(dadosDesafio());
    Secretario secretario = new Secretario();
    Vendedor vendedor = new Vendedor();
    Gerente gerente = new Gerente();
    Funcionario funcionario;

    label:
    while (true) {
      System.out.print(menuPrincipal());
      System.out.print("==> ");
      var escolha = sc.nextLine();

      switch (escolha) {
        case "1":
          funcionario = criarFuncionario(sc);
          this.empresa.contratarFuncionario(funcionario);
          break;
        case "2":
          atualizarFuncionario(sc);
          break;
        case "3":
          removerFuncionario(sc);
          break;
        case "4":
          if (!this.empresa.getFuncionarios().isEmpty())
            this.empresa.getFuncionarios().forEach(item -> System.out.println(item));
          else
            System.out.print("\u001b[01;31mNenhum funcionário contratado\u001b[0m");
          break;
        case "5":
          //TODO PARA MOSTRAR O SALARIO DEVE-SE PEDIR O NOME E CARGO
          System.out.println("\n" + getSalario(sc));
          break;
        case "6":
          atualizarVendasFuncionario(sc);
          break;
        case "7":
          listarVendas();
          break;
        case "8":
          menuSecundario(sc);
          break;
        case "99":
          break label;
      }
    }
  }

  private String dadosDesafio() {
    return """
        1
        Jorge Carvalho
        Secretario
        1
        2018
        1
        Maria Souza
        Secretario
        12
        2015
        1
        Ana Silva
        Vendedor
        12
        2021
        1
        João Mendes
        Vendedor
        12
        2021
        1
        Juliana Alves
        Gerente
        7
        2017
        1
        BentoAlbino
        Gerente
        3
        2014
        4
        6
        Ana Silva
        10
        2024
        6800
        6
        Ana Silva
        12
        2021
        5200
        6
        João Mendes
        10
        2024
        6350
        6
        João Mendes
        12
        2021
        3400
        5
        Ana Silva
        Vendedor
        12
        2021
        5
        Ana Silva
        Vendedor
        10
        2024
        7
        4
        8
        1
        10
        2024
        2
        10
        2024
        3
        10
        2024
        4
        10
        2024
        5
        10
        2024
        7
        99
        """;
  }

  private String carregarDados() {
    return """
        1
        João
        Vendedor
        4
        2018
        4
        2
        João
        Vendedor
        Thiago
        Gerente
        6
        2016
        4
        1
        Maria
        Gerente
        6
        2017
        1
        Raymond
        Secretario
        7
        2015
        4
        3
        John
        Vendedor
        4
        1
        Giselle
        Vendedor
        4
        2022
        4
        5
        """;
  }

  private String menuPrincipal() {
    return """
        \n
        \t\tMenu principal
        -----------------------------
        [1] Contratar funcionário
        [2] Atualizar funcionário
        [3] Remover funcionário
        [4] Listar funcionários
        [5] Salário de um funcionário
        [6] Adicionar vendas
        [7] Listar vendas
        [8] Contabilidade
        [99] Sair
        """;
  }

  private String menuSecundario() {
    return """
        \n
        \t\tMenu para contabilidade
        ----------------------------------------------------
        [1] Valor total pago em salários e benefícios no mês
        [2] Valor total pago em salários no mês
        [3] Valor total pago em benefícios no mês
        [4] Salário mais alto no mês
        [5] Benefício mais alto
        [6] Vendedor que mais vendeu no mês
        [7] Voltar
        """;
  }

  private void menuSecundario(Scanner sc) {
    label:
    while (true) {
      System.out.println(menuSecundario());
      System.out.print("==> ");
      var escolha = sc.nextLine();

      switch (escolha) {
        case "1":
          valorTotal(sc);
          break;
        case "2":
          valorSalario(sc);
          break;
        case "3":
          valorBeneficioTotal(sc);
          break;
        case "4":
          valorMaisAltoSalario(sc);
          break;
        case "5":
          valorMaisAltoBeneficio(sc);
          break;
        case "7":
          break label;
      }
    }
  }

  public void valorTotal(Scanner sc) {
    System.out.print("Mês de referência: ");
    int mes = sc.nextInt();

    System.out.print("Ano de referência: ");
    int ano = sc.nextInt();


    System.out.println("\nValor total pago em SALÁRIOS e BENEFÍCIOS no mês " + mes + " e no ano " + ano + ": " +
        this.empresa.valorTotalPago(this.empresa.getFuncionarios(), mes, ano));
  }

  public void valorSalario(Scanner sc) {
    System.out.print("Mês de referência: ");
    int mes = sc.nextInt();

    System.out.print("Ano de referência: ");
    int ano = sc.nextInt();

    System.out.println("\nValor total pago SOMENTE em salários no mês " + mes + " e no ano " + ano + ": " +
        this.empresa.salarioTotalPago(this.empresa.getFuncionarios(), mes, ano));
  }

  public void valorBeneficioTotal(Scanner sc) {
    System.out.print("Mês de referência: ");
    int mes = sc.nextInt();

    System.out.print("Ano de referência: ");
    int ano = sc.nextInt();

    System.out.println("\nValor total pago SOMENTE em benefícios no mês " + mes + " e no ano " + ano + ": " +
        this.empresa.beneficiosTotalPago(this.empresa.getFuncionariosComBeneficio(), mes, ano));
  }

  public void valorMaisAltoSalario(Scanner sc) {
    System.out.print("Mês de referência: ");
    int mes = sc.nextInt();

    System.out.print("Ano de referência: ");
    int ano = sc.nextInt();

    System.out.println("\nSalário MAIS ALTO pago à um funcionário no mês " + mes + " e no ano " + ano + ": " +
        this.empresa.valorAltoTotalPago(this.empresa.getFuncionarios(), mes, ano));
  }

  public void valorMaisAltoBeneficio(Scanner sc) {
    System.out.print("Mês de referência: ");
    int mes = sc.nextInt();

    System.out.print("Ano de referência: ");
    int ano = sc.nextInt();

    System.out.println("\nO funcionário que recebeu o MAIOR BENEFÍCIO no mês " + mes + " e no ano " + ano + " foi: "
        + this.empresa.funcionarioComMaiorBeneficio(this.empresa.getFuncionariosComBeneficio(), mes, ano));
  }

  private void listarVendas() {
    List<Funcionario> todosFuncionarios = this.empresa.getFuncionarios();
    Vendedor vendedor;
    for (Funcionario funcionario : todosFuncionarios) {
      if (funcionario instanceof Vendedor) {
        vendedor = (Vendedor) funcionario;
        if (vendedor.hasVendas()) {
          System.out.println("Vendedor: " + vendedor.getNome());
          vendedor.listarVendas();
        }
      }
    }
  }

  private void atualizarVendasFuncionario(Scanner sc) {
    System.out.print("Digite seu nome: ");
    String nome = sc.nextLine();

    Optional<Funcionario> funcionario = this.empresa.buscarFuncionario(nome, String.valueOf(Cargo.VENDEDOR));
    if (funcionario.isEmpty()) {
      System.out.println("Funcionário " + nome + "não encontrado.");
      return;
    }
    Vendedor vendedor = (Vendedor) funcionario.get();
    System.out.println("Digite o mês de referência: ");
    int mes = sc.nextInt();

    System.out.println("Digite o ano de referência: ");
    int ano = sc.nextInt();

    System.out.println("Digite o valor total de vendas no mês: ");
    double valor = sc.nextDouble();

    vendedor.addVendas(mes, ano, valor);
  }

  private Funcionario criarFuncionario(Scanner sc) {
    Funcionario retornoFuncionario;
    System.out.print("Digite seu nome: ");
    String nome = sc.nextLine();

    System.out.print("Cargo na empresa: ");
    String cargo = sc.nextLine();

    retornoFuncionario = flagFuncionario(Cargo.valueOf(cargo.toUpperCase()));
    var tipoDeFuncionario = retornoFuncionario instanceof Secretario ? (Secretario) retornoFuncionario :
        retornoFuncionario instanceof Vendedor ? (Vendedor) retornoFuncionario : retornoFuncionario instanceof
            Gerente ? (Gerente) retornoFuncionario : null;

    if (tipoDeFuncionario == null) {
      System.exit(1);
    }
    System.out.print("Mês de entrada no cargo: ");
    int mes = sc.nextInt();

    System.out.print("Ano de entrada no cargo: ");
    int ano = sc.nextInt();
    retornoFuncionario.setNome(nome);
    retornoFuncionario.setCargo(Cargo.valueOf(cargo.toUpperCase()));
    retornoFuncionario.setMes(mes);
    retornoFuncionario.setAno(ano);

    sc.nextLine();
    return retornoFuncionario;
  }

  public double getSalario(Scanner sc) {
    System.out.print("Digite o nome do funcionário para ver o salário: ");
    String nome = sc.nextLine();

    System.out.print("Cargo na empresa: ");
    String cargo = sc.nextLine();

    Optional<Funcionario> funcionario = this.empresa.buscarFuncionario(nome, cargo);
    if (funcionario.isPresent()) {
      if (funcionario.get() instanceof Vendedor) {
        System.out.println("Informe o mês que deseja ver para calcular o salário: ");
        int mes = sc.nextInt();
        System.out.println("Informe o ano que deseja ver para calcular o salário: ");
        int ano = sc.nextInt();

        return ((Vendedor) funcionario.get()).salarioTotal(mes, ano);
      }

      return funcionario.get().salarioTotal();
    }
    throw new IllegalArgumentException("Funcionário " + nome + " de cargo" + cargo + " não encontrado.");
  }

  public Funcionario flagFuncionario(Cargo cargo) {
    return switch (cargo) {
      case SECRETARIO -> new Secretario();
      case VENDEDOR -> new Vendedor();
      case GERENTE -> new Gerente();
      //TODO FAZER LÓGICA PARA QUANDO EXISTIR A CLASSE GERENTE
      default -> throw new RuntimeException("Cargo " + cargo + " não definido.");
    };
  }

  private void removerFuncionario(Scanner r) {
    System.out.println("Nome do funcionário que deseja remover: ");
    String nome = r.nextLine();

    System.out.println("Cargo do funcionário que deseja remover: ");
    String cargo = r.nextLine();

    this.empresa.removerFuncionario(nome, cargo);
  }

  private void atualizarFuncionario(Scanner a) {
    System.out.println("Nome do funcionário que deseja atualizar: ");
    String nome = a.nextLine();

    System.out.println("Cargo do funcionário que deseja atualizar: ");
    String cargo = a.nextLine();


    try {
      Funcionario enviar = this.empresa.buscarFuncionario(nome, cargo).get();

      enviar.setNome(nome);
      enviar.setCargo(Cargo.valueOf(cargo.toUpperCase()));

      Map<String, String> fields = new LinkedHashMap<>();
      String answer;
      Field[] campos = Funcionario.class.getDeclaredFields();
      for (Field campo : campos) {
        System.out.printf("Informe o campo %s?\n ou dê apenas `Enter`: ", campo.getName());
        answer = a.nextLine().trim();
        if (!answer.equalsIgnoreCase("\n") && !answer.isEmpty()) {
          fields.put(campo.getName(), answer);
        } else
          fields.put(campo.getName(), null);
      }
      updateFuncionario(enviar, fields);
      System.out.println(enviar);
/*
      Funcionario result = this.empresa.atualizaFuncionario();
      System.out.println("Cadastro de " + result.getNome() + " atualizado.");
*/
    } catch (RuntimeException ex) {
      System.out.println(ex.getMessage());
    }
  }

  public void updateFuncionario(Funcionario funcionario, Map<String, String> mapa) {
    mapa.forEach((k, v) -> {
      if (v != null) {
        switch (k) {
          case "nome":
            funcionario.setNome(v);
            break;
          case "cargo":
            funcionario.setCargo(Cargo.valueOf(v.toUpperCase()));
            break;
          case "mes":
            funcionario.setMes(Integer.parseInt(v));
            break;
          case "ano":
            funcionario.setAno(Integer.parseInt(v));
            break;
          default:
            System.out.println("Campo não associado/mapeado.");
        }
      }

    });
  }
}