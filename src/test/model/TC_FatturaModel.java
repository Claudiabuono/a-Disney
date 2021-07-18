package test.model;

import coreModels.beans.Adress;
import coreModels.beans.FatturaBean;
import coreModels.beans.Registered;
import coreModels.beans.UserBean;
import coreModels.model.AdminModel;
import coreModels.model.DM.AdminModelDM;
import coreModels.model.DM.FatturaModelDM;
import coreModels.model.FatturaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class TC_FatturaModel {
    FatturaModel fatturaModel;

    @BeforeEach
    void setUp() throws Exception{
        fatturaModel = new FatturaModelDM();
    }

    private Registered getUtente(){
        String username= "matteo@alice.it";
        String password= "matteo";
        String nome= "matteo";
        String cognome= "russo";
        Registered utente= new Registered(nome,cognome,username,password);
        return utente;
    }
/*
    private FatturaBean getFattura(){
       GregorianCalendar date= '2019-06-20';
        int cod= 1;
        FatturaBean fattura= new FatturaBean(getUtente(), date, cod);
        return fattura;
    }
*/
    @Test
    void doSave (FatturaBean f) throws Exception{

    }
}
