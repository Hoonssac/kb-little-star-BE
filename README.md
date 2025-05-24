## KB 리틀스타 백엔드 서버
<p align="center"><img src="https://github.com/user-attachments/assets/51c0ec51-4ed0-4ae9-b209-d4e727ac803a"></p>




### 📌 프로젝트 소개

본 프로젝트는 Vue 기반의 어린이 가계부 웹 애플리케이션 [KB 리틀스타](https://github.com/KB-Triple777/kb-little-star)의 백엔드 서버입니다.

기존에는 json-server를 사용했으나, 데이터 무결성·동시성 이슈로 인해 실제 서비스 환경에는 적합하지 않았습니다.

Spring Boot와 MySQL 기반의 RESTful API 서버를 직접 구현하여,
유지보수성과 안정성을 갖춘 구조로 개선하는 것이 최종 목표입니다.



### ⚙️ 기술 스택
- Java 17
- Spring Boot 3.4.5
- Gradle
- MySQL
- Spring Data JPA
- Spring Security

 

### 🎯 세부 목표
- Spring Boot 기반 RESTful API 서버 구축
- 세션 기반 인증을 적용한 회원가입 / 로그인 기능
- 가계부 기록 및 퀴즈 데이터 CRUD 기능 제공
- JPA + MySQL 연동을 통한 실제 데이터베이스 사용
- DTO 패턴, 예외 처리, Controller-Service-Repository 계층 분리 설계
- Vue 프론트엔드와의 연동 테스트 및 CORS / 세션 설정 완료


