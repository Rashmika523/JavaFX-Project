import java.sql.SQLException;
import java.util.Date;

public class Initializer {

    public static void main(String[] args) {
        CrudManager  crudManager  = new CrudManager();

        /*Account account =  new Account("A-1","kamal",50000);
        Payment payment = new Payment("P-1",new Date(),25000);*/

        Account account2 =  new Account("A-2","Amal",75000);
        Payment payment2 = new Payment("P-2",new Date(),25000);

        try {
            System.out.println(
                    crudManager.saveData(account2,payment2)
            );
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

}
