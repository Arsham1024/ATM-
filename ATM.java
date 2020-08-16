import java.util.Scanner;

public class ATM {
   public static void main(String[] args) {
      // init Scanner
      Scanner sc = new Scanner( (System.in));

      //init bank
      Bank theBank = new Bank("The Bank of Ser Arsham");

      //add a user to the bank (Also creates a savings account)
      User user1 = theBank.addUser("Tim", "Cook" , "1976");

      //Add checking account for our user
      Account checkingAccountUser1 = new Account("Checking", user1, theBank);
      user1.addAccount(checkingAccountUser1);
      theBank.addAccount(checkingAccountUser1);

      User currentUser;
      while(true){
         //Stay in the login prompt until successful login.
         currentUser = ATM.mainMenuPrompt (theBank, sc);

         //Stay in the main menu until user quits
         ATM.printUserMenu(currentUser,sc);
      }
   }

   public static void printUserMenu(User currentUser, Scanner sc) {
      //print a summary of user's accounts
      currentUser.printAccountsSummary();

      //init
      int choice = -1;

      //user Menu
      do{
         System.out.printf("Welcome %s, what would you like to do?", currentUser.getFirstName());

         System.out.println("1- transaction history");
         System.out.println("2- Withdraw");
         System.out.println("3- Deposit");
         System.out.println("4- Transfer");
         System.out.println("5- Quit\n");

         System.out.print("Enter choice: ");

         //Invalid input check
         try{
            choice = Integer.parseInt(sc.nextLine());
         }catch (NumberFormatException e){
            System.out.println("Invalid choice enter a valid choice (1-6)");
         }
         if (choice<1 || choice>5){
            System.out.println("Invalid choice enter a valid choice (1-6)");
         }
      }while (choice<1 || choice >5);

      //process the choice
      switch (choice){
         case 1:
            ATM.showTransactionHistory (currentUser, sc);
            break;
         case 2:
            ATM.withdrawFunds (currentUser, sc);
            break;
         case 3:
            ATM.depositFunds(currentUser, sc);
            break;
         case 4:
            ATM.transferFunds(currentUser, sc);
            break;

      }
      //redisplay the menu unless Quit
      //Change this to a simple do while loop later
      if (choice != 5){
         ATM.printUserMenu(currentUser, sc);
      }


   }

   private static void transferFunds(User currentUser, Scanner sc) {
      //inits
      int fromAcct;
      int toAcct;
      double amount;
      double accBall;

      //get the account to transfer from
      do {
         System.out.printf("Enter the number (1-%d) of the acoount \n"
                           + "to transfer from: ");
         //the accounts are numbered naturally.
         fromAcct = sc.nextInt()-1 ;
         if (fromAcct<0 || fromAcct>= currentUser.numAccounts()){
            System.out.println("Invalid account. please try again.");
         }
      }while(fromAcct<0 || fromAcct>= currentUser.numAccounts());

      accBall = currentUser.getAccBalance(fromAcct);

      //get the account to transfer TO.
      do {
         System.out.printf("Enter the number (1-%d) of the acoount \n"
            + "to transfer to: ");
         //the accounts are numbered naturally.
         toAcct = sc.nextInt()-1 ;
         if (toAcct<0 || toAcct>= currentUser.numAccounts()){
            System.out.println("Invalid account. please try again.");
         }
      }while(toAcct<0 || toAcct>= currentUser.numAccounts());

      //get the amount to transfer

      do{
         System.out.printf("Enter the amount to transfer (Max $%.02f): $", accBall);
         amount = sc.nextDouble();
         if (amount<0 || amount>accBall){
            System.out.println("The amount is not valid!");
         }
      }while(amount<0 || amount>accBall);

      //do the transfer
      currentUser.addAcctTransaction (fromAcct, -1*amount,
                                     String.format("Transfer to account %s", currentUser.getAccUUID(toAcct)));
      currentUser.addAcctTransaction (toAcct, amount,
                                     String.format("Transfer to account %s", currentUser.getAccUUID(fromAcct)));

   }

   private static void depositFunds(User currentUser, Scanner sc) {
   }

   private static void withdrawFunds(User currentUser, Scanner sc) {
   }

   private static void showTransactionHistory(User currentUser, Scanner sc) {
      int theAccount;
      //get the account whose transactions history to look at
      do{
         System.out.printf("Enter the number (1-%d) of the account\n"
                           + "to display: ", currentUser.numAccounts());
         theAccount = sc.nextInt()-1;
         if(theAccount<0 || theAccount>= currentUser.numAccounts()){
            System.out.println("Invalid account. please try again.");
         }
      } while (theAccount<0 || theAccount>= currentUser.numAccounts());
      //print the transaction history
      currentUser.printAccTransactionHistory (theAccount);
   }

   /**
    * Print the ATM's main menu
    * @param theBank the Bank object which holds the account to use.
    * @param sc      the Scanner object to use for user input
    * @return        the authenticated user object
    */
   public static User mainMenuPrompt(Bank theBank, Scanner sc) {
      String userID;
      String pin;
      User authUser;

      // prompt user for user ID/pin until a correct one is reached.
      do {
         System.out.printf("\n\nwelcome to %s\n\n" , theBank.getName());
         System.out.print("Enter user ID: ");
         userID = sc.nextLine();
         System.out.printf("Enter pin: ");
         pin = sc.nextLine();

         // get the user object corresponding the above ID/pin
         authUser = theBank.userLogin(userID, pin);
         if (authUser == null)
            System.out.println("Either user ID or pin is wrong");
      }while (authUser == null); //continue loopin until successful login.

      return authUser;
   }

}
