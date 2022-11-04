# Development Log

> This is a development log for the project. It is a place to keep track of what
> has been done, what is being done, and what needs to be done.

- [x] On the Home page the enter key now submits a search query
- [x] The search bar supports its first operator, ```openai:```, which searches
  for OpenAI GPT-3 completions
- [ ] inter service communication (fanout)
    - [x] Vertx Event Bus communication
    - [ ] Introduce NATS for inter-service communication
- [ ] Multiplexed Search
    - [ ] Implement support for search operators
        - each operator is a service
        - [ ] ```openai:```
        - [ ] ```wiki:```
        - [ ] ```stack:```
        - [ ] ```source-graph:```
        - [ ] ```google:```
        - [ ] ```duckduckgo:```
- [ ] Collaborative Browsing
    - [ ] Implement support for collaborative browsing
    - [ ] Bugfix: Cursors don't update in the background
    - [ ] Benchmark performance of collaborative cursors
        - [ ] Determine how many cursors can be supported
- [ ] User Management
    - [ ] Implement support for user management
    - [ ] Implement support for user authentication
    - [ ] Implement support for user authorization
- [ ] Branding
    - [ ] Come up with a better name for the project
    - [ ] Add a favicon
    - [ ] Add a logo
    - [ ] Add a description
    - [ ] Add a screenshot
    - [ ] Add a link to the source code
- [ ] Ship code to production
    - [ ] Setup a CI/CD pipeline
    - [ ] Setup a deployment pipeline
    - [ ] Write kubernetes deployment manifests
- [ ] Code Review:
    - [ ] Add unit tests
    - [ ] Refactor codebase
        - [ ] Rename modules to be more descriptive
        - [ ] Look into hiding gradle files
    - [ ] Add documentation

````mermaid
flowchart TB
    subgraph Client
        subgraph SDK
            lib:client:rpc:ws
        end 
        subgraph React
            Controller -->
            State -->
            Render
        end
        
        SDK --> React
    end
    
    subgraph server
        subgraph Service
            lib:server:rpc:mqtt
        end
        subgraph Endpoint
            lib:server:rpc:ws
        end
        
    end
    
    subgraph Services
        subgraph SearchProviders
            OpenAI
            Wiki
            Stack
            SourceGraph
            Google
            DuckDuckGo
        end
        subgraph UserManagement
            
            AuthN --> Keycloak
            AuthZ --> Keycloak
            
        end
        subgraph Interaction
            Cursor
            Chat
        end
    end
    
    Endpoint <--> NGINX <--> SDK
    
    Service <--> NATS <--> Services



````