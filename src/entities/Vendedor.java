package entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Vendedor extends Funcionario {
  //Classe filha de funcionario
  private double beneficio;
  public double incrementoAnual;
  private double vendaDoMes = 0;
  Funcionario funcionario;
  Map<String, Double> vendas = new LinkedHashMap<>();

  public void addVendas(int mes, int ano, double valor) {
    Scanner s = new Scanner(System.in);
    String chave = mes + "/" + ano;
    int opc = 1;

    if (vendas.containsKey(chave)) {
      System.out.println("Já existe um valor total de vendas esse mês. Deseja atualizar? [1] - Sim [2] - Não");
      opc = s.nextInt();
    }

    switch (opc) {
      case 1:
        vendas.put(chave, valor);
        return;
      case 2:
        break;
      default:
    }
  }

  public void listarVendas() {
    StringBuilder result = new StringBuilder("Listagem de vendas:");
    result.append("\nMês\tAno\t\tValor\n");

    this.vendas.forEach((k, v) -> {
      String[] mesAno = k.split("/");
      result.append(String.format("%s\t%s\t%.2f\n", mesAno[0], mesAno[1], v));
    });
    System.out.print(result);
  }

  public boolean hasVendas() {
    return !this.vendas.isEmpty();
  }

  public double salarioTotal() {
    LocalDate dataAtual = LocalDate.now();
    return salarioTotal(dataAtual.getMonthValue(), dataAtual.getYear());
  }

  public double salarioTotal(int mes, int ano) {
    LocalDate dataAtual = LocalDate.of(ano, mes, 1);
    LocalDate admissao = LocalDate.of(this.getAno(), this.getMes(), 1);
    int anos = 0;
    //entrega mês e ano
    Period intervalo = Period.between(admissao, dataAtual);
    anos = Math.abs(intervalo.getYears());

    System.out.println(dataAtual);
    String chave = mes + "/" + ano;
    if (vendas.containsKey(chave)) {
      vendaDoMes = vendas.get(chave);
    }

    return getSalario(mes, ano, incrementoAnual) + beneficio * vendaDoMes;
  }

  public double getBeneficio(int mes, int ano, double incrementoAnual) {
    LocalDate dataAtual = LocalDate.of(ano, mes, 1);
    LocalDate admissao = LocalDate.of(this.getAno(), this.getMes(), 1);
    int anos = 0;
    //entrega mês e ano
    Period intervalo = Period.between(admissao, dataAtual);
    anos = Math.abs(intervalo.getYears());

    return beneficio * this.getSalario(mes, ano, incrementoAnual);
  }

  public Vendedor() {
    this.setSalario(12000);
    this.beneficio = 0.3;
    this.incrementoAnual = 1800;
  }
}
