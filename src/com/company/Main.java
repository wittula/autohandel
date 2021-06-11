package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static com.company.config.Config.*;

public class Main {
    List<Vehicle> carToBuyList = new ArrayList<>();
    List<Client> clientsList = new ArrayList<>();

    static Player player = new Player();

    public static void showOptionsList() {
        System.out.println("\n\nAUTOHANDEL - MENU GŁÓWNE");
        System.out.println("Liczba ruchów: " + player.movesCounter + "\n--------------------------");
        System.out.println("\t1) Przeglądaj bazę samochodów do kupienia");
        System.out.println("\t2) Kup samochód");
        System.out.println("\t3) Przeglądaj bazę posiadanych samochodów");
        System.out.println("\t4) Napraw samochód");
        System.out.println("\t5) Przejrzyj potencjalnych klientów");
        System.out.println("\t6) Sprzedaj samochód");
        System.out.println("\t7) Kup reklamę");
        System.out.println("\t8) Sprawdź stan konta");
        System.out.println("\t9) Sprawdź historię transakcji");
        System.out.println("\t10) Sprawdź historię napraw każdego samochodu");
        System.out.println("\t11) Sprawdź sumę kosztów napraw i mycia dla każdego z posiadanych samochodów");
        System.out.println("\n\t12) Wyjdź\n\n");
    }

    public static void generateCars() {
        System.out.println("TODO generateCars");
    }

    public static void generateClients() {
        System.out.println("TODO generateClients");
    }

    public static void main(String[] args) {
        generateCars();
        generateClients();

        final Scanner scanner = new Scanner(System.in);
        int option = 0;

        showOptionsList();

        while (true) {
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("TODO 1");
                    break;
                case 2:
                    player.movesCounter = player.movesCounter + 1;
                    showOptionsList();
                    System.out.println("TODO 2");

                    break;
                case 3:
                    System.out.println("TODO 3");
                    break;
                case 4:
                    System.out.println("TODO 4");
                    break;
                case 5:
                    System.out.println("TODO 5");
                    break;
                case 6:
                    System.out.println("TODO 6");
                    break;
                case 7:
                    System.out.println("TODO 7");
                    break;
                case 8:
                    showOptionsList();
                    System.out.println("Aktualny stan konta: " + player.actualMoneyValue);
                    break;
                case 9:
                    System.out.println("TODO 9");
                    break;
                case 10:
                    System.out.println("TODO 10");
                    break;
                case 11:
                    System.out.println("TODO 11");
                    break;
                case 12:
                    System.out.println("Dziękuję za grę.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wprowadzona opcja nie istnieje");
                    break;
            }
        }
    }
}
