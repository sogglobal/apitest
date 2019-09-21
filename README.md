# Author: Soala Gbenedio

# Project title: Coding Challenge - Marvel API - v1

## How to clone

```
git clone https://github.com/sogglobal/apitest.git
```


## How to download dependencies and build project

```
cd apitest

mvn clean install
```
## How to run project

```
mvn spring-boot:run -Dserver.port=8080
```

## How to run tests

```
mvn clean test
```

## URL examples

[default set (page 0, size 2) -> http://localhost:8080/v1/public/characters](http://localhost:8080/v1/public/characters)

[next page -> http://localhost:8080/v1/public/characters?page=1](http://localhost:8080/v1/public/characters?page=1)

[get 100 per page -> http://localhost:8080/v1/public/characters?size=100](http://localhost:8080/v1/public/characters?size=100)

[get by id -> http://localhost:8080/v1/public/characters/1](http://localhost:8080/v1/public/characters/1)

[get 404 error by id which does not exist -> http://localhost:8080/v1/public/characters/999](http://localhost:8080/v1/public/characters/999)


## Resources used

Project seed
https://hellokoding.com/restful-apis-example-with-spring-boot-integration-test-with-mockmvc-ui-integration-with-vuejs/

Paging
https://www.baeldung.com/rest-api-pagination-in-spring

Integration tests syntax
https://www.petrikainulainen.net/programming/spring-framework/integration-testing-of-spring-mvc-applications-write-clean-assertions-with-jsonpath/

Get mapping by id
https://stackoverflow.com/questions/19803731/spring-mvc-pathvariable

And many many more...