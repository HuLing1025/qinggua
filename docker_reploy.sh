# stop the container
docker stop qinggua_prod
# delete the container
docker rm qinggua_prod
# delete the image
docker rmi zhangshixue328/qinggua:latest
# login
docker login  --username=zhangshixue328 --password zhangshixue
# pull the latest image
docker pull zhangshixue328/qinggua:latest
# run the latest image and mount the images folder
docker run -v /images/:/images/ -v /var/log/docker:/var/log -p 8088:8088 --name qinggua_prod -d zhangshixue328/qinggua:latest