package ua.volkov.beans;

import org.springframework.stereotype.Component;
import ua.volkov.interfaces.Developer;


@Component
public class CppDeveloper implements Developer {
    public void writeCode() {
        System.out.println("Developer writes c++ code");
    }
}
