#### 잡소리

정말 오랜만에 째찌삐띠형님이 아닌 직접 쓴 글을 게시하게 되었습니다!  
오늘의 주제는 헥사고날 아키텍쳐 입니다.

## 헥사고날 아키텍쳐란?

![cleanArchitecture](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

[출처 :엉클 밥 님의 Clean Coder Blog](https://blog.cleancoder.com/)  
클린 아키텍쳐를 깔끔하게 구현할 수 있는 아키텍쳐 중 하나로, 도메인 비즈니스 로직과 외부세계를 격리하고 외부에 어떤 의존성도 갖지 않게 하기 위한 아키텍쳐 입니다.

### 결국 커플링이 문제다!

그렇다면 이같은 클린 아키텍쳐를 지향하는 이유는 무엇일까요?  
이는 결국 유지 보수를 쉽게 하기 위함입니다.  
먼저 헥사고날 아키텍쳐는 모듈간 결합도를 현저히 낮춰줍니다.  
모듈간 결합도를 떨어뜨리면 모듈 별로 단위 테스트를 하기 쉽고, 오류 발생시 오류 추적과 수정이 쉬워집니다.

![useServerMeme](https://i.imgur.com/92ZFhV2.jpg)![useBinaryMeme](https://imgur.com/dxnqEjc.jpg)

[출처 : Peer Richelsen Twitter](https://twitter.com/peer_rich/status/1717899010025910430)  
최근 Next.JS의 업데이트에서 약간의 조롱(?)거리가 되며 meme이 되기도 했던 짤 역시, 프레젠테이션 레이어와 퍼시스턴스 레이어를 강하게 결합시키는 방향의 업데이트 였기 때문에 조롱이 되었던 것이죠.  
프레젠테이션 레이어가 퍼시스턴스 레이어의 역할까지 수행하게 되면서 스파게티 코드가 만들어질 것을 우려한 meme이었죠.

또 헥사고날 아키텍쳐는 in & out port와 port에 결합되는 adapter의 개념을 사용하면서 내가 정의한 클래스나 코드를 어느 패키지에 위치시켜야 할지 의미론적으로(Semantic 하게) 알려줍니다.

의미론에 기반한 패키지 구조는 처음에는 깔끔한 아키텍쳐로 시작했어도 유지보수를 하는 과정에서 점점 커다란 진흙덩어리 코드로 변질 되는걸 막는데에 큰 도움을 줍니다.

### 학습하게 된 계기 및 목표

![feelingGreat](https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788931023183.jpg)

[출처 : 교보문고](https://www.kyobobook.co.kr/)  
사이드 프로젝트 주제를 찾던 중, Feeling Great라는 책을 읽고, 기분을 개선하기 위한 일지를 관리해주는 어플리케이션을 구상하게 되었습니다.  
주제가 정해지고 언어와 라이브러리를 정하는 과정에서, 나름 익숙해진 클래식한 레이어드 아키텍쳐 보다는 좀더 모던한 아키텍쳐를 적용해 보고 싶다는 생각이 들었습니다.  
결론 부터 말하면, 적용은 하겠지만 규모가 작은 서비스에서 헥사고날 아키텍쳐를 정석으로 구현하는 것은 꽤 낭비적인 부분이 많다고 느꼈습니다 ㅋㅋ  
아무튼, 목표는 차기 프로젝트를 정석적으로 구현한(모든 레이어마다, 레이어간 DTO가 존재하는)헥사고날 아키텍쳐로 완성하는 것 입니다.

## 헥사고날 아키텍쳐 개요

![hexagonalArchitectureMap](https://imgur.com/pdEhAPP.jpg)

\[직접 그려본 구조도\]  
헥사고날 아키텍쳐는 사실 육각형일 필요는 없습니다.  
부착된 adapter가 많을 수록 원에 가까워지는 형태가 되겠죠.  
중요한건 비즈니스 로직의 영역인 application 영역은 외부세계(adapter)를 전혀 알지 못하고 분리되어 있다는 점입니다.  
오직 데이터가 오고가는 형태인 port만 알고 있을 뿐이죠.  
때문에 화살표의 방향을 잘 살펴 보시면 application영역에서 화살표가 바깥으로 나가는 형태가 아닌 외부에서 application 영역을 향하고 있는 것을 확인할 수 있습니다.

![hexagonalArchitectureInPort](https://imgur.com/ljISlxj.png)

사실 외부에서 서비스 로직을 호출이 들어올 때(In) 화살표가 안쪽으로 향하는 것은, 어찌보면 당연합니다.

![hexagonalArchitectureOutPort](https://imgur.com/Jozx1sp.png)

반대의 경우 내 로직에서 외부로 호출을 내보냄에도(Out) 마찬가지로 화살표는 안쪽으로 향하게 됩니다.  
UML에 익숙하신 분들은 아시겠지만 이는 인터페이스를 외부 어답터가 구현하게 함으로써 '의존 역전'이 일어나게 되는 것입니다.  
(이 글에서, 의존 역전에 대해 자세히 다루지는 않겠습니다.)  
글로만 봐서는 이해가 쉽지 않기 때문에, 헥사고날 아키텍쳐를 연습한 프로그램 구성을 소개한 뒤, 코드와 함께 살펴보겠습니다.

## 프로젝트 구성

![kotlinDesignPatterns](https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791161757810.jpg)

[출처 : 교보문고](https://www.kyobobook.co.kr/)(교보문고 사랑해요! 독서 좋아~)  
프로젝트는 도서 Kotlin Design Patterns에 수록된 CatsHostel 실습을 기반으로 하였습니다.  
단순히 고양이를 등록하고 조회할 수 있는 CRUD API Server라고 생각하시면 됩니다.

#### 아키텍쳐는 크게 세가지 영역으로 나뉘게 됩니다.

![packageTree](https://i.imgur.com/8embzHQ.png)

1.  Application 레이어
    -   비즈니스 로직을 구현한 계층 입니다.
    -   외부로의 어떤 의존성도 가지지 않습니다.
    -   모든 데이터는 Port를 통해, DTO에 의해 교류됩니다.
    -   순수 코틀린으로 구성되어 있습니다.
2.  Adapter Hexagon
    -   In Adapter와 Out Adater로 이루어져 있습니다.
    -   계층간 데이터를 실어 나를 DTO를 정의합니다.
    -   만약 직렬화가 필요하다면 해당 모듈에서 DTO에 직렬화를 정의합니다(ex. Kotlinx @Serialization 어노테이션)
3.  Bootstrap Hexagon
    -   모듈 전체를 아우르며 의존 주입 설정, 트랜잭션 설정 등 전반 적인 설정을 합니다.
    -   어플리케이션의 Main 엔트리가 위치합니다.

#### 각 어답터 및 헥사곤은 모두 코틀린 멀티 모듈을 사용하여 모듈화 되어있습니다.(= 별도의 build.gradle을 사용합니다)

도서에 수록된 원래 프로젝트는 단순히 엔드포인트를 설정하는 방법만 나와있지만, 헥사고날 아키텍쳐를 적용해서 구현하였습니다.  
어플리케이션 헥사곤은 외부에 전혀 의존하지 않기 때문에 어답터만 바꿔서 전혀 다른 프레임워크나 라이브러리를 적용할 수 있습니다.  
해당 예제에는 크게 두 가지 라이브러리 스택이 적용되어 있습니다.

1.  Ktor(Web), Koin(DI), Exposed(Transaction 및 퍼시스턴스 레이어 연결)
2.  Spring WebFlux(Reactive Web), Spring IoC Container(DI), SpringTransation(Transaction), R2DBC(Reactive ORM)

## Application Layer

![application_layer_package_tree](https://i.imgur.com/2ODYF9g.png)

\[어플리케이션 레이어 패키지 트리\]

```
plugins {
    kotlin("jvm")
}

dependencies {
}
```

\[build.gradle.kts(:application)\]

-   빌드를 정의한 코틀린 스크립트를 보면 실제로 외부에 어떤 디펜던시도 가지지 않는 것을 확인할 수 있습니다.
-   어플리케이션 계층은 순수하게 비즈니스 로직만 수행하기 때문입니다.
-   서비스 하려는 대상(개념 또는 사물)을 추상화한 entity와 이를 사용하는 케이스인 UseCase를 통해 외부와 상호작용 할 수 있습니다.

```
interface CatManagementUseCase {
    fun register(command: CreateCatCommand): Cat
    fun findById(query: FindCatQuery): Cat
    fun findAll(): Cats
    fun modify(command: ModifyCatCommand): Cat
    fun delete(deleteCatCommand: DeleteCatCommand): Cat

}
```

\[고양이를 등록하고, 수정하고, 등록을 삭제하고, 조회할 수 있는 UseCase를 정의한 인터페이스\]

-   외부의 프레젠테이션 레이어에서 이벤트를 통해 호출 되던, 다른 API가 비즈니스 로직을 사용하던, 결국 분석, 설계에서 도메인 전문가가 정의한 UseCase를 통해 비즈니스 로직을 사용하게 됩니다.
-   때문에 호출이 들어오는 In Adapter 에서는 UseCase에 정의된 Case만 바라보고(Interface), 해당 비즈니스 로직을 사용할 수 있습니다.즉, In Port는 UseCase로 정의될 수 있습니다.
-   서비스 로직이 복잡해질 경우 Usecase를 전부 분리시키는 것이 원칙이지만 단순 CRUD를 Service로 구현할 계획이기 때문에 CRUD를 하나의 UseCase에 정의하고 Management라는 네이밍을 사용했습니다.

```
interface CatCommandRepository {
    fun save(cat: Cat): Cat
    fun update(cat: Cat): Cat
    fun delete(id: CatId): Cat

}

interface CatQueryRepository {
    fun findByOrderById(): Cats
    fun findById(id: CatId): Cat

}
```

\[비즈니스 로직을 수행 하는 과정에서 외부로 호출을 보내야 할(out)경우가 정의된 인터페이스\]

-   해당 예제 에서는 비즈니스 로직 수행 과정에서 데이터에 영속성을 부여하고 이를 조회해야 하기 때문에 Repository라는 네이밍을 사용했습니다.
-   인터페이스로 정의된 레파지토리의 구현체는 Exposed가 될 수도 있고, R2DBC가 될 수도 있으며, 하이버네이트나 심지어는 다른 REST API를 호출할 수도 있습니다. 즉, Repository 인터페이스는 Out Port를 정의합니다.
-   어플리케이션 계층은 외부와의 결합이 없기 때문에 도메인 전문가가 정의해놓은 기능을 사용할 뿐 입니다.
-   차후 CQRS 패턴으로의 확장성을 고려해 인터페이스 단계에서 Repogitory로의 접근을 Command(C,U,D)와 Query(R)로 분리시켜 두었습니다.

```
class CatManagementService(
    private val catCommandRepository: CatCommandRepository, //외부에 직접 의존하지 않고 인터페이스에만 의존합니다.
    private val catQueryRepository: CatQueryRepository
) : CatManagementUseCase {
    override fun findById(query: FindCatQuery): Cat = catQueryRepository.findById(query.id)
    //...상세 코드 생략...
}
```

##### \-> 위처럼 어플리케이션 어떤 식으로 데이터가 오고 나가는지 (In, Out Port)만 알고 외부세계와 완벽히 격리 되어있습니다.

##### 이와같은 아키텍쳐는 단위 테스트에 용이하고, 비즈니스 로직의 테스트 검증이 끝났다면 에러의 원인을 다른 곳에서 찾아볼 수 있습니다.(= 유지 보수에 굉장히 용이합니다.)

## Apdapter Hexagon

![adapter_hexagon_package_tree](https://i.imgur.com/4JjTvo8.png)

\[어답터 헥사곤 패키지 트리\]

-   크게 In, Out으로 나뉘어 있습니다.
-   In패키지의 하위에는 웹에서의 호출을 의미하는 Web패키지가 있습니다.
-   다시 구현에 따라 ktor\_web과 webflux\_web으로 나뉩니다.
-   만일 웹이 아니라 MQ가 호출한다면 In 하위 패키지에 Web패키지 대신, MQ패키지를 두고 구현하는 것 만으로, 도메인을 호출하는 어답터를 쉽게 바꿀 수 있습니다.

#### In - Web

```
plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(project(":application")) //어플리케이션 레이어에 의존하고 있음을 알 수 있습니다.

    api("io.ktor:ktor-server-core-jvm:$ktor_version")
    api("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    api("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    api("io.ktor:ktor-server-cio-jvm:$ktor_version")
    api("ch.qos.logback:logback-classic:$logback_version")
}
```

\[build.gradle.kts(:adapter:in:web:ktor\_web)\]

```
plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {
   plugins {
      kotlin("jvm")
      kotlin("plugin.serialization")
      kotlin("plugin.spring")
      id("org.springframework.boot")
      id("io.spring.dependency-management")
  }

  tasks.withType<KotlinCompile>().configureEach {
      kotlinOptions {
          jvmTarget = "17"
      }
  }


  dependencies {
      implementation(project(":application")) //어플리케이션 레이어에 의존하고 있음을 알 수 있습니다.

      api("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
      api("org.springframework.boot:spring-boot-starter-webflux")
      implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
      implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
      implementation("org.jetbrains.kotlin:kotlin-reflect")
      implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
  }
}
```

\[build.gradle.kts(:adapter:in:web:webflux\_web)\]  
(`_web`이 패키지명에 붙은 이유는 다른 모듈의 이름과 겹칠경우 빌드 과정에서 디펜던시 순환참조가 일어나기 때문입니다.)

-   web으로 부터 application으로 들어오는(in) adapter를 Ktor와 Spring WebFlux를 사용해서 구현한 모습입니다.
-   각각의 모듈은 모두 `application 레이어`에 의존하고 있음을 알 수 있습니다.
-   만약 웹에서의 호출이 아닌 MQ에서의 호출이라면 In 패키지 하위에 MQ 모듈을 만들어 구현할 수 있습니다.
-   이처럼 외부가 바뀌어도 도메인 로직에는 변화가 없습니다.

```
@Serializable
data class CreateCatCommandDto(val name: String, val age: Int, val species: String) {
    fun toDomainCommand(): CreateCatCommand = CreateCatCommand(
        name = Name(name),
        age = Age(age),
        species = species
    )
}
```

-   어플리케이션 레이어와 상호작용에서 데이터를 전달하기 위해 DTO를 정의하고 DomainCommand로 매핑하는 함수를 구현해 줍니다.
-   외부에서 데이터를 바인딩 해오는 부분이기 때문에 직렬화를 위한 어노테이션(@Serializable)이 추가된 것도 볼 수 있습니다.
-   이처럼 어플리케이션 레이어 레벨에서는 데이터가 JSON으로 직렬화 된 데이터가 들어오는지 바이트 어레이로 들어오는지 모르도록 설계해야 합니다. 이에 대한 결정은 외부와 직접 맞닿아 있는 어댑터 헥사곤에서 책임을 져야 하기 때문입니다.

```
fun Route.catApiController(catManagementService: CatManagementUseCase) {
                           //웹 어댑터는 UseCase에만 의존하고 있습니다.
    route("cats") {
        get("") {
            call.respond(catManagementService.findAll().toCatsResponseDto())
        }
    // ... 상세 코드 생략 ...
  }
}
```

\[Ktor로 구현된 In Web Adatper\]

```
class CatApiController(
    private val catManagementService: CatManagementUseCase
            //마찬가지로 웹 어댑터는 UseCase에만 의존하고 있습니다.
) : RouterFunction<ServerResponse> {
    private val delegate = coRouter {
        "/api/v1/cats".nest {

            GET("/{id}") { request ->
                try {
                    val id = CatId(request.pathVariable("id").toInt())
                    val query = FindCatQuery(id)
                    ok().bodyValueAndAwait(catManagementService.findById(query))
                } catch (e: Exception) {
                    status(HttpStatus.NOT_FOUND).bodyValueAndAwait(e.message ?: "Unknown Error")
                }
            }
//... 상세 코드 생략 ...
        }
    }

    override fun route(request: ServerRequest): Mono<HandlerFunction<ServerResponse>> = delegate.route(request)
}
```

\[Spring WebFlux로 구현된 In Web Adatper\]

-   어플리케이션 레이어가 어떠한 의존성도 가지지 않기 때문에 위처럼 자유롭고 유연하게 어답터를 바꿀 수 있습니다.

#### Out - Persistence

```
plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":application"))

    api("org.jetbrains.exposed:exposed-core:$exposed_version")
    api("org.jetbrains.exposed:exposed-jdbc:$exposed_version")

//    implementation("mysql:mysql-connector-java:$mysql_jdbc_version")
    implementation("com.microsoft.sqlserver:mssql-jdbc:$mssql_jdbc_version")
    implementation("org.jetbrains.exposed:exposed-crypt:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-json:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-money:$exposed_version")
}
```

\[build.gradle.kts(:adapter:out:persistence:exposed)\]

```
plugins {
    java
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}
//apply(plugin = "io.spring.dependency-management")

dependencies {
    implementation(project(":application"))
    api("org.springframework.data:spring-data-r2dbc:$spring_boot_version")
    implementation("io.r2dbc:r2dbc-mssql:1.0.2.RELEASE")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-debug:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")
}
```

\[build.gradle.kts(:adapter:out:persistence:r2dbc)\]

-   application으로 부터 퍼시스턴스 레이어로 나가는(out) adapter를 Exposed와 R2dbc를 사용해서 구현한 모습입니다.
-   각각의 모듈은 모두 `application 레이어`에 의존하고 있음을 알 수 있습니다.
-   만약 다른 API서비스를 이용해 데이터를 저장하고 조회한다면, Retrofit이나 Ktor-Client등을 활용해 Repository를 구현할 수 있습니다.
-   위와 같은 경우처럼 외부가 완전히 바뀌어도 도메인 로직에는 변화가 없습니다.

```
// 도메인 전문가가 Command와 Query를 분리해 놓더라도 다중 구현을 통해, 구현단계에서는 통합되어도 상관이 없습니다.
// 어플리케이션 레이어는 Out Port에 의존하고, Adapter는 Out Port 인터페이스를 구현합니다.
// 의존 역전을 통해 유연하게 모듈을 변경할 수 있습니다.
class ExposedCatRepository : CatCommandRepository, CatQueryRepository {

    override fun findByOrderById(): Cats = CatEntity.all().sortedBy { it.id }.toCats()

}
```

-   차후 생각해 볼 점 파트에서 다시 얘기하겠지만 트랜잭션은 persistence 어답터에서 관리했어야 한다고 생각한다.

## Bootstrap Hexagon

![bootstrap_hexagon_package_tree](https://i.imgur.com/RXoMc2r.png)

\[부트스트랩 헥사곤 패키지 트리\]

-   bootstrap 패키지는 크게 DI, Transaction 그리고 서버를 구동하는 프레임워크 각각의 엔트리 포인트(Ktor, Spring)으로 구성되어 있습니다.

#### bootstrap - transaction

```
plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":adapter:out:persistence:exposed"))
    implementation(project(":application"))
}
```

\[build.gradle.kts(:bootstrap:transaction:exposed\_transaction)\]

```
plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":adapter:out:persistence:r2dbc"))
    implementation(project(":application"))
}
```

\[build.gradle.kts(:bootstrap:transaction:spring\_transaction)\]

-   단순히 트랜잭션만 관리하기 때문에 의존이 간단하다.

```
package io.hss27.catshostel.bootstrap.spring_transaction

import io.hss27.catshostel.application.port.`in`.usecase.ReactiveCatManagementUseCase
import org.springframework.transaction.annotation.Transactional

@Transactional
class TransactionalCatManagementService(
    private val catManagementService: ReactiveCatManagementUseCase
) : ReactiveCatManagementUseCase by catManagementService
```

\[스프링 트랜잭션으로 함수단위 트랜잭션이 적용된 Service Class\]

-   트랜잭션은 대리자 패턴으로 구현한다.

#### bootstrap - di

```
plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":application"))
    implementation(project(":adapter:in:web:ktor_web"))
    implementation(project(":adapter:out:persistence:exposed"))
    implementation(project(":bootstrap:transaction:exposed_transaction"))
    api("io.insert-koin:koin-logger-slf4j:$koin_logger_slf4j")
    api("io.insert-koin:koin-core:$koin_version")
}
```

\[build.gradle.kts(:bootstrap:di:koin)\]

```
plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.serialization")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":application"))
    implementation(project(":adapter:in:web:webflux_web"))
    implementation(project(":adapter:out:persistence:r2dbc"))
    implementation(project(":bootstrap:transaction:spring_transaction"))
}
```

\[build.gradle.kts(:bootstrap:di:spring\_ioc\_container)\]

-   모든 부분에서의 빈을 인스턴스화 하고 주입해야 하기때문에 DI모듈에서는 프로젝트의 모듈 전체에 각각 의존해야 한다.

```
val koinModule = module {
    single<CatCommandRepository> { ExposedCatRepository() }
    single<CatQueryRepository> { ExposedCatRepository() }
    // Transaction이 적용된 Service의 인스턴스를 주입한다.
    single<CatManagementUseCase> { TransactionalCatManagementService(CatManagementService(get(), get())) }
}
```

\[koin으로 구현한 DI\]

```
val applicationBeans = beans {
    bean {
        ReactiveCatManagementService(
            catQueryRepository = ref(),
            catCommandRepository = ref()
        )
    }
    // Transaction이 적용된 Service를 Primary로 설정한다.
    bean(isPrimary = true) {
        val catManagementService = ref<ReactiveCatManagementService>()
        TransactionalCatManagementService(
            catManagementService = catManagementService
        )
    }
}

val webBeans = beans {
    // ... 상세 코드 생략 ...
}
```

Spring Bean Container로 관리하는 DI

#### bootstrap - ktor, spring

```
plugins {
    kotlin("jvm")
}


dependencies {
    implementation(project(":adapter:in:web:ktor_web"))
    implementation(project(":adapter:out:persistence:exposed"))
    implementation(project(":bootstrap:di:koin"))
    implementation("io.insert-koin:koin-ktor:3.5.1")
    implementation(project(":application"))
}
```

\[build.gradle.kts(:bootstrap:ktor)\]

```
plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}


dependencies {
    implementation(project(":adapter:in:web:webflux_web"))
    implementation(project(":adapter:out:persistence:r2dbc"))
    implementation(project(":bootstrap:di:spring_ioc_container"))
    implementation(project(":application"))
}
```

\[build.gradle.kts(:bootstrap:spring)\]

-   엔트리 포인트 만들어 주고, 정의된 Controller와, DI 정의를 플러그인 한다.

```
fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDataSource() // 데이터 소스
    configureSerialization() // 직렬화 설정
    configureDi() // DI
    configureRouting() // Controller
}
```

Ktor Entry Point

```
@SpringBootApplication
@ComponentScan(basePackages = ["io.hss27"])
class CatsHostelApplication

fun main(args: Array<String>) {
    runApplication<CatsHostelApplication>(*args) {
        addInitializers(applicationBeans, webBeans) // DI 및 직렬화 설정
    }
}
```

Spring Entry Point

## 마무리

#### 느낀 점

-   단순한 CRUD라면 도메인 엔티티를 DTO처럼 그대로 사용할 수 있다.
-   직렬화나, 트랜잭션, 웹 프레임워크 같이 잘 변하지 않을 프레임 워크라면 도메인 수준에서 의존하게 하는 것으로 편의성을 향상시킬 수 있다.
-   그렇지 않으면 보일러 플레이트 코드가 상당히 많이 발생한다.
-   연습삼아 빡빡하게 모듈을 분리했고, 사이드 프로젝트에서도 빡빡하게 적용해 볼 생각이지만, 역시나 `은 탄환`은 없다. 상황에 맞춰 변형하는 것이 중요하다.

#### 생각해 볼 점

`도메인 로직에서 로깅코드(Cross Concern)을 분리시키려면 어떻게 하는 것이 좋을까?`

-   코틀린은 기본적으로 상속을 허용하지 않기 때문에 open키워드를 사용하지 않으면 프록시 패턴 사용이 불가능해서 스프링 AOP등을 사용할 수 없다.해결책 두 가지

1.  도메인 코드를 전부 open선언을 해준다.
2.  스프링이 사용될걸(그리고 프레임워크가 바뀌지 않을거라는걸) 어플리케이션 레벨에서 확정하고 @Component나 @Bean같은 어노테이션을 사용, All-Open 플러그인을 사용한다.

#### 글을 마치며

단순 CRUD API지만 무척 오래걸린 작업이었습니다.  
하지만 책으로만 아키텍쳐를 배웠을 때 보다 확실히 이해도가 올라가는 시간들이었습니다.  
계층간 완벽한 분리를 위해 DTO와 같은 보일러 플레이트 코드가 많이 발생하지만, 어플리케이션을 유연하게 관리할 수 있는 장점이 돋보이는 아키텍쳐라고 생각합니다.  
또, 패키지 구조가 굉장히 semantic하기 때문에, 내가 정의하는 코드의 패키지 위치를 특정하기 쉽고, 유지 보수시에도 특정 코드에 빠르고 수월하게 접근이 가능하리라 생각 됩니다.  
생각할 거리를 많이 던져주었기 때문에, 설계 및 구현 내내 상당히 재미있고 시간가는 줄 모르고 코딩하였습니다.  
당연히 완벽한 구조라고 생각하지 않고 오류가 많은 코드라고 생각되지만, 이제 남은 부분은 실제로 사이드 프로젝트를 진행하면서 더 배워나갈 생각입니다.  
이 글을 읽으신 분들도 작은 규모로나마 헥사고날 아키텍쳐를 적용해 보셨으면 하는 바람입니다.(배울점이 많고 재밌기 때문에!)  
  
  
[전체 코드 : https://github.com/SHINISEONG/CatsHostel](https://github.com/SHINISEONG/CatsHostel)  
참고 자료 : 만들면서 배우는 클린 아키텍쳐(톰 홈버그 | 위키북스), 우아한 기술 블로그 김현겸 님의 포스팅
