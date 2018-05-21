package ua.volkov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.volkov.App;

import java.util.Calendar;

@Configuration

public class AppConfig {

    @Bean
    public App app(){
        return new App();
    }

    public static boolean isDay(){
        Calendar cal= Calendar.getInstance();
        if(cal.get(Calendar.HOUR_OF_DAY) >8 && cal.get(Calendar.HOUR_OF_DAY)<=18){
            return true;
        }
        return false;
    }
}
