# build the latest image
docker build -t huling1025/qinggua:latest .
# login
docker login  --username=huling1025 --password Huling1025
# push the latest image
docker push huling1025/qinggua:latest
# delete the image
docker rmi huling1025/qinggua:latest