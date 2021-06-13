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
    public int partsPriceMultiplier;

    // True - sprawne, False - uszkodzone
    public boolean brakeState;          // 1
    public boolean suspensionState;     // 2
    public boolean engineState;         // 3
    public boolean bodyState;           // 4
    public boolean transmissionState;   // 5

    public List<String> fixHistory = new ArrayList<>(); // historia napraw
    public int maintenanceMoney = 0; // kwota wydana na naprawy i mycie pojazdu

    public Vehicle(int value, String brand, int mileage, String color, int segment, boolean isCargo, int cargoSpace,
                   int partsPriceMultiplier, boolean brakeState, boolean suspensionState, boolean engineState,
                   boolean bodyState, boolean transmissionState) {
        this.value = value;
        this.brand = brand;
        this.mileage = mileage;
        this.color = color;
        this.segment = segment;
        this.isCargo = isCargo;
        this.cargoSpace = cargoSpace;
        this.partsPriceMultiplier = partsPriceMultiplier;

        this.brakeState = brakeState;
        this.suspensionState = suspensionState;
        this.engineState = engineState;
        this.bodyState = bodyState;
        this.transmissionState = transmissionState;
    }

    public boolean doesNeedFix() {
        return !(brakeState && suspensionState && engineState && bodyState && transmissionState);
    }

    public void fix(Player player, int partID) {
        System.out.println("TODO Fix");
    }

    public void buy(Player player) {
        double carValue = this.value + this.value*TAX_AMOUNT + CAR_WASH_COST;

        if (player.actualMoneyValue >= carValue) {
            player.takeMoney(carValue);

            carToBuyList.remove(this);
            player.ownedCarsList.add(this);
            System.out.println("Zakup przebiegł pomyślnie!");
            System.out.println("Koszt pojazdu: " + this.value);
            System.out.println("Koszt mycia: " + CAR_WASH_COST);
            System.out.println("2% podatku: " + this.value*TAX_AMOUNT);
            System.out.println("Razem wydano: " + carValue);

            for (int i = 0; i < NEW_VEHICLES_AMOUNT_AFTER_TRANSACTION; i++) {
                generateCar();
            }

            for (int i = 0; i < NEW_CLIENTS_AMOUNT_AFTER_TRANSACTION; i++) {
                generateClient();
            }

            player.nextMove();
        } else {
            System.out.println("Nie stać Cię na zakup tego samochodu!");
        }
    }

    public void sell(Player player, Client client) {
        System.out.println("TODO Sell");
    }


    @Override
    public String toString() {
        String carType = (isCargo) ? "dostawczy" : "osobowy";
        String brokenParts;

        if (doesNeedFix()) {
            brokenParts = "Uszkodzone części:";

            if (!brakeState) {
                brokenParts = brokenParts + "\n\t- hamulce";
            }

            if (!suspensionState) {
                brokenParts = brokenParts + "\n\t- zawieszenie";
            }

            if (!engineState) {
                brokenParts = brokenParts + "\n\t- silnik";
            }

            if (!bodyState) {
                brokenParts = brokenParts + "\n\t- karoseria";
            }

            if (!transmissionState) {
                brokenParts = brokenParts + "\n\t- skrzynia biegów";
            }
        } else {
            brokenParts = "Wszystkie części sprawne";
        }


        return brand + " " + color + " " + carType +
                "\n\tsegment: " + carSegmentNames[segment] +
                "\n\tprzebieg: " + mileage + "\n\tcena: " + value +
                "\n\tprzestrzeń ładunkowa: " + (isCargo ? cargoSpace : "nie dotyczy") +
                "\n\n\t" + brokenParts + "\n";
    }
}
