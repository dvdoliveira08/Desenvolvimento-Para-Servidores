package packageExercicio;

public class TestaFuncionario {

    public static void main(String[] args) {
        
        
        Funcionario f1 = new Funcionario();
        f1.nome = "Deivide Oliveira";
        f1.salario = 3900;

       
        Funcionario f2 = new Funcionario();
        f2.nome = "Helena Oliveira";
        f2.salario = 4500;

        
        System.out.println("Antes do aumento de salario recebiam: ");
        f1.exibirDados();
        f2.exibirDados();

        
        f1.aumentaSalario(500);
        f2.aumentaSalario(700);

        
        System.out.println("Apos aumento de salario recebem:");
        
        f1.exibirDados();
        f2.exibirDados();
    }
}

