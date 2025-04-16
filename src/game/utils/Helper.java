package game.utils;

import java.util.Scanner;

public class Helper {
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
        }
    }

    public static void pressToContinue(Scanner scanner) {
        System.out.println("\nTekan Enter untuk melanjutkan...");
        scanner.nextLine();
    }

    public static void dialog(String speaker, String text) {
        System.out.println("\n[" + speaker + "]");
        System.out.println(text);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}