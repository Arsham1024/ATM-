import java.util.ArrayList;
import java.util.Random;

public class Bank {

   /**
    * the name of the bank
    */
   private String name;

   /**
    * the list of all users/customers of the bank
    */
   private ArrayList <User> users;

   /**
    * the list of all the accounts of the customers
    * It is convenient to have this instance even though you can get it other ways.
    */
   private ArrayList <Account> accounts;


   /**
    * Getter methods in this section.
    * get name.
    * @return the name of the bank
    */
   public String getName() {
      return name;
   }



   /**
    * Create a new bank with empty list of accounts and users.
    * @param name name of the bank
    */
   public Bank (String name){
      this.name = name;
      this.users = new ArrayList<User>();
      this.accounts = new ArrayList<Account>();
   }




   /**
    *
    * @return
    */
   public String getNewUserUUID() {
      // inits
      String uuid;
      Random rn = new Random();
      int len = 6;
      boolean notUnique;

      // continue looping until we get a unique ID
      do {
         uuid = "";
         for (int i = 0; i<len; i++)
            uuid += ((Integer) rn.nextInt(10)).toString();

         //Check to make sure it is unique now
         notUnique = false;
         for (User u: this.users) {
            if (uuid.compareTo(u.getUUID())==0){
               notUnique =true;
               break;
            }
         }
      }while (notUnique);
      return uuid ;
   }

   /**
    * Generate a new universally unique ID for an account.
    * @return the uuid
    */
   public String getNewAccountUUID(){
      // inits
      String uuid;
      Random rn = new Random();
      int len = 10;
      boolean notUnique;

      // continue looping until we get a unique ID
      do {
         uuid = "";
         for (int i = 0; i<len; i++)
            uuid += ((Integer) rn.nextInt(10)).toString();

         //Check to make sure it is unique now
         notUnique = false;
         for (Account a: this.accounts) {
            if (uuid.compareTo(a.getUUID())==0){
               notUnique =true;
               break;
            }
         }
      }while (notUnique);
      return uuid ;
   }


   /**
    * Add an account
    * @param account The account to add
    */
   public void addAccount(Account account) {
      this.accounts.add(account);
   }

   /**
    * Create a new user of the bank
    * @param firstName the user's first name
    * @param lastName  the user's last name
    * @param pin       the user's pin number
    * @return          the new user object
    */
   public User addUser(String firstName, String lastName, String pin){
      // Create a new User Object and add to the list.
      User newUser = new User (firstName, lastName, pin, this);
      this.users.add(newUser);

      //Create a savings account
      Account newAccount = new Account("Savings" , newUser, this);
      newUser.addAccount (newAccount);
      this.addAccount (newAccount);

      return newUser;
   }

   /**
    *
    * @param userID
    * @param pin
    * @return
    */
   public User userLogin (String userID, String pin){

      // Search through the list of User to see if we can find the new user ID.

      for (User u: this.users) {

         //Check the user ID if correct
         if (u.getUUID().compareTo(userID) == 0
                            && u.validatePin (pin))
            return u;
      }

      // either the user doesn't exist of the pin is wrong.
      return null;
   }













}
