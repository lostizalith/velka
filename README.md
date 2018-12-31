# velka
Financial Budget Instrument

[![Build Status](https://travis-ci.com/lostizalith/velka.svg?branch=master)](https://travis-ci.com/lostizalith/velka)

## Run

```sh
mvn spring-boot:run

or

mvn clean package

java --add-opens java.base/java.lang=spring.core \ 
--module-path=target/modules --module velka
```
