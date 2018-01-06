echo "Pushing service docker images to docker hub ...."
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker push gongzhaopeng/tmx-zipkinsvr:$BUILD_NAME
docker push gongzhaopeng/tmx-eurekasvr:$BUILD_NAME
docker push gongzhaopeng/tmx-confsvr:$BUILD_NAME
docker push gongzhaopeng/tmx-zuulsvr:$BUILD_NAME
docker push gongzhaopeng/tmx-authentication-service:$BUILD_NAME
docker push gongzhaopeng/tmx-licensing-service:$BUILD_NAME
docker push gongzhaopeng/tmx-organization-service:$BUILD_NAME
