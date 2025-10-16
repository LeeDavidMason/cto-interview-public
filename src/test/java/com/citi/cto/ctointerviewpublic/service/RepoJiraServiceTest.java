package com.citi.cto.ctointerviewpublic.service;

import com.citi.cto.ctointerviewpublic.dtos.*;
import com.citi.cto.ctointerviewpublic.factory.RepoFactory;
import com.citi.cto.ctointerviewpublic.http.HttpClient;
import com.citi.cto.ctointerviewpublic.model.CommitJiraInfo;
import com.citi.cto.ctointerviewpublic.transformer.RepoResponseTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Applies solid testing principles, well named tests, external dependencies mocked and 100% test coverage of the class
 * under test
 */
@ExtendWith(MockitoExtension.class)
class RepoJiraServiceTest {

    @Mock
    private CacheService cacheService;
    @Mock
    private RepoFactory repoFactory;
    @Mock
    private HttpClient httpClient;
    @Mock
    private RepoResponseTransformer transformer;
    @Mock
    private GitService gitService;
    private RepoJiraService repoJiraService;

    @BeforeEach
    public void setup() {
        repoJiraService = new RepoJiraService(cacheService, repoFactory, httpClient, transformer);
    }

    @Test
    public void retrieveRepoIntegrationData_ReturnsRepoJiraResponse_WhenValueFoundInCache() {
        Mockito.when(cacheService.getValue("commitFrom", "commitTo")).thenReturn(createResponse());

        RepoJiraResponse response = repoJiraService.retrieveRepoIntegrationData("git", "commitFrom", "commitTo");

        assertThat(response).isEqualTo(createResponse());
        Mockito.verifyNoInteractions(repoFactory);
    }

    @Test
    public void retrieveRepoIntegrationData_ReturnsRepoJiraResponse_WhenValueIsNotFoundInCache() {
        Mockito.when(cacheService.getValue("commitFrom", "commitTo")).thenReturn(null);
        Mockito.when(repoFactory.getService("git")).thenReturn(gitService);
        Mockito.when(gitService.getAndTransform("commitFrom-commitTo")).thenReturn(createRepoResponse());
        Mockito.when(httpClient.get("test-jira-api/ROD-123", JiraResponse.class)).thenReturn(createJiraResponse());
        Mockito.when(transformer.transform(List.of(new CommitJiraInfo("CommitHash1", createJiraResponse())))).thenReturn(createResponse());

        RepoJiraResponse response = repoJiraService.retrieveRepoIntegrationData("git", "commitFrom", "commitTo");

        assertThat(response).isEqualTo(createResponse());
    }

    private RepoJiraResponse createResponse() {
        return RepoJiraResponse
                .builder()
                .data(List.of(createDataResponse("ROD-123", "CommitHash1")))
                .build();
    }

    private Data createDataResponse(String jiraId, String commitHash) {
        return Data.builder()
                .jiraId(jiraId)
                .commitHash(commitHash)
                .jiraComments(List.of("Jira Comment"))
                .build();
    }

    private RepoResponse createRepoResponse() {
        return RepoResponse.builder()
                .commitComments(List.of(RepoCommitComment.builder()
                                .comment("ROD-123: Test comment")
                                .commit("CommitHash1")
                        .build()))
                .build();
    }

    private JiraResponse createJiraResponse() {
        return JiraResponse.builder()
                .jiraId("ROD-123")
                .build();
    }
}