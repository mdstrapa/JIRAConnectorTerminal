package marcosoft;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import marcosoft.usd.UsdJsonFormatter;

public class UsdJsonFormatterTest {
    
    @Test
    public void should_Return_TicketNumber_FromResponse(){

        UsdJsonFormatter usdJsonFormatter = new UsdJsonFormatter();

        //given
        String jsonResponse = "{\"in\":{\"@id\": 9690500,\"@REL_ATTR\": \"cr:9690500\", \"@COMMON_NAME\": 9286798, \"link\": {\"@href\": \"http://usd.des.sicredi.net:8050/caisd-rest/cr/9690500\",\"@rel\": \"self\"}}}";

        //when
        String ticketNumber = usdJsonFormatter.getCommonNameFromResponse(jsonResponse);

        //then
        assertEquals("9286798", ticketNumber);
    }
    @Test
    public void should_Return_Object_FromResponse(){

        UsdJsonFormatter usdJsonFormatter = new UsdJsonFormatter();

        //given
        String jsonResponse = "{\"in\":{\"@id\": 9690500,\"@REL_ATTR\": \"cr:9690500\", \"@COMMON_NAME\": 9286798, \"link\": {\"@href\": \"http://usd.des.sicredi.net:8050/caisd-rest/cr/9690500\",\"@rel\": \"self\"}}}";

        //when
        String ticketNumber = usdJsonFormatter.getObjectFromResponse(jsonResponse);

        //then
        assertEquals("in", ticketNumber);
    }

    @Test
    public void should_Return_RellAttr_FromResponse(){

        UsdJsonFormatter usdJsonFormatter = new UsdJsonFormatter();

        //given
        String jsonResponse = "{\"in\":{\"@id\": 9690500,\"@REL_ATTR\":\"in:9690500\", \"@COMMON_NAME\": 9286798, \"link\": {\"@href\": \"http://usd.des.sicredi.net:8050/caisd-rest/cr/9690500\",\"@rel\": \"self\"}}}";

        //when
        String ticketNumber = usdJsonFormatter.getRellAttrFromResponse(jsonResponse);

        //then
        assertEquals("in:9690500", ticketNumber);
    }
}
