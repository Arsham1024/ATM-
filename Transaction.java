
import java.util.Date;

public class Transaction {

   /**
    * The amount of this transaction
    */
   private double amount;

   /**
    * the time of this transaction
    */
   private Date timeStamp;

   /**
    * a memo or brief explanation for this transaction
    */
   private String memo;

   /**
    * the account in which the transacting was performed
    */
   private Account inAccount;


   /**
    * Two argument constructor to make a new transaction
    * @param amount the amount of the transaction
    * @param inAccount the account the transaction belongs to
    * calls the three argument constructor
    */
   public Transaction (double amount, Account inAccount){
      this (amount, "", inAccount);
   }

   /**
    * create a new transaction
    * @param amount the amount of the transaction
    * @param memo The memo for the transaction
    * @param inAccount the account the transaction belongs to
    */
   public Transaction (double amount, String memo, Account inAccount){
      this.amount = amount;
      this.memo = memo;
      this.inAccount = inAccount;
      //we create the timeStamp
      this.timeStamp = new Date ();
   }
}
