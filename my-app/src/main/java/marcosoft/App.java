package marcosoft;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Trying to Access Jira" );
        System.out.println( "=========================" );

        // Incident incident = null;
        // Customer incidentCustomer = new Customer("U'2C975EEBC83E224C9F7A8868415036D9'");
        // Category incidentCategory = new Category("USD.Desenvolvimento.Views");
        // incident = new Incident(incidentCustomer, "Resumo vindo do Java", "Descrição vindo do Java", incidentCategory);

        // int newIncidentNumber = incident.save(ExternalSystem.USD);

        // if(newIncidentNumber != 0) System.out.println( "--- The incidente was CREATED :) The number is " + newIncidentNumber);
        // else System.out.println( "--- The incidente was NOT CREATED :( ");


        Jira jira = new Jira();

        JiraIssue jiraIssue = jira.getIssue("MSSD-2");
        System.out.println(jiraIssue.id);
        System.out.println(jiraIssue.key);
        System.out.println(jiraIssue.fields.summary);

        System.out.println("");
        System.out.println("==============");
        System.out.println("END OF PROGRAM");

    }
}
