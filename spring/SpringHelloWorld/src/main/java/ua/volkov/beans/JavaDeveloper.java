package ua.volkov.beans;

import org.springframework.stereotype.Component;
import ua.volkov.interfaces.Developer;

@Component
public class JavaDeveloper implements Developer {
    public void writeCode() {
        System.out.println("Developer writes java code");
    }
}
