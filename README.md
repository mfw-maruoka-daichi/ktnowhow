# Ktnow-How

## 使用するライブラリ

- [x] GraphQL: GraphQL Kotlin
  - スキーマは自動生成させたい
- [x] FW: Spring Boot(WebFlux)
  - GraphQL Kotlinでktorも使えるが、スキーマ生成のFunctionを実装する必要がある
    - Springならアノテーションで自動生成
- [ ] Auth: Spring Security
- [x] ORM: Exposed(DBは~~MySQL~~H2)
  - KotlinのものでKtorm以外のを
- [ ] Test: Kotest + MockK
  - JUnitは書けることわかっているので
  - mockitoはAndroidのプロジェクトで使っているので
- [ ] Lint: detekt
  - ktlintはAndroidのプロジェクトで使っているので
- [x] validation: konform
  - これのほうがKotlinっぽい書き方できるので
  - Kotestと連携できるのも良さそう https://github.com/kotest/kotest-assertions-konform
- [x] serialize: ~~kotlinx.serialization~~jackson
  - jsonだけだろうしkotlinx.serialization使ってみる
  - spring-bootがjacksonに依存しているのでjacksonで……
    - spring-boot 2.6からは使えそう https://github.com/spring-projects/spring-boot/issues/24238

