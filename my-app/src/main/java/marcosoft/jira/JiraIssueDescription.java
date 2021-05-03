package marcosoft.jira;

import java.util.List;

public class JiraIssueDescription{
    String type;
    int version;
    public List<DescriptionContent> content;
    public JiraIssueDescription(String doc, int version, List<DescriptionContent> content){
        this.type = doc;
        this.version = version;
        this.content = content;
    }
}