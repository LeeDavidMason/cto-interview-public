package com.citi.cto.ctointerviewpublic.transformer;

import com.citi.cto.ctointerviewpublic.dtos.JiraResponse;
import com.citi.cto.ctointerviewpublic.dtos.RepoJiraResponse;
import com.citi.cto.ctointerviewpublic.model.CommitJiraInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RepoResponseTransformerTest {

    private RepoResponseTransformer transformer;

    @BeforeEach
    public void setup() {
        transformer = new RepoResponseTransformer();
    }

    @Test
    public void transform_ReturnsValidRepoJiraResponse() {
        List<CommitJiraInfo> commitJiraInfoList = new ArrayList<>();
        commitJiraInfoList.add(new CommitJiraInfo("commitHash", JiraResponse
                .builder()
                .jiraId("JiraId")
                .comment(List.of("Test comment"))
                .build()));

        RepoJiraResponse response = transformer.transform(commitJiraInfoList);

        assertThat(response.getData().size()).isEqualTo(1);
        assertThat(response.getData().getFirst().getJiraId()).isEqualTo("JiraId");
        assertThat(response.getData().getFirst().getCommitHash()).isEqualTo("commitHash");
        assertThat(response.getData().getFirst().getJiraComments().size()).isEqualTo(1);
        assertThat(response.getData().getFirst().getJiraComments().getFirst()).isEqualTo("Test comment");
    }
}