package marcosoft;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UsdJsonFormatterTest {
    
    @Test
    public void should_Return_TicketNumber(){

        UsdJsonFormatter usdJsonFormatter = new UsdJsonFormatter();

        //given
        String jsonResponse = "{\"@id\": 9690500,\"@REL_ATTR\": \"cr:9690500\", \"@COMMON_NAME\": 9286798, \"link\": {\"@href\": \"http://usd.des.sicredi.net:8050/caisd-rest/cr/9690500\",\"@rel\": \"self\"}}";

        //when
        int ticketNumber = usdJsonFormatter.getTicketNumberFromResponse(jsonResponse);

        //then
        assertEquals(9286798, ticketNumber);
    }
}
