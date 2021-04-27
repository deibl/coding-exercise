# Coding Exercise
## Run
### Dockerized
Easiest way to run this application ist doing it dockerized.
You need to have docker and docker-compose installed.
#### Prepare
To make the application run, a valid API Key for fixer.io needs to be configured.
To do so, open docker-compose.yml and set the corresponding environment variable CURRENCY_API_KEY.
#### Run
To start, execute
```
docker-compose up
```
Frontend will be available under "localhost" and the api under "localhost:8080/swagger-ui.html"
#### Stop
To stop, execute
```
docker-compose up
```
#### Rebuild
On code changes the docker containers need to be rebuild for the changes to be applied.
To do so, you cann just add to the next up:
```
docker-compose up --build
```
### Local / native
#### Frontend
to start the frontend go to frontend directory and run
```
npm start
```
Frontend will be available under "localhost:3000"
#### Backend
to start the backend you need to provide a valid api key via application.properties and go to backend directory and run
```
./gradlew bootRun
```
Backend api will be available under "localhost:8080/swagger-ui.html"
