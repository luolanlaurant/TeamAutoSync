package com.proposal;

public class Main {
    public static void main(String[] args) {
       
        Team team = yamlTeamLoader.loadTeam("permissions/plugin-dark-theme.YAML");

        GitHubTeamSync.syncTeamMembers(team);
    }
}