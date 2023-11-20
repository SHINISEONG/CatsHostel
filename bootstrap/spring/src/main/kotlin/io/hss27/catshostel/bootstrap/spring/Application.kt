package io.hss27.catshostel.bootstrap.spring

import io.hss27.catshostel.bootstrap.di.spring_ioc_container.applicationBeans
import io.hss27.catshostel.bootstrap.di.spring_ioc_container.webBeans
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["io.hss27"])
class CatsHostelApplication

fun main(args: Array<String>) {
    runApplication<CatsHostelApplication>(*args) {
        addInitializers(applicationBeans, webBeans)
    }
}