package in.digeshwar;

import in.digeshwar.config.AppConfig;
import in.digeshwar.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main(String[] args) {

        // Starting IOC Container
        ApplicationContext ctxt = new AnnotationConfigApplicationContext(AppConfig.class);

        // Getting Spring Bean obj from IoC
        UserService us = ctxt.getBean(UserService.class);
        us.getName();
    }
}
