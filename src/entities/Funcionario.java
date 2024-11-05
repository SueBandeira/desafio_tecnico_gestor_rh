package entities;

import enums.Cargo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Objects;

public class Funcionario {
  private String nome;
  private Cargo cargo;
  private int mes;//Fazer validação
  private int ano;//Fazer validação
  private double salario;
  protected double incrementoAnual;
  LocalDateTime mesAno = LocalDateTime.now();

  //Construtor
  //Inicializa com default
  public Funcionario() {

  }

  public double getBeneficio(int mes, int ano, double incrementoAnual) {
    return 0;
  }

  public double getIncrementoAnual() {
    return this.incrementoAnual;
  }

  public static class FuncionarioBuilder {

    private Funcionario entity;

    private FuncionarioBuilder() {
      this.entity = new Funcionario();
    }

    public static FuncionarioBuilder builder() {
      return new FuncionarioBuilder();
    }

    public Funcionario build() {
      return this.entity;
    }

    public FuncionarioBuilder nome(String nome) {
      this.entity.setNome(nome);
      return this;
    }

    public FuncionarioBuilder cargo(Cargo cargo) {
      this.entity.setCargo(cargo);
      return this;
    }

    public FuncionarioBuilder mes(int mes) {
      this.entity.setMes(mes);
      return this;
    }

    public FuncionarioBuilder ano(int ano) {
      this.entity.setAno(ano);
      return this;
    }

    public FuncionarioBuilder salario(double salario) {
      this.entity.setSalario(salario);
      return this;
    }
  }

  public double salarioTotal() {
    return this.salario;
  }

  //TODO VERIFICAR ESSE MÉTODO
  public double salarioTotal(int mes, int ano) {
    return this.salario;
  }

  //Getters: imprimir os dados
  public String getNome() {
    return nome;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public int getMes() {
    return mes;
  }

  public int getAno() {
    return ano;
  }

  public double getSalario() {
    return salario;
  }

  public double getSalario(int mes, int ano, double incrementoAnual) {
    LocalDate dataAtual = LocalDate.of(ano, mes, 1);
    LocalDate admissao = LocalDate.of(this.getAno(), this.getMes(), 1);
    int anos = 0;
    //entrega mês e ano
    Period intervalo = Period.between(admissao, dataAtual);
    anos = Math.abs(intervalo.getYears());

    return this.getSalario() + anos * incrementoAnual;
  }


  //Setters: definir os dados
  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public void setMes(int mes) {
    this.mes = mes;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public void setSalario(double salario) {
    this.salario = salario;
  }

  public String toString() {
    return "Nome: " + nome + ", Cargo: " + cargo + ", Mês: " + mes + ", Ano: " + ano;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Funcionario that = (Funcionario) o;
    return mes == that.mes && ano == that.ano && Double.compare(salario, that.salario) == 0 && Objects.equals(nome, that.nome) && cargo == that.cargo;
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, cargo, mes, ano, salario);
  }
}