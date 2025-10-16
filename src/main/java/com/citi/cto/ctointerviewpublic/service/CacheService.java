package com.citi.cto.ctointerviewpublic.service;

import com.citi.cto.ctointerviewpublic.dtos.RepoJiraResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Not perfect, just looking for them to abstract away a cache implementation
 * Could expand by implementing an interface etc, unlikely necessary
 * Can ask questions here about concurrency using a HashMap, if they use a HashMap
 * Also, what's the problem with this if you have multiple K8s pods?
 */
@Service
public class CacheService {

    private final Map<String, RepoJiraResponse> cache = new ConcurrentHashMap<>();

    public RepoJiraResponse getValue(String commitHashFrom, String commitHashTo) {
        return cache.getOrDefault(commitHashFrom + "-" + commitHashTo, null); //could do better than null, but this is fine for interview example
    }

    public void insertValue(String commitHashFrom, String commitHashTo, RepoJiraResponse value) {
        cache.put(commitHashFrom + "-" + commitHashTo, value);
    }
}
