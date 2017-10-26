package ua.lesson.lessons;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class VetClinicTest {
    @Test
    public void removeClient() throws Exception {
        VetClinic vet=new VetClinic();
        vet.addNewClient("John Doe");
        vet.addNewClient("William Doe");

        Client c=new Client(Client.generateId(),"William Doe","123");
        vet.removeClient(c);
        assertEquals(1, vet.clientCount());
    }

    @Test
    public void searchClientByName() throws Exception {
        VetClinic vet=new VetClinic();
        vet.addNewClient("John Doe");
        vet.addNewClient("William Doe");

        Client c=vet.searchClientByName("William Doe");
        assertEquals("William Doe", c.getName());
    }

    @Test
    public void seachClientByPetName() throws Exception {
        VetClinic vet=new VetClinic();
        vet.addNewClient("John Doe");
        vet.addNewClient("William Doe");

        Client c=vet.searchClientByName("William Doe");
        c.addNewPet("Jennifer","Cat");

        Client d=vet.searchClientByPetName("Jennifer");
        assertEquals("William Doe", d.getName());

        c=vet.searchClientByName("John Doe");
        c.addNewPet("J","Dog");

        d=vet.searchClientByPetName("Ja");
        assertEquals("Null",d.getName());

    }

    @Test
    public void seachAllClientsByPetName() throws Exception {
        VetClinic vet=new VetClinic();
        vet.addNewClient("John Doe");
        vet.addNewClient("William Doe");

        Client c=vet.searchClientByName("William Doe");
        assertEquals("William Doe" , c.getName());
        c.addNewPet("Jennifer","Cat");

        c=vet.searchClientByName("John Doe");
        assertEquals("John Doe" , c.getName());
        c.addNewPet("Jennifer","Dog");

        List<Client> d=vet.searchAllClientsByPetName("Jennifer");
        assertEquals(2,d.size());

        d=vet.searchAllClientsByPetName("j");
        assertEquals(0,d.size());
    }

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

    @Test
    public void equalsTest() throws UserException {
        Procedure p1=new Procedure(Procedure.generateId(),"Temp", 10);
        Procedure p2=new Procedure(Procedure.generateId(), "Temp", 10);
        assertEquals(true, p1.equals(p2));

        Pet pet1=new Pet(Pet.generateId(),"J","Cat");
        Pet pet2=new Pet(Pet.generateId(),"J","Cat");
        assertEquals(true, pet1.equals(pet2));

        pet1.addProcedure("T",10);
        pet2.addProcedure("T",10);
        assertEquals(true, pet1.equals(pet2));

        Client a= new Client(Pet.generateId(), "James", "123");
        Client b= new Client(Pet.generateId(), "James", "123");
        assertEquals(true,a.equals(b));

        a.addNewPet("Jen", "Cat");
        b.addNewPet("Jen", "Cat");
        assertEquals(true,a.equals(b));
    }


}