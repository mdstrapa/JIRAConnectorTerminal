package marcosoft;


public class Incident extends Ticket {

    public Incident(Customer incidentCustomer, String incidentSumary, String  incidentDescription, Category incidentCategory){
        this.customer = incidentCustomer;
        this.summary = incidentSumary;
        this.description = incidentDescription;
        this.category = incidentCategory;
    }

    public int save(ExternalSystem destination){

        int newIncidentNumber = 0;

        switch (destination){
            case USD:
                System.out.println("This ticket will be save into USD");
                Usd usd = new Usd();
                newIncidentNumber = usd.createIncident(this);
                break;
            case JIRA:
                System.out.println("This ticket will be save into JIRA");
                break;
            default:
                System.out.println("This ticket will not be saved");
                break;
        }
        return newIncidentNumber;
    }

    public boolean addComment(){
        return true;
    }

}
