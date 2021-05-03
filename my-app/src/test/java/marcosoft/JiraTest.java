package marcosoft;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import marcosoft.jira.Jira;
import marcosoft.jira.JiraIssue;
import marcosoft.jira.JiraIssueFields;

public class JiraTest {

    @Test
    @Ignore
    public void should_Return_CorrectResponse(){

        //given
        Jira jira = new Jira();

        //when
        JiraIssueFields jiraIssueFields = new JiraIssueFields("MSSD", "10004", "USD has crashed", "Users need USD");
        JiraIssue jiraIssue = new JiraIssue(jiraIssueFields);
        String jsonResponse = jira.createJiraIssue(jiraIssue);

        //then
        assertEquals("{\"id\":\"", jsonResponse.substring(0,6));

    }
    
}
