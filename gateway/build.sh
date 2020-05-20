DOCKER_BUILDKIT=1 docker build --build-arg MODULE=gateway . -t microservices-gateway
docker tag microservices-gateway localhost:5000/microservices-gateway
docker push localhost:5000/microservices-gateway
