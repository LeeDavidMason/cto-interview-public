package com.citi.cto.ctointerviewpublic.service;

import com.citi.cto.ctointerviewpublic.dtos.RepoResponse;

public interface RepoService {
    RepoResponse getAndTransform(String pathUrl);
}
