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
   }
}
