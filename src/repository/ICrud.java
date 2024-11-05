package repository;

import entities.Funcionario;

import java.util.List;
import java.util.Optional;

public interface ICrud {
  List<Funcionario> getFuncionarios();

  List<Funcionario> getFuncionariosComBeneficio();

  void contratarFuncionario(Funcionario funcionario);

  void removerFuncionario(String nome, String cargo);

  Optional<Funcionario> buscarFuncionario(String nome, String cargo);

  Funcionario atualizaFuncionario(String nome, String cargo);

  double valorTotalPago(List<Funcionario> funcionario, int mes, int ano);

  double salarioTotalPago(List<Funcionario> funcionario, int mes, int ano);

  double beneficiosTotalPago(List<Funcionario> funcionario, int mes, int ano);

  double valorAltoTotalPago(List<Funcionario> funcionario, int mes, int ano);

  String funcionarioComMaiorBeneficio(List<Funcionario> funcionario, int mes, int ano);

  double vendedoresTotalPago(List<Funcionario> vendedores, int mes, int ano);

  void verificarBeneficioFuncionario(List<Funcionario> funcionarios);

}
