package com.labb2;

public class TextMessage {

    public void printMenu() {
        System.out.println("-------------------------------");
        System.out.println("   WELCOME TO THE STORE ABC");
        System.out.println("-------------------------------");
        System.out.println("1. Sälja produkten");
        System.out.println("2. Hantera produkter");
        System.out.println("3. Avsluta");
    }

    public void sellProductMenu() {
        System.out.println("1. Om du vet streckkoden.");
        System.out.println("2. Filtrera produkt.");
    }

    public void createProductMenu() {
        System.out.println("1. Skapa eller uppdatera produkt");
        System.out.println("2. Alla inköp");
        System.out.println("3. Spara Ändringarna");
    }

    public void sellProductMenuMenu() {

        System.out.println("1. Filter by [Price Range]");
        System.out.println("2. Filter by [Category]");
        System.out.println("3. Filter by [Available stock ]");

    }

}
