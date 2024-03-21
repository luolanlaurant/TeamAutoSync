package com.proposal;

import java.util.List;

public class Team {
    private String name;
    private List<String> members;

    public Team(String name, List<String> members) {
        this.name = name;
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

    public List<String> getMembers() {
        return this.members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Team [members=" + members + ", name=" + name + "]";
    }

}
