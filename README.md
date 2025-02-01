## 👋🏻 About

## 👨🏻‍💻 Contributors

|[<img src="https://github.com/minjo-on.png" alt="박민준" width="150" />](https://github.com/minjo-on)|[<img src="https://github.com/LeeShinHaeng.png" alt="이신행" width="150" />](https://github.com/LeeShinHaeng)|[<img src="https://github.com/LeeHanEum.png" alt="이한음" width="150" />](https://github.com/LeeHanEum)|
|:---:|:---:|:---:|
| [**박민준**](https://github.com/minjo-on) | [**이신행**](https://github.com/LeeShinHaeng) | [**이한음**](https://github.com/LeeHanEum) |

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
├── aics-common         // 공통 기능 (인증 및 인가, 로깅 등)
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
