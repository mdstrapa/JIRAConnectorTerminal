package marcosoft;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.io.IOException;
import java.net.http.HttpRequest.BodyPublishers;
import  com.google.gson.Gson;

public class Usd {


    private HttpRequest buildUsdRequest(String requestBody){

        HttpRequest usdRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://usd.des.sicredi.net:8050/caisd-rest/rest_access"))
            .POST(BodyPublishers.ofString(requestBody))
            .setHeader("Content-Type", "application/xml")
            .setHeader("Accept", "application/json")
            .setHeader("Authorization", "Basic bWFyY29zX3N0cmFwYXpvbjpFVUEyMDIxZWhub2lz")
            .build();

        return usdRequest;
    }

    private String formatResponseJSON(String originalResponseJSON){
        int charactersToRemoveFromBeginning = 15;
        String formattedJson;
        formattedJson = originalResponseJSON.substring(0, originalResponseJSON.length() - 1);
        return formattedJson.substring(charactersToRemoveFromBeginning, formattedJson.length());
    }

    public void createIncident(){
        String data = "<rest_access/>";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://usd.des.sicredi.net:8050/caisd-rest/rest_access"))
            .POST(BodyPublishers.ofString(data))
            .setHeader("Content-Type", "application/xml")
            .setHeader("Accept", "application/json")
            .setHeader("Authorization", "Basic bWFyY29zX3N0cmFwYXpvbjpFVUEyMDIxZWhub2lz")
            .build();

        try {
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                //if (response.statusCode()==200) {
                    System.out.println(response.body());
                //}

                String responseBody = response.body();
                responseBody = responseBody.substring(0, responseBody.length() - 1);
                responseBody = responseBody.substring(15, responseBody.length());

                RestAccess obj = new Gson().fromJson(responseBody, RestAccess.class);
    
                System.out.println("THE access key is: " + obj.access_key);
                System.out.println("THE expiration is: " + obj.expiration_date);
    

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
    }
    
    public RestAccess getAccessKey(){
        RestAccess restAccess = null;
        String responseBody;
        String requestBody = "<rest_access/>";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = buildUsdRequest(requestBody);

        try {
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                responseBody = formatResponseJSON(response.body());
                restAccess = new Gson().fromJson(responseBody, RestAccess.class);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        return restAccess;
    }
}
