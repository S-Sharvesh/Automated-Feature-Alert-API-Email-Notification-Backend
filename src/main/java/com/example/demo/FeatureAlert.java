package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "alerts")
public class FeatureAlert {
    @Id
    private String id;
    private String featureName;
    private String version;
    private String team;
    private String childTeamDlEmail;
    private LocalDateTime date;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFeatureName() { return featureName; }
    public void setFeatureName(String featureName) { this.featureName = featureName; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
    public String getChildTeamDlEmail() { return childTeamDlEmail; }
    public void setChildTeamDlEmail(String childTeamDlEmail) { this.childTeamDlEmail = childTeamDlEmail; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}
