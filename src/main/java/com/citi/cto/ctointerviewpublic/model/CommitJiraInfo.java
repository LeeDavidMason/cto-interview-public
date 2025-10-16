package com.citi.cto.ctointerviewpublic.model;

import com.citi.cto.ctointerviewpublic.dtos.JiraResponse;

public record CommitJiraInfo(String commitHash, JiraResponse jiraResponse) {
}
