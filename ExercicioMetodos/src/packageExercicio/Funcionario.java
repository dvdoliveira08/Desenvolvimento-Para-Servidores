
package packageExercicio;


public class Funcionario {
    String nome;
    double salario;
    
    void aumentaSalario (double valor){
        this.salario += valor;
    }
    
    void exibirDados() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Salario: R$ " + this.salario);
    }
}
