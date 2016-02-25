/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import ejb.OSSManagerBeanRemote;
import java.util.Scanner;
import javax.ejb.EJB;

/**
 *
 * @author Daren
 */
public class Main {

    @EJB
    private static OSSManagerBeanRemote ossmbr;
    public Scanner sc = new Scanner(System.in);
    
    
    public Main(){
    }   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Main client = new Main();
        client.getOption();
    }
    
    public void getOption() {
        String userInput = "";
        while (userInput ==null || (!userInput.equals("Q") && !userInput.equals("q"))) {
            System.out.println();
            displayMenu();
            userInput = sc.nextLine();
            
            if (userInput.equals("1")) {
                addUser();
            } else if (userInput.equals("2")) {
                deleteUser();
            } else if (userInput.equals("3")) {
                addProduct();
            } else if (userInput.equals("4")) {
                updateProduct();
            } else if (userInput.equals("5")) {
                deleteProduct();
            } else if (userInput.equals("6")) {
                viewNewOrders();
            } else if (userInput.equals("7")) {
                viewPayments();
            } else if (userInput.equals("8")) {
                shipOrders();
            } else if (userInput.equals("9")) {
                viewDeliveryReport();
            } else if (userInput.equals("10")) {
                viewMonthlyOrderReport();
            } else if (userInput.equals("11")) {
                viewReorderReport();
            } else if (userInput.equals("12")) {
                processRequest();
            } else if (userInput.equals("Q") || userInput.equals("q")) 
                System.out.println("Terminated. Exiting...");
            else {
                System.out.println("Please Input a Number from 1 to 12.");
                System.out.println("To exit, input 'Q' or 'q'.");
                userInput = sc.nextLine();
            }
        }
    }
    
      private void displayMenu() {
        System.out.println("***************************************************");
        System.out.println("Good day Admin, welcome to the Online Sales System!");
        System.out.println("***************************************************");
        System.out.println();
        System.out.println("Select an option:");
        System.out.println("1.  Add user account(s)");
        System.out.println("2.  Delete use account(s)");
        System.out.println("3.  Add product(s)");
        System.out.println("4.  Update products(s)");
        System.out.println("5.  Delete products(s)");
        System.out.println("6.  View new order(s)");
        System.out.println("7.  View payment(s)");
        System.out.println("8.  Ship Orders(s)");
        System.out.println("9.  View order delivery report(s)");
        System.out.println("10. View monthly order report(s)");
        System.out.println("11. View reorder report(s)");
        System.out.println("12. Process request(s)");
        System.out.println();
        System.out.println("Enter 'q' or 'Q' to exit this admin console");
        System.out.println();
        System.out.print("Enter your option: ");
    }
      
    public void addUser() {
        System.out.println("Add User:");
        String exitOption = "";
        while (!exitOption.equals("q") && !exitOption.equals("Q")) {
            try {
                System.out.println();
                System.out.print("Enter username: ");
                String username = sc.nextLine();
                boolean isNew = ossmbr.isNewUser(username);
                if (isNew) {
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();
                    System.out.print("Enter contact number: ");
                    String contactNum = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter address: ");
                    String address = sc.nextLine();
                    ossmbr.createUser(username, password, contactNum, email, address);
                    System.out.println("User " + username + " has been added.");
                } else {
                    System.out.println("Sorry, this user already exists!");
                }
            } catch (Exception ex) {
                System.err.println("Caught unexpected exception.");
                ex.printStackTrace();
            }
            
            System.out.println("Enter Q To Quit, Enter any other input to continue.");
            exitOption = sc.nextLine();
                    
        }
    }
    
    public void deleteUser() {
        System.out.println("Delete User:");
        System.out.print("Enter username of user: ");
        String username = sc.nextLine();
        String result = ossmbr.removeUser(username);
        System.out.println();
        System.out.println(result);
    }
    
    public void addProduct() {
        System.out.println("Add Product:");
        String exitOption = "";
        while (!exitOption.equals("q") && !exitOption.equals("Q")) {
            try {
                System.out.println();
                System.out.print("Enter Brand: ");
                String brand = sc.nextLine();
                System.out.print("Enter Model: ");
                String model = sc.nextLine();
                boolean isNew = ossmbr.isNewProduct(brand, model);
                if (isNew) {
                    System.out.print("Enter ProductType: ");
                    String productType = sc.nextLine();
                    System.out.print("Enter description: ");
                    String description = sc.nextLine();
                    System.out.print("Enter Unit Price: ");
                    String unitPrice = sc.nextLine();
                    System.out.print("Enter Stock quantity: ");
                    String stockqty = sc.nextLine();
                    while (!isNumberInput(stockqty)){
                        System.out.println("Enter a number 0 or greater");
                        System.out.print("Enter Stock quantity: ");
                        stockqty = sc.nextLine();
                    }
                    int stockQty = Integer.parseInt(stockqty);
                    System.out.print("Enter reorder level: ");
                    String reorderLevel = sc.nextLine();
                    while (!isNumberInput(reorderLevel)){
                        System.out.println("Enter a number 0 or greater");
                        System.out.print("Enter reorder level: ");
                        reorderLevel = sc.nextLine();
                    }
                    int reorderLvl = Integer.parseInt(reorderLevel);
                    ossmbr.createProduct(productType, brand, model, description, unitPrice, stockQty, reorderLvl);
                    System.out.println("Product " + brand + model + " has been added.");
                } else {
                    System.out.println("Sorry, this product already exists!");
                }
            } catch (Exception ex) {
                System.err.println("Caught unexpected exception.");
                ex.printStackTrace();
            }
            System.out.println("Enter Q To Quit, Enter any other input to continue.");
            exitOption = sc.nextLine();
                    
        }
    }
    
    public void updateProduct() {
        System.out.println("Update Product:");
        String exitOption = "";
        while (!exitOption.equals("q") && !exitOption.equals("Q")) {
            try {
                System.out.println();
                System.out.print("Enter Brand: ");
                String brand = sc.nextLine();
                System.out.print("Enter Model: ");
                String model = sc.nextLine();
                boolean isNew = ossmbr.isNewProduct(brand, model);
                if (!isNew) {
                    // update unitprice, stock qty and reorder level for products with orders
                    if (ossmbr.productHasOrders(brand, model)) {
                        System.out.print("This product has order(s). Only unit price, stock quantity and reorder level can be changed!");
                        System.out.print("Enter Unit Price: ");
                        String unitPrice = sc.nextLine();
                        System.out.print("Enter Stock quantity: ");
                        String stockqty = sc.nextLine();
                        while (!isNumberInput(stockqty)) {
                            System.out.println("Enter a number 0 or greater");
                            System.out.print("Enter Stock quantity: ");
                            stockqty = sc.nextLine();
                        }
                        int stockQty = Integer.parseInt(stockqty);
                        System.out.print("Enter reorder level: ");
                        String reorderLevel = sc.nextLine();
                        while (!isNumberInput(reorderLevel)) {
                            System.out.println("Enter a number 0 or greater");
                            System.out.print("Enter reorder level: ");
                            reorderLevel = sc.nextLine();
                        }
                        int reorderLvl = Integer.parseInt(reorderLevel);
                        ossmbr.updateProductWithOrders(brand, model, unitPrice, stockQty, reorderLvl);
                        System.out.println("Product " + brand + model + " has been updated.");
                        
                    }
                    else {
                                
                    
                    System.out.print("Enter ProductType: ");
                    String productType = sc.nextLine();
                    System.out.print("Enter description: ");
                    String description = sc.nextLine();
                    System.out.print("Enter Unit Price: ");
                    String unitPrice = sc.nextLine();
                    System.out.print("Enter Stock quantity: ");
                    String stockqty = sc.nextLine();
                    while (!isNumberInput(stockqty)){
                        System.out.println("Enter a number 0 or greater");
                        System.out.print("Enter Stock quantity: ");
                        stockqty = sc.nextLine();
                    }
                    int stockQty = Integer.parseInt(stockqty);
                    System.out.print("Enter reorder level: ");
                    String reorderLevel = sc.nextLine();
                    while (!isNumberInput(reorderLevel)){
                        System.out.println("Enter a number 0 or greater");
                        System.out.print("Enter reorder level: ");
                        reorderLevel = sc.nextLine();
                    }
                    int reorderLvl = Integer.parseInt(reorderLevel);
                    ossmbr.updateProduct(productType, brand, model, description, unitPrice, stockQty, reorderLvl);
                    System.out.println("Product " + brand + model + " has been updated.");
                    
                    }
                }
                else {
                    System.out.println("Sorry, this product do not exist!");
                }
            } catch (Exception ex) {
                System.err.println("Caught unexpected exception.");
                ex.printStackTrace();
            }
            System.out.println("Enter Q To Quit, Enter any other input to continue.");
            exitOption = sc.nextLine();
        }            
        
    }
    
    public void deleteProduct() {System.out.println("Update Product:");
        String exitOption = "";
        while (!exitOption.equals("q") && !exitOption.equals("Q")) {
            System.out.println("Delete Product:");
            System.out.print("Enter brand of Product: ");
            String brand = sc.nextLine();
            System.out.print("Enter model of Product: ");
            String model = sc.nextLine();
            String result = ossmbr.removeProduct(brand, model);
            System.out.println();
            System.out.println(result);
            System.out.println("Enter Q To Quit, Enter any other input to continue.");
            exitOption = sc.nextLine();
        }
    }
    
    public void viewNewOrders() {
        System.out.println(ossmbr.getNewOrders());
    }
    
    public void viewPayments() {
        System.out.println("Viewing Payments:");
        System.out.print("Enter Order Id: ");
        String orderId = sc.nextLine();
        while (!isNumberInput(orderId)) {
            System.out.println("Incorrect input format!");
            System.out.println("Enter Order Id: ");
            orderId = sc.nextLine();
        }
        Long orderID = Long.parseLong(orderId);
        System.out.println(ossmbr.viewPayment(orderID));
        
    }
    
    public void shipOrders() {
        System.out.println("Shipping Orders:");
        System.out.print("Enter Order Id: ");
        String orderId = sc.nextLine();
        while (!isNumberInput(orderId)) {
            System.out.println("Incorrect input format!");
            System.out.print("Enter Order Id: ");
            orderId = sc.nextLine();
        }
        Long orderID = Long.parseLong(orderId);
        if (ossmbr.readyToShip(orderID)){
            
            System.out.println("Enter Tracking Number: ");
            String trackingNum = sc.nextLine();
            while (!isNumberInput(trackingNum)) {
                System.out.println("Incorrect input format!");
                System.out.println("Enter Tracking  Number: ");
            trackingNum = sc.nextLine();
            }
            Long trackingNo = Long.parseLong(trackingNum);
            
            System.out.println("Enter Expected Delivery Date(DDMMYYYY): ");
            String delDate = sc.nextLine();
            while (!isNumberInput(delDate)) {
                System.out.println("Incorrect input format!");
                System.out.println("Enter Expected Delivery Date(DDMMYYYY): ");
                trackingNum = sc.nextLine();
            }
            ossmbr.shipOrder(orderID, trackingNo, delDate);
        }
            
    }
    
    public void viewDeliveryReport() {
        
        System.out.println("View order delivery report:");
        System.out.println("Enter Order ID: ");
        String orderId = sc.nextLine();
        while (!isNumberInput(orderId)) {
            System.out.println("Incorrect input format!");
            System.out.println("Enter Order  ID: ");
            orderId = sc.nextLine();
        }
        Long orderID = Long.parseLong(orderId);
        System.out.println(ossmbr.orderDeliveryReport(orderID));
        
        
    }
    
    public void viewMonthlyOrderReport() {
        System.out.print("Enter Brand: ");
        String brand = sc.nextLine();
        System.out.print("Enter Model: ");
        String model = sc.nextLine();
        boolean isNew = ossmbr.isNewProduct(brand, model);
        if (isNew) {
            System.out.println("Product not found!");
        }
        else {
            System.out.println(ossmbr.monthlyOrderReport(brand, model));
        }
    }
    
    public void viewReorderReport() {
        System.out.println(ossmbr.reorderReport());
    }
    
    public void processRequest() {
        System.out.println("List of Request(s):");
        System.out.println(ossmbr.processRequest());
        String exitOption = "";
        while (!exitOption.equals("q") && !exitOption.equals("Q")) {

            System.out.println("Select Request by entering request ID:");
            String requestId = sc.nextLine();
            while (!isNumberInput(requestId)) {
                System.out.println("Incorrect input format!");
                System.out.println("Enter Request ID: ");
                requestId = sc.nextLine();
            }
            Long orderID = Long.parseLong(requestId);
            System.out.println("Enter comments:");
            String comments = sc.nextLine();
            System.out.println("Enter status:");
            String status = sc.nextLine();

            System.out.println(ossmbr.handleRequest(orderID, comments, status));
            System.out.println("Enter 'q' or 'Q' To exit, enter any other input to continue");
            exitOption = sc.nextLine();
        }
    }
    
    public boolean isNumberInput(String input) {
        int stockqty = -1;
        boolean isInt = false;
        try {
            stockqty = Integer.parseInt(input);
            isInt = true;
        } catch (NumberFormatException e) {
            return false;
        }
        if (stockqty < 0)
            isInt = false;
        return isInt;
    }
                    
    
}
