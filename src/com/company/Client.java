package com.company;

import static com.company.config.Config.carSegmentNames;

public class Client {
    public final String name;
    public final String surname;
    public double money;
    public final boolean interestedInCargo; // False - osobowy, True - dostawczy
    public final int cargoCapacity; // rozmiar przestrzeni ładunkowej, jaka interesuje klienta w przypadku zainteresowania pojazdami dostawczymi
    public final String[] favouriteBrands;
    public final int toleranceStrength; // 0 - tylko sprawne pojazdy, 1 - toleruje uszkodzone zawieszenie, 2 - toleruje uszkodzony pojazd

    public Client(String name, String surname, int money, boolean interestedInCargo, int cargoCapacity, String[] favouriteBrands, int toleranceStrength) {
        this.name = name;
        this.surname = surname;
        this.money = money;
        this.interestedInCargo = interestedInCargo;
        this.cargoCapacity = cargoCapacity;
        this.favouriteBrands = favouriteBrands;
        this.toleranceStrength = toleranceStrength;
    }

    public boolean isInterestedIn(Vehicle car) {
        System.out.println("TODO isInterestedIn");
        return true;
    }

    @Override
    public String toString() {
        String carType = (interestedInCargo) ? "dostawczy" : "osobowy";
        String[] tolerance = {
                "pojazd musi być całowicie sprawy",
                "pojazd może mieć uszkodzone zawieszenie",
                "pojazd może być uszkodzony"
        };

        return name + " " + surname +
                "\n\ttyp pojazdu: " + carType +
                "\n\tmarki: " + favouriteBrands[0] + ", " + favouriteBrands[1] +
                "\n\tpieniądze: " + money +
                "\n\tprzestrzeń ładunkowa: " + (interestedInCargo ? "min. " + cargoCapacity : "nie dotyczy") +
                "\n\tdodatkowe uwagi: " + tolerance[toleranceStrength] + "\n";
    }
}
