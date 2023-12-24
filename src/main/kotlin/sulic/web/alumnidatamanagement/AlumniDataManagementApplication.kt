package sulic.web.alumnidatamanagement

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


// Generated by https://start.springboot.io
// 优质的 spring/boot/data/security/cloud 框架中文文档尽在 => https://springdoc.cn
@SpringBootApplication
@EnableJpaAuditing
class AlumniDataManagementApplication

fun main(args: Array<String>) {
    runApplication<AlumniDataManagementApplication>(*args)
}