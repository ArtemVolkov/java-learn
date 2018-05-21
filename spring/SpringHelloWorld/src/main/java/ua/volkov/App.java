package ua.volkov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ua.volkov.interfaces.Developer;
import ua.volkov.config.AppConfig;

import javax.annotation.Resource;

@Component
public class App {

    @Resource(name = "#{T(ua.volkov.config.AppConfig).isDay() ? 'javaDeveloper': 'cppDeveloper'}")
    private  Developer developer;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("ua.volkov.*");
        applicationContext.refresh();

        App app=applicationContext.getBean(App.class);
        app.developer.writeCode();
    }


}
