//Parsing YAML with SnakeYAML
//https://www.baeldung.com/java-snake-yaml

package com.prosoal;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class yamlTeamLoader {

    public static void loadTeams(String filePath) {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = yamlTeamLoader.class
                .getClassLoader()
                .getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Cannot find " + filePath + " file");
            }
            Map<String, Object> data = yaml.load(inputStream);

            // Directly extract "developers" from the loaded YAML
            String teamMembers;
            if (data.containsKey("github_team")) {
                if (data.get("github_team") != null){
                    teamMembers = "github_team";
                }else{
                    teamMembers = "developers";
                }
                List<String>maintainers = (List<String>) data.get(teamMembers);
                String teamName = (String) data.get("github");
                System.out.println(teamName + ":");
                    for (String maintainer : maintainers) {
                    System.out.println(" - " + maintainer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load: " + filePath, e);
        }
    }

    public static void main(String[] args) {
        loadTeams("teams/plugin-dark-theme.YAML");
    }
}
