package com.proposal;

import java.util.List;

public class Team {
    private String name;
    private String repoName;
    private List<String> members;

    public Team(String name, String repoName, List<String> members) {
        this.name = name;
        this.repoName = repoName;
        this.members = members;
    }

    public Team() {
        
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepoName() {
        return this.repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public List<String> getMembers() {
        return this.members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

}
