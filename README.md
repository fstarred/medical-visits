# stellato Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

Create image with docker
./mvnw package -DskipTests -Dquarkus.native.container-build=true

Then start containers with:

```shell script
docker compose -f src/main/docker/docker-compose.yaml up
```