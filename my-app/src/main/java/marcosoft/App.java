package marcosoft;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Trying to create a ticket in USD" );
        System.out.println( "=========================" );

        Incident incident = null;
        Customer incidentCustomer = new Customer("U'2C975EEBC83E224C9F7A8868415036D9'");
        Category incidentCategory = new Category("USD.Desenvolvimento.Views");
        incident = new Incident(incidentCustomer, "Resumo vindo do Java", "Descrição vindo do Java", incidentCategory);

        int newIncidentNumber = incident.save(ExternalSystem.USD);

        if(newIncidentNumber != 0) System.out.println( "--- The incidente was CREATED :) The number is " + newIncidentNumber);
        else System.out.println( "--- The incidente was NOT CREATED :( ");

        System.out.println("");
        System.out.println("==============");
        System.out.println("END OF PROGRAM");

    }
}
