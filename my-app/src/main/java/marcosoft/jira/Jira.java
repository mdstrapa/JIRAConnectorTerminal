package marcosoft.jira;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest.BodyPublishers;
import java.io.IOException;
import java.time.Duration;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import com.google.gson.Gson;


public class Jira {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .authenticator(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                            "marcos_strapazon@sicredi.com.br",
                            "UzGZ7kLu1Z4dWrxojUA029E9".toCharArray());
                }
            })
            .version(Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    private HttpRequest buildJiraRequest(String method, String jiraIssue){

        URI jiraEndPoint = URI.create("https://team-1611176048161.atlassian.net/rest/api/3/issue/" + jiraIssue);
        HttpRequest jiraRequest = null;

        String requestBody = "";
        jiraRequest = HttpRequest.newBuilder()
            .uri(jiraEndPoint)
            .method(method, BodyPublishers.ofString(requestBody))
            .setHeader("Authorization", "Basic bWFyY29zX3N0cmFwYXpvbkBzaWNyZWRpLmNvbS5icjpVekdaN2tMdTFaNGRXcnhvalVBMDI5RTk=")
            .setHeader("Cookie", "atlassian.xsrf.token=1daa5eb3-9376-46ba-8fe5-2af656c2b5a3_0e39fcff6e568608a263669aad1eda1e24972eae_lin")
            .build();

        return jiraRequest;
    }


    public String createJiraIssue(JiraIssue issue){

        System.out.println( " entrou no metodo" ); 

        URI jiraEndPoint = URI.create("https://team-1611176048161.atlassian.net/rest/api/3/issue");
        HttpRequest jiraRequest = null;

        String requestbody = new Gson().toJson(issue);

        System.out.println( "esse eh o body: " + requestbody); 


        String requestResponse = "<initial message>";

        jiraRequest = HttpRequest.newBuilder()
            .uri(jiraEndPoint)
            .method("POST", BodyPublishers.ofString(requestbody))
            .setHeader("Authorization", "Basic bWFyY29zX3N0cmFwYXpvbkBzaWNyZWRpLmNvbS5icjpVekdaN2tMdTFaNGRXcnhvalVBMDI5RTk=")
            .setHeader("Cookie", "atlassian.xsrf.token=1daa5eb3-9376-46ba-8fe5-2af656c2b5a3_0e39fcff6e568608a263669aad1eda1e24972eae_lin")
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .build();

        try {
            System.out.println( "entrou no try" ); 
            HttpResponse<String> response = httpClient.send(jiraRequest, BodyHandlers.ofString());
            requestResponse = response.body();
        } catch (IOException | InterruptedException e) {
            System.out.println( "entrou no catch" ); 
            e.printStackTrace();
        }
        
        return  requestResponse;
        
    }


    public JiraIssue getIssue(String jiraIssueKey){
        JiraIssue jiraIssue = null;
        HttpRequest jiraRequest = buildJiraRequest("GET", jiraIssueKey);
        try {
            HttpResponse<String> response = httpClient.send(jiraRequest, BodyHandlers.ofString());
            jiraIssue = new Gson().fromJson(response.body(), JiraIssue.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return jiraIssue;
    }




    
}
