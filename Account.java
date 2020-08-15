import java.util.ArrayList;

public class Account {

   /**
    * The name of the account/Type of the account Ex: checking or savings etc.
    */
   private String name;

   /**
    * The current balance of the account. maybe we can calculate it everytime?
    */
   private double balance;

   /**
    * The account ID number.
    */
   private String uuid;

   /**
    * The User object that owns this account. basically the account holder.
    */
   private User holder;

   /**
    * A list of the transactions for this account.
    */
   private ArrayList <Transaction> transactions;

   public Account (String name, User holder, Bank theBank){

      //set the basic info
      this.name = name;
      this.holder = holder;

      //init transactions
      this.transactions = new ArrayList<Transaction>();
   }

   /**
    * get the account ID
    * @return the uuid
    */
   public String getUUID() {
      return this.uuid;
   }
}
