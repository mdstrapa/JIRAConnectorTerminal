package marcosoft.jira;

import java.util.ArrayList;
import java.util.List;

public class JiraIssueFields {
    public String summary;
    JiraIssueType issuetype;
    JiraProject project;
    JiraIssueDescription description;

    public JiraIssueFields(String projectKey, String issueTypeId, String summary, String issueDescription){
        this.summary = summary;

        JiraIssueType issueType = new JiraIssueType(issueTypeId);
        this.issuetype = issueType;

        JiraProject issueProject = new JiraProject(projectKey);
        this.project = issueProject;

        DescriptionContentContent descriptionContentContent = new DescriptionContentContent(issueDescription,"text");
        List<DescriptionContentContent> descriptionContentContentList = new ArrayList<>();
        descriptionContentContentList.add(descriptionContentContent);

        DescriptionContent descriptionContent = new DescriptionContent("paragraph",descriptionContentContentList);
        List<DescriptionContent> descriptionContentList = new ArrayList<>();
        descriptionContentList.add(descriptionContent);

        JiraIssueDescription description = new JiraIssueDescription("doc",1,descriptionContentList);
        this.description = description;
    }
}

class JiraIssueDescription{
    String type;
    int version;
    List<DescriptionContent> content;
    public JiraIssueDescription(String doc, int version, List<DescriptionContent> content){
        this.type = doc;
        this.version = version;
        this.content = content;
    }
}

class DescriptionContent{
    String type;
    List<DescriptionContentContent> content;
    public DescriptionContent(String type, List<DescriptionContentContent> content){
        this.type = type;
        this.content = content;
    }
}

class DescriptionContentContent{
    String text;
    String type;
    public DescriptionContentContent(String text, String type){
        this.text = text;
        this.type = type;
    }
}

class JiraProject {
    String key;
    public JiraProject(String key){
        this.key = key;
    }
}

class JiraIssueType {
    String id;

    public JiraIssueType(String id){
        this.id = id;
    }
}
