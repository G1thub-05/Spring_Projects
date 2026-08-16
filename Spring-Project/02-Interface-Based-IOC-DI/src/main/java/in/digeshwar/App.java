package in.digeshwar;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main(String[] args) throws BeansException {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        ATM atm = context.getBean(ATM.class);
        atm.Sprint(100);
        atm.Hprint(200);

    }
}
