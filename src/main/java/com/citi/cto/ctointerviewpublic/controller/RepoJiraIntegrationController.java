package com.citi.cto.ctointerviewpublic.controller;

import com.citi.cto.ctointerviewpublic.dtos.RepoJiraResponse;
import com.citi.cto.ctointerviewpublic.service.RepoJiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Should be no logic in the controller, delegates it all to a service class
 * Use query parameters instead of a request body for GET --can discuss RESTful design
 * Could validate input body
 * Response entity with a DTO
 * Constructor injection
 */
@RestController
@RequestMapping("/api")
public class RepoJiraIntegrationController {

    private final RepoJiraService repoJiraService;

    @Autowired
    public RepoJiraIntegrationController(RepoJiraService repoJiraService) {
        this.repoJiraService = repoJiraService;
    }

    @GetMapping("/jira/integration")
    public ResponseEntity<RepoJiraResponse> getJiraCommitResults(@RequestParam String type,
                                                                 @RequestParam String commitHashFrom,
                                                                 @RequestParam String commitHashTo) {
        return ResponseEntity.ok(repoJiraService.retrieveRepoIntegrationData(type, commitHashFrom, commitHashTo));
    }
}
