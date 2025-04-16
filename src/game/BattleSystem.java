package game;

import game.character.Player;
import java.util.Scanner;
import java.util.Random;

public class BattleSystem {
    public static boolean startBattle(Player player, String enemyName, int enemyAttack, int enemyDefense, int enemyHealth, Scanner scanner, Random random) {
        int maxEnemyHealth = enemyHealth;

        while (enemyHealth > 0 && player.getHealth() > 0) {
            // Display status
            System.out.println("\n" + player.getName() + ": HP " + player.getHealth() + "/" + player.getMaxHealth());
            if (player.hasArmor()) {
                System.out.println("reyy: ARMOR Bintang (damage reduction)");
            }
            System.out.println(enemyName + ": HP " + enemyHealth + "/" + maxEnemyHealth);
            System.out.println("Healing Potion: " + player.getHealingPotions() + "/2");
            System.out.println("Skill Khusus:");
            System.out.println("  4. Supernova (" + player.getSupernovaUses() + "/2)");
            System.out.println("  5. Black Hole (" + player.getBlackHoleUses() + "/2)");
            System.out.println("  6. Cosmic Strike (" + player.getCosmicStrikeUses() + "/2)");

            // Action menu
            System.out.println("\nApa yang ingin Anda lakukan?");
            System.out.println("1. Serang");
            System.out.println("2. Bertahan");
            System.out.println("3. Gunakan Healing Potion (+100 HP)");
            System.out.println("4. Gunakan Supernova ");
            System.out.println("5. Gunakan Black Hole )");
            System.out.println("6. Gunakan Cosmic Strike ");
            System.out.print("Pilihan: ");
            String choice = scanner.nextLine();

            int playerDamage = 0;
            int enemyDamage = 0;

            // Player's turn
            switch (choice) {
                case "1":
                    playerDamage = Math.max(0, player.getAttack() - enemyDefense / 2);
                    enemyHealth -= playerDamage;
                    System.out.println("\nAnda menyerang " + enemyName + " dan memberikan " + playerDamage + " damage!");
                    break;
                case "2":
                    System.out.println("\nAnda bertahan dari serangan musuh");
                    break;
                case "3":
                    if (player.getHealingPotions() > 0) {
                        player.useHealingPotion();
                        System.out.println("\nAnda menggunakan Healing Potion! HP pulih ke " + player.getMaxHealth() + "!");
                        System.out.println("Healing Potion tersisa: " + player.getHealingPotions() + "/2");
                    } else {
                        System.out.println("\nAnda tidak memiliki Healing Potion!");
                    }
                    break; // Ganti continue dengan break
                case "4":
                    if (player.getSupernovaUses() > 0) {
                        playerDamage = (int) (enemyHealth * 0.8);
                        enemyHealth -= playerDamage;
                        player.useSupernova();
                        System.out.println("\nAnda menggunakan SUPEROVA! " + enemyName + " terkena SUPERNOVA!");
                        System.out.println(enemyName + " menerima " + playerDamage + " damage!");
                    } else {
                        System.out.println("\nAnda sudah menggunakan semua Supernova!");
                    }
                    break;
                case "5":
                    if (player.getBlackHoleUses() > 0) {
                        playerDamage = (int) (enemyHealth * 0.8);
                        enemyHealth -= playerDamage;
                        player.useBlackHole();
                        System.out.println("\nAnda menggunakan BLACK HOLE! " + enemyName + " terkena BLACK HOLE!");
                        System.out.println(enemyName + " menerima " + playerDamage + " damage!");
                    } else {
                        System.out.println("\nAnda sudah menggunakan semua Black Hole!");
                    }
                    break;
                case "6":
                    if (player.getCosmicStrikeUses() > 0) {
                        playerDamage = (int) (enemyHealth * 0.8);
                        enemyHealth -= playerDamage;
                        player.useCosmicStrike();
                        System.out.println("\nAnda menggunakan COSMIC STRIKE! " + enemyName + " terkena COSMIC STRIKE!");
                        System.out.println(enemyName + " menerima " + playerDamage + " damage!");
                    } else {
                        System.out.println("\nAnda sudah menggunakan semua Cosmic Strike!");
                    }
                    break;
                default:
                    System.out.println("\nPilihan tidak ada! Anda kehilangan kesempatan menyerang.");
            }

            // Enemy's turn if still alive
            if (enemyHealth > 0) {
                enemyDamage = Math.max(0, enemyAttack - player.getDefense());

                if (player.hasArmor()) {
                    enemyDamage = (int) (enemyDamage * 0.3);
                    System.out.println("Armor Anda berhasil mereduce serangan musuh!");
                }

                if (choice.equals("2")) {
                    enemyDamage = Math.max(0, enemyDamage / 2);
                    System.out.println("Pertahanan Anda mengurangi damage musuh!");
                }

                player.takeDamage(enemyDamage);
                System.out.println(enemyName + " menyerang Anda dan menyebabkan " + enemyDamage + " damage!");
            }
        }

        // Battle result
        if (player.getHealth() <= 0) {
            System.out.println("\nAnda kalah dalam pertarungan...");
            return false;
        } else {
            System.out.println("\nAnda mengalahkan " + enemyName + "!");
            player.resetSkillUses();

            // 30% chance to get healing potion if not full
            if (random.nextInt(100) > 70 && player.getHealingPotions() < 2) {
                player.addHealingPotion(1);
                System.out.println("Anda mendapatkan 1 Healing Potion!");
                System.out.println("Healing Potion sekarang: " + player.getHealingPotions() + "/2");
            }

            System.out.println("Semua skill khusus telah diisi kembali!");
            return true;
        }
    }
}