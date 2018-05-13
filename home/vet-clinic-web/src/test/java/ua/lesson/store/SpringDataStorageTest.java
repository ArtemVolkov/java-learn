package ua.lesson.store;

import org.junit.Assert;
import org.junit.Test;
import ua.lesson.lessons.Client;
import ua.lesson.lessons.Pet;
import ua.lesson.lessons.Procedure;


import static org.junit.Assert.*;

public class SpringDataStorageTest {

    Storage jdbcStorage = new JdbcStorage();
    Storage spDataStorage = new SpringDataStorage();

    @Test
    public void generateIdTest(){
        Assert.assertEquals(jdbcStorage.generateClientId(), spDataStorage.generateClientId());
        Assert.assertEquals(jdbcStorage.generatePetId(), spDataStorage.generatePetId());
        Assert.assertEquals(jdbcStorage.generateProcedureId(), spDataStorage.generateProcedureId());
    }

    @Test
    public void getCl(){
        Client cl=spDataStorage.getClient(3);
        System.out.println(cl);
    }

}