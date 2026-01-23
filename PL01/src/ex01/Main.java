package ex01;

public class Main {
    public static void main(String[] args) {
        // 1. Criar 3 instanias da classe Aluno (Objectos)
        Aluno aluno1 = new Aluno("João Silva", 1100, 15.5);
        Aluno aluno2 = new Aluno("Maria Santos", 1101, 18.0);
        Aluno aluno3 = new Aluno("Pedro Costa", 1102, 12.5);

        // 2. Mostrar os dados dos 3 alunos
        System.out.println("Dados dos alunos:");
        System.out.println("Aluno 1: " + aluno1.getNome() + " - " + aluno1.getNumero() + " - " + aluno1.getMedia());
        System.out.println("Aluno 2: " + aluno2.getNome() + " - " + aluno2.getNumero() + " - " + aluno2.getMedia());
        System.out.println("Aluno 3: " + aluno3.getNome() + " - " + aluno3.getNumero() + " - " + aluno3.getMedia());
    }

}
