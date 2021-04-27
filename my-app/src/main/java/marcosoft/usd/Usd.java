package marcosoft.usd;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;


public class Usd {

    private static HttpClient httpClient = HttpClient.newHttpClient();

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

    public UsdIncident getIncident(){
        UsdContact incidentCustomer = new UsdContact("U'2C975EEBC83E224C9F7A8868415036D9'");
        Category incidentCategory = new Category("USD.Desenvolvimento.Views");
        UsdIncident incident = new UsdIncident(incidentCustomer, "Resumo vindo do Java", "Descrição vindo do Java", incidentCategory);

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
