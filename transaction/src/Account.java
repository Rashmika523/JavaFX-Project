public class Account {

    private String account_id;
    private String name;
    private double amount;

    public Account() {
    }

    public Account(String account_id, String name, double amount) {
        this.account_id = account_id;
        this.name = name;
        this.amount = amount;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
