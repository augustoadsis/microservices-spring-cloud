DOCKER_BUILDKIT=1 docker build --build-arg MODULE=discovery . -t microservices-discovery
docker tag microservices-discovery localhost:5000/microservices-discovery
docker push localhost:5000/microservices-discovery
