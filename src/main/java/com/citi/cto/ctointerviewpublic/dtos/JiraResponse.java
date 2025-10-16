package com.citi.cto.ctointerviewpublic.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JiraResponse {
    private String jiraId;
    private List<String> comment;
}
