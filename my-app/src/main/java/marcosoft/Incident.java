package marcosoft;

import java.util.Date;

public class Incident extends Ticket {
    int priority;

    public Incident(String incidentSumary,Date incidentOpenDate, int incidentPriority){
        this.summary = incidentSumary;
        this.openDate = incidentOpenDate;
        this.priority = incidentPriority;
        this.type = 'I';
    }

    public boolean save(ExternalSystem destination){
        switch (destination){
            case USD:
                System.out.println("This ticket will be save into USD");
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

    public boolean addComment(){
        return true;
    }

}
