package ex10;

public class Main {
    public static void main(String[] args) {
        // 1. Criar um objecto Jogador
        Jogador jogador1 = new Jogador("GamerPro", "EquipaX");

        // 2. O jogador ganha pontos em várias ocasiões
        jogador1.ganharPontos(50);
        jogador1.ganharPontos(80);
        jogador1.ganharPontos(70);


        // 3. Mostrar estatísticas do jogador
        jogador1.mostrarEstatisticas();
    }

}
