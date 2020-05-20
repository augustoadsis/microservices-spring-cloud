DOCKER_BUILDKIT=1 docker build --build-arg MODULE=course . -t microservices-course
docker tag microservices-course localhost:5000/microservices-course
docker push localhost:5000/microservices-courses
