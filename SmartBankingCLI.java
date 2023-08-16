import java.util.Arrays;
import java.util.Scanner;

public class SmartBankingCLI{
    private static Scanner scanner= new Scanner(System.in) ;
    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking";
        final String CREATE_ACCOUNT = "Create New Account";
        final String DEPOSITS = "Deposit";
        final String WITHDRAW = "WITHDRAW";
        final String TRANSFER = "🖨 TRANSFER";
        final String CHECK_BALANCE = "Check Balance" ;
        final String DELETE_ACCOUNT = "Delete account" ;

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String screen = DASHBOARD;
        String[][] customerDetails = new String[0][] ;

        do {
            final String APP_TITLE = String.format("%s%s%s",COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch(screen){
                case DASHBOARD :
                    System.out.println("1] Create new Account");
                    System.out.println("2] Deposits");
                    System.out.println("3] Withdraw");
                    System.out.println("4] Transfer");
                    System.out.println("5] Check balance");
                    System.out.println("6] Delete Account");
                    System.out.println("7] Exit");
                    System.out.print("\n"+"Enter option :");
                    int option = scanner.nextInt() ;
                    scanner.nextLine();
                    switch(option){
                        case 1 : screen = CREATE_ACCOUNT ; break ;
                        case 2 : screen = DEPOSITS ; break ;
                        case 3 : screen = WITHDRAW ; break ;
                        case 4 : screen = TRANSFER ; break ;
                        case 5 : screen = CHECK_BALANCE ; break ;
                        case 6 : screen = DELETE_ACCOUNT ; break ;
                        case 7 : System.exit(0);
                        default : continue ;
                    }
                    break;
                case CREATE_ACCOUNT :
                    System.out.printf("\tNew Customer ID: SDB-%05d\n", (customerDetails.length + 1));
                    boolean valid;
                    String name;
                    do{
                        valid = false;
                        System.out.print("\tEnter Customer Name: ");
                        name = scanner.nextLine().strip();
                        if (name.isBlank()){
                            System.out.printf(ERROR_MSG,"Name cannot be empty!!");
                            valid = true;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) || 
                                Character.isSpaceChar(name.charAt(i))) ) {
                                System.out.printf("\t%sInvalid Name%s\n", COLOR_RED_BOLD, RESET);
                                valid = true;
                                break;
                            }
                        }
                    }while(valid);
                    double initialDeposit ;
                    do {
                        valid = false ;
                        System.out.print("\tEnter Initial Deposit :");
                        initialDeposit = scanner.nextDouble() ;
                        scanner.nextLine() ;
                        if(initialDeposit<=5000){
                            System.out.printf(ERROR_MSG,"Iniial ammount should be higher than 5000");
                            valid = true ;
                            continue;
                        }
                        
                    } while (valid) ;
                    String newCustomerDetails[][] = new String[customerDetails.length + 1][3] ;
                    for( int i = 0 ; i < customerDetails.length ; i++ ){
                        newCustomerDetails[i] = customerDetails[i] ;
                    }
                    newCustomerDetails[newCustomerDetails.length - 1][0] = String.format("SDB-%05d", newCustomerDetails.length) ;
                    newCustomerDetails[newCustomerDetails.length - 1][1] = name ;
                    newCustomerDetails[newCustomerDetails.length - 1][2] = initialDeposit + "" ;
                    customerDetails = newCustomerDetails ;
                   
                    System.out.printf("\t%sID : %s Name : %s Added Successfully !!!%s\n",COLOR_GREEN_BOLD, customerDetails[customerDetails.length-1][0], customerDetails[customerDetails.length - 1][1], RESET); 
                    System.out.print("\tDo you want to add another{Y/N} :");
                    if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
                case DEPOSITS :
            }
        }while (true);
    }
}