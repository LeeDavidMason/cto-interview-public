package com.citi.cto.ctointerviewpublic;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RepoJiraIntegrationController {

    @Data
    private class CachedResponse {
        private String id;
        private String response;
    }

    @Autowired
    private HttpClient httpClient;

    private List<CachedResponse> cache = new ArrayList<>();

    @GetMapping("/jira/integration")
    public String getJiraCommitResults(@RequestBody RepoDataRequest repoDataRequest) throws IOException {
        //look in cache
        Optional<CachedResponse> response = cache.stream()
                .filter(cachedResponse ->
                        cachedResponse.id.equals(repoDataRequest.getCommitHashFrom() + "-" + repoDataRequest.getCommitHashTo()))
                .findFirst();

        if (response.isPresent()) {
            return response.get().response;
        }

        //if not there fetch from API
        String gitResponse = httpClient.get("git-repo", String.class);

        //loop through the Jira IDs returned and extract the comments


        //save to cache

        //return
        return gitResponse;
    }
}
