package sample;

public class Account {
    long balance=0;
    ErrorWindow errorWindow = new ErrorWindow();
    public boolean withdraw(long amount)
    {
        if(balance>=amount) {
            balance = balance - amount;
            return true;
        }
        else
        {
            errorWindow.displayErrorWindow("Withdraw Failed","Your balance is not enough");
            return false;
        }
    }
    void deposit(long amount)
    {
        balance = balance + amount;
    }
    long getBalance()
    {
        return balance;
    }




}
