package sulic.web.alumnidatamanagement.config

import cn.dev33.satoken.interceptor.SaInterceptor
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import xyz.ifilk.com.example.labmembermanagersystem.config.LogInterceptor


@Configuration
@CrossOrigin(origins = ["*"])
class WebConfigurerAdapter: WebMvcConfigurer {
    @Value("\${web.upload-path}")
    private val uploadPath: String? = null

    @Bean
    fun logInterceptor(): LogInterceptor {
        return LogInterceptor()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(logInterceptor())
//            .addPathPatterns("/**")
//                .excludePathPatterns("/testxx.html");

        registry.addInterceptor(SaInterceptor()).addPathPatterns("/api/**")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/api/res/**").addResourceLocations("file:$uploadPath/")
        super.addResourceHandlers(registry)
    }
}