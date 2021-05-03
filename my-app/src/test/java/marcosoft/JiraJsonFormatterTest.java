package marcosoft;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import marcosoft.jira.JiraJsonFormatter;


public class JiraJsonFormatterTest {

    @Test
    public void should_Return_Key_FromResponse(){

        JiraJsonFormatter jiraJsonFormatter = new JiraJsonFormatter();

        //given
        String jsonResponse = "{\"id\":\"10114\",\"key\":\"MSSD-18\",\"self\":\"https://team-1611176048161.atlassian.net/rest/api/3/issue/10114\"}";

        //when
        String ticketNumber = jiraJsonFormatter.getKeyFromResponse(jsonResponse);

        //then
        assertEquals("MSSD-18", ticketNumber);
    }
    
}
