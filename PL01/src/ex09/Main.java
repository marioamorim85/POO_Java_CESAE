package ex09;

public class Main {
    public static void main(String[] args) {
        //1. Criar objecto Restaurante com cácidade máxima de 50 lugares
        Restaurante restaurante1 = new Restaurante("Restaurante A", 50);

        //2. Reservar 20 lugares
        restaurante1.reservarMesa(20);
        System.out.println("Lugares ocupados atualmente: " + restaurante1.getLugaresOcupados());

        //3. Libertar 5 lugares
        restaurante1.libertarMesa(5);
        System.out.println("Lugares ocupados após saída: " + restaurante1.getLugaresOcupados());

        //4. Reseervar 40 lugares (deve falhar, pois 15 + 40 = 55 > 50 da capacidade máxima)
        restaurante1.reservarMesa(40);
        System.out.println("Lugares ocupados no final: " + restaurante1.getLugaresOcupados());
    }
}
