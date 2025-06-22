## KB 리틀스타 백엔드 서버
<p align="center"><img src="https://github.com/user-attachments/assets/51c0ec51-4ed0-4ae9-b209-d4e727ac803a"></p>




### 📌 프로젝트 소개

이 저장소는 Vue 기반 어린이 가계부 웹 애플리케이션 KB 리틀스타의 백엔드 서버입니다.
어린이 대상의 금융 교육 서비스를 제공하며, 아이들이 직접 수입·지출을 관리하고 퀴즈를 통해 금융 지식을 습득할 수 있도록 설계되었습니다.

초기 버전에서는 json-server를 사용해 API 응답을 제공했으나,
데이터 무결성 보장 및 사용자 인증·예외 처리 등 실제 서비스 수준의 기능을 구현하기 어려운 한계가 존재했습니다.

이에 따라 백엔드 전반을 Spring Boot와 MySQL, MyBatis를 기반으로 재구성하고 있습니다.
유효성 검증, 예외 처리, 세션 기반 인증, RESTful API 구조를 도입하여
보다 안정적이고 확장 가능한 서버 환경을 구축하는 것이 본 프로젝트의 핵심 목표입니다.



### ⚙️ 기술 스택
- Language: Java 17
- Framework: Spring Boot 3.4.5
- ORM/SQL Mapper: MyBatis
- Database: MySQL
- Build Tool: Gradle
- API 테스트: Postman
- Frontend 연동: Vue.js (CORS 및 세션 연동 완료)
 

### 🎯 주요 목표 및 개선 방향

- **Spring Boot 기반 RESTful API 서버 구현**  
  Controller - Service - Mapper 구조로 역할을 명확히 분리하고, 유지보수성과 확장성을 고려한 설계

- **MyBatis 기반 데이터베이스 연동**  
  SQL을 직접 작성하고 명시적으로 매핑하여, 복잡한 쿼리 대응 및 성능 최적화 가능

- **세션 기반 사용자 인증 구현**  
  로그인 및 회원가입 시 `HttpSession`을 활용하여 사용자 인증 상태를 안정적으로 관리

- **DTO 및 전역 예외 처리 구조 설계**  
  요청과 응답 객체를 명확히 분리하고, `@Valid`, 커스텀 예외, `@RestControllerAdvice`를 활용해 예외 응답의 일관성 확보

- **프론트엔드(Vue) 연동을 고려한 CORS 및 세션 설정**  
  도메인이 다른 프론트엔드와 연동 시 발생할 수 있는 CORS 문제 해결 및 세션 연동 테스트 완료

- **json-server 기반의 임시 구조에서 MySQL 기반 실제 DB 구조로 전환 진행 중**  
  더 이상 mock 데이터가 아닌, 실시간 데이터 조작이 가능한 신뢰성 있는 구조로 개선

