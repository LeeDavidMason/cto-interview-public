package com.citi.cto.ctointerviewpublic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepoJiraResponse {
    private List<Data> data;
}
