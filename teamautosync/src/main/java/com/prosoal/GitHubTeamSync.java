package com.prosoal;
import java.io.IOException;
import java.util.List;

import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHTeam;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

public class GitHubTeamSync {

    public static void syncTeamMembers(List<String> yamlDevelopers) {
        try {
            GitHub github = GitHub.connectUsingOAuth(System.getenv("GITHUB_TOKEN"));
            GHOrganization org = github.getOrganization("your-github-organization");
            GHTeam team = org.getTeamByName("your-github-team-name");

            for (String developer : yamlDevelopers) {
                try {
                    GHUser user = github.getUser(developer);
                    if (!team.hasMember(user)) {
                        team.add(user);
                        System.out.println("Added " + developer + " to the team.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
