package com.citi.cto.ctointerviewpublic.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RepoCommitComment {
    private String commit;
    private String comment;
}
