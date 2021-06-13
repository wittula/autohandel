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

    public static void showMechanicsList(Vehicle car, int partID) {
        
        System.out.println("[1] Janusz (" + car.getPartFixPrice(partID) * MECHANICS_PRICE_MULTIPLIER[0] + ")"); // 100%
        System.out.println("[2] Marian (" + car.getPartFixPrice(partID) * MECHANICS_PRICE_MULTIPLIER[1] + ")"); // 90% + interwencja Janusza jeśli nie pyknie
        System.out.println("[3] Adrian (" + car.getPartFixPrice(partID) * MECHANICS_PRICE_MULTIPLIER[2] + ")"); // 80% + 2% na zniszczenie innej rzeczy
        System.out.println("[0]: Anulowanie naprawy");
        System.out.println("Wpisz numer mechanika, którego chcesz wybrać:");
    }

    public static int randomize(int startValue, int endValue) {
        return startValue + r.nextInt((endValue - startValue) + 1);
    }

    public static void generateCar() {
        int segment = randomize(0, 2);
        int value = randomize(CAR_PRICE_RANGE[segment][0], CAR_PRICE_RANGE[segment][1]) * 100;
        int mileage = randomize(CAR_MILEAGE_RANGE[segment][0], CAR_MILEAGE_RANGE[segment][1]) * 1000;
        String color = CAR_COLORS[randomize(0, CAR_COLORS.length - 1)];
        boolean isCargo = r.nextBoolean();

        String brand;
        int cargoSpace;
        double partsPriceMultiplier;
        
        boolean[] partsState = {r.nextBoolean(), r.nextBoolean(), 
                r.nextBoolean(), r.nextBoolean(), r.nextBoolean()};

        if (segment == 2) {
            brand = CAR_BRANDS[1][randomize(0, CAR_BRANDS[1].length - 1)][0];
            partsPriceMultiplier = Double.parseDouble(CAR_BRANDS[1][randomize(0, CAR_BRANDS.length - 1)][1]);
        } else {
            brand = CAR_BRANDS[0][randomize(0, CAR_BRANDS[0].length - 1)][0];
            partsPriceMultiplier = Double.parseDouble(CAR_BRANDS[0][randomize(0, CAR_BRANDS.length - 1)][1]);
        }

        cargoSpace = (isCargo) ? randomize(CAR_CARGO_SPACE[0], CAR_CARGO_SPACE[1]) : 0;

        Vehicle car = new Vehicle(value, brand, mileage, color, segment, isCargo,
                cargoSpace, partsPriceMultiplier, partsState);

        carToBuyList.add(car);
    }

    public static void generateClient() {
        String name = CLIENT_NAMES[randomize(0, CLIENT_NAMES.length - 1)];
        String surname = CLIENT_SURNAMES[randomize(0, CLIENT_SURNAMES.length - 1)];
        int money = randomize(CLIENT_MONEY_RANGE[0], CLIENT_MONEY_RANGE[1]);
        boolean interestedInCargo = r.nextBoolean();

        int tolerance = randomize(0,10);
        int toleranceStrength = (tolerance < 8) ? 0 : randomize(1,2);

        int categoryID = (money > 80000) ? 1 : 0;

        int brand1 = randomize(0, CAR_BRANDS[categoryID].length - 1);
        int brand2 = randomize(0, CAR_BRANDS[categoryID].length - 1);

        while (brand1 == brand2) {
            brand2 = randomize(0, CAR_BRANDS[categoryID].length - 1);
        }

        String[] favouriteBrands = {
                CAR_BRANDS[categoryID][brand1][0],
                CAR_BRANDS[categoryID][brand2][0]
        };

        int cargoCapacity = (interestedInCargo) ? randomize(CLIENT_CARGO_RANGE[0], CLIENT_CARGO_RANGE[1])*100 : 0;

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
                    if (player.ownedCarsList.isEmpty()) {
                        showOptionsList();
                        System.out.println("Nie posiadasz żadnego pojazdu!");
                        break;
                    }

                    showOwnedCarsList();
                    System.out.println("[0]: Anulowanie naprawy");
                    System.out.println("Wpisz numer samochodu, który chcesz naprawić:");

                    option = scanner.nextInt();

                    if (option == 0) {
                        showOptionsList();
                        break;
                    }

                    if (option <= player.ownedCarsList.size()) {
                        Vehicle selectedCar = player.ownedCarsList.get(option - 1);

                        if (selectedCar.doesNeedFix()) {

                            System.out.println("Wybrany pojazd:");
                            System.out.println(selectedCar);

                            System.out.println("\t[1]: " + CAR_PARTS_NAMES[0] + " (" + ((selectedCar.partsState[0]) ? "sprawne)" : "uszkodzone)"));
                            System.out.println("\t[2]: " + CAR_PARTS_NAMES[1] + " (" + ((selectedCar.partsState[1]) ? "sprawne)" : "uszkodzone)"));
                            System.out.println("\t[3]: " + CAR_PARTS_NAMES[2] + " (" + ((selectedCar.partsState[2]) ? "sprawny)" : "uszkodzony)"));
                            System.out.println("\t[4]: " + CAR_PARTS_NAMES[3] + " (" + ((selectedCar.partsState[3]) ? "nie wymaga naprawy)" : "uszkodzona)"));
                            System.out.println("\t[5]: " + CAR_PARTS_NAMES[4] + " (" + ((selectedCar.partsState[4]) ? "sprawne)" : "uszkodzone)"));
                            
                            System.out.println("\t[0]: Anulowanie naprawy");
                            System.out.println("Wpisz numer części, którą chcesz naprawić:");

                            option = scanner.nextInt();

                            if (option == 0) {
                                showOptionsList();
                                break;
                            } else if (option < 0 || option > 5) {
                                showOptionsList();
                                System.out.println("Wprowadzono zły numer części!");
                                break;
                            }

                            if (selectedCar.partsState[option - 1]) {
                                showOptionsList();
                                System.out.println("Ta część nie wymaga naprawy!");
                                break;
                            }
                            
                            int partID = option - 1;
                            showMechanicsList(selectedCar, partID);
                            option = scanner.nextInt();

                            selectedCar.fix(player, partID, option - 1);

                            break;
                        }
                        
                    } else {
                        showOptionsList();
                        System.out.println("Podany numer jest nieprawidłowy!");
                    }

                    break;
                case 5:
                    showClientsList();
                    showOptionsList();
                    break;
                case 6:
                    if (player.ownedCarsList.isEmpty()) {
                        showOptionsList();
                        System.out.println("Nie posiadasz żadnego pojazdu!");
                        break;
                    }

                    showOwnedCarsList();
                    System.out.println("[0]: Anulowanie sprzedaży");
                    System.out.println("Wpisz numer pojazdu, który chcesz sprzedać:");

                    option = scanner.nextInt();

                    if (option == 0) {
                        showOptionsList();
                        break;
                    }

                    if (option <= player.ownedCarsList.size()) {
                        Vehicle selectedVehicle = player.ownedCarsList.get(option - 1);

                        showClientsList();
                        System.out.println("[0]: Anulowanie sprzedaży");
                        System.out.println("Wpisz numer klienta, któremu chcesz sprzedać wcześniej wybrany pojazd:");

                        option = scanner.nextInt();

                        if (option == 0) {
                            showOptionsList();
                            break;
                        }

                        if (option <= clientsList.size()) {
                            Client selectedClient = clientsList.get(option - 1);

                            selectedVehicle.sell(player, selectedClient);
                        } else {
                            showOptionsList();
                            System.out.println("Wprowadzony klient nie istnieje!");
                        }
                    } else {
                        showOptionsList();
                        System.out.println("Wprowadzony pojazd nie istnieje!");
                    }

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
