package marcosoft.usd;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import marcosoft.Configuration;


public class Usd {

    private static HttpClient httpClient = HttpClient.newHttpClient();

    UsdJsonFormatter usdJsonFormatter = new UsdJsonFormatter();

    private HttpRequest buildUsdRequest(String method, String usdObject, String requestBody, int accessKey){

        Configuration configuration = new Configuration();

        URI usdEndPoint = URI.create(configuration.getProperty("usdEndPoint")+ usdObject);
        HttpRequest usdRequest = null;

        if (usdObject == "rest_access"){
            usdRequest = HttpRequest.newBuilder()
                .uri(usdEndPoint)
                .method(method, BodyPublishers.ofString(requestBody))
                .setHeader("Content-Type", "application/json")
                .setHeader("Accept", "application/json")
                .setHeader("Authorization", "Basic bWFyY29zX3N0cmFwYXpvbjpFVUEyMDIxZWhub2lz")
                .build();
        }else if (method == "GET") {
            usdRequest = HttpRequest.newBuilder()
                .uri(usdEndPoint)
                .method(method, BodyPublishers.ofString(requestBody))
                .setHeader("Accept", "application/json")
                .setHeader("X-Obj-Attrs", "summary,description")
                .setHeader("X-AccessKey", String.valueOf(accessKey))
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
    
    private RestAccess getAccessKey(){
        RestAccess restAccess = null;
        String responseBody;
        String requestBody = "{ \"rest_access\" : {} }";

        HttpRequest request = buildUsdRequest("POST","rest_access",requestBody,0);

        try {
                HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
                responseBody = usdJsonFormatter.formatResponse(response.body(),"rest_access");
                restAccess = new Gson().fromJson(responseBody, RestAccess.class);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        return restAccess;
    }

    public UsdIncident getObject(String usdFactory, String objectId){

        String responseBody;
        UsdIncident incident = null;
 
        RestAccess restAccess = getAccessKey();

        HttpRequest request = buildUsdRequest("GET",usdFactory + "/"  + objectId, "", restAccess.access_key);

        try {            
            
            HttpResponse<String> httpResponse = httpClient.send(request, BodyHandlers.ofString());

            if (httpResponse.statusCode()==200){
                responseBody = usdJsonFormatter.formatResponse(httpResponse.body(),"in");
                
                incident = new Gson().fromJson(responseBody, UsdIncident.class);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("An error has occurred: " + e.getMessage());
            e.printStackTrace();
        }


        return incident;
    }

    public UsdObjectCreationResponse createObject(Object objectToCreate, String usdFactory){
        UsdObjectCreationResponse response = new UsdObjectCreationResponse();

        RestAccess restAccess = getAccessKey();
    
        String requestBody = usdJsonFormatter.formatRequestBody(objectToCreate,usdFactory);

        
        HttpRequest request = buildUsdRequest("POST",usdFactory, requestBody, restAccess.access_key);
        try {
            
            HttpResponse<String> httpResponse = httpClient.send(request, BodyHandlers.ofString());
            if (httpResponse.statusCode()==201){
                response.object = usdJsonFormatter.getObjectFromResponse(httpResponse.body());
                response.REL_ATTR = usdJsonFormatter.getRellAttrFromResponse(httpResponse.body());
                response.COMMON_NAME = usdJsonFormatter.getCommonNameFromResponse(httpResponse.body());
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("An error has occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

}
