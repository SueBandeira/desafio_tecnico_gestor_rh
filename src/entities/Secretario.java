package entities;

import java.time.LocalDate;
import java.time.Period;

public class Secretario extends Funcionario {
  public double incrementoAnual;
  public double beneficio;
  Funcionario funcionario;


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

    return getSalario(mes, ano, incrementoAnual) + beneficio * (getSalario(mes, ano, incrementoAnual));
  }

  public double getBeneficio(int mes, int ano, double incrementoAnual) {
    LocalDate dataAtual = LocalDate.of(ano, mes, 1);
    LocalDate admissao = LocalDate.of(this.getAno(), this.getMes(), 1);
    int anos = 0;
    //entrega mês e ano
    Period intervalo = Period.between(admissao, dataAtual);
    anos = Math.abs(intervalo.getYears());
    double result = beneficio * this.getSalario(mes, ano, incrementoAnual);
    return result;
  }

  //Construtor para secretário
  public Secretario() {
    this.setSalario(7000);
    this.beneficio = 0.2;
    this.incrementoAnual = 1000;
  }
}
