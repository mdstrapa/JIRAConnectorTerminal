package marcosoft.usd;

import marcosoft.Ticket;



public class UsdIncident extends Ticket {

    //the transient modifier prevents this property to be 
    //serializaded in json files, which is needed when we create objects
    public transient String RELL_ATTR; 
    public transient String ref_num;

    public UsdIncident(UsdContact incidentCustomer, String incidentSumary, String  incidentDescription, Category incidentCategory){
        this.customer = incidentCustomer;
        this.summary = incidentSumary;
        this.description = incidentDescription;
        this.category = incidentCategory;
        this.RELL_ATTR = "";
        this.ref_num = "";
    }

    public boolean create(){
        boolean operationResult = false;
        UsdObjectCreationResponse creationResponse = new UsdObjectCreationResponse();
        Usd usd = new Usd();
        try {
            creationResponse = usd.createObject(this, "in");
            this.RELL_ATTR = creationResponse.REL_ATTR;
            this.ref_num = creationResponse.COMMON_NAME;
            operationResult = true;
        } catch (Exception e) {
            System.out.println("An error has occured: " + e.getMessage());
        }
        return operationResult;
    }

    public boolean addComment(String comment){
        boolean operationResult = false;
        Usd usd = new Usd();
        UsdContact analyst = new UsdContact("U'2C975EEBC83E224C9F7A8868415036D9'");
        UsdAlgType type = new UsdAlgType("LOG");
        UsdIncidentRel incidentRel = new UsdIncidentRel(this.RELL_ATTR);
        UsdAlg alg = new UsdAlg(comment, analyst, type, incidentRel);
        try {
            usd.createObject(alg, "alg");
            operationResult = true;
        } catch (Exception e) {
            System.out.println("An error has occured: " + e.getMessage());
        }
        return operationResult;
    }


}
