package com.company.config;

public final class Config {
    public final static int START_MONEY_VALUE = 80000;
    public final static int DESTINATION_MONEY_VALUE = START_MONEY_VALUE * 2;
    public final static int VEHICLES_AMOUNT_ON_START = 15;
    public final static int CLIENTS_AMOUNT_ON_START = 10;

    public final static double TAX_PERCENT = .02; // 2%

    public final static int CAR_WASH_COST = 10;
    public final static int[] CAR_PARTS_PERCENT = {10, 20, 100, 50, 50};
    public final static String[] CAR_PARTS_NAMES = {"hamulce", "zawieszenie", "silnik",
            "karoseria", "skrzynia biegów"};

    public final static int NEW_VEHICLES_AMOUNT_AFTER_TRANSACTION = 3;
    public final static int NEW_CLIENTS_AMOUNT_AFTER_TRANSACTION = 2;

    public final static int AD_NEWSPAPER_COST = 4000;
    public final static int AD_INTERNET_COST = 1500;
    public final static int AD_MAX_NEW_NEWSPAPER_CLIENTS = 10;
    public final static int AD_NEW_INTERNET_CLIENTS = 1;

    public final static double[] MECHANICS_PRICE_MULTIPLIER = {2.0, 1.5, 1.0};
    public final static String[] MECHANICS_NAMES = {"Janusz", "Marian", "Adrian"};

    public final static int[][] CAR_PRICE_RANGE = { // * 100
            {10, 40}, // budget
            {41, 400}, // standard
            {401, 800} // premium
    };

    public final static int[][] CAR_MILEAGE_RANGE = { // * 1000
            {250, 490}, // budget
            {160, 249}, // standard
            {10, 159} // premium
    };

    public final static int[] CAR_CARGO_SPACE = {2000, 4500};
    public final static String[] CAR_SEGMENT_NAMES = {"budżetowy", "standardowy", "premium"};

    public final static int[] CAR_PARTS_PRICE = {100, 800, 1000, 1200, 150}; // ceny początkowe za poszczególne części

    public final static String[][][] CAR_BRANDS = {
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

    public final static String[] CAR_COLORS = {"czarny", "biały", "czerwony", "niebieski", "srebrny", "zielony"};

    public final static String[] CLIENT_NAMES = {"Jan", "Grzegorz", "Kacper", "Robert", "Patryk", "Marcin", "Piotr", "Paweł", "Krzysztof", "Wojciech"};
    public final static String[] CLIENT_SURNAMES = {"Kowalski", "Nowak", "Pek", "Stencel", "Karsznia", "Suchodolski", "Kononowicz", "Łoś"};
    public final static int[] CLIENT_MONEY_RANGE = {10000, 220000};
    public final static int[] CLIENT_CARGO_RANGE = {20, 40}; // *100

}
