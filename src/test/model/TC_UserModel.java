package test.model;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import coreModels.beans.Registered;
import coreModels.beans.UserBean;
import coreModels.model.DM.AdminModelDM;
import coreModels.model.DM.RegisteredModelDM;
import coreModels.model.RegisteredModel;
import coreModels.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
public class TC_UserModel {
    UserModel userReg, userAdmin;

    @BeforeEach
    void setUp() throws Exception {
        userReg = new RegisteredModelDM();
        userAdmin = new AdminModelDM();
    }
}
