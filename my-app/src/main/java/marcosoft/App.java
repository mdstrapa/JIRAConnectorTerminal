package marcosoft;

import marcosoft.usd.UsdIncident;
import marcosoft.usd.UsdContact;
import marcosoft.usd.Category;
import marcosoft.jira.JiraIssue;
import marcosoft.jira.JiraIssueFields;

;public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "=========================" );
        System.out.println( "Jira Conector" );
        System.out.println( "=========================" );

        // UsdIncident incident = null;
        // UsdContact incidentCustomer = new UsdContact("U'2C975EEBC83E224C9F7A8868415036D9'");
        // Category incidentCategory = new Category("USD.Desenvolvimento.Views");
        // incident = new UsdIncident(incidentCustomer, "Resumo vindo do Java", "Descrição vindo do Java", incidentCategory);


        // if(incident.create()) {
        //     System.out.println( "--- The incidente was CREATED :) The number is " + incident.ref_num);
        //     System.out.println( "--- The incidente was CREATED :) The rell_attr is " + incident.RELL_ATTR);
        //     incident.addComment("this is the comment to add");
        // }
        // else System.out.println( "--- The incidente was NOT CREATED :( ");



        // Jira jira = new Jira();

        JiraIssueFields issueFields = new JiraIssueFields("MSSD", "10004", "USD has crashed", "Users need USD");
        JiraIssue jiraIssue = new JiraIssue(issueFields);
        jiraIssue.create();
        //JiraIssue jiraIssue = jira.getIssue("MSSD-2");
        // System.out.println(jiraIssue.id);
        // System.out.println(jiraIssue.key);
        // System.out.println(jiraIssue.fields.summary);



        System.out.println("");
        System.out.println("=========================");
        System.out.println("END OF PROGRAM");
        System.out.println("=========================");

    }
}
