# Citi CTO Interview

## Overview

A junior engineer on your team has been working on a feature to integrate Git commit data with Jira tickets. They've presented you with their initial implementation, but it's currently not working and they've become stuck.

The system is designed to:
1. Accept HTTP requests with a "commit from" and "commit to" range
2. Call a Git API to retrieve commits within that range
3. Parse Jira ticket IDs from commit messages
4. Call the Jira API to fetch ticket details
5. Return a custom DTO containing the enriched data
