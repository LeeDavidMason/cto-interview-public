package com.citi.cto.ctointerviewpublic.factory;

import com.citi.cto.ctointerviewpublic.service.GitService;
import com.citi.cto.ctointerviewpublic.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepoFactory {
    private final GitService gitService;

    @Autowired
    public RepoFactory(GitService gitService) {
        this.gitService = gitService;
    }

    public RepoService getService(String type) {
        if (type.equals("git")) { //Should be an enum, but for the interview I'm only looking for a factory pattern
            return gitService;
        }
        return null;
    }
}
