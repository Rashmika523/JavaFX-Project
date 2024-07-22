import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudManager {

    public boolean saveData(Account account,Payment payment) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement1 = connection.prepareStatement(
                "INSERT INTO account VALUES (?,?,?)"
        );
        preparedStatement1.setString(1,account.getAccount_id());
        preparedStatement1.setString(2,account.getName());
        preparedStatement1.setDouble(3,account.getAmount());

        boolean isSaved = preparedStatement1.executeUpdate() > 0;

        if(isSaved){
            PreparedStatement preparedStatement2 = connection.prepareStatement(
                    "INSERT INTO payment VALUES (?,?,?,?)"
            );

            preparedStatement2.setString(1,payment.getPay_id());
            preparedStatement2.setObject(2,payment.getPay_date());
            preparedStatement2.setDouble(3,payment.getPayment());
            preparedStatement2.setString(4,account.getAccount_id());

            return preparedStatement2.executeUpdate() > 0;

        }else {
            return false;
        }

    }

}
