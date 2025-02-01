## 👋🏻 About

경기대학교 컴퓨터공학부 학과 홈페이지의 백엔드 레포지토리는 경기대학교 컴퓨터공학부 학과 홈페이지 개발팀 KGU Developers가 주도하는 프로젝트로, 기존 홈페이지의 한계를 극복하고자 새롭게 리팩토링되었습니다.
기존 시스템은 오래된
기술 스택으로 인해 성능 저하, 불안정한 사용자 경험, 그리고 유지보수의 어려움 등의 문제를 겪고 있었습니다. 이러한 문제들을 해결하고 더 나은 서비스를 제공하기 위해 이 프로젝트가 시작되었습니다.

이번 리팩토링은 단순한 개선을 넘어 성능 최적화와 기능 확장성 확보에 중점을 두고 있습니다. 최신 기술을 도입하여 더 빠르고 안정적인 서비스 환경을 구축하고, 유지보수가 쉬운 구조로 설계하였습니다.

특히, 학과 운영에 필수적인 졸업 논문 관리 시스템을 전면적으로 개선하여 학생들과 교수진이 논문 제출 및 관리 업무를 더욱 효율적으로 처리할 수 있도록 하였습니다. 또한, 학과 내 협업 환경을 지원하기 위해 팀
프로젝트실 예약 시스템을 새롭게 개발하여 학생들이 쉽게 공간을 예약하고 활용할 수 있게 되었습니다.

이 프로젝트는 단순한 내부 개선을 넘어, 이와 유사한 홈페이지 개발을 고민하는 다른 개발자들에게도 도움이 되는 참고 자료로 활용될 수 있도록 설계되었습니다. 코드와 서버 설정을 공개하고 있으며, 지속적인 리팩토링과
다양한 실험을 통해 코드 품질을 높이고 있습니다.

이 레포지토리는 백엔드 로직과 API 개발을 담당하고 있으며, 프론트엔드 시스템도 함께 공개되어 있습니다. 관심 있는
분들은 [프론트엔드 리포지토리](https://github.com/kgu-developers/aics-client)도 확인해 보시기 바랍니다.

## 👨🏻‍💻 Contributors

| [<img src="https://github.com/minjo-on.png" alt="박민준" width="150" />](https://github.com/minjo-on) | [<img src="https://github.com/LeeShinHaeng.png" alt="이신행" width="150" />](https://github.com/LeeShinHaeng) | [<img src="https://github.com/LeeHanEum.png" alt="이한음" width="150" />](https://github.com/LeeHanEum) |
|:--------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------:|
|                               [**박민준**](https://github.com/minjo-on)                               |                                 [**이신행**](https://github.com/LeeShinHaeng)                                 |                               [**이한음**](https://github.com/LeeHanEum)                                |

## 🏗️ System Architecture

## 🗼 Application Architecture

## 🚧 Project Structure

### Multi Module

```
.
├── aics-admin          // 관리자 기능 관련 엔드포인트 및 비즈니스로직 
│   ├── src
│   │   └── ...
│   ├── buid.gradle
│   └── Dockerfile
├── aics-api            // 일반 사용자 기능 관련 엔드포인트 및 비즈니스로직 
│   ├── src
│   │   └── ...
│   ├── buid.gradle
│   └── Dockerfile
├── aics-auth           // 인증 및 인가 (추후 분리 예정)
│   ├── src
│   │   └── ...
│   ├── buid.gradle
├── aics-common         // 공통 엔티티 및 기능 (로깅, 예외 등)
│   ├── src
│   │   └── ...
│   └── buid.gradle
├── aics-domain         // 도메인 로직
│   ├── src
│   │   └── ...
│   └── buid.gradle
├── aics-global-utils   // 각종 Util
│   ├── src
│   │   └── ...
│   └── buid.gradle
├── aics-infra          // 인프라 (각종 설정 및 구성)
│   ├── src
│   │   └── ...
│   └── buid.gradle
// ..
```

### Module Detail Structure - api

```
aics-api.src.main.java.kgu.developers.api
├── foo
│   ├── application                 // 비즈니스 로직
│   │   └── FooFacde.java           // CQRS 패턴 적용을 위한 Facade 계층
│   └── presentation
│       ├── response                // 응답 객체
│       │   └── ...
│       ├── FootController.java     // Swagger 분리를 위한 추상화
│       └── FooControllerImpl.java  // 실제 API 엔드포인트 구현
// ..
```

### Module Detail Structure - domain

```
aics-domain.src.main.java.kgu.developers.domain
├── foo
│   ├── application                     // 비즈니스 로직
│   │   ├── command                     // Command 서비스
│   │   │   ├── FooCommandService.java
│   │   └── query                       // Query 서비스
│   │       └── FooQueryService.java   
│   ├── domain                          // 도메인
│   │   ├── Foo.java                    // 객체
│   │   ├── FooRepository.java          // Repository 추상화
│   │   └── Enum1.java                  // Foo 객체에서 사용하는 Enum
│   ├── exeption                        // 예외
│   │   ├── FooDomainExceptionCode.java // 통일된 예외 처리를 위한 Enum
│   │   └── FooException1.java          // 실제 예외 객체
│   └── infrastructure
│       ├── FooRepositoryImpl.java      // Repository 구현체
│       └── {ORM}FooRepository.java     // ORM을 사용한 로직 구현 (Repository 구현체에서 사용) 
// ..
```

## 📄 License
