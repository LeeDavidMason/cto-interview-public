package com.citi.cto.ctointerviewpublic.service;

import com.citi.cto.ctointerviewpublic.http.HttpClient;
import com.citi.cto.ctointerviewpublic.dtos.GitResponse;
import com.citi.cto.ctointerviewpublic.dtos.RepoResponse;
import com.citi.cto.ctointerviewpublic.transformer.GitTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitService implements RepoService {

    private static final String baseUrl = "gitRepo/";

    private final HttpClient httpClient;
    private final GitTransformer gitTransformer;

    @Autowired
    public GitService(HttpClient httpClient, GitTransformer gitTransformer) {
        this.httpClient = httpClient;
        this.gitTransformer = gitTransformer;
    }

    public RepoResponse getAndTransform(String pathUrl) {
        try {
            GitResponse response = httpClient.get(baseUrl + pathUrl, GitResponse.class);
            return gitTransformer.transform(response); //Add better error handling than this, just an example
        } catch(Exception e) {
            return null;
        }
    }
}
