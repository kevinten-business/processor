package io.processor.template;

import com.kevinten.vrml.core.beans.SpringContextConfigurator;
import com.kevinten.vrml.core.tags.Todo;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class AppTemplate implements ApplicationContextAware {

    public static void main(String[] args) {
        SpringApplication.run(AppTemplate.class, args);
    }

    /**
     * 需要为启动类实现{@link ApplicationContextAware}接口，从而注入Spring上下文到静态容器{@link SpringContextConfigurator}中
     */
    @Todo
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextConfigurator.setStaticApplicationContext(applicationContext);
    }
}
