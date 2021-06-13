package com.company.config;

public final class Config {
    public final static int START_MONEY_VALUE = 80000;
    public final static int DESTINATION_MONEY_VALUE = START_MONEY_VALUE * 2;
    public final static int VEHICLES_AMOUNT_ON_START = 15;
    public final static int CLIENTS_AMOUNT_ON_START = 10;

    public final static double TAX_AMOUNT = .02; // 2%

    public final static int CAR_WASH_COST = 10;
    public final static int CAR_BRAKES_PERCENT = 10;
    public final static int CAR_SUSPENSION_PERCENT = 20;
    public final static int CAR_ENGINE_PERCENT = 100;
    public final static int CAR_BODY_PERCENT = 50;
    public final static int CAR_TRANSMISSION_PERCENT = 50;

    public final static int NEW_VEHICLES_AMOUNT_AFTER_TRANSACTION = 3;
    public final static int NEW_CLIENTS_AMOUNT_AFTER_TRANSACTION = 2;

    public final static int AD_NEWSPAPER_COST = 4000;
    public final static int AD_INTERNET_COST = 1500;
    public final static int AD_MAX_NEW_NEWSPAPER_CLIENTS = 10;
    public final static int AD_NEW_INTERNET_CLIENTS = 1;


    public final static int[][] carPriceRange = { // * 100
            {10, 40}, // budget
            {41, 400}, // standard
            {401, 800} // premium
    };

    public final static int[][] carMileageRange = { // * 1000
            {250, 490}, // budget
            {160, 249}, // standard
            {10, 159} // premium
    };

    public final static int[] carCargoSpace = {2000, 4500};
    public final static String[] carSegmentNames = {"budżetowy", "standardowy", "premium"};

    public final static int[] carPartsPrice = {300, 1600, 3000, 2000, 450}; // ceny początkowe za poszczególne części

    public final static String[][][] carBrands = {
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

    public final static String[] carColors = {"czarny", "biały", "czerwony", "niebieski", "srebrny", "zielony"};

    public final static String[] clientNames = {"Jan", "Grzegorz", "Kacper", "Robert", "Patryk", "Marcin", "Piotr", "Paweł", "Krzysztof", "Wojciech"};
    public final static String[] clientSurnames = {"Kowalski", "Nowak", "Pek", "Stencel", "Karsznia", "Suchodolski", "Kononowicz", "Łoś"};
    public final static int[] clientMoneyRange = {10000, 220000};
    public final static int[] clientCargoRange = {20, 40};

}
