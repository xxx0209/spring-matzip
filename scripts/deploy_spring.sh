#!/bin/bash

# 환경 변수
IMAGE=xxx0209/spring-backend:latest
GREEN_PORT=8081

# 1. 최신 이미지 Pull
docker pull $IMAGE

# 2. Green 컨테이너 실행
docker run -d --name spring-green -p ${GREEN_PORT}:8080 $IMAGE

# 3. Health Check
SUCCESS=false

for i in {1..10}; do
  STATUS=$(curl -s http://localhost:${GREEN_PORT}/health | tr -d '\r\n')
  if [[ "$STATUS" == *"UP"* ]]; then
      SUCCESS=true
      echo "Health Check passed on attempt #$i"
      break
  fi
  echo "Waiting for FastAPI... ($i)"
  sleep 3
done

if [[ "$SUCCESS" != true ]]; then
  echo "Health Check Failed! Rolling back."
  docker stop spring-green
  docker rm spring-green
  exit 1
fi

# 4. 기존 Blue 컨테이너 제거
docker stop spring-blue 2>/dev/null || true
docker rm spring-blue 2>/dev/null || true

# 5. Green 컨테이너 이름 변경
docker rename spring-green spring-blue

echo "Deployment successful!"
