package marcosoft;


public class Incident extends Ticket {

    //the transient modifier prevents this property to be 
    //serializaded in json files, which is needed when we create objects
    transient String RELL_ATTR; 
    transient String ref_num;

    public Incident(UsdContact incidentCustomer, String incidentSumary, String  incidentDescription, Category incidentCategory){
        this.customer = incidentCustomer;
        this.summary = incidentSumary;
        this.description = incidentDescription;
        this.category = incidentCategory;
        this.RELL_ATTR = "";
        this.ref_num = "";
    }

    public String save(ExternalSystem destination){

        UsdObjectCreationResponse newIncidentResponse = new UsdObjectCreationResponse();

        switch (destination){
            case USD:
                System.out.println("This ticket will be save into USD");
                Usd usd = new Usd();
                newIncidentResponse = usd.createIncident(this);
                this.ref_num = newIncidentResponse.COMMON_NAME;
                this.RELL_ATTR = newIncidentResponse.REL_ATTR;
                break;
            case JIRA:
                System.out.println("This ticket will be save into JIRA");
                break;
            default:
                System.out.println("This ticket will not be saved");
                break;
        }
        return newIncidentResponse.COMMON_NAME;
    }

    public boolean addComment(String comment, ExternalSystem destination){
        switch (destination){
            case USD:
                System.out.println("This ticket will be save into USD");
                Usd usd = new Usd();
                usd.addCommentToTicket(this, comment);
                
                break;
            case JIRA:
                System.out.println("This ticket will be save into JIRA");
                break;
            default:
                System.out.println("This ticket will not be saved");
                break;
        }
        return true;
    }

}
