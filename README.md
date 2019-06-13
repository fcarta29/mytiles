# MyTiles

##Project Setup



### Build Deploy Container (from /deploy dir)
``` 
$ docker build -t mytiles-deploy --rm -f deploy.dockerfile .
```

### Run Deploy Container
```

```

## Deploying MyTiles Appliction to AWS
###
EKS setup here - https://docs.aws.amazon.com/eks/latest/userguide/getting-started-console.html
Needed to add Internet Gateway to Routing Table for Subnets 0.0.0.0/0 - igw-XXXXXXXXX
Install countour on k8 
```
kubectl apply -f https://j.hept.io/contour-deployment-rbac
```
### Run and Connect to Deploy Container
```
$ docker run --name mytiles-deploy -td fcarta29/mytiles-deploy:latest
$ docker exec -it mytiles-deploy bash
```
### Configure AWS CLI and EKS
```
$ aws configure
$ aws eks --region us-west-2 update-kubeconfig --name MyTiles-Cluster
```

Deploy yamls - persistence, mytiles app, and networking
### Get AWS External IP
```
 kubectl get -n heptio-contour service contour -o wide

Get External IP
a44c888c68d5c11e99a980653b4b550e-837031113.us-west-2.elb.amazonaws.com
```


### Push Deploy Container
```
docker login
docker tag mytiles-deploy:latest fcarta29/mytiles-deploy:latest
docker push fcarta29/mytiles-deploy:latest
```


### Build Base Container (from /build dir)
```
docker build -t mytiles-build --rm -f build.dockerfile .
```
### Push Build Container
```
docker login
docker tag mytiles-build:latest fcarta29/mytiles-build:latest
docker push fcarta29/mytiles-build:latest
```






##OLD
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
