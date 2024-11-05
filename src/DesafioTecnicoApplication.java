import entities.Empresa;

public class DesafioTecnicoApplication {
  public static void main(String[] args) {
    UIEmpresa gestor = new UIEmpresa(new Empresa());

    gestor.start();
  }
}