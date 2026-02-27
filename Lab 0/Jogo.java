import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int quantidade = 0;
        while (quantidade < 2 || quantidade > 10) {
            System.out.print("Quantos tanques deseja criar? (minimo 2, maximo 10): ");
            quantidade = scanner.nextInt();
            scanner.nextLine();
            if (quantidade < 2 || quantidade > 10) {
                System.out.println("Valor invalido! Digite um numero entre 2 e 10.");
            }
        }

        ArrayList<Tank> tanques = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            System.out.print("Digite o nome do tanque " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            tanques.add(new Tank(nome));
        }

        int rodada = 1;

        while (tanques.size() > 1) {
            System.out.println("\n=== Rodada " + rodada + " ===");

            ArrayList<Integer> ordem = new ArrayList<>();
            for (int i = 0; i < tanques.size(); i++) {
                ordem.add(i);
            }
            Collections.shuffle(ordem, random);

            for (int idx : ordem) {
                if (idx >= tanques.size() || !tanques.get(idx).isAlive()) {
                    continue;
                }

                Tank jogador = tanques.get(idx);

                ArrayList<Tank> vivos = new ArrayList<>();
                for (Tank t : tanques) {
                    if (t.isAlive() && t != jogador) {
                        vivos.add(t);
                    }
                }
                if (vivos.isEmpty()) {
                    break;
                }

                System.out.println("\nVez de " + jogador.getName() + "!");
                System.out.println("Adversarios disponiveis:");
                for (int i = 0; i < vivos.size(); i++) {
                    System.out.println((i + 1) + " - " + vivos.get(i).getName()
                            + " (" + vivos.get(i).getArmor() + " armor)");
                }

                int escolha = 0;
                while (escolha < 1 || escolha > vivos.size()) {
                    System.out.print("Escolha o alvo (1-" + vivos.size() + "): ");
                    escolha = scanner.nextInt();
                    scanner.nextLine();
                    if (escolha < 1 || escolha > vivos.size()) {
                        System.out.println("Escolha invalida!");
                    }
                }

                Tank alvo = vivos.get(escolha - 1);
                jogador.fire_at(alvo);

                if (!alvo.isAlive()) {
                    tanques.remove(alvo);
                }
            }

            tanques.removeIf(t -> !t.isAlive());

            System.out.println("\n--- Status da Rodada " + rodada + " ---");
            for (Tank t : tanques) {
                System.out.println(t);
            }

            rodada++;
        }

        System.out.println("\n=== Fim do Jogo ===");
        if (!tanques.isEmpty()) {
            System.out.println("Vencedor: " + tanques.get(0).getName() + "!");
        }

        scanner.close();
    }
}
