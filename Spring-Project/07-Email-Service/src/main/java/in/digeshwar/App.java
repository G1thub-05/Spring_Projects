package in.digeshwar;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("mybeans.xml");
        UserService us = context.getBean(UserService.class);
        us.register("Digeshwar", "Mr.Digeshwar05@gmail.com");
    }
}
