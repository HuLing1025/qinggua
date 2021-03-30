# build the latest image
docker build -t zhangshixue328/qinggua:latest .
# login
docker login  --username=zhangshixue328 --password zhangshixue
# push the latest image
docker push zhangshixue328/qinggua:latest
# delete the image
docker rmi zhangshixue328/qinggua:latest