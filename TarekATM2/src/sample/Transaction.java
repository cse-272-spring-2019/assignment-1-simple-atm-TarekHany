package sample;

public class Transaction {
    long amount;
    String type;
    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String printTransaction() {
        return (type + ": "+ Long.toString(amount));
    }
}

