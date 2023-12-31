# sprint-team-2-back-end

# How to start the java-api application
---

1. Run the `mvn clean install` to build the application.
2. Start the application with `java -jar target/SprintTeam2Backend-1.0-SNAPSHOT.jar server` to run it locally.
3. To check that the application is running enter the url `http://localhost:8080`

# How to test the java-api application
---

- To Run all the tests, Use the command `mvn clean test`

# Checkstyle
---
In the Maven.yml, you can see that checkstyle is running a check on the code after the maven build:

- To add any new rules to the linting process navigate to the config/checkstyle/checkstyle.xml file, from the project directory.
- To add any suppressions navigate to the config/checkstyle/suppressions.xml, from the project directory.
- Navigate to checkstyle website to see what the full rule set that they provide at https://checkstyle.sourceforge.io/google_style.html

- To run Checkstyle, use the command `mvn checkstyle:check`

# Building and running the API using Docker
---

To build and deploy the API on Docker, you must first ensure you have Docker installed and set up.

1. Ensure you have set the DB_USER, DB_PASSWORD, DB_HOST and DB_NAME properties as environment variables in your ~/.zshrc file.
2. Run `docker build -t <service name + optional tag> .` which will read the docker file, build the environment and create local image.
3. Run `docker images` to verify your image is available after building.
4. Run `docker run -p 8080:8080 <service name + optional tag>` to run the Docker build and display on port 8080.

Docker on GitHub Actions
---

When running pull request to main branch, the deploy.yml file will be run which will deploy the API to the AWS Server.
The link to the server is https://sgrrqppfh2.eu-west-1.awsapprunner.com/swagger
