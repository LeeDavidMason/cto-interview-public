package com.citi.cto.ctointerviewpublic.transformer;

import com.citi.cto.ctointerviewpublic.dtos.Data;
import com.citi.cto.ctointerviewpublic.dtos.RepoJiraResponse;
import com.citi.cto.ctointerviewpublic.model.CommitJiraInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepoResponseTransformer {

    public RepoJiraResponse transform(List<CommitJiraInfo> commitJiraInfoList) {
        List<Data> data = new ArrayList<>();
        commitJiraInfoList.forEach(commitJiraInfo -> {
            data.add(new Data(commitJiraInfo.jiraResponse().getJiraId(), commitJiraInfo.commitHash(), commitJiraInfo.jiraResponse().getComment()));
        });
        return new RepoJiraResponse(data);
    }
}
