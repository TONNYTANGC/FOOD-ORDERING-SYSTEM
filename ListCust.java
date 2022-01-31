package GAsg;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ListCust {

    public static void listcustomers(int numOfOrders, int[] ID, int[] orderID, String[] testname, String[] testemail) {
        Scanner scan = new Scanner(System.in);
        char list = 0;
        boolean error;
        int pastorder = 0;
        // Q5:List Customers:
        // ask
        // to
        // show
        // or
        // not
        // situation of list =y

        do {
            do {
                try {
                    error = false;
                    System.out.print("PLEASE ENTER THE NUMBER OF ORDER THAT YOU WISH TO KNOW>");
                    pastorder = scan.nextInt();
                    // key in user id when order so it can record here
                    // name is same sequence as user id in order id array
                } catch (InputMismatchException e) {
                    System.out.println("Please enter only with integer.");
                    scan.nextLine();
                    error = true;
                } catch (Exception e) {
                    System.out.println("Please enter only with integer.");
                    scan.nextLine();
                    error = true;
                }
            } while (error);
            int total = 0; // calculate how many users have order more than that, display no user if total
            // is 0

            for (int b = 0; b < numOfOrders; b++) { // loop for every user id that input when order
                int k = 1; // k is count for the number of past order for each user
                int e = b;
                boolean repeat = false;

                repeat = repeatID(b, orderID, repeat);
                k = check(e, orderID, b, k);

                if (repeat == false) { // if any previous user id is repeat with current user id, the following
                    // statement wont continue.
                    if (k > pastorder) { // if number of k more than value input then diaplay
                        System.out.println("");
                        System.out.println("CUSTOMER NAME         : " + testname[b]);
                        System.out.println("CUSTOMER ID           : " + orderID[b]);
                        for (int i = 0; i < ID.length; i++) {
                            if (orderID[b] == ID[i]) {
                                System.out.println("CUSTOMER EMAIL ADDRESS: " + testemail[i]);
                                break;
                            }
                        }
                        System.out.println("");
                        total++;
                    }
                }

            }
            if (total == 0) {
                System.out.println("THERE IS NO CUSTOMER ORDER MORE THAN " + pastorder + " TIMES.");
                System.out.println("");
            }

            System.out.print("ENTER Y TO CONTINUE CHECK OR N TO QUIT> ");
            list = scan.next().charAt(0);

            if (list == 'y' || list == 'Y') {
                System.out.println("");
            } else if (list == 'n' || list == 'N') {
                System.out.println("=============================================================");
                System.out.println("");
                break;
            } else {
                while (list != 'y' && list != 'Y' && list != 'n' && list != 'N') {
                    System.out.println("INVALID!");
                    System.out.print("ENTER Y TO CONTINUE CHECKING OR N TO QUIT> ");
                    list = scan.next().charAt(0);
                }
            }
        } while (list == 'y' || list == 'Y' || list == 'n' || list == 'N');

    }

    public static boolean repeatID(int b, int[] orderID, boolean repeat) {
        if (b >= 1) { // when the number of user is bigger than 1,it will check the previous user id,so it wont count the repeat user id
            for (int d = 0; d < b; d++) {
                if (orderID[b] == orderID[d]) { // if any repeat user id is found, then it is repeat, wont display output on below
                    repeat = true;
                }
            }
        }
        return repeat;
    }

    public static int check(int e, int[] orderID, int b, int k) {
        for (int c = e + 1; e < orderID.length - 1; e++) { // loop for the user id with every user id that is after the user id
            c = e + 1;// c start from b+1 but if user c+b+1,value of b will change after this loop, so e is use. 
            //every loop, c increase by 1 but it wont add to c automatically,so manually add a c=e+1 here.
            if (orderID[b] == orderID[c]) { // the user id check with every user id behind,record in k if repeat
                k++;
            }
        }
        return k;
    }

}
