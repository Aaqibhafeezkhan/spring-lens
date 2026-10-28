package com.sdlcpro.springlens.autoconfigure;

import com.sdlcpro.springlens.annotation.SpringLensInternalComponent;
import com.sdlcpro.springlens.exposure.AbstractSpringLensHttpMessageConverter;
import com.sdlcpro.springlens.exposure.GsonSpringLensHttpMessageConverter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Role;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@AutoConfiguration
@SpringLensInternalComponent
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({
        WebMvcConfigurer.class,
        HttpMessageConverter.class,
        AbstractSpringLensHttpMessageConverter.class
})
@Import(SpringLensHttpExposureAutoConfiguration.GsonSpringLensHttpMessageConverterConfiguration.class)
public class SpringLensHttpExposureAutoConfiguration {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @ConditionalOnBean(AbstractSpringLensHttpMessageConverter.class)
    public SpringLensHttpMessageConverterConfigurer springLensHttpMessageConverterConfigurer(
            AbstractSpringLensHttpMessageConverter httpMessageConverter) {
        return new SpringLensHttpMessageConverterConfigurer(httpMessageConverter);
    }

    @SpringLensInternalComponent
    private record SpringLensHttpMessageConverterConfigurer(AbstractSpringLensHttpMessageConverter httpMessageConverter)
            implements WebMvcConfigurer {

        @Override
        public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
            converters.add(0, this.httpMessageConverter);
        }
    }

    @Configuration
    @SpringLensInternalComponent
    @ConditionalOnClass(GsonSpringLensHttpMessageConverter.class)
    static class GsonSpringLensHttpMessageConverterConfiguration {

        @Bean
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        @ConditionalOnMissingBean(GsonSpringLensHttpMessageConverter.class)
        public GsonSpringLensHttpMessageConverter gsonSpringLensHttpMessageConverter() {
            return new GsonSpringLensHttpMessageConverter();
        }
    }
}
