package marcosoft;

public class UsdAlg {
    String description;
    UsdContact analyst;
    UsdAlgType type;
    UsdIncidentRel call_req_id;


    public UsdAlg(String algDescription, UsdContact algAnalyst, UsdAlgType algType, UsdIncidentRel call_req_id){
        this.description = algDescription;
        this.analyst = algAnalyst;
        this.type = algType;
        this.call_req_id = call_req_id;
    }
    
}
