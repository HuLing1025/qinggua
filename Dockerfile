FROM hub.c.163.com/library/java:latest
ADD . .
WORKDIR .
# Time zone
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java","-jar","/target/qinggua.jar"]