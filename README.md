# ✏️ 끄적끄적

![logo](https://user-images.githubusercontent.com/20836023/210495864-5916d416-823e-4167-9260-b5356e4b0525.jpeg)

내 트리를 꾸며줘에서 영감을 받아 서로 롤링페이퍼를 나눌 수 있는 서비스입니다. 백엔드 제작에 집중하기 위해 화면 설계는 Kakao Oven을 활용했습니다.

(단체 레파지토리 쓰기 권한이 사라져 포크한 저장소에 기록하였습니다. 👉 [기존 레파지토리](https://github.com/f-lab-edu/ggeu-jeog-ggeu-jeog))
##💡 프로젝트 중점 사항

**1. 객체 지향 원리를 적용하여 구현**

**2. 늘어나는 트래픽을 감당할 수 있도록 Scale-out 서버 구조 설계**

**3. 테스트 코드를 작성하여 코드 품질 향상**

**4. 모니터링하며 문제점 개선**

## 🏛 서버 구조
![architecture](https://user-images.githubusercontent.com/20836023/210495720-11e542a7-60a9-4453-9986-a7ba9767c245.png)
## 🏛 CI/CD 구조
![deploy](https://user-images.githubusercontent.com/20836023/210496238-e13a2d69-ab01-40f5-97b7-fdafad36f1b1.png)
## 🧺 사용 기술 스택

- Java 11
- Spring boot 2.7
- Mybatis
- Redis
- Docker
- Nginx
- Jenkins
- JMeter

## 📎 발생한 이슈 정리

**1. [인증번호 전송 비동기 처리를 위한 Transactional Outbox Pattern 적용하기]()**

**2. [Spring Batch 페이징 쿼리 조회 개선하기]()**

**3. [Scale-out 시 발생하는 문제점 개선하기 1 (배치 작업 동시성 문제)]()**

**4. [Scale-out 시 발생하는 문제점 개선하기 2 (Session 불일치 문제)]()**

**5. [Scale-out, Scale-up 성능 비교와 선택]()**

**6. [무중단 배포를 위한 Blue/Green 배포 전략]()**

## 그 외
- [ER 다이어그램]()
- [API 명세서]()
- [화면 설계]()
