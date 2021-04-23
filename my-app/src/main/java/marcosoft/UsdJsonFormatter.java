package marcosoft;
import com.google.gson.Gson;

public class UsdJsonFormatter {
    

    public String formatResponse(String originalResponseJSON, String usdObject){
        //{"rest_access":
        //{"in":
        //{"chg":
        int charactersToRemoveFromBeginning = usdObject.length() + 4;
        String formattedJson;
        formattedJson = originalResponseJSON.substring(0, originalResponseJSON.length() - 1);
        return formattedJson.substring(charactersToRemoveFromBeginning, formattedJson.length());
    }

    public String formatRequestBody(Object objectToFormat, String usdObject){

        Gson gson = new Gson();
        String requestBody = gson.toJson(objectToFormat)
            .concat("}")
            .replace("COMMON_NAME", "@COMMON_NAME")
            .replace("id","@id");
        
        requestBody = "{\"" + usdObject + "\" : ".concat(requestBody);

        return requestBody;
    }

    public int getTicketNumberFromResponse(String responseJson){
        return Integer.parseInt(responseJson.substring(
            responseJson.indexOf(":", responseJson.indexOf("@COMMON_NAME")) + 1,
            responseJson.indexOf(",", responseJson.indexOf(":", responseJson.indexOf("@COMMON_NAME"))))
            .trim());
    }
}
