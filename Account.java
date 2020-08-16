import java.util.ArrayList;

public class Account {

   /**
    * The name of the account/Type of the account Ex: checking or savings etc.
    */
   private String name;

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

      //init UUID
      this.uuid = theBank.getNewUserUUID();
   }

   /**
    * get the account ID
    * @return the uuid
    */
   public String getUUID() {
      return this.uuid;
   }

   /**
    * get Summary line for the account at hand.
    * @return the line summary.
    */
   public String getSummaryLine() {
      //get the balance
      double balance = this.getBalance();

      // format the summery line, depending on whether the balance is negative

      if (balance >= 0){
         return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
      }else
         return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
   }

   public double getBalance() {
      double balance = 0;
      for (Transaction t : this.transactions){
         balance  += t.getAmount();
      }
      return balance;
   }

   public void printTransHistory() {
      System.out.printf("\nTransaction history for the account %s \n", this.uuid);
      for (int i = this.transactions.size()-1; i >= 0 ; i--) {
         System.out.printf(this.transactions.get(i).getSummaryLine());
      }
      System.out.println();
   }

   public void addTransactio(double theAmount, String memo) {
      // create the new transaction object and add to the list
      Transaction newTrans = new Transaction(theAmount, memo, this);
      this.transactions.add(newTrans);
   }
}
