package marcosoft;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Trying to create a ticket in USD" );
        System.out.println( "=========================" );

        Usd usd = new Usd();
        Incident inc = null;

        RestAccess restAccess = usd.getAccessKey();

        System.out.println( "USD accessKey: " + restAccess.access_key );
        System.out.println( "Works until: " + restAccess.expiration_date );

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        Date incidentOpenDate;
        try{

            incidentOpenDate = simpleDate.parse("1984-03-31");

            inc = new Incident("Resumo", incidentOpenDate, 1);

        }catch(ParseException e){
            e.printStackTrace();
        }

        boolean incidentCreationResult = inc.save(ExternalSystem.USD);

        if(incidentCreationResult) System.out.println( "--- The incidente was CREATED :) ");
        else System.out.println( "--- The incidente was NOT CREATED :( ");


    }
}
