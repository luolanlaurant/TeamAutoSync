//Parsing YAML with SnakeYAML
//https://www.baeldung.com/java-snake-yaml

package com.proposal;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class yamlTeamLoader {

    public static Team loadTeam(String filePath) {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Map<String, Object> data = yaml.load(inputStream);
            String teamName = "";
            List<String> members = null;
            String repoName = "";
            
            if (data.containsKey("name")) {
                teamName = (String) data.get("name");
            }
            
            if (data.containsKey("github")) {
                String fullRepoName = (String) data.get("github");
                // username/repoName
                String[] parts = fullRepoName.split("/");
                if (parts.length == 2) {
                    repoName = parts[1];
                }
            }
            
            String teamMembersKey = data.containsKey("github_team") ? "github_team" : "developers";
            if (data.get(teamMembersKey) != null) {
                members = (List<String>) data.get(teamMembersKey);
            }

            return new Team(teamName, repoName, members);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load: " + filePath, e);
        }
    }

    public static void main(String filePath) {
        Team team = loadTeam(filePath);
        System.out.println("Team: " + team.getName());
        System.out.println("Repo: " + team.getRepoName());
        System.out.println("Members:");
        for (String member : team.getMembers()) {
            System.out.println(" - " + member);
        }
    }
}