package GAsg;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {

    private static Scanner scan = new Scanner(System.in);
    private static int numOfOrders = 0;
    private static int numOfCustomers = 0;

    public static void main(String[] args) {
        int option = 0;
        int MAX = 10; // maximum value for number of customer
        int MAXORDERS = 10; // maximum value for number of orders

        int[] ID = new int[MAX]; // must be six digits
        String[] names = new String[MAX];
        String[] HomeAddress = new String[MAX];
        String[] EmailAddress = new String[MAX];

        int[] orderID = new int[MAXORDERS];
        String[] customer_name = new String[MAXORDERS];
        String[] customer_restaurant = new String[MAXORDERS];
        String[] customer_ordertime = new String[MAXORDERS];
        double[] customer_amount = new double[MAXORDERS];

        System.out.printf("%44s%n", "********** FOOD PUPPY ***********");
        System.out.println("-------------------------------------------------------------");

        Menu(option, ID, names, HomeAddress, EmailAddress, orderID, customer_name, customer_restaurant, customer_ordertime, customer_amount);
    }

    public static void Menu(int option, int[] ID, String[] names, String[] HomeAddress, String[] EmailAddress, int[] orderID, String[] customer_name, String[] customer_restaurant, String[] customer_ordertime, double[] customer_amount) {
        boolean error = false;
        try {
            while (option != 6) {
                displayMenu();
                option = scan.nextInt();
                while (option < 1 || option > 6) {
                    System.out.println("Please enter correct value");
                    displayMenu();
                    option = scan.nextInt();
                }
                switch (option) {
                    // register new customer
                    case 1:
                        System.out.println("");
                        registerCust(names, ID, HomeAddress, EmailAddress);
                        if (names[names.length - 1] != null) { // IF REGISTERED CUSTOMER EXCEED THE MAX ARRAY'S SIZE
                            ID = Arrays.copyOf(ID, (ID.length + 1));
                            names = Arrays.copyOf(names, (names.length + 1));
                            HomeAddress = Arrays.copyOf(HomeAddress, (HomeAddress.length + 1));
                            EmailAddress = Arrays.copyOf(EmailAddress, (EmailAddress.length + 1));
                        } else {
                            break;
                        }
                        break;

                    case 2: // take new order
                        if (names[0] == null) { // IF HAVENT REGISTER ANY CUSTOMER
                            System.out.println("Please Register First Before Login!");
                            System.out.println("");
                            break;
                        }
                        newOrders(ID, orderID, customer_name, names, customer_restaurant, customer_ordertime, customer_amount, HomeAddress, EmailAddress);

                        if (orderID[orderID.length - 1] != 0) {
                            orderID = Arrays.copyOf(orderID, (orderID.length + 1));
                            customer_name = Arrays.copyOf(customer_name, (customer_name.length + 1));
                            customer_restaurant = Arrays.copyOf(customer_restaurant, (customer_restaurant.length + 1));
                            customer_ordertime = Arrays.copyOf(customer_ordertime, (customer_ordertime.length + 1));
                            customer_amount = Arrays.copyOf(customer_amount, (customer_amount.length + 1));
                        } else {
                            break;
                        }
                        break;

                    case 3: // modify customer details
                        System.out.println("");
                        if (names[0] == null) { // IF HAVENT REGISTER ANY CUSTOMER
                            System.out.println("Please Register First Before Login!");
                            System.out.println("");
                            // call register method
                            break;
                        }
                        EditCust.modify(ID, names, HomeAddress, EmailAddress, error);
                        break;

                    case 4: // display customer past orders 
                        System.out.println("");
                        if (names[0] == null) { // IF HAVENT REGISTER ANY CUSTOMER
                            System.out.println("Please Register First Before Login!");
                            System.out.println("");
                            // call register method
                            break;
                        }
                        if (orderID[0] == 0) {
                            System.out.println("Please Add New Order First Before Login!");
                            System.out.println("");
                            break;
                        }
                        displayPastOrder(numOfOrders, orderID, customer_restaurant, customer_ordertime, customer_amount);
                        break;

                    case 5: //list number of customer orders more than x times
                        System.out.println("");
                        if (names[0] == null) { // IF HAVENT REGISTER ANY CUSTOMER
                            System.out.println("Please Register First Before Login!");
                            System.out.println("");
                            // call register method
                            break;
                        }
                        if (orderID[0] == 0) {
                            System.out.println("Please Add New Order First Before Login!");
                            System.out.println("");
                            break;
                        }
                        ListCust.listcustomers(numOfOrders, ID, orderID, customer_name, EmailAddress);
                        break;

                    case 6: //terminate
                        System.out.println("");
                        System.out.println("Thank you for using our services. Hope to see you again in future!");
                        System.out.printf("%34s%n", "----- End -----");
                        System.out.println("-------------------------------------------------------------");
                        break;

                    default:
                        System.out.println("Invalid!");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter correct value");
            scan.nextLine();
            Menu(option, ID, names, HomeAddress, EmailAddress, orderID, customer_name, customer_restaurant, customer_ordertime, customer_amount);
        }
    }

    public static void displayMenu() { // MENU FOR CUSTOMERS TO CHOOSE
        System.out.printf("%33s%n", "-- OPTIONS --");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("x 1 : To Register New Customers                             x"); // REGISTER CUSTOMER
        System.out.println("x 2 : To Place New Orders                                   x"); // TAKE NEW ORDERS
        System.out.println("x 3 : To Modify Customer Details                            x"); // MODIFY CUSTOMER DETAILS
        System.out.println("x 4 : To Display The Past Orders                            x"); // DSIPLAY CUSTOMER PAST ORDERS
        System.out.println("x 5 : To List Customer that Ordered More than x Times       x"); // DISPLAY WHICH CUSTOMER ORDERED MORE THAN X TIMES
        System.out.println("x 6 : To Terminate This Program                             x"); // TERMINATE AND EXIT PROGRAM
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.print("Please Enter the Corresponding Number To Perform Operation> ");
    }

    public static void registerCust(String[] names, int[] ID, String[] HomeAddress, String[] EmailAddress) {
        char a = 0;

        do {
            try {
                System.out.print("ENTER CUSTOMER'S NAME                    : ");
                names[numOfCustomers] = scan.next();
                names[numOfCustomers] = names[numOfCustomers] + scan.nextLine();

                System.out.print("ENTER CUSTOMER'S ID (ID must be 6 Digits): ");
                ID[numOfCustomers] = scan.nextInt();
                while (String.valueOf(ID[numOfCustomers]).length() != 6) {
                    System.out.print("ENTER CUSTOMER'S ID (ID must be 6 Digit): ");
                    ID[numOfCustomers] = scan.nextInt();
                }
                while (checkID(numOfCustomers, ID[numOfCustomers], ID)) {
                    System.out.println("This ID already exist!");
                    System.out.print("ENTER CUSTOMER'S ID (ID must be 6 Digit): ");
                    ID[numOfCustomers] = scan.nextInt();
                    checkID(numOfCustomers, ID[numOfCustomers], ID);
                }
                System.out.print("ENTER CUSTOMER'S HOME ADDRESS            : ");
                HomeAddress[numOfCustomers] = scan.next();
                HomeAddress[numOfCustomers] = HomeAddress[numOfCustomers] + scan.nextLine();
                System.out.print("ENTER CUSTOMER'S EMAIL ADDRESS           : ");
                EmailAddress[numOfCustomers] = scan.next();

            } catch (InputMismatchException e) {
                System.out.println("Please enter correct value");
                scan.nextLine();
                registerCust(names, ID, HomeAddress, EmailAddress);
            } catch (Exception e) {
                System.out.println("Something went wrong");
                scan.nextLine();
                registerCust(names, ID, HomeAddress, EmailAddress);
            }
            System.out.println("");
            System.out.println("Congratulations! NO." + (numOfCustomers + 1) + " customers has been registered successfully.");
            System.out.println("*************************************************************");
            System.out.printf("%33s%n", "== CUSTOMER DETAILS ==");
            System.out.printf("%-30s%-10s%n", "CUSTOMER'S NAME: ", names[numOfCustomers]);
            System.out.printf("%-30s%-10s%n", "CUSTOMER'S ID: ", ID[numOfCustomers]);
            System.out.printf("%-30s%-10s%n", "CUSTOMER'S HOME ADDRESS: ", HomeAddress[numOfCustomers]);
            System.out.printf("%-30s%-10s%n", "CUSTOMER'S EMAIL ADDRESS: ", EmailAddress[numOfCustomers]);
            System.out.println("*************************************************************");
            System.out.println("");

            numOfCustomers++;

            System.out.print("Enter Y To Register Another Customer Or N To End Register> ");
            a = scan.next().charAt(0);
            if (a == 'y' || a == 'Y') {

            } else if (a == 'n' || a == 'N') {
                System.out.println("*************************************************************");
                System.out.printf("%40s%n", "---- DONE REGISTER ----");
                System.out.println("*************************************************************");
                System.out.println("");
                break;

            } else {
                while (a != 'y' && a != 'Y' && a != 'n' && a != 'N') {
                    System.out.println("Invalid");
                    System.out.print("Enter Y To Register Another Customer Or N To End Register> ");
                    a = scan.next().charAt(0);
                }
            }
        } while (a == 'y' || a == 'Y' || a == 'n' || a == 'N');

    }

    public static boolean checkID(int i, int n, int[] ID) {
        for (int j = 0; j < i; j++) {
            if (n == ID[j]) {
                return true;
            }
        }
        return false;
    }

    public static void newOrders(int[] ID, int[] orderID, String[] ordername, String[] names, String[] testRestaurant, String[] testOrderTime, double[] testamount, String[] HomeAddress, String[] EmailAddress) {
        // call to get ID array
        int i = 0;
        int j = 0;
        char a, b;
        boolean status = false;

        // take orders
        do {
            do {
                try {
                    System.out.println("");
                    System.out.print("PLEASE ENTER THE CUSTOMER ID> ");
                    int n = scan.nextInt();
                    System.out.println("-------------------------------------------------------------");

                    for (i = 0; i < ID.length; i++) {
                        if (n == ID[i]) { // IF CUSTOMER EXIST
                            status = true;
                            System.out.printf("%33s%n", "NEW ORDER");
                            System.out.println("-------------------------------------------------------------");
                            System.out.println("CUSTOMER ID     :" + ID[i]);
                            System.out.println("NO BIL          :" + (numOfOrders + 1));

                            ordertimes(testOrderTime);
                            orderID[numOfOrders] = ID[i]; // STORE ID IN ARRAY CALLED ORDERID
                            ordername[numOfOrders] = names[i]; // STORE CUSTOMER NAME IN ARRAY CALLED ORDERNAME
                            break;
                        }

                    }
                    if (i == ID.length) { // IF CUSTOMER ID DOEST NOT EXIST
                        status = false;
                        System.out.println("INVALID CUSTOMER ID!");
                        System.out.println("CUSTOMER HASN'T REGISTERED YET!");
                        System.out.println("Please Register before login>");
                        // call ID method to register customer 
                        registerCust(names, ID, HomeAddress, EmailAddress);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter correct value");
                    scan.nextLine();
                    newOrders(ID, orderID, ordername, names, testRestaurant, testOrderTime, testamount, HomeAddress, EmailAddress);
                } catch (Exception e) {
                    System.out.println("Something went wrong");
                    scan.nextLine();
                    newOrders(ID, orderID, ordername, names, testRestaurant, testOrderTime, testamount, HomeAddress, EmailAddress);
                }
            } while (status == false);

            // CALL METHOD TO TAKE NEW ORDERS
            orderplace(j, testRestaurant);
            System.out.println("-------------------------------------------------------------");
            amounts(testamount);
            System.out.println("-------------------------------------------------------------");

            numOfOrders++;// MEANS NO. BIL + 1

            System.out.print("Enter Y To Add New Order or N To End Order> ");
            a = scan.next().charAt(0);
            if (a == 'y' || a == 'Y') {

            } else if (a == 'n' || a == 'N') {
                System.out.println("*************************************************************");
                System.out.printf("%40s%n", "---- DONE ORDERING ----");
                System.out.println("*************************************************************");
                System.out.println("");
                break;

            } else {
                while (a != 'y' && a != 'Y' && a != 'n' && a != 'N') {
                    System.out.println("Invalid");
                    System.out.print("Enter Y To Add New Order or N To End Order> ");
                    a = scan.next().charAt(0);
                }
            }
        } while (a == 'y' || a == 'Y' || a == 'n' || a == 'N');

    }

    public static String orderplace(int j, String[] testRestaurant) {
        String[] restaurant = {"KFD", "SUSHIZANMEI", "PIZZAHOT", "SUGARMUM", "MIZHICHICKEN"};
        int[] label = {1, 2, 3, 4, 5};

        String[] items0 = {"3-pc Combo", "2-pc Rice Combo", "Zinger Cheezy Combo", "Zinger Cheezy Combo2", "9-pc Nuggets", "Zinger Cheezy"};
        String[] items1 = {"Chuka Wakame", "Takoyaki", "Salmon Mentai Roll", "Nigiri Sushi", "Sukiyaki", "Kimuchi Seafood Nabe"};
        String[] items2 = {"Sweet&Sour Combo 1", "Sweet&Sour Combo 2", "Myboxduo", "Pizza Limo Combo", "Hot Value Meal Pasta", "Double Box Large"};
        String[] items3 = {"Broasted Chicken", "Chicken Mushroom", "Eco Fish Assam", "Chicken Sandwich Combo", "Fish Burger Combo", "Fish&Chips Combo"};
        String[] items4 = {"Savers Good Luck", "Ladies Good Luck", "Healthy Good Luck", "Noodle Good Luck", "Lunch for 2", "Dinner for 2"};

        double[] price0 = {20.49, 16.99, 16.70, 11.99, 13.99, 12.70};
        double[] price1 = {5.80, 10.80, 17.80, 18.80, 25.80, 25.80};
        double[] price2 = {46.90, 69.90, 30.00, 99.00, 48.00, 54.20};
        double[] price3 = {13.50, 14.00, 14.00, 7.90, 14.90, 14.90};
        double[] price4 = {10.45, 12.85, 15.50, 13.85, 29.80, 32.85};

        do {
            System.out.printf("%-10s%26s%n", "NO", "RESTAURANT NAME");
            System.out.println("-------------------------------------------------------------");
            display(restaurant);
            System.out.println("-------------------------------------------------------------");
            System.out.print("PlEASE SELECT THE RESTAURANT NUMBER(1-5)> ");
            int customer_rest = scan.nextInt();

            for (j = 0; j < restaurant.length; j++) {
                if (customer_rest == label[j]) {
                    System.out.println("RESTAURANT      :" + restaurant[j]);
                    System.out.println("*************************************************************");
                    System.out.printf("%29s%n", "MENU");
                    System.out.println("*************************************************************");
                    System.out.printf("%-10s%39s%n", "ITEM", "PRICE(RM)");
                    System.out.println("-------------------------------------------------------------");
                    if (j == 0) {
                        display2(items0, price0);

                    } else if (j == 1) {
                        display2(items1, price1);

                    } else if (j == 2) {
                        display2(items2, price2);

                    } else if (j == 3) {
                        display2(items3, price3);

                    } else {
                        display2(items4, price4);

                    }
                    testRestaurant[numOfOrders] = restaurant[j];
                    break;
                }
            }
            if (j == restaurant.length) {
                System.out.println("Invalid Restaurant! Please Enter Again!");
                System.out.println("");
            }
        } while (j == restaurant.length);
        return testRestaurant[numOfOrders];
    }

    public static void display(String[] n) {
        for (int i = 0; i < n.length; i++) {
            System.out.printf("%-23s%-30s%n", (i + 1) + ")", n[i]);
        }
    }

    public static void display2(String[] n, double[] m) {
        for (int i = 0; i < n.length; i++) {
            System.out.printf("%-2s%-39s%-1.2f%n", (i + 1) + ".", n[i], m[i]);
        }
    }

    public static String ordertimes(String[] testordertime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("DATE            :" + dtf.format(now));
        testordertime[numOfOrders] = dtf.format(now);
        System.out.println("-------------------------------------------------------------");
        return dtf.format(now);
    }

    public static void amounts(double[] testamount) {
        try {
            System.out.print("PLEASE ENTER THE AMOUNT PAID: RM");
            double rm = scan.nextDouble();
            System.out.printf("AMOUNT PAID                 : RM%.2f%n", rm);
            testamount[numOfOrders] = rm;
        } catch (InputMismatchException e) {
            System.out.println("Please enter correct value");
            scan.nextLine();
            amounts(testamount);
        } catch (Exception e) {
            System.out.println("Something went wrong");
            scan.nextLine();
            amounts(testamount);
        }
    }

    public static void displayPastOrder(int numberOfOrder, int[] orderID, String[] restaurant, String[] date, double[] amount) {
        int c = 0;    // for row
        int a = 0; // for column
        char e = 0;

        boolean check = true;    // check if got ordered history or not
        String[][] res = new String[numberOfOrder][numberOfOrder];
        String[][] d = new String[numberOfOrder][numberOfOrder];
        double[][] paid = new double[numberOfOrder][numberOfOrder];
        int[] n = new int[numberOfOrder];

        do {
            try {
                System.out.print("ENTER CUSTOMER ID: ");
                n[c] = scan.nextInt();
            } catch (InputMismatchException q) {
                System.out.println("Please enter integer only");
                scan.nextLine();
                displayPastOrder(numberOfOrder, orderID, restaurant, date, amount);
            } catch (Exception q) {
                System.out.println("Something Went  Wrong");
                scan.nextLine();
                displayPastOrder(numberOfOrder, orderID, restaurant, date, amount);
            }

            for (int i = 0; i < numberOfOrder; i++) {
                if (n[c] == orderID[i]) {
                    res[c][a] = restaurant[i];
                    d[c][a] = date[i];
                    paid[c][a] = amount[i];
                    a++;
                }
            }

            for (int i = 0; i < res[c].length; i++) {
                for (int j = 0; j < res[c].length; j++) {
                    if (res[i][j] != null && d[i][j] != null) {
                        System.out.println("");
                        System.out.printf("%33s%n", "PAYMENT RECEIPTS");
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("RESTAURANT      : " + res[i][j]);
                        System.out.println("DATE ORDERED    : " + d[i][j]);
                        System.out.printf("AMOUNT PAID     : RM%.2f%n", paid[i][j]);
                        System.out.println("-------------------------------------------------------------");
                        check = false;
                    }
                }
            }
            if (check) {
                System.out.println("");
                System.out.println("No history found!");
                System.out.println("");
            }

            c++;

            System.out.print("Enter Y to Continue or N to Quit> ");
            e = scan.next().charAt(0);
            if (e == 'y' || e == 'Y') {
                c--; // to avoid index out of bounds since c++ cause the next ID store in second index of array (1) instead of first (0)
            } else if (e == 'n' || e == 'N') {
                System.out.println("*************************************************************");
                System.out.printf("%40s%n", "---- DONE ----");
                System.out.println("*************************************************************");
                System.out.println("");
                break;

            } else {
                while (e != 'y' && e != 'Y' && e != 'n' && e != 'N') {
                    System.out.println("Invalid");
                    System.out.print("Enter Y to Continue or N to Quit> ");
                    e = scan.next().charAt(0);
                }
            }
        } while (e == 'y' || e == 'Y' || e == 'n' || e == 'N');

    }
}
