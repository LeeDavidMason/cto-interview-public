package com.citi.cto.ctointerviewpublic.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RepoResponse {
    private List<RepoCommitComment> commitComments;
}
