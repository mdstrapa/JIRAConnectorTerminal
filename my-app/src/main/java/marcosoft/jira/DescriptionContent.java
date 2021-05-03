package marcosoft.jira;

import java.util.List;

public class DescriptionContent{
    String type;
    public List<DescriptionContentContent> content;
    public DescriptionContent(String type, List<DescriptionContentContent> content){
        this.type = type;
        this.content = content;
    }
}