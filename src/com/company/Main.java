package com.company;

import java.util.*;

import static com.company.config.Config.*;

public class Main {
    static List<Vehicle> carToBuyList = new ArrayList<>();
    static List<Client> clientsList = new ArrayList<>();
    static Random r = new Random();

    static Player player = new Player();

    public static void showOptionsList() {
        System.out.println("\n\nAUTOHANDEL - MENU GŁÓWNE");
        System.out.println("Liczba ruchów: " + player.movesCounter);
        System.out.println("Stan konta: " + player.actualMoneyValue + "\n--------------------------");
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
        System.out.println("\n\t0) Wyjdź\n\n");
    }

    public static void showCarsToBuyList() {
        int counter = 1;

        System.out.println("Samochody możliwe do kupienia:");

        for (Vehicle car: carToBuyList) {
            System.out.println("[" + counter + "]: " + car);
            counter++;
        }
    }

    public static void showOwnedCarsList() {
        int counter = 1;

        System.out.println("Aktualnie posiadane samochody:");

        for (Vehicle car: player.ownedCarsList) {
            System.out.println("[" + counter + "]: " + car);
            counter++;
        }
    }

    public static void showClientsList() {
        int counter = 1;

        System.out.println("Aktualnie potencjalni klienci:");

        for (Client client: clientsList) {
            System.out.println("[" + counter + "]: " + client);
            counter++;
        }
    }

    public static int randomize(int startValue, int endValue) {
        return startValue + r.nextInt((endValue - startValue) + 1);
    }

    public static void generateCar() {
        int segment = randomize(0, 2);
        int value = randomize(carPriceRange[segment][0], carPriceRange[segment][1]) * 100;
        int mileage = randomize(carMileageRange[segment][0], carMileageRange[segment][1]) * 1000;
        String color = carColors[randomize(0, carColors.length - 1)];
        boolean isCargo = r.nextBoolean();

        String brand;
        int cargoSpace;
        int partsPriceMultiplier;

        boolean brakeState = r.nextBoolean();
        boolean suspensionState = r.nextBoolean();
        boolean engineState = r.nextBoolean();
        boolean bodyState = r.nextBoolean();
        boolean transmissionState = r.nextBoolean();

        if (segment == 2) {
            brand = carBrands[1][randomize(0, carBrands[1].length - 1)][0];
            partsPriceMultiplier = Integer.parseInt(carBrands[1][randomize(0, carBrands.length - 1)][1]);
        } else {
            brand = carBrands[0][randomize(0, carBrands[0].length - 1)][0];
            partsPriceMultiplier = Integer.parseInt(carBrands[0][randomize(0, carBrands.length - 1)][1]);
        }

        if (isCargo) {
            cargoSpace = randomize(carCargoSpace[0], carCargoSpace[1]);
        } else {
            cargoSpace = 0;
        }

        Vehicle car = new Vehicle(value, brand, mileage, color, segment, isCargo,
                cargoSpace, partsPriceMultiplier, brakeState, suspensionState,
                engineState, bodyState, transmissionState);

        carToBuyList.add(car);
    }

    public static void generateClient() {
        String name = clientNames[randomize(0, clientNames.length - 1)];
        String surname = clientSurnames[randomize(0, clientSurnames.length - 1)];
        int money = randomize(clientMoneyRange[0], clientMoneyRange[1]);
        boolean interestedInCargo = r.nextBoolean();

        int tolerance = randomize(0,10);
        int toleranceStrength = (tolerance < 8) ? 0 : randomize(1,2);

        int categoryID = (money > 80000) ? 1 : 0;

        int brand1 = randomize(0, carBrands[categoryID].length - 1);
        int brand2 = randomize(0, carBrands[categoryID].length - 1);

        while (brand1 == brand2) {
            brand2 = randomize(0, carBrands[categoryID].length - 1);
        }

        String[] favouriteBrands = {
                carBrands[categoryID][brand1][0],
                carBrands[categoryID][brand2][0]
        };

        int cargoCapacity = (interestedInCargo) ? randomize(clientCargoRange[0], clientCargoRange[1])*100 : 0;

        Client client = new Client(name, surname, money, interestedInCargo, cargoCapacity,
                favouriteBrands, toleranceStrength);

        clientsList.add(client);
    }

    public static void main(String[] args) {
        for (int i = 0; i < VEHICLES_AMOUNT_ON_START; i++) {
            generateCar();
        }

        for (int i = 0; i < CLIENTS_AMOUNT_ON_START; i++) {
            generateClient();
        }

        final Scanner scanner = new Scanner(System.in);
        int option;

        showOptionsList();

        while (true) {
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    showCarsToBuyList();
                    showOptionsList();
                    break;
                case 2:
                    showCarsToBuyList();
                    System.out.println("[0]: Powrót");
                    System.out.println("Wpisz numer pojazdu, który chcesz kupić:");

                    option = scanner.nextInt();

                    if (option == 0) {
                        showOptionsList();
                        break;
                    }

                    if (option <= carToBuyList.size()) {
                        carToBuyList.get(option-1).buy(player);
                        showOptionsList();
                    } else {
                        System.out.println("Podany numer jest nieprawidłowy!");
                    }

                    break;
                case 3:
                    showOwnedCarsList();
                    showOptionsList();
                    break;
                case 4:
                    System.out.println("TODO 4");
                    break;
                case 5:
                    showClientsList();
                    showOptionsList();
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
                case 0:
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
