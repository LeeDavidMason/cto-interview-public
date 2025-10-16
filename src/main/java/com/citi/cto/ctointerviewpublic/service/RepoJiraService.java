package com.citi.cto.ctointerviewpublic.service;

import com.citi.cto.ctointerviewpublic.dtos.*;
import com.citi.cto.ctointerviewpublic.http.HttpClient;
import com.citi.cto.ctointerviewpublic.factory.RepoFactory;
import com.citi.cto.ctointerviewpublic.model.CommitJiraInfo;
import com.citi.cto.ctointerviewpublic.transformer.RepoResponseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class which details the business logic step by step, delegating functionality to relevant components
 * They should hopefully realise that they should rely on an abstraction for the repo service, so they're not tied into Git
 * This is shown here with a factory returning an interface, showing polymorphism
 * Cache service is abstracted, shouldn't be part of the service class itself
 * Can they spot the N+1 query problem with the Jira service? Talk through what it means and how to solve
 */
@Service
public class RepoJiraService {

    private final CacheService cacheService;
    private final RepoFactory repoFactory;
    private final HttpClient httpClient;
    private final RepoResponseTransformer repoResponseTransformer;

    @Autowired
    public RepoJiraService(CacheService cacheService,
                           RepoFactory repoFactory,
                           HttpClient httpClient,
                           RepoResponseTransformer repoResponseTransformer) {
        this.cacheService = cacheService;
        this.repoFactory = repoFactory;
        this.httpClient = httpClient;
        this.repoResponseTransformer = repoResponseTransformer;
    }

    public RepoJiraResponse retrieveRepoIntegrationData(String type,
                                                        String commitHashFrom,
                                                        String commitHashTo) {
        if (cacheService.getValue(commitHashFrom, commitHashTo) != null) {
            return cacheService.getValue(commitHashFrom, commitHashTo);
        }

        //Using the strategy pattern (open closed principle), return a repository service and get data from repository
        RepoService repoService = repoFactory.getService(type);
        RepoResponse response = repoService.getAndTransform(commitHashFrom + "-" + commitHashTo);

        //Loop through all commits, extract the Jira ID from each commit
        List<CommitJiraInfo> commitJiraInfoList = new ArrayList<>();
        for (RepoCommitComment commitComment : response.getCommitComments()) {
            //Commit example ROD-123: Hello
            String jiraId = commitComment.getComment().split(":")[0];
            //N+1 issue here, could be solved if the API allowed us to pass in all Jira IDs
            JiraResponse jiraResponse = httpClient.get("test-jira-api/" + jiraId, JiraResponse.class);
            commitJiraInfoList.add(new CommitJiraInfo(commitComment.getCommit(), jiraResponse));
        }

        RepoJiraResponse repoJiraResponse = repoResponseTransformer.transform(commitJiraInfoList);

        cacheService.insertValue(commitHashFrom, commitHashTo, repoJiraResponse);

        return repoJiraResponse;
    }
}
