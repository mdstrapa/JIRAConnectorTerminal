package marcosoft;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.io.IOException;
import java.net.http.HttpRequest.BodyPublishers;
import com.google.gson.Gson;


public class Usd {

    UsdJsonFormatter usdJsonFormatter = new UsdJsonFormatter();

    private HttpRequest buildUsdRequest(String method, String usdObject, String requestBody, int accessKey){

        URI usdEndPoint = URI.create("http://usd.des.sicredi.net:8050/caisd-rest/" + usdObject);
        HttpRequest usdRequest = null;

        if (usdObject == "rest_access"){
            usdRequest = HttpRequest.newBuilder()
                .uri(usdEndPoint)
                .method(method, BodyPublishers.ofString(requestBody))
                .setHeader("Content-Type", "application/json")
                .setHeader("Accept", "application/json")
                .setHeader("Authorization", "Basic bWFyY29zX3N0cmFwYXpvbjpFVUEyMDIxZWhub2lz")
                .build();
        }else{
            usdRequest = HttpRequest.newBuilder()
                .uri(usdEndPoint)
                .method(method, BodyPublishers.ofString(requestBody))
                .setHeader("Content-Type", "application/json")
                .setHeader("Accept", "application/json")
                .setHeader("X-AccessKey", String.valueOf(accessKey))
                .build();
        }

        return usdRequest;
    }

    public int createIncident(Incident incidentToCreate){

        //in each operation we have to evaluate the current access key.
        //if it is expired, we have to renew it
        int newIncidentNumber = 0;

        RestAccess restAccess = getAccessKey();

        String requestBody = usdJsonFormatter.formatRequestBody(incidentToCreate,"in");

        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = buildUsdRequest("POST","in",requestBody,restAccess.access_key);


        try {
           
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                
                String responseBody = usdJsonFormatter.formatResponse(response.body(),"in");

                newIncidentNumber = usdJsonFormatter.getTicketNumberFromResponse(responseBody);

            } catch (IOException | InterruptedException e) {
                System.out.println("DEU MERDA");
                e.printStackTrace();
            }

        return newIncidentNumber;
    }
    
    private RestAccess getAccessKey(){
        RestAccess restAccess = null;
        String responseBody;
        String requestBody = "{ \"rest_access\" : {} }";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = buildUsdRequest("POST","rest_access",requestBody,0);

        try {
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                responseBody = usdJsonFormatter.formatResponse(response.body(),"rest_access");
                restAccess = new Gson().fromJson(responseBody, RestAccess.class);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        return restAccess;
    }
}
