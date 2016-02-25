# mytiles

#Project Setup

Install redis
Download http://redis.io/download
For Mac
For OS X use “curl -O http://download.redis.io/releases/redis-3.0.1.tar.gz"
$ wget http://download.redis.io/releases/redis-3.0.1.tar.gz
$ tar xzf redis-3.0.1.tar.gz
$ cd redis-3.0.1
$ make
Install Git
https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
Create GitHub account
Fork repo 
https://github.com/fcarta29/mytiles
how to fork
https://help.github.com/articles/fork-a-repo/
Clone GIt repo to local workspace
git clone git@github.com:<your_repo_name>/mytiles.git
Building Applications
"mvn clean install" from /mytiles directory to build all
Starting Applications
Start redis 
Start reds from reds home dir
under src/
./redis-server
(Optional)
start up redis cli
under src/
./redis-cli
Starting mytiles-data
mvn spring-boot:run
Starting mytiles-app
mvn spring-boot:run
Starting mytiles-webapp
mvn jetty:run
Using/Testing
Open 2 browser windows to http://localhost:8080 
Click Connect
Enter name … tmp until login is ready 
REST endpoints on Swagger
http://localhost:9080/swagger-ui.html#/



#Project Start/Run

Debugging
export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=y”
Redis
Start reds from reds home dir
under src/
./redis-server
(Optional)
start up redis cli
under src/
./redis-cli
Starting mytiles-data
mvn spring-boot:run
Rest API
http://localhost:9080/swagger-ui.html
http://localhost:9080/v2/api-docs
Starting mytiles-app
mvn spring-boot:run
Starting mytiles-webapp
mvn jetty:run
Connect to localhost:8080