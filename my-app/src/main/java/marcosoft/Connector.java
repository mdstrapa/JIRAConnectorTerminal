package marcosoft;

import marcosoft.jira.Jira;
import marcosoft.jira.JiraIssue;
import marcosoft.jira.JiraIssueFields;
import marcosoft.usd.Usd;
import marcosoft.usd.UsdIncident;
import marcosoft.usd.UsdContact;
import marcosoft.usd.Category;


public class Connector {
    
    Usd usd = new Usd();
    Jira jira = new Jira();

    public boolean createTicket(String origemSystem, String origemTicket, String destinationSystem){
        boolean result = false;

        if (origemSystem == "USD"){

            UsdIncident usdIncident = usd.getObject("in", origemTicket);
    
            JiraIssueFields jiraIssueFields = new JiraIssueFields("MSSD", "10004", usdIncident.summary, usdIncident.description);
            JiraIssue jiraIssue = new JiraIssue(jiraIssueFields);
    
            try {
                if (jiraIssue.create()) result = true;
                
            } catch (Exception e) {
                System.out.println("An error has ocurred: " + e.getMessage());
            }
        }else{

            JiraIssueFields jiraIssueFields = new JiraIssueFields("", "", "", "");
            JiraIssue jiraIssue = new JiraIssue(jiraIssueFields);
            jiraIssue.key = origemTicket;  
            
            jiraIssue = jira.getIssue(jiraIssue);

            UsdIncident usdIncident = null;
            UsdContact incidentCustomer = new UsdContact("U'2C975EEBC83E224C9F7A8868415036D9'");
            Category incidentCategory = new Category("USD.Desenvolvimento.Views");

            String jiraDescription = jiraIssue.fields.description.content.get(0).content.get(0).text;

            usdIncident = new UsdIncident(incidentCustomer, jiraIssue.fields.summary , jiraDescription , incidentCategory);

            try {
                if (usdIncident.create()) result = true;
                
            } catch (Exception e) {
                System.out.println("An error has ocurred: " + e.getMessage());
            }

        }

        return result;
    }
}
