package com.labb2;
import java.util.Scanner;

public class AppStart implements Runnable {

    private boolean runProgram = true;
    static Scanner scanner = new Scanner(System.in);
    ManageStore manageStore = new ManageStore();
    TextMessage text = new TextMessage();

    @Override
    public void run() {
        manageStore.fillList();
    }

    public static void main(String[] args) {
        AppStart appStart = new AppStart();
        Thread thread = new Thread(appStart);

        thread.start();


        while (appStart.runProgram) {

           appStart.mainMenu();

        }
    }

    public void mainMenu(){
        text.printMenu();

        int choice = 0;
        try {
            String userType = scanner.nextLine();
            choice = Integer.parseInt(userType);
        } catch (NumberFormatException e) {
            System.out.println("Fel Input!");
        }

        if (choice == 1){
            sellMenu();

        }else if (choice == 2){
            manageProductMenu();

        }else if (choice == 3) {
            runProgram = false;
        }else {
            System.out.println("Unrecognized option!");
        }


    }
    private void sellMenu(){
        text.sellProductMenu();
        int choice = 0;
        try {
            String userType = scanner.nextLine();
            choice = Integer.parseInt(userType);
        } catch (NumberFormatException e) {
            System.out.println("Fel Input!");
        }

        if (choice == 1){
            manageStore.sellProduct();

        }else if (choice == 2){
            sellMenuMenu();
        }else  {
            System.out.println("Unrecognized option");
        }

    }
    private void sellMenuMenu(){
        text.sellProductMenuMenu();
        int choice = 0;
        try {
            String userType = scanner.nextLine();
            choice = Integer.parseInt(userType);
        } catch (NumberFormatException e) {
            System.out.println("Fel Input!");
        }

        if (choice == 1){
            manageStore.priceRange();

        }else if (choice == 2){
            manageStore.filterByCategory();

        }else if (choice == 3){
            manageStore.lagerSaldo();
        }else {
            System.out.println("Unrecognized option");
        }

    }
    private void manageProductMenu(){
        text.createProductMenu();
        int choice = 0;
        try {
            String userType = scanner.nextLine();
            choice = Integer.parseInt(userType);
        } catch (NumberFormatException e) {
            System.out.println("Fel Input!");
        }

        if (choice == 1){
            manageStore.updateProduct();

        }else if (choice == 2){
            manageStore.purchases();

        }else if (choice == 3){
        manageStore.saveChanges();

        }else {
            System.out.println("Unrecognized option");
        }

    }
}
