package marcosoft;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import marcosoft.usd.Category;
import marcosoft.usd.Usd;
import marcosoft.usd.UsdContact;
import marcosoft.usd.UsdIncident;
import marcosoft.usd.UsdObjectCreationResponse;

public class UsdTest {

    @Test
    @Ignore
    public void should_Return_CorrectResponse(){

        //given
        Usd usd = new Usd();

        //when
        UsdIncident usdIncident = null;
        UsdContact incidentCustomer = new UsdContact("U'2C975EEBC83E224C9F7A8868415036D9'");
        Category incidentCategory = new Category("USD.Desenvolvimento.Views");
        usdIncident = new UsdIncident(incidentCustomer, "Resumo vindo do Java", "Descrição vindo do Java", incidentCategory);
        UsdObjectCreationResponse usdResponse = usd.createObject(usdIncident, "in");

        //then
        assertEquals("in", usdResponse.object);

    }
    
}
