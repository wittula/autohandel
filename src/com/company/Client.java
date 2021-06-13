package com.company;

import static com.company.config.Config.CAR_SEGMENT_NAMES;

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
        if (car.brand.equals(this.favouriteBrands[0]) || car.brand.equals(this.favouriteBrands[1])) {
            if (this.interestedInCargo == car.isCargo) {
                boolean isStateOK = false;

                switch(this.toleranceStrength) {
                    case 0: // tylko sprawne
                        if (!car.doesNeedFix()) {
                            isStateOK = true;
                            break;
                        }
                    case 1: // zawieszenie
                        if (car.isOnlySuspensionBroken()) {
                            isStateOK = true;
                            break;
                        }
                    case 2: // moze byc uszkodzony
                        isStateOK = true;
                        break;
                }

                if (isStateOK) {
                    if (car.cargoSpace >= this.cargoCapacity) {
                        return this.money >= car.value;
                    }
                }
            }
        }

        return false;
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
                " | typ pojazdu: " + carType +
                " | marki: " + favouriteBrands[0] + ", " + favouriteBrands[1] +
                " | pieniądze: " + money +
                "\n\tprzestrzeń ładunkowa: " + (interestedInCargo ? "min. " + cargoCapacity : "nie dotyczy") +
                " | dodatkowe uwagi: " + tolerance[toleranceStrength] + "\n";
    }
}
