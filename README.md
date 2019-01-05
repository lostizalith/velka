# velka
Financial Budget Instrument

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://travis-ci.com/lostizalith/velka.svg?branch=master)](https://travis-ci.com/lostizalith/velka)
[![Coverage Status](https://coveralls.io/repos/github/lostizalith/velka/badge.svg?branch=%2349-integrate-coveralls-with-jacoco)](https://coveralls.io/github/lostizalith/velka?branch=%2349-integrate-coveralls-with-jacoco)
[![DepShield Badge](https://depshield.sonatype.org/badges/lostizalith/velka/depshield.svg)](https://depshield.github.io)

[![Total alerts](https://img.shields.io/lgtm/alerts/g/lostizalith/velka.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/lostizalith/velka/alerts/)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/lostizalith/velka.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/lostizalith/velka/context:java)

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
export REPO_TOKEN=token
mvn clean test jacoco:report coveralls:report -DrepoToken=$REPO_TOKEN
```

or 

```bash
export REPO_TOKEN=token
mvn clean isntall
mvn coveralls:report -DrepoToken=$REPO_TOKEN
```

## Contribute

add `close #issue` in pull request
