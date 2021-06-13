package com.company;

import java.util.ArrayList;
import java.util.List;

import static com.company.Main.*;
import static com.company.config.Config.*;

public class Vehicle {
    public int value;
    public String brand;
    public int mileage;
    public String color;
    public int segment; // 0 - budget, 1 - standard, 2 - premium
    public boolean isCargo; // False - osobowy, True - dostawczy
    public int cargoSpace;
    public double partsPriceMultiplier;

    // True - sprawne, False - uszkodzone
    public boolean[] partsState;

    public List<String> fixHistory = new ArrayList<>(); // historia napraw
    public double maintenanceMoney = 0; // kwota wydana na naprawy i mycie pojazdu

    public Vehicle(int value, String brand, int mileage, String color, int segment, boolean isCargo, int cargoSpace,
                   double partsPriceMultiplier, boolean[] partsState) {
        this.value = value;
        this.brand = brand;
        this.mileage = mileage;
        this.color = color;
        this.segment = segment;
        this.isCargo = isCargo;
        this.cargoSpace = cargoSpace;
        this.partsPriceMultiplier = partsPriceMultiplier;

        this.partsState = partsState;
    }

    public boolean doesNeedFix() {
        return !(partsState[0] &&
                partsState[1] &&
                partsState[2] &&
                partsState[3] &&
                partsState[4]);
    }

    public boolean isOnlySuspensionBroken() {
        return (partsState[0] &&
                !partsState[1] &&
                partsState[2] &&
                partsState[3] &&
                partsState[4]);
    }

    public double getPartFixPrice(int partID) {
        return CAR_PARTS_PRICE[partID] + (CAR_PARTS_PRICE[partID] * (this.partsPriceMultiplier/100));
    }

    public void fix(Player player, int partID, int mechanicID) {
        double fixCost = this.getPartFixPrice(partID) * MECHANICS_PRICE_MULTIPLIER[mechanicID];

        if (player.actualMoneyValue < fixCost) {
            System.out.println("Nie stać Cię na naprawę!");
            return;
        }

        int fixChance = randomize(1,10);
        player.nextMove();

        switch (mechanicID) {
            case 1: // Marian 90% + interwencja Janusza jesli nie pyknie
                if (fixChance == 1) {
                    player.takeMoney(fixCost);
                    System.out.println("Zapłaciłeś mechanikowi " + MECHANICS_NAMES[1] + "owi " + fixCost);
                    System.out.println("jednak " + MECHANICS_NAMES[1] + " niestety nie poradził sobie z problemem.");
                    System.out.println("Trzeba będzie oddać auto do " + MECHANICS_NAMES[0] + "a...");

                    player.transactionHistory.add("[-] Nieudana naprawa pojazdu: " + this.brand +
                            " " + (this.isCargo ? "dostawczy" : "osobowy") +
                            ", część: " + CAR_PARTS_NAMES[partID] +
                            ", mechanik: " + MECHANICS_NAMES[mechanicID] +
                            ", kwota: " + fixCost);

                    fixCost = this.getPartFixPrice(partID) * MECHANICS_PRICE_MULTIPLIER[0];

                    if (player.actualMoneyValue < fixCost) {
                        System.out.println("Niestety nie stać Cię na naprawę u " + MECHANICS_NAMES[0] + "a.");
                        return;
                    }
                }

                break;

            case 2: // Adrian 80% + 2% szans na zniszczenie innej rzeczy
                if (fixChance == 1 || fixChance == 2) {
                    int destroyOtherPartChance = randomize(1,50); // 2%

                    player.takeMoney(fixCost);
                    System.out.println("Zapłaciłeś mechanikowi " + MECHANICS_NAMES[2] + "owi " + fixCost);
                    System.out.println("jednak " + MECHANICS_NAMES[2] + " niestety nie poradził sobie z problemem.");

                    player.transactionHistory.add("[-] Nieudana naprawa pojazdu: " + this.brand +
                            " " + (this.isCargo ? "dostawczy" : "osobowy") +
                            ", część: " + CAR_PARTS_NAMES[partID] +
                            ", mechanik: " + MECHANICS_NAMES[mechanicID] +
                            ", kwota: " + fixCost);

                    if (destroyOtherPartChance == 1) {
                        int otherPartID = randomize(0,4);

                        while ((otherPartID == partID) && this.partsState[otherPartID]) {
                            otherPartID = randomize(0,4);
                        }

                        this.partsState[otherPartID] = false;
                        System.out.println("Dodatkowo przez przypadek zniszczył Ci inną część w samochodzie.");
                        System.out.println("Zniszczona część: " + CAR_PARTS_NAMES[otherPartID]);
                    }

                    return;
                }

                break;
        }

        player.takeMoney(fixCost);
        this.partsState[partID] = true;

        showOptionsList();
        this.fixHistory.add("Część: " + CAR_PARTS_NAMES[partID] +
                ", mechanik: " + MECHANICS_NAMES[mechanicID] +
                ", kwota: " + fixCost);
        this.value = this.value + (this.value * CAR_PARTS_PERCENT[partID]/100);
        this.maintenanceMoney = this.maintenanceMoney + fixCost;

        player.transactionHistory.add("[-] Udana naprawa pojazdu: " + this.brand +
                " " + (this.isCargo ? "dostawczy" : "osobowy") +
                ", część: " + CAR_PARTS_NAMES[partID] +
                ", mechanik: " + MECHANICS_NAMES[mechanicID] +
                ", kwota: " + fixCost);

        System.out.println("Dokonano udanej naprawy za " + fixCost);
        System.out.println("Nowa wartość samochodu: " + this.value);
    }

    public void buy(Player player) {
        double carValue = this.value + this.value*TAX_PERCENT + CAR_WASH_COST;

        if (player.actualMoneyValue >= carValue) {
            player.takeMoney(carValue);

            carToBuyList.remove(this);
            player.ownedCarsList.add(this);
            System.out.println("Zakup przebiegł pomyślnie!");
            System.out.println("Koszt pojazdu: " + this.value);
            System.out.println("Koszt mycia: " + CAR_WASH_COST);
            System.out.println("2% podatku: " + this.value*TAX_PERCENT);
            System.out.println("Razem wydano: " + carValue);
            this.maintenanceMoney = this.maintenanceMoney + CAR_WASH_COST;

            for (int i = 0; i < NEW_VEHICLES_AMOUNT_AFTER_TRANSACTION; i++) {
                generateCar();
            }

            for (int i = 0; i < NEW_CLIENTS_AMOUNT_AFTER_TRANSACTION; i++) {
                generateClient();
            }

            player.transactionHistory.add("[-] Zakup pojazdu + mycie: " + this.brand +
                    " " + ((this.isCargo) ? "dostawczy" : "osobowy") +
                    ", kwota: " + carValue);

            player.nextMove();
        } else {
            System.out.println("Nie stać Cię na zakup tego samochodu!");
        }
    }

    public void sell(Player player, Client client) {
        if (client.isInterestedIn(this)) {
            double tax = this.value * TAX_PERCENT;
            double profit = this.value - tax - CAR_WASH_COST;

            player.giveMoney(profit);
            player.ownedCarsList.remove(this);
            System.out.println("Sprzedano!");
            clientsList.remove(client);
            player.nextMove();
            showOptionsList();

            for (int i = 0; i < NEW_VEHICLES_AMOUNT_AFTER_TRANSACTION; i++) {
                generateCar();
            }

            for (int i = 0; i < NEW_CLIENTS_AMOUNT_AFTER_TRANSACTION; i++) {
                generateClient();
            }

            player.transactionHistory.add("[+] Sprzedaż pojazdu + mycie: " + this.brand +
                    " " + (this.isCargo ? "dostawczy" : "osobowy") +
                    ", kwota: " + profit);
        }
    }


    @Override
    public String toString() {
        String carType = (isCargo) ? "dostawczy" : "osobowy";
        String brokenParts;

        if (doesNeedFix()) {
            brokenParts = "Uszkodzone części:";

            if (!partsState[0]) {
                brokenParts = brokenParts + "\n\t- "+ CAR_PARTS_NAMES[0];
            }

            if (!partsState[1]) {
                brokenParts = brokenParts + "\n\t- "+ CAR_PARTS_NAMES[1];
            }

            if (!partsState[2]) {
                brokenParts = brokenParts + "\n\t- "+ CAR_PARTS_NAMES[2];
            }

            if (!partsState[3]) {
                brokenParts = brokenParts + "\n\t- "+ CAR_PARTS_NAMES[3];
            }

            if (!partsState[4]) {
                brokenParts = brokenParts + "\n\t- "+ CAR_PARTS_NAMES[4];
            }
        } else {
            brokenParts = "Wszystkie części sprawne";
        }


        return brand + " " + color + " " + carType +
                "\n\tsegment: " + CAR_SEGMENT_NAMES[segment] +
                "\n\tprzebieg: " + mileage + "\n\tcena: " + value +
                "\n\tprzestrzeń ładunkowa: " + (isCargo ? cargoSpace : "nie dotyczy") +
                "\n\n\t" + brokenParts + "\n";
    }
}
