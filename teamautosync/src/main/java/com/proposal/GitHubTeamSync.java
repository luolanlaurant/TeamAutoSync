package com.proposal;
import java.io.IOException;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

public class GitHubTeamSync {

    public static void syncTeamMembers(Team team) {
        try {
            GitHub github = GitHub.connectUsingOAuth(System.getenv("GITHUB_TOKEN"));
            GHRepository repo = github.getRepository(team.getName());

            // add collaborators to the repository
            System.out.println("Current Collaborators:");
            for (GHUser collaborator : repo.listCollaborators()) {
                System.out.println(collaborator.getLogin());
            }

            for (String developer : team.getMembers()) {
                try {
                    GHUser user = github.getUser(developer);
                    // check if user is already a collaborator
                    if (!repo.isCollaborator(user)) {
                        repo.addCollaborators(user);
                        System.out.println("Added " + developer + " as a collaborator to the repository.");
                    } else {
                        System.out.println(developer + " is already a collaborator of the repository.");
                    }
                } catch (IOException e) {
                    System.out.println("Error handling user: " + developer);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Error connecting to GitHub or fetching repository.");
            e.printStackTrace();
        }
    }
}