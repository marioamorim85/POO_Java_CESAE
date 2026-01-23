package ex05;

public class Main {
    public static void main(String[] args) {
        // 1. Criar dois objevtyos telemóvel com diferentes marcas, modelos e níveis de bateria.
        Telemovel t1 = new Telemovel("Apple", "Iphone 16", 100);
        Telemovel t2 = new Telemovel("Samsung", "Galaxy S25", 5);

        // Cenário 1: Ligar e usar o prieiro telemóvel
        System.out.println("--- Cenário 1 ---");
        t1.ligar();
        t1.usarTelemovel(50); // deve gastar 5% da bateria
        t1.desligar();
        t1.usarTelemovel(10); // não deve funcionar porque o telemóvel está desligado
        System.out.println("O nível de bateria do " + t1.getMarca() + " " + t1.getModelo() + " é: " + t1.getBateria() + "% e encontra-se " + (t1.isLigado() ? "ligado" : "desligado") );

        // Cenário 2: Ligar e usar o segundo telemóvel
        System.out.println("\n--- Cenário 2 ---");
        t2.ligar();
        t2.usarTelemovel(60); // deve gastar 6% da bateria
        t2.ligar();
        System.out.println("O nível de bateria do " + t2.getMarca() + " " + t2.getModelo() + " é: " + t2.getBateria() + "% e encontra-se " + (t2.isLigado() ? "ligado" : "desligado") );
    }
}
