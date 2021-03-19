FROM hub.c.163.com/library/java:latest
ADD . .
WORKDIR .
ENTRYPOINT ["nohup","java","-jar","/qinggua.jar","&"]