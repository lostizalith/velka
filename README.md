# velka
Financial Budget Instrument

[![Build Status](https://travis-ci.com/lostizalith/velka.svg?branch=master)](https://travis-ci.com/lostizalith/velka)
[![Coverage Status](https://coveralls.io/repos/github/lostizalith/velka/badge.svg?branch=%2349-integrate-coveralls-with-jacoco)](https://coveralls.io/github/lostizalith/velka?branch=%2349-integrate-coveralls-with-jacoco)

## Run

```bash
mvn spring-boot:run
```

## Run Unit tests

```bash
mvn clean test
```

## Run Integration tests

```bash
mvn clean verify -P integration-test
```

## Run all tests

```bash
mvn clean verify -P all-tests
```

## Run coveralls

```bash
mvn clean test jacoco:report coveralls:report -DrepoToken=token
```

## Contribute

add `close #issue number` in pull request
