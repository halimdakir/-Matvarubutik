package com.labb2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class ManageStore implements StoreFunction {
    List<Product> products = new ArrayList<>();
    List<Brand> brands = new ArrayList<>();
    List<ProductInfo> stock = new ArrayList<>();
    List<Category> categories = new ArrayList<>();
    HashMap<ProductInfo, Discount> discounts = new HashMap<>();
    List<Map<ProductInfo, Integer>> receipts = new ArrayList<>();




    public List readFile() {

        String path = System.getProperty("user.home") +
                File.separator + "Documents" +
                File.separator + "CustomFolder";

        File dir = new File(path);

        if (dir.exists())
            System.out.println("Folder exist");
        else if (dir.mkdir())
            System.out.println("Folder created");
        else
            System.out.println("Folder not created");

        File filePath = new File(path + File.separator + "savefile.csv");
        List<ProductInfo> productInfoList = new ArrayList<>();


        if (filePath.exists())
            try {
                Scanner scanner = new Scanner(filePath);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    String[] fields = line.split(",");
                    ProductInfo productInfo = new ProductInfo(new Product(fields[0], fields[1], fields[2], fields[3], new Brand(fields[4]), new Category(fields[5])), Integer.parseInt(fields[6]), Float.parseFloat(fields[7]));
                    //stock.add(productInfo);
                    productInfoList.add(productInfo);


                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        return productInfoList;
    }
    public void fillList(){
        stock = readFile();
    }
    public void writeIntoFile( List<ProductInfo> productList){

        String path = System.getProperty("user.home") +
                File.separator + "Documents" +
                File.separator + "CustomFolder";

        File dir = new File(path);

        if (dir.exists())
            System.out.println("Folder exist");
        else if (dir.mkdir())
            System.out.println("Folder created");
        else
            System.out.println("Folder not created");

        File filePath = new File(path + File.separator + "savefile.csv");

        try {
            FileWriter out = new FileWriter(filePath);
            for (ProductInfo p : productList) {
                out.write(p.product.getProductName() + "," + p.product.getProductNumber() + "," + p.product.getColor() + "," + p.product.getInfo() + "," + p.product.getBrand() + "," + p.product.getCategory() + "," + p.quantity + "," + p.price + "\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void saveChanges() {
        writeIntoFile(stock);
    }
    @Override
    public void purchases() {
        int kvittonummer = 1;

        // get Iterator for looping through AL
        Iterator<Map<ProductInfo, Integer>> iterator =
                receipts.iterator();
        while(iterator.hasNext()) {

            System.out.println("\nKvitto Nummer : " + kvittonummer);
            System.out.println("------------------------------------------------------------------------");
            System.out.format("%-6s %-15s %-15s %10s\n","Q",  "Product", "Streckkod ", " Unit Price" );

            Map<ProductInfo, Integer> mapProduct = iterator.next();

            // getting entrySet() into Set
            Set<Entry<ProductInfo, Integer>> entrySet = mapProduct.entrySet();

            // for-each loop
            for(Entry<ProductInfo, Integer> entry : entrySet) {
                System.out.format("%-6d %-15s %-16s %10.2f\n",entry.getValue(),entry.getKey().product.getProductName() , entry.getKey().product.getProductNumber() , entry.getKey().price );
            }
            System.out.println("------------------------------------------------------------------------");

                kvittonummer++;
        }

    }
    @Override
    public void priceRange() {

        try {
            System.out.print("Min of the price range :");
            int a = AppStart.scanner.nextInt();
            System.out.print("Max of the price range :");
            int b = AppStart.scanner.nextInt();

            List<ProductInfo> range = stock.stream()
                    .filter(e -> e.price >= (float) a && e.price <= (float) b)
                    .collect(Collectors.toList());


            for (ProductInfo p :range){
                System.out.println(p);
            }
            
        } catch (Exception e) {
            System.out.println("Input must be a number");
            AppStart.scanner.nextLine();
        }

    }
    @Override
    public void filterByCategory() {
        System.out.print("Enter the category of product:");
        String keyWord = AppStart.scanner.nextLine();

        List<ProductInfo> productByCategory = stock.stream()
                .filter(e -> e.product.getCategory().getCategories().startsWith(keyWord))
                .collect(Collectors.toList());



        Iterator<ProductInfo> productIterator = productByCategory.iterator();
        while (productIterator.hasNext()) {
            ProductInfo p = productIterator.next();
            System.out.println(p);
        }
    }
    @Override
    public void lagerSaldo() {
        List<ProductInfo> productStock = stock.stream()
                .filter(e -> e.quantity > 0)
                .collect(Collectors.toList());

        for (ProductInfo p : productStock) {
            System.out.println(p);
        }
    }
    public void sellProduct() {
        Map<ProductInfo, Integer> clientList = new HashMap<>();
        char choice = ' ';
        int quantity = 0;

        do {

            try {
                System.out.print("Quantity :");
                quantity = AppStart.scanner.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
            }
            AppStart.scanner.nextLine();
            System.out.print("Type the product bar code :");
            String word = AppStart.scanner.nextLine();

            //Check if product number that we type exist
            boolean produktNumberExists = stock.stream()
                    .anyMatch(t -> t.product.getProductNumber().equalsIgnoreCase(word));

            if (produktNumberExists && quantity !=0) {
                List<ProductInfo> productFilter = stock.stream()
                        .filter(e -> e.product.getProductNumber().equalsIgnoreCase(word))
                        .collect(Collectors.toList());


                boolean isPossible = ifQuantityAvailable(quantity, productFilter);
                if (!isPossible) {
                    System.out.println(" Antalet i lager är mindre än den mängd du har valt!");
                } else {

                    // list of sell
                    clientList.put(productFilter.get(0), quantity);
                    //update the stock after sell
                    updateStock(quantity, productFilter);
                }

            }else if ( quantity == 0) {
                        System.out.println("Quantity must be a number");
                    }else {
                        System.out.println("Denna Produktnummer är inte tillgängligt!");
                    
            }

            System.out.print("Add another product Yes = Y | No = N  :");
            choice = AppStart.scanner.next().charAt(0);

        } while ((choice == 'y') || (choice == 'Y'));

        System.out.println("------------------------------------------------------------------------");
        System.out.format("%-4s %-40s %-10s  %-10s\n", "Q",  "Product", "Unit Price ", " Total Price" );
        System.out.println("------------------------------------------------------------------------");

        discounts.put(stock.get(0), ((q, unitPrice) -> discountTenPercent(q, unitPrice)));
        discounts.put(stock.get(1), ((q, unitPrice) -> discountTreForTwo(q, unitPrice)));

        Discount d = (a,b)-> a*b;

        double sum = clientList.entrySet().stream().mapToDouble(e -> discounts.getOrDefault(e.getKey(), d).calculatePrice(e.getValue(), e.getKey().price)).sum();

        Integer sumQuantity = clientList.values().stream().mapToInt(Integer::intValue).sum();


        clientList.forEach((key, value) -> System.out.println(String.format("%-4d %-40s %10.2f  %13.2f", value, key.product.getProductName(), key.price , discounts.getOrDefault(key, d).calculatePrice(value , key.price))));

        System.out.println("------------------------------------------------------------------------");

        if (sumQuantity <= 1){
            System.out.println("Totalt " + sumQuantity + " vara.");

        }else {
            System.out.println("Totalt " + sumQuantity + " varor.");
        }


        System.out.format("%-54s %13.2f %-4s\n","Totalt att betala:", sum, ":-");
        System.out.println("------------------------------------------------------------------------");

        receipts.add(clientList);

    }
    public float discountTreForTwo(int quantity, float unitPrice) {
        var discountQuantity = quantity / 3;
        var finalquantity = quantity - discountQuantity;
        return finalquantity * unitPrice;
    }
    public float discountTenPercent(int quantity , float unitPrice){
        return quantity*(unitPrice - (unitPrice*10/100));
    }
    public List createProductInput() {
        List<Product> temporalyList = new ArrayList<>();

        System.out.print(" Product's name :");
        String pName = AppStart.scanner.nextLine();
        System.out.print(" Product's number :");
        String pNumber = AppStart.scanner.nextLine();
        System.out.print("Product's color :");
        String pColor = AppStart.scanner.nextLine();
        System.out.print("Product info :");
        String pInfo = AppStart.scanner.nextLine();
        System.out.print("Product's brand name :");
        String bName = AppStart.scanner.nextLine();
        System.out.print("Product's category name :");
        String cName = AppStart.scanner.nextLine();
        temporalyList.add(new Product(pName, pNumber, pColor, pInfo, new Brand(bName), new Category(cName)));

        return temporalyList;
    }
    @Override
    public void updateProduct() {
        char choice = ' ';

        do {
            List<Product> temporalyList1 = createProductInput();

            String barcode1 = temporalyList1.get(0).getProductNumber();

            List<ProductInfo> filterList = stock.stream()
                    .filter(e -> e.product.getProductNumber().equalsIgnoreCase(barcode1))
                    .collect(Collectors.toList());

            boolean finns;
            if (filterList.size()>0){
                finns = true;
            }else {
                finns = false;
            }

           if (finns ){

               try {
                   System.out.print("Den Produkt är redan finns i lager, Ange mängden du vill lägga till i lager :");
                   int newQuantity = AppStart.scanner.nextInt();


                   int stockIndex = stock.indexOf(filterList.get(0));
                   int quantity = stock.get(stockIndex).getQuantity();
                   stock.get(stockIndex).setQuantity(quantity+newQuantity);
               } catch (Exception e) {
                   e.printStackTrace();
               }

           }else {

               try {
                   System.out.print("Proudct's stock :");
                   int piStock = AppStart.scanner.nextInt();
                   System.out.print("Product's price :");
                   float piPrice = AppStart.scanner.nextFloat();
                   ProductInfo productInfo = new ProductInfo(new Product(temporalyList1.get(0).getProductName(), temporalyList1.get(0).getProductNumber(),temporalyList1.get(0).getColor(),temporalyList1.get(0).getInfo(), temporalyList1.get(0).getBrand(), temporalyList1.get(0).getCategory()), piStock, piPrice);
                   stock.add(productInfo);

               } catch (Exception e) {
                   System.out.println(e);
               }

           }

            System.out.print("Vill du skapa en ny produkt eller uppdatera antalet i lager? Yes = Y | No = N :");
            choice = AppStart.scanner.next().charAt(0);
            AppStart.scanner.nextLine();

        }while (( choice =='Y')||(choice =='y'));
    }
    @Override
    public void updateStock(int quantity, List<ProductInfo> list) {

        int index = stock.indexOf(list.get(0));

        int quantityExist = stock.get(index).getQuantity();

        stock.get(index).setQuantity(quantityExist-quantity);


        // for (ProductInfo p: stock){ System.out.println(p); }
    }
    @Override
    public boolean ifQuantityAvailable(int quantity, List<ProductInfo> list) {

        int index = stock.indexOf(list.get(0));
        int quantityExist = stock.get(index).getQuantity();
        if (quantityExist >= quantity){
            return true;
        }else {
            return false;
        }
    }

}

