package ex02;

public class Main {
    public static void main(String[] args) {
        // Criar duas instâncias da classe Gato
        Gato gato1 = new Gato("Farrusco","Preto");
        Gato gato2 = new Gato("Mingau","Branco");

        // Fazer o gato 1 miar e brincar
        gato1.miar();
        gato1.brincar();

        System.out.println("----------------");

        //Modificar o som do gato 2
        gato2.setSom("Mrrrr");

        gato2.miar();
        gato2.brincar();
        gato1.miar();
    }
}
