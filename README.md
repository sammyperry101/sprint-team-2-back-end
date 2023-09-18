# sprint-team-2-back-end

Building and running the API using Docker
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