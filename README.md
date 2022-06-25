# api-server
cafe api server repository

# JDK
JDK : 1.7

# Build
```
gradle boot jar
```

# Jar Start
```
 - java -jar -Dfile.encoding=UTF-8 cafe-0.0.1-SNAPSHOT.jar
```

# docker
docker run --name pgsql_10.1 -e POSTGRES_USER=testuser -e POSTGRES_PASSWORD=testuser -e "TZ=Asia/Seoul" -d -p 5434:5432 postgres:10.1

