import java.util.Date;

public class Payment {

    private String pay_id;
    private Date pay_date;
    private double payment;

    public Payment() {
    }

    public Payment(String pay_id, Date pay_date, double payment) {
        this.pay_id = pay_id;
        this.pay_date = pay_date;
        this.payment = payment;
    }

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }

    public Date getPay_date() {
        return pay_date;
    }

    public void setPay_date(Date pay_date) {
        this.pay_date = pay_date;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
