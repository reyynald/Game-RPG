package game;

import java.util.Scanner;

import game.character.Player;

import java.util.Random;

import static game.utils.Helper.*;

public class SeaOfStars {
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private Player player;

    public void start() {
        printIntro();
        createCharacter();
        chapter1();
    }

    private void printIntro() {
        System.out.println("==========================================");
        System.out.println("          SEA OF STARS RPG");
        System.out.println("==========================================");
        System.out.println("\nDi alam semesta yang dipenuhi bintang-bintang ajaib...");
        System.out.println("Sebuah legenda sedang menanti untuk ditulis...\n");
    }

    private void createCharacter() {
        System.out.println("\n=== Ciptakan Pahlawan Anda ===");
        System.out.print("Masukkan nama pahlawan Anda: ");
        String name = scanner.nextLine();
        player = new Player(name, 100, 20, 10, 2, false);
        System.out.println("\n==========================================");
        System.out.println("  Selamat datang, " + player.getName() + "!");
        System.out.println("  Kekuatan: " + player.getAttack() + ", Pertahanan: " + player.getDefense());
        System.out.println("  Healing Potion: " + player.getHealingPotions() + "/2");
        System.out.println("  Armor: " + (player.hasArmor() ? "70% damage reduction" : "Tidak ada"));
        System.out.println("  Skill Khusus:");
        System.out.println("  1. Supernova (2/2) ");
        System.out.println("  2. Black Hole (2/2) ");
        System.out.println("  3. Cosmic Strike (2/2) ");
        System.out.println("==========================================");
        pressToContinue(scanner);
    }

    private void chapter1() {
        clearScreen();
        System.out.println("\n=== Bangkitnya Ancaman ===");
        System.out.println("\nAnda terbangun di dek kapal angkasa 'Celestial Mariner'.");
        System.out.println("Kapten Orion mendekati Anda dengan wajah khawatir.");

        dialog("Kapten Orion", "Akhirnya kau bangun, " + player.getName() + "! Kita dalam masalah besar.");
        dialog("Kapten Orion", "Armada Shadow Nebula telah menyerang konstelasi kita.");
        dialog("Kapten Orion", "Mereka mencuri Crystal Bintang dari kuil kuno di Aetheria.");
        dialog(player.getName(), "Apa yang harus kita lakukan, Kapten?");
        dialog("Kapten Orion", "Kau harus pergi ke planet Aetheria dan mengambil kembali crystal itu.");
        dialog("Kapten Orion", "Aku akan mengantarmu ke permukaan, tapi kau harus melanjutkan sendiri dari sana.");

        pressToContinue(scanner);

        System.out.println("\nKapal mendarat di permukaan Aetheria. Udara berkilau dengan debu bintang.");
        System.out.println("Di kejauhan, Anda melihat kuil kuno yang dikelilingi oleh pasukan Shadow Nebula.");

        String choice;
        do {
            System.out.println("\nApa yang ingin Anda lakukan?");
            System.out.println("1. Menyusup ke kuil secara diam-diam");
            System.out.println("2. Menyerang langsung pasukan penjaga");
            System.out.println("3. Mencari jalan alternatif");
            System.out.print("Pilihan Anda: ");
            choice = scanner.nextLine();

            switch(choice) {
                case "1":
                    stealthApproach();
                    break;
                case "2":
                    directAttack();
                    break;
                case "3":
                    alternativePath();
                    break;
                default:
                    System.out.println("Pilihan tidak ada!");
            }
        } while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3"));
    }

    private void stealthApproach() {
        clearScreen();
        System.out.println("\nAnda memutuskan untuk menyusup secara diam-diam.");
        System.out.println("Dengan hati-hati, Anda menghindari patroli dan memasuki kuil.");

        int stealthCheck = random.nextInt(20) + 1;
        if (stealthCheck > 10) {
            System.out.println("\nAnda berhasil masuk tanpa terdeteksi!");
            System.out.println("Di dalam kuil, Anda menemukan Crystal Bintang di altar utama.");
            findCrystal();
        } else {
            System.out.println("\nSeorang penjaga melihat bayangan Anda!");
            System.out.println("Anda harus bertarung!");
            battle("Penjaga Shadow Nebula", 30, 15, 50);
            if (player.getHealth() > 0) findCrystal();
        }
    }

    private void directAttack() {
        clearScreen();
        System.out.println("\nAnda memutuskan untuk menyerang langsung!");
        System.out.println("Pasukan Shadow Nebula terkejut dengan serangan Anda.");

        battle("Penjaga Shadow Nebula", 30, 15, 50);
        if (player.getHealth() > 0) {
            battle("Elite Shadow Nebula", 40, 20, 70);
            if (player.getHealth() > 0) findCrystal();
        }
    }

    private void alternativePath() {
        clearScreen();
        System.out.println("\nAnda mencari jalan alternatif dan menemukan lorong bawah tanah.");
        System.out.println("Lorong ini gelap dan berbahaya.");

        int luckCheck = random.nextInt(20) + 1;
        if (luckCheck > 15) {
            System.out.println("\nAnda menemukan jalan langsung ke ruang altar!");
            findCrystal();
        } else if (luckCheck > 5) {
            System.out.println("\nAnda bertemu dengan makhluk bawah tanah!");
            battle("Makhluk Bawah Tanah", 35, 10, 60);
            if (player.getHealth() > 0) findCrystal();
        } else {
            System.out.println("\nAnda tersesat! Butuh waktu lama untuk menemukan jalan.");
            System.out.println("Pasukan Shadow Nebula telah bersiaga.");
            battle("Penjaga Shadow Nebula", 30, 15, 50);
            battle("Elite Shadow Nebula", 40, 20, 70);
            if (player.getHealth() > 0) findCrystal();
        }
    }

    private void findCrystal() {
        clearScreen();
    
        System.out.println("\nAnda berhasil mengalahkan penjaga dan mencapai altar utama.");
        System.out.println("Crystal Bintang bersinar dengan cahaya yang indah di atas altar.");

        System.out.println("\nDi samping altar, terdapat sebuah chest kuno yang berdebu.");
        System.out.println("Apakah Anda ingin membukanya? (ya/tidak)");
        String openChest = scanner.nextLine();

        if (openChest.equalsIgnoreCase("ya")) {
            System.out.println("\nAnda membuka chest tersebut...");
            System.out.println("Di dalamnya terdapat armor bintang yang bersinar!");
            System.out.println("Armor ini dapat mereduce damage musuh.");

            System.out.println("\nApakah Anda ingin mengambil armor ini? (ya/tidak)");
            String takeArmor = scanner.nextLine();

            if (takeArmor.equalsIgnoreCase("ya")) {
                player.setArmor(true);
                System.out.println("\nAnda mengenakan armor bintang!");
                System.out.println("Damage yang diterima direduce.");
            } else {
                System.out.println("\nAnda meninggalkan armor tersebut.");
            }
        } else {
            System.out.println("\nAnda memutuskan untuk tidak membuka chest.");
        }

        pressToContinue(scanner);

        System.out.println("\nAnda mengambil Crystal Bintang dari altar...");
        dialog(player.getName(), "Akhirnya! Crystal Bintang...");

        System.out.println("\nTiba-tiba, bayangan besar muncul di belakang Anda!");
        dialog("Lord Nihilus", "Berani-beraninya kau mencuri milikku!");

        battle("Lord Nihilus", 50, 25, 100);
        if (player.getHealth() > 0) ending();
    }

    private void battle(String enemyName, int enemyAttack, int enemyDefense, int enemyHealth) {
        BattleSystem.startBattle(player, enemyName, enemyAttack, enemyDefense, enemyHealth, scanner, random);
    }

    private void ending() {
        clearScreen();
        System.out.println("\n=== AKHIR YANG BAHAGIA ===");
        System.out.println("\nDengan Lord Nihilus terkalahkan, Crystal Bintang kembali ke tempatnya.");
        System.out.println("Konstelasi ini selamat dari ancaman Shadow Nebula.");

        dialog("Kapten Orion", "Kau telah melakukan yang luar biasa, " + player.getName() + "!");
        dialog("Kapten Orion", "Nama mu akan dikenang sebagai pahlawan Sea of Stars!");

        System.out.println("\nTekan Enter untuk keluar dari game...");
        scanner.nextLine();
    }
}
