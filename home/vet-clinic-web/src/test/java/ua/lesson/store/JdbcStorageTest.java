package ua.lesson.store;

import org.junit.Test;
import ua.lesson.lessons.Client;
import ua.lesson.lessons.Pet;
import ua.lesson.lessons.Procedure;
import ua.lesson.lessons.UserException;

import static org.junit.Assert.*;

public class JdbcStorageTest {

    private Storage vet=new JdbcStorage();

    @Test
    public void JdbcOpenCloseTest(){
        vet=new JdbcStorage();
        vet.close();
    }
    @Test
    public void JdbcTest(){
        vet=new JdbcStorage();
        Client client=new Client(vet.generateClientId(),"Lucy","1234");
        Pet p=new Pet(vet.generatePetId(), "Betty", "Cat");
        p.setClient(client);
        Procedure proc=new Procedure(vet.generateProcedureId(),"TestProc",200);
        proc.setPet(p);
        p.addProcedure(proc);

        try {
            client.addNewPet(p);
            vet.addClient(client);
        } catch (UserException e) {
            e.printStackTrace();
        }

        Client client1=vet.getClient(client.getId());
        assertTrue(client.equals(client1));
        client1=vet.findByClientName("Lucy");
        assertTrue(client.equals(client1));

        Pet p1=vet.getPet(p.getId());
        assertTrue(p.equals(p1));
        p1=vet.findByPetName("Betty");
        assertTrue(p.equals(p1));

        Procedure proc1=vet.getProcedure(proc.getId());
        assertTrue(proc.equals(proc1));
        proc1=vet.findByProcedureName("TestProc");
        assertTrue(proc.equals(proc1));

        assertEquals(3,vet.getClients().size());
        assertEquals(1,vet.getPetsList(client1.getId()).size());
        assertEquals(1,vet.getProceduresList(p1.getId()).size());

        vet.deleteClient(client1.getId());
        assertEquals(2,vet.getClients().size());


    }

}