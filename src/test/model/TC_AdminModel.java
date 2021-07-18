package test.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import coreModels.beans.Admin;
import coreModels.beans.UserBean;
import coreModels.model.AdminModel;
import coreModels.model.DM.AdminModelDM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

public class TC_AdminModel {
    AdminModel adminModel;
    UserBean user;

    @BeforeEach
    void setUp() throws Exception{
        adminModel = new AdminModelDM();

    }

    private Admin getAdmin(){
        String username= "claudiabuono99@gmail.com";
        String password= "p1234";
        Admin admin= new Admin(username,password);
        return admin;
    }

    @Test
    void doRetrieveByKey() throws SQLException {
        UserBean user= adminModel.doRetrieveByKey("claudiabuono99@gmail.com");
        Admin oracolo= new Admin("claudiabuono99@gmail.com", "p1234");
        assertEquals(oracolo.getLogin(), user.getLogin());
    }
}
