package ua.lesson.service;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class for database settings
 */
public class Settings {

    private static final Settings INSTANCE=new Settings();
    private final Properties properties=new Properties();

    private Settings(){
        try{
            properties.load(new FileInputStream(Settings.class.getClassLoader().getResource("db.properties").getFile()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Settings getInstance(){
        return INSTANCE;
    }

    public String value(String key){
        return this.properties.getProperty(key);
    }

}
