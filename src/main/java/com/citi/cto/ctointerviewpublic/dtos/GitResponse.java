package com.citi.cto.ctointerviewpublic.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GitResponse {
    private String id;
    private List<RepoCommitComment> comments;
}
