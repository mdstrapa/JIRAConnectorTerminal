package marcosoft;

import marcosoft.usd.UsdIncident;
import marcosoft.usd.UsdContact;
import marcosoft.usd.Category;
import marcosoft.usd.Usd;
import marcosoft.jira.JiraIssue;
import marcosoft.jira.JiraIssueFields;

;public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "=========================" );
        System.out.println( "Jira Conector" );
        System.out.println( "=========================" );



        // Usd usd = new Usd();

        // UsdIncident usdIncidente = usd.getObject("in", "9690522");

        // System.out.println("Summary: " + usdIncidente.summary);
        // System.out.println("Description: " + usdIncidente.description);



        Connector connector = new Connector();

        connector.createTicket("USD", "9690524", "JIRA");
        connector.createTicket("JIRA", "MSSD-21", "USD");


        // System.out.println("");
        // System.out.println("USD--------------------------------");
        // UsdIncident usdIncident = null;
        // UsdContact incidentCustomer = new UsdContact("U'2C975EEBC83E224C9F7A8868415036D9'");
        // Category incidentCategory = new Category("USD.Desenvolvimento.Views");
        // usdIncident = new UsdIncident(incidentCustomer, "Resumo vindo do Java", "Descrição vindo do Java", incidentCategory);
        // if(usdIncident.create()) {
        //     System.out.println( "USD INCIDENT was created. The Ref_Num is: " + usdIncident.ref_num);
        //     usdIncident.addComment("this is the comment to add");
        // }
        // else System.out.println( "An error has ocurred in USD");


        // System.out.println("");
        // System.out.println("JIRA-------------------------------");
        // JiraIssueFields issueFields = new JiraIssueFields("MSSD", "10004", "USD has crashed", "Users need USD");
        // JiraIssue jiraIssue = new JiraIssue(issueFields);
        // if (jiraIssue.create()){
        //     System.out.println( "JIRA ISSUE was created. The Key is: " + jiraIssue.key);
        // }else{
        //     System.out.println( "An has error ocurrend in JIRA");
        // }
  

        System.out.println("");
        System.out.println("=========================");
        System.out.println("END OF PROGRAM");
        System.out.println("=========================");

    }
}
