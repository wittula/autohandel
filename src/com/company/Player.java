package com.company;

import java.util.ArrayList;
import java.util.List;
import static com.company.config.Config.*;

public class Player {
    public List<Vehicle> ownedCarsList = new ArrayList<>();

    public int movesCounter = 0; // liczba ruchów
    public double actualMoneyValue = START_MONEY_VALUE;

    public boolean isGameOver() {
        return (actualMoneyValue >= DESTINATION_MONEY_VALUE);
    }

    public void nextMove() {
        this.movesCounter = this.movesCounter + 1;
    }

    public void takeMoney(double amount) {
        this.actualMoneyValue = this.actualMoneyValue - amount;
    }

    public void giveMoney(double amount) {
        this.actualMoneyValue = this.actualMoneyValue + amount;
    }
}
