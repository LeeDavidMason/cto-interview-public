package com.citi.cto.ctointerviewpublic.controller;


import com.citi.cto.ctointerviewpublic.SerialisationUtil;
import com.citi.cto.ctointerviewpublic.dtos.RepoJiraResponse;
import com.citi.cto.ctointerviewpublic.service.RepoJiraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RepoJiraIntegrationControllerTest {

    private RepoJiraService repoJiraService;
    private RepoJiraIntegrationController controller;

    @BeforeEach
    public void setup() {
        repoJiraService = Mockito.mock(RepoJiraService.class);
        controller = new RepoJiraIntegrationController(repoJiraService);
    }

    /**
     * Example of a unit test for a controller
     * They should not use SpringBootTest in a unit test
     * Good test naming, shouldn't start with 'test'
     */
    @Test
    public void getJiraCommitResults_ReturnsResponseEntityOK_WhenNoExceptionsThrown() throws IOException {
        RepoJiraResponse response = SerialisationUtil.loadJsonFromResources("repo-jira-response.json", RepoJiraResponse.class);

        Mockito.when(repoJiraService.retrieveRepoIntegrationData("git", "123", "456")).thenReturn(response);

        ResponseEntity<RepoJiraResponse> result = controller.getJiraCommitResults("git", "123", "456");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(response);
    }
}