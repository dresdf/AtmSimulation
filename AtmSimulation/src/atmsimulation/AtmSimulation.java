package atmsimulation;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * main class for ATM simulation program
 *
 * @author Dragos Secara
 */
public class AtmSimulation {

    //constants enforcing account rules
    public static final int MAX_INPUT_ATTEMPTS = 3;
    public static final int MIN_PASSWORD_LENGTH = 4;

    //ArrayLists to simulate database
    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<String> passwords = new ArrayList<>();
    public static ArrayList<Integer> sumsInAcounts = new ArrayList<>();

    //variables to contain current user's credentials
    private String clientName, clientPassword;
    private int clientID, loginAttempts;

    /**
     * main method of the ATM simulation program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*instance of class used to avoid having all methods static 
         and create demo accounts */
        AtmSimulation atm = new AtmSimulation();

        //start application
        atm.start();

    }

    /**
     * Start the ATM options to log into existing account, create a new account, or exit program
     */
    public void start() {
        Scanner sc = new Scanner(System.in);

        //display menu
        System.out.println("*************************************************");
        System.out.println("Welcome");
        System.out.println("To log into your account -> input 1 or login");
        System.out.println("To sign up for new account -> input 2 or signup");
        System.out.println("To exit the program -> input 3 or exit");
        System.out.println("*************************************************");
        System.out.print("Your choice is: ");

        //receive user input and take required action
        String choice = sc.nextLine().toLowerCase();
        switch (choice) {
            case "1"://log in
            case "login":
                login();
                break;
            case "2"://sign up
            case "signup":
                signup();
                break;
            case "3"://exit program
            case "exit":
                exitProgram();
                break;
            default://restart method
                System.out.println("Unknown command. Try again");
                start();
                break;
        }
    }

    /**
     * Verifies name and password. Starts the main menu or closes the program
     */
    private void login() {
        Scanner sc = new Scanner(System.in);

        //check if the maximum number of failed attempts has been reached
        if (loginAttempts == MAX_INPUT_ATTEMPTS) {
            System.out.println("Access denied!Program closing");
            exitProgram();
        }

        //receive and check name and password
        System.out.println("Input your name: ");
        clientName = sc.nextLine().toLowerCase();
        System.out.println("Input your password: ");
        clientPassword = sc.nextLine();

        for (String item : names) {
            if (item.equalsIgnoreCase(clientName)) {
                clientID = names.indexOf(clientName);
                if (passwords.get(clientID).equals(clientPassword)) {
                    mainMenu();
                }
            }
        }

        System.out.println("Name and password do not match!");
        loginAttempts++;
        System.out.println((MAX_INPUT_ATTEMPTS - loginAttempts) + " attempts remaining.\n");
        login();

    }

    /**
     * Creates a new account and inserts data into database
     */
    private void signup() {
        Scanner sc = new Scanner(System.in);

        //receive name
        System.out.println("Input your name: ");
        clientName = sc.nextLine().toLowerCase();
        if (names.contains(clientName)) {
            System.out.println("Name is unavailable.");

            //retry option
            if (repeatOperation()) {
                signup();
            } else {
                start();
            }
        }

        //receive password
        System.out.println("Input your password (at least 4 characters): ");
        clientPassword = sc.nextLine();
        if (clientPassword.length() < MIN_PASSWORD_LENGTH) {
            System.out.println("Password not long enough.");

            //retry option
            if (repeatOperation()) {
                signup();
            } else {
                start();
            }
        }

        //add new user to database
        names.add(clientName);
        passwords.add(clientPassword);
        sumsInAcounts.add(0);
        System.out.println("\nCongratulations! Your account has been created.");

        //return to start. new users must re-log
        start();
    }

    /**
     * Changes the password for the current user
     */
    private void changePassword() {
        Scanner sc = new Scanner(System.in);
        String newPassword;

        System.out.println("Input current password: ");
        clientPassword = sc.nextLine();

        if (passwords.get(clientID).equals(clientPassword)) {

            //receive new password
            System.out.println("Input new password (at least 4 characters): ");
            newPassword = sc.nextLine();

            //check new password
            if (newPassword.length() < MIN_PASSWORD_LENGTH) {//failure. Password too short
                System.out.println("Password not long enough.");

                //retry option
                if (repeatOperation()) {
                    signup();
                } else {
                    start();
                }
            } else {
                //verify password
                System.out.println("Verifiy new password: ");
                if (newPassword.equals(sc.nextLine())) {
                    passwords.add(clientID, newPassword);
                    mainMenu();
                } else {//failure. Password mismatch
                    System.out.println("Passwords do not match.");

                    //retry option
                    if (repeatOperation()) {
                        changePassword();
                    } else {
                        mainMenu();
                    }
                }
            }
        } else {

            //retry option
            if (repeatOperation()) {
                changePassword();
            } else {
                mainMenu();
            }
        }
    }

    /**
     * Clears current user credentials and displays the login/sign up option does not stop the program
     */
    private void logout() {

        //clear temporary containers
        clientID = 0;
        clientName = null;
        clientPassword = null;
        System.out.println("You have been logged out of your account.\nHave a nice day!");

        //return to start menu
        start();
    }

    /**
     * Clears current user credentials and closes the program
     */
    private void exitProgram() {

        //clear temporary containers
        clientID = 0;
        clientName = null;
        clientPassword = null;
        System.out.println("Program will close.\nThank you for your business and come back soon!");

        //close the program
        System.exit(0);
    }

    /**
     * Central decision hub for the ATM options: account summary, cash withdraw and deposit, log out, exit
     */
    private void mainMenu() {
        Scanner sc = new Scanner(System.in);

        //display options menu
        System.out.println("**************************************");
        System.out.println("Account options:");
        System.out.println("For account summary -> input 1 or summary");
        System.out.println("For cash withdraval -> input 2 or withdraw");
        System.out.println("For cash deposit -> input 3 or deposit");
        System.out.println("To log out of your account -> input 4 or logout");
        System.out.println("To change your account's password -> input 5 or change");
        System.out.println("To exit the program -> input 6 or exit");
        System.out.println("**************************************");
        System.out.print("Your choice is: ");

        //receive user input and take required action
        String choice = sc.nextLine().toLowerCase();
        switch (choice) {
            case "1"://show the account summary
            case "summary":
                accountSummary();
                break;
            case "2"://start cash withdrawal process
            case "withdraw":
                cashWithdrawal();
                break;
            case "3"://start cash deposit process
            case "deposit":
                cashDeposit();
                break;
            case "4"://logout user
            case "logout":
                logout();
                break;
            case "5"://change password
            case "change":
                changePassword();
                break;
            case "6"://exit program
            case "exit":
                exitProgram();
                break;
            default://incorrect input. restart method
                System.out.println("Unknown command. Try again");
                mainMenu();
                break;
        }
    }

    /**
     * Displays the name and total cash amount for the current user
     */
    private void accountSummary() {
        Scanner sc = new Scanner(System.in);

        //get data from database
        String name = names.get(clientID);
        int cashAmount = sumsInAcounts.get(clientID);
        System.out.println("Account owner: " + name + "\nTotal funds: " + cashAmount + " credits");

        //wait for user input before returning to Main Menu
        System.out.print("Press Enter to return to Main Menu.");
        sc.nextLine();
        mainMenu();
    }

    /**
     * Subtracts a given amount from the current user's cash total.Does not allow negative input or negative result
     */
    private void cashWithdrawal() {
        Scanner sc = new Scanner(System.in);
        int cashAmount = sumsInAcounts.get(clientID);

        System.out.print("Input withdrawal amount: ");
        int withdraw = checkNumber(sc.nextLine());
        if (withdraw < 0) {//input is negative or not a number

            //retry option
            if (repeatOperation()) {
                cashWithdrawal();
            } else {
                mainMenu();
            }
        } else {//input is correct
            if (withdraw < cashAmount) {

                //set new value in database. Print new account balance.Return to main
                sumsInAcounts.set(clientID, (cashAmount - withdraw));
                System.out.println("Amount withdrawn: " + withdraw + " credits" + "\nTotal Funds: " + sumsInAcounts.get(clientID));
                mainMenu();
            } else {//input is bigger than available amount
                System.out.println("Insufficient funds.");

                //retry option
                if (repeatOperation()) {
                    cashWithdrawal();
                } else {
                    mainMenu();
                }
            }
        }
    }

    /**
     * Adds a given amount to the current user's cash total. Does not allow negative input
     */
    private void cashDeposit() {
        Scanner sc = new Scanner(System.in);
        int cashAmount = sumsInAcounts.get(clientID);

        System.out.print("Input deposit amount: ");
        int deposit = checkNumber(sc.nextLine());
        if (deposit < 0) {//input is negative or not a number

            //retry option
            if (repeatOperation()) {
                cashWithdrawal();
            } else {
                mainMenu();
            }
        } else {//input is correct
            sumsInAcounts.set(clientID, (cashAmount + deposit));
            System.out.println("Amount deposited: " + deposit + " credits" + "\nTotal Funds: " + sumsInAcounts.get(clientID));
            mainMenu();
        }
    }

    /**
     * Provide the user with the option to repeat the operation or quit to previous state
     *
     * @return true for repeat, false for quit
     */
    private boolean repeatOperation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Try again?(Y/N): ");
        switch (sc.nextLine().toUpperCase()) {
            case "Y":
                return true;
            case "N":
                return false;
            default:
                System.out.println("Invalid choice. Input Y or N.");
                return repeatOperation();
        }
    }

    /**
     * Checks if a string is a positive integer
     *
     * @param numberStr string to be checked
     * @return the integer equivalent of numberStr or -1 if the string represents a negative number or is not an integer
     */
    private int checkNumber(String numberStr) {
        int result = -1;
        try {
            result = Integer.parseInt(numberStr);
            if (result < 0) {
                System.out.println("The amount you have entered is not a positive integer.");
                return -1;
            } else {
                return result;
            }
        } catch (NumberFormatException e) {
            System.out.println("The amount you have entered is not a valid integer.");
            return -1;
        }
    }

    /**
     * Default constructor. Creates 3 accounts for testing
     */
    public AtmSimulation() {

        names.add(0, "ION");
        names.add(1, "DAN");
        names.add(2, "RADU");
        passwords.add(0, "abcd");
        passwords.add(1, "efgh");
        passwords.add(2, "ijkl");
        sumsInAcounts.add(0, 100);
        sumsInAcounts.add(1, 25);
        sumsInAcounts.add(2, 40);
    }
}
