package com.citi.cto.ctointerviewpublic;

import lombok.Data;

@Data
public class RepoDataRequest {
    private String type;
    private String commitHashFrom;
    private String commitHashTo;
}
