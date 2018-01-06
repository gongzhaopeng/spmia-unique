echo "Launching $BUILD_NAME IN AMAZON ECS"
ecs-cli configure profile --profile-name base --access-key $AWS_ACCESS_KEY --secret-key $AWS_SECRET_KEY
ecs-cli configure --cluster spmia-tmx-dev --region ap-northeast-2 --config-name base
ecs-cli compose --file docker/common/docker-compose.yml up
rm -rf ~/.ecs
