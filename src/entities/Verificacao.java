package entities;

import enums.Cargo;

import java.util.LinkedHashSet;
import java.util.Set;

public class Verificacao {
  public static void main(String[] args) {
    Funcionario f2 = new Secretario();
    f2.setNome("João");
    f2.setCargo(Cargo.SECRETARIO);
    f2.setMes(1);
    f2.setAno(2018);
    Funcionario f3 = new Secretario();
    f3.setNome("Maria");
    f3.setCargo(Cargo.SECRETARIO);
    f3.setMes(1);
    f3.setAno(2015);
    Funcionario f4 = new Secretario();
    f4.setNome("Maria");
    f4.setCargo(Cargo.SECRETARIO);
    f4.setMes(1);
    f4.setAno(2015);
    Funcionario f5 = new Vendedor();
    f5.setNome("Kleber");
    f5.setCargo(Cargo.VENDEDOR);
    f5.setMes(1);
    f5.setAno(2019);

    Set<Funcionario> setFuncionario = new LinkedHashSet<>();
    setFuncionario.add(f2);
    setFuncionario.add(f3);
    setFuncionario.add(f4);
    setFuncionario.add(f5);

    for (Funcionario funcionario : setFuncionario) {
      System.out.println(funcionario);
    }

  }
}
