# MyTiles

##Project Setup

1. Install redis
  * Download http://redis.io/download
  * For Mac OS X use 
    * “curl -O http://download.redis.io/releases/redis-3.0.1.tar.gz"
    ```
    $ wget http://download.redis.io/releases/redis-3.0.1.tar.gz
    $ tar xzf redis-3.0.1.tar.gz
    $ cd redis-3.0.1
    $ make
    ```
2. Install Git
  * https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
3. Create GitHub account
4. Fork repo 
  * https://github.com/fcarta29/mytiles
  * how to fork - https://help.github.com/articles/fork-a-repo/
5. Clone GIt repo to local workspace
  * git clone git@github.com:<your_repo_name>/mytiles.git
6. Building Applications
  * "mvn clean install" from /mytiles directory to build all


##Project Start/Run

1. Debugging
  * export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=y”
2. Starting Applications
  1. Start redis 
    * Start reds from reds home dir
      * under src/
      * ./redis-server <path-to-redis-config>/redis.conf
    * (Optional) start up redis cli
      * under src/
      * ./redis-cli
  2. Starting mytiles-data
    * mvn spring-boot:run
  3. Starting mytiles-app
    * mvn spring-boot:run
  4. Starting mytiles-webapp
    * mvn jetty:run
3. REST endpoints on Swagger
  * http://localhost:9080/swagger-ui.html
  * http://localhost:9080/v2/api-docs
4. Using/Testing
  1. Open 2 browser windows to http://localhost:8080 
  2. Click Connect
  3. Enter name … tmp until login is ready 
