import java.util.ArrayList;
import java.util.Random;

public class Simulacao {
    public static void main(String[] args) {
        Random random = new Random();

        ArrayList<Tank> tanques = new ArrayList<>();
        tanques.add(new Tank("Bruno"));
        tanques.add(new Tank("Jorge"));
        tanques.add(new Tank("Cleiton"));
        tanques.add(new Tank("Washington"));
        tanques.add(new Tank("Adilson"));

        int rodada = 1;

        while (tanques.size() > 1) {
            System.out.println("=== Rodada " + rodada + " ===");

            int indiceAtacante = random.nextInt(tanques.size());
            Tank atacante = tanques.get(indiceAtacante);

            int indiceAlvo;
            do {
                indiceAlvo = random.nextInt(tanques.size());
            } while (indiceAlvo == indiceAtacante);
            Tank alvo = tanques.get(indiceAlvo);

            atacante.fire_at(alvo);

            if (!alvo.isAlive()) {
                tanques.remove(alvo);
            }

            System.out.println("--- Status ---");
            for (Tank t : tanques) {
                System.out.println(t);
            }
            System.out.println();

            rodada++;
        }

        System.out.println("=== Fim da Simulacao ===");
        System.out.println("Vencedor: " + tanques.get(0).getName());
    }
}
