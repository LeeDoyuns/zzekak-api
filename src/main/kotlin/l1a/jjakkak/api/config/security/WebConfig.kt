package l1a.jjakkak.api.config.security

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**CorsFilter 대응*/

@Configuration
internal class WebConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:8870", "https://zzekak.co.kr")
            .allowedHeaders("*")
        super.addCorsMappings(registry)
    }
}
