package io.processor.application;

import com.kevinten.vrml.core.beans.SpringContextConfigurator;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 一个示例程序，Controller将会接收Flight/Hotel两种类型的请求，然后分别执行Flight/Hotel两种类型的Processor处理链
 */
@SpringBootApplication
public class Application implements ApplicationContextAware {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextConfigurator.setStaticApplicationContext(applicationContext);
    }
}
