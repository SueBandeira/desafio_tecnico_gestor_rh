package entities;

import java.time.LocalDate;
import java.time.Period;

public class Gerente extends Funcionario {
  public double incrementoAnual;

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

    return getSalario(mes, ano, incrementoAnual);
  }

  //Construtor para secretário
  public Gerente() {
    this.setSalario(20000);
    this.incrementoAnual = 3000;
  }
}
