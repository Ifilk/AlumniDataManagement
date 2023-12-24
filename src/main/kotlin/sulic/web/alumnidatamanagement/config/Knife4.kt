package sulic.web.alumnidatamanagement.config

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableKnife4j
class Knife4 {
    @Bean
    fun springMemberOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(apiInfo())
    }
    @Bean
    fun apiInfo(): Info{
        return Info()
            .title("校友管理系统 API文档")
            .description("校友管理系统 Restful API接口文档 <br/> <font color=\"red\">测试阶段，数据库数据不会长期存储</font>")
            .contact(Contact()
                .name("Ifilk")
                .email("suaxc@yeah.net")
            )
            .version("v1.0")

    }
}