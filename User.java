import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {

   /**
    * The first name of the user.
    */
   private String firstName;

   /**
    * the last name of the user.
    */
   private String lastName;

   /**
    * unique user identifier / ID number of the user.
    */
   private String uuid;

   /**
    * the MD5 hash of the user's pin number
    */
   private byte pinHash[];

   /**
    * The list of accounts for this user.
    */
   private ArrayList <Account> accounts;


   /**
    * Create a new User
    * @param firstName the user's first name
    * @param lastName  the user's last name
    * @param pin       the user's account pin number
    * @param theBank   the Bank object that the user is a customer of
    */
   public User (String firstName, String lastName, String pin, Bank theBank){
      // set user's info
      this.firstName = firstName;
      this.lastName = lastName;

      // we are going to hash pin using MD5 algorithm. for security.
      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
         //getting the bytes and digesting them through MD5 algorithm.
         this.pinHash = md.digest(pin.getBytes());
      } catch (NoSuchAlgorithmException e) {
         System.out.println("error, caught NoSuchAlgorithmException" + e.getCause());
         e.printStackTrace();
         System.exit(1);
      }

      // get a new, universal ID for the user
      this.uuid = theBank.getNewUserUUID();

      //create empty list of accounts
      this.accounts = new ArrayList<Account>();

      //print log message
      System.out.printf("New user %s, %s with ID %s created.\n",
                        lastName, firstName, this.uuid);



   }

   /**
    * Add an account for the user
    * @param account the account to add
    */
   public void addAccount(Account account) {
      this.accounts.add(account);
   }

   /**
    * get the UUID
    * @return uuid;
    */
   public String getUUID() {
      return this.uuid;
   }

   /**
    * check if a given pin matches the true user pin
    * @param pin  the pin to check
    * @return     boolean value if the pin is valid or not
    */
   public boolean validatePin(String pin) {
      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
         return MessageDigest.isEqual(md.digest(pin.getBytes()),this.pinHash);
      } catch (NoSuchAlgorithmException e) {
         System.out.println(e.getCause());
         e.printStackTrace();
         System.exit(1);
      }
      return false;
   }
}
