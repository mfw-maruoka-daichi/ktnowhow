# Ktnow-How

## 使用するライブラリ

- [x] GraphQL: GraphQL Kotlin
  - スキーマは自動生成させたい
- [x] FW: Spring Boot(WebFlux)
  - GraphQL Kotlinでktorも使えるが、スキーマ生成のFunctionを実装する必要がある
    - Springならアノテーションで自動生成
- [ ] Auth: Spring Security
- [ ] ORM Exposed(DBはMySQL)
  - Kotlinのもので別のを
- [ ] Test: Kotest + MockK
  - JUnitは書けることわかっているので
  - mockitoはAndroidのプロジェクトで使っているので
- [ ] Lint: detekt
  - ktlintはAndroidのプロジェクトで使っているので
- [ ] validation: konform
  - これのほうがKotlinっぽい書き方できるので
- [x] serialize: ~~kotlinx.serialization~~jackson
  - jsonだけだろうしkotlinx.serialization使ってみる
  - spring-bootがjacksonに依存しているのでjacksonで……
    - spring-boot 2.6からは使えそう https://github.com/spring-projects/spring-boot/issues/24238

