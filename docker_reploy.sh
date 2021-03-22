# stop the container
docker stop qinggua_prod
# delete the container
docker rm qinggua_prod
# delete the image
docker rmi huling1025/qinggua:latest
# login
docker login  --username=huling1025 --password Huling1025
# pull the latest image
docker pull huling1025/qinggua:latest
# run the latest image and mount the images folder
docker run -v /images/:/images/ -p 8088:8088 --name qinggua_prod -d huling1025/qinggua:latest