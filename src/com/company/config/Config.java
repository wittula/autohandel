package com.company;

public final class Config {
    public final static int START_MONEY_VALUE = 80000; // początkowa wartość pieniędzy dla gracza
    public final static int DESTINATION_MONEY_VALUE = START_MONEY_VALUE * 2; // cel jaki musi osiągnąć gracz, żeby ukończyć grę
    public final static int VEHICLES_AMOUNT_ON_START = 15; // liczba samochodów znajdujących się na początkującej liście
    public final static int NEW_VEHICLES_AMOUNT_AFTER_TRANSACTION = 3; // ile nowych samochodów dojdzie do kupienia po każdej transakcji
    public final static int NEW_CLIENTS_AMOUNT_AFTER_TRANSACTION = 2; // ile nowych potencjalnych klientów przybędzie po udanej transakcji
    public final static int TAX_AMOUNT = 2; // % podatku
    public final static int CAR_WASH_COST = 10; // koszt mycia pojazdu

    // Dane dla generatorów
    public final int[][] carPriceRange = { // * 100
            {10, 40}, // budget
            {41, 300}, // standard
            {301, 800} // premium
    };

    public final int[][] carMileageRange = { // * 1000
            {250, 490}, // budget
            {160, 249}, // standard
            {10, 159} // premium
    };

    public int[] carPartsPrice = {300, 1600, 3000, 2000, 450}; // ceny początkowe za poszczególne części

    public final String[][][] carBrands = {
            { // budget & standard
                    //  {"Nazwa", "% o ile droższe będą części od bazowej ceny"}
                    {"Daewoo", "5"},
                    {"Fiat", "10"},
                    {"Ford", "10"},
                    {"Dacia", "10"},
                    {"Volkswagen", "15"},
                    {"Opel", "15"},
                    {"Renault", "15"}
            },

            { // premium
                    //  {"Nazwa", "% o ile droższe będą części od bazowej ceny"}
                    {"Audi", "40"},
                    {"BMW", "35"},
                    {"Bugatti", "40"},
                    {"Ferrari", "50"},
                    {"Lamborghini", "50"}
            }
    };

    public final String[] carColors = {"czarny", "biały", "czerwony", "niebieski", "srebrny", "zielony"};

    public final String[] clientNames = {"Jan", "Grzegorz", "Kacper", "Robert", "Patryk", "Marcin", "Piotr", "Paweł", "Krzysztof", "Wojciech"};
    public final String[] clientSurnames = {"Kowalski", "Nowak", "Pek", "Stencel", "Karsznia", "Suchodolski", "Kononowicz", "Łoś"};
    public final int[] clientMoneyRange = {3500, 220000};

}
