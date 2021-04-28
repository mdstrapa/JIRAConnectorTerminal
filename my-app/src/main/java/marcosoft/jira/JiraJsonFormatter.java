package marcosoft.jira;

public class JiraJsonFormatter {

    public String getKeyFromResponse(String responseJson){
        return responseJson.substring(
            responseJson.indexOf(":", responseJson.indexOf("key")) + 2, 
            responseJson.indexOf(",", responseJson.indexOf(":", responseJson.indexOf("key"))) - 1).
            trim();
    }
}
