package entities;

import enums.Cargo;
import repository.ICrud;

import java.util.*;

public class Empresa implements ICrud {
  private List<Funcionario> funcionarios = new ArrayList<>();
  private List<Funcionario> funcionarioComB = new ArrayList<>();
  private Set<Funcionario> funcionariosBeneficio = new HashSet<>();

  public void contratarFuncionario(Funcionario funcionario) {
    funcionarios.add(funcionario);
  }

  @Override
  public void verificarBeneficioFuncionario(List<Funcionario> funcionarios) {
    for (Funcionario funcionario : funcionarios) {
      if (funcionario.getCargo() == Cargo.VENDEDOR)
        funcionariosBeneficio.add(funcionario);
      else if (funcionario.getCargo() == Cargo.SECRETARIO)
        funcionariosBeneficio.add(funcionario);
    }
  }

  @Override
  public double valorTotalPago(List<Funcionario> funcionarios, int mes, int ano) {
    double somaSalario = 0;
    //Esse método define quanto a empresa vai gastar no total para pagar os funcionários
    for (Funcionario funcionario : funcionarios) {
      if (funcionario.getCargo() == Cargo.VENDEDOR) {
        somaSalario += ((Vendedor) funcionario).salarioTotal(mes, ano);
      } else if (funcionario.getCargo() == Cargo.SECRETARIO) {
        somaSalario += ((Secretario) funcionario).salarioTotal(mes, ano);
      } else {
        somaSalario += ((Gerente) funcionario).salarioTotal(mes, ano);
      }
    }
    return somaSalario;
  }

  @Override
  public double salarioTotalPago(List<Funcionario> funcionarios, int mes, int ano) {
    double somaSalario = 0;
    //Esse método define quanto a empresa vai gastar no total para pagar os funcionários
    for (Funcionario funcionario : funcionarios) {
      if (funcionario.getCargo() == Cargo.VENDEDOR) {
        somaSalario += ((Vendedor) funcionario).getSalario(mes, ano, ((Vendedor) funcionario).incrementoAnual);
      } else if (funcionario.getCargo() == Cargo.SECRETARIO) {
        somaSalario += ((Secretario) funcionario).getSalario(mes, ano, ((Secretario) funcionario).incrementoAnual);
      } else {
        somaSalario += ((Gerente) funcionario).getSalario(mes, ano, ((Gerente) funcionario).incrementoAnual);
      }
    }
    return somaSalario;
  }

  @Override
  public double beneficiosTotalPago(List<Funcionario> funcionariosComB, int mes, int ano) {
    double somaBeneficio = 0;
    for (Funcionario funcionario : funcionariosComB) {
      if (funcionario.getCargo() == Cargo.VENDEDOR) {
        double getSalario = ((Vendedor) funcionario).getSalario(mes, ano, ((Vendedor) funcionario).incrementoAnual);
        double salarioTotal = ((Vendedor) funcionario).salarioTotal(mes, ano);
        somaBeneficio += salarioTotal - getSalario;
      } else if (funcionario.getCargo() == Cargo.SECRETARIO) {
        somaBeneficio += (-((Secretario) funcionario).getSalario(mes, ano, ((Secretario) funcionario).incrementoAnual)
            + ((Secretario) funcionario).salarioTotal(mes, ano));
      }
    }
    return somaBeneficio;
  }

  @Override
  public double valorAltoTotalPago(List<Funcionario> funcionarios, int mes, int ano) {
    double salario = 0;
    double maiorSalario = 0;
    //Esse método define quanto a empresa vai gastar no total para pagar os funcionários
    for (Funcionario funcionario : funcionarios) {
      if (funcionario.getCargo() == Cargo.VENDEDOR) {
        salario = ((Vendedor) funcionario).salarioTotal(mes, ano);
        maiorSalario = Math.max(salario, maiorSalario);
      } else if (funcionario.getCargo() == Cargo.SECRETARIO) {
        salario = ((Secretario) funcionario).salarioTotal(mes, ano);
        maiorSalario = Math.max(salario, maiorSalario);
      } else {
        salario = ((Gerente) funcionario).salarioTotal(mes, ano);
        maiorSalario = Math.max(salario, maiorSalario);
      }
    }
    return maiorSalario;
  }

  @Override
  public String funcionarioComMaiorBeneficio(List<Funcionario> funcionariosComB, int mes, int ano) {
    double salarioBeneficio = 0;
    double maiorBeneficio = 0;
    String nomeFuncionario = null;
    Funcionario fulano;

    for (Funcionario funcionario : funcionariosComB) {
      salarioBeneficio = 0;
      if (funcionario.getCargo() == Cargo.VENDEDOR) {
        fulano = (Vendedor) funcionario;
        salarioBeneficio = fulano.getBeneficio(mes, ano, fulano.getIncrementoAnual());
      } else if (funcionario.getCargo() == Cargo.SECRETARIO) {
        fulano = (Secretario) funcionario;
        salarioBeneficio = fulano.getBeneficio(mes, ano, fulano.getIncrementoAnual());
      } else
        continue;

      if (salarioBeneficio > maiorBeneficio) {
        nomeFuncionario = fulano.getNome();
        maiorBeneficio = salarioBeneficio;
      }
    }
    return nomeFuncionario;
  }

  @Override
  public double vendedoresTotalPago(List<Funcionario> vendedores, int mes, int ano) {
    return 0;
  }

  public List<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void removerFuncionario(String nome, String cargo) {
    funcionarios.removeIf(item -> item.getNome().equals(nome) && item.getCargo().equals((Cargo.valueOf(cargo.toUpperCase()))));
  }

  public Optional<Funcionario> buscarFuncionario(String nome, String cargo) {
    return funcionarios.stream().filter(item -> item.getNome().equals(nome) && item.getCargo().equals(Cargo.valueOf(cargo.toUpperCase()))).findFirst();
  }

  public Funcionario atualizaFuncionario(String nome, String cargo) {
    Funcionario funcionario = buscarFuncionario(nome, cargo).orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
    funcionario.setNome(nome);
    funcionario.setCargo(Cargo.valueOf(cargo.toUpperCase()));

    return funcionario;
  }

  public List<Funcionario> getFuncionariosComBeneficio() {
    verificarBeneficioFuncionario(this.funcionarios);
    return funcionariosBeneficio.stream().toList();
  }
}
