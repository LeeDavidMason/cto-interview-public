package com.citi.cto.ctointerviewpublic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Data {
    private String jiraId;
    private String commitHash;
    private List<String> jiraComments;
}
