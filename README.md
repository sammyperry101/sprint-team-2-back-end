# sprint-team-2-back-end

# How to start the java-api application
---

1. Run the `mvn clean install` to build the application
2. Start the application with `mvn start`
3. To check that the application is running enter the url `http://localhost:8080`

# How to test the java-api application
---

- To Run all the tests, Use the command `mvn clean test`

# How to start the java-api application
---

1. Run the `mvn clean install` to build the application
2. Start the application with `mvn start`
3. To check that the application is running enter the url `http://localhost:8080`

# Checkstyle
---
In the Maven.yml, you can see that checkstyle is running a check on the code after the maven build:

- To add any new rules to the linting process navigate to the config/checkstyle/checkstyle.xml file, from the project directory.
- To add any suppressions navigate to the config/checkstyle/suppressions.xml, from the project directory.
- Navigate to checkstyle website to see what the full rule set that they provide at https://checkstyle.sourceforge.io/google_style.html

- To run Checkstyle, use the command `mvn checkstyle:check`

