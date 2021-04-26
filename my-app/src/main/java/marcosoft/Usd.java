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

    public UsdObjectCreationResponse createIncident(Incident incidentToCreate){

        //in each operation we have to evaluate the current access key.
        //if it is expired, we have to renew it
        RestAccess restAccess = getAccessKey();
        
        UsdObjectCreationResponse response = new UsdObjectCreationResponse();

        String requestBody = usdJsonFormatter.formatRequestBody(incidentToCreate,"in");
        //String responseBody = "";
        HttpRequest request = buildUsdRequest("POST","in",requestBody,restAccess.access_key);
        
        try {
           
                HttpResponse<String> httpResponse = httpClient.send(request, BodyHandlers.ofString());
                
                response.object = usdJsonFormatter.getObjectFromResponse(httpResponse.body());
                response.REL_ATTR = usdJsonFormatter.getRellAttrFromResponse(httpResponse.body());
                response.COMMON_NAME = usdJsonFormatter.getCommonNameFromResponse(httpResponse.body());

            } catch (IOException | InterruptedException e) {
                System.out.println("DEU MERDA");
                e.printStackTrace();
            }

        return response;
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

    public Incident getIncident(){
        UsdContact incidentCustomer = new UsdContact("U'2C975EEBC83E224C9F7A8868415036D9'");
        Category incidentCategory = new Category("USD.Desenvolvimento.Views");
        Incident incident = new Incident(incidentCustomer, "Resumo vindo do Java", "Descrição vindo do Java", incidentCategory);

        return incident;
    }

    public boolean addCommentToTicket(Incident incidentToAddComment, String comment){
        boolean operationResult = false;

        RestAccess restAccess = getAccessKey();

        UsdContact analyst = new UsdContact("U'2C975EEBC83E224C9F7A8868415036D9'");
        UsdAlgType type = new UsdAlgType("LOG");
        UsdIncidentRel incidentRel = new UsdIncidentRel(incidentToAddComment.RELL_ATTR);
        UsdAlg usdAlg = new UsdAlg(comment, analyst, type, incidentRel);

        String requestBody = usdJsonFormatter.formatRequestBody(usdAlg,"alg");

        System.out.println(requestBody);
        
        HttpRequest request = buildUsdRequest("POST","alg", requestBody, restAccess.access_key);
        try {
           
            HttpResponse<String> httpResponse = httpClient.send(request, BodyHandlers.ofString());
            if (httpResponse.statusCode()==201) operationResult = true;

        } catch (IOException | InterruptedException e) {
            System.out.println("DEU MERDA");
            e.printStackTrace();
        }

        return operationResult;
    }
}
