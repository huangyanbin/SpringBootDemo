package bootdemo.config;

import bootdemo.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by David on 2017/5/23.
 */
@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = "bootdemo")
@PropertySource(value = "classpath:application.properties",
        ignoreResourceNotFound = true,encoding = "UTF-8")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(interceptor)
        // 排除配置
        .excludePathPatterns("/error")
        .excludePathPatterns("/login**")
         .excludePathPatterns("/user/login**")
         .excludePathPatterns("/artAdd")
        // 拦截配置
        .addPathPatterns("/**");
    }
}
