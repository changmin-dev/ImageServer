# 움짤 생성 서버
이미지를 제공 받으면 gif를 생성해줍니다. 

## 인터페이스
POST /merge-gif
```

// 실제 이미지 path는 /사전에 정한 Path/jh3/frames/jh-*.jpg
{
    "paths":[
        "/jh3/frames/jh-0.jpg", 
        "/jh3/frames/jh-1.jpg"
    ],
    "delay":10
}

```
## 개발 환경
Spring Boot, JMagick