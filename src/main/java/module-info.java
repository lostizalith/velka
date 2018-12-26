open module velka {
    exports com.github.lostizalith.velka;

    requires java.instrument;
    requires java.sql;
    requires java.validation;

    requires org.hibernate.validator;

    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;

    requires spring.web;
    requires spring.webmvc;

    requires jdk.unsupported;
}
