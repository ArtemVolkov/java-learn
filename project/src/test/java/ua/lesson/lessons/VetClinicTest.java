package ua.lesson.lessons;

import org.junit.Test;

import static org.junit.Assert.*;

public class VetClinicTest {
    @Test (expected =IllegalArgumentException.class)
    public void addNewClient() throws Exception {
        VetClinic vet=new VetClinic();
        vet.addNewClient("Null");
    }

    @Test //(expected =IllegalArgumentException.class)
    public void addNewClient1() throws Exception {
        VetClinic vet=new VetClinic();
        vet.addNewClient(new String());
    }

    @Test(expected =UserException.class)
    public void addNewClient3() throws Exception {
        VetClinic vet=new VetClinic();
        vet.addNewClient("Jason");
        vet.addNewClient("Jason");
    }


}