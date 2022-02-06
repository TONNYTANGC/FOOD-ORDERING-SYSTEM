package GROUP2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EditCust {

    private static Scanner scan = new Scanner(System.in);

    public static void modify(int[] ID, String[] names, String[] HomeAddress, String[] EmailAddress, boolean error) {
        char modify = 0;
        int change = 0, cusID = 0;
        boolean newCus = true;

        // Q4:Edit Customer:
        // start
        do {
            try {
                error = false;
                System.out.println("");
                System.out.print("ENTER CUSTOMER ID>  ");
                cusID = scan.nextInt();

                if (!error) {
                    do {
                        for (int a = 0; a < ID.length; a++) {
                            if (cusID == ID[a]) {
                                newCus = false;

                            }
                        }
                        if (newCus == true) {
                            System.out.println("INVALID CUSTOMER ID!");
                            System.out.print("Please enter customer ID again> ");
                            cusID = scan.nextInt();
                        }
                    } while (newCus);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter integer only");
                scan.nextLine();
                error = true;
            } catch (Exception e) {
                System.out.println("Please enter integer only");
                scan.nextLine();
                error = true;
            }
        } while (error);
        
        for (int a = 0; a < ID.length; a++) {
            if (cusID == ID[a]) {
                System.out.println("=============================================================");
                System.out.println("CUSTOMER DETAILS:");
                System.out.println("NAME            : " + names[a]);
                System.out.println("HOME ADDRESS    : " + HomeAddress[a]);
                System.out.println("EMAIL ADDRESS   : " + EmailAddress[a]);
                System.out.println("=============================================================");
                change = SelectSection(error);

                while (change < 1 || change > 3) {
                    System.out.print("PLEASE ENTER WTH NUMBER 1-3 only.");
                    change = SelectSection(error);
                }
                InputNew(change, names, HomeAddress, EmailAddress, a);
                modify = success(cusID, names, HomeAddress, EmailAddress, modify, a);
                do {
                    if (modify != 'y' && modify != 'Y' && modify != 'n' && modify != 'N') {
                        System.out.print("Invalid! Please enter only m or q.");
                        modify = scan.next().charAt(0);
                    }
                    if (modify == 'n' || modify == 'N') {
                        break;
                    }
                } while (modify != 'y' && modify != 'Y' && modify != 'n' && modify != 'N');
            }
        }
    }

    public static int SelectSection(boolean error) {
        int change = 0;
        do {
            error = false;
            try {
                System.out.println("*************************************************************");
                System.out.println("=============================================================");
                System.out.println("|name                                                     1 |");
                System.out.println("|home address                                             2 |");
                System.out.println("|email address                                            3 |");
                System.out.println("=============================================================");
                System.out.println("");
                System.out.print("ENTER THE SECTION THAT YOU WANT TO CHANGE (1-3): ");// choose either one by tping 1 2 3

                change = scan.nextInt();
                return change;
            } catch (InputMismatchException e) {
                System.out.print("Please enter only with integer.");
                scan.nextLine();
                error = true;
            } catch (Exception e) {
                System.out.println("Please enter only with integer.");
                scan.nextLine();
                error = true;
            }
        } while (error);
        return change;
    }

    public static void InputNew(int change, String[] N, String[] H, String[] E, int a) {
        if (change == 1) {
            System.out.print("ENTER THE NEW CUSTOMER NAME>  ");
            String name = scan.next() + scan.nextLine();
            N[a] = name;
        } else if (change == 2) {
            System.out.print("ENTER THE NEW CUSTOMER HOME ADDRESS> ");
            String address = scan.next() + scan.nextLine();
            H[a] = address;
        } else if (change == 3) {
            System.out.print("ENTER THE NEW CUSTOMER EMAIL ADDRESS> ");
            String email = scan.next() + scan.nextLine();
            E[a] = email;
        }
    }

    public static char success(int cusID, String[] N, String[] H, String[] E, char M, int a) {
        System.out.println("=============================================================");
        System.out.println("Congratulation! Details of ID " + cusID + " have been change successfully.");
        System.out.println("=============================================================");
        System.out.println("Latest Details : ");
        System.out.println("NAME           : " + N[a]);
        System.out.println("HOME ADDRESS   : " + H[a]);
        System.out.println("EMAIL ADDRESS  : " + E[a]);
        System.out.println("=============================================================");
        System.out.print("ENTER Y TO CONTINUE MODIFY OR N TO QUIT> ");// ask whether to change again
        M = scan.next().charAt(0);
        return M;
    }

}
