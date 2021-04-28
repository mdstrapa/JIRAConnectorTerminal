package marcosoft.jira;


public class JiraIssue {
    public transient int id;
    public transient String key;
    public JiraIssueFields fields;
    public JiraIssue(JiraIssueFields fields){
        this.id = 0;
        this.key = "";
        this.fields = fields;
    }

    public boolean create(){
        boolean operationResult = false;
        Jira jira = new Jira();
        try {
            this.key = jira.createJiraIssue(this);
            operationResult = true;
        } catch (Exception e) {
            System.out.println("An error has occured: " + e.getMessage());
        }
        return operationResult;
    }

}