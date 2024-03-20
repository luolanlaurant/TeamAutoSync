package com.proposal;
import java.io.IOException;

import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHTeam;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

public class GitHubTeamSync {

    public static void syncTeamMembers(Team team) {
        try {
            GitHub github = GitHub.connectUsingOAuth(System.getenv("secrets.MY_PERSONAL_TOKEN"));
            GHOrganization org = github.getOrganization(team.getName());
            GHTeam ghTeam = org.getTeamByName(team.getRepoName());

            for (String developer : team.getMembers()) {
                try {
                    GHUser user = github.getUser(developer);
                    if (!ghTeam.hasMember(user)) {
                        ghTeam.add(user);
                        System.out.println("Added " + developer + " to the team.");
                    } else {
                        System.out.println(developer + " is already a member of the team.");
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
