# HMCTS Dev Test Backend
## Manage a Task Service
A simple Spring Boot app to help with managing tasks. Forked from [hmcts-dev-test-backend](https://github.com/hmcts/hmcts-dev-test-backend) as part of the coding challenge.
### Dependencies

* Java JDK 21
* Gradle
* Postgres
* IDE of choice


### Latest merge state
Failing to build

### Building the app
```bash
docker-compose build --no-cache
```

### Starting the application
```bash
docker-compose up
```
### Shutting down
```bash
docker-compose down -v
```

### To do list:
1. [x] Set up db
2. [x] Set up db creds
3. [ ] Set up test data
4. [ ] Swagger docs
5. [ ] Makeffile
6. [x] Update ReadMe
7. [x] Dockerise
