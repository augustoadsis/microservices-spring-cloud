DOCKER_BUILDKIT=1 docker build --build-arg MODULE=auth . -t microservices-auth
docker tag microservices-auth localhost:5000/microservices-auth
docker push localhost:5000/microservices-auth
