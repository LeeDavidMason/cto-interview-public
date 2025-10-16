package com.citi.cto.ctointerviewpublic.dtos;

import lombok.Data;

import java.util.List;

@Data
public class BitBucketResponse {
    private List<RepoCommitComment> bitBucket;
}
