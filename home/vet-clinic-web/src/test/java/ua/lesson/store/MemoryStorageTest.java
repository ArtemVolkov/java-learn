package ua.lesson.store;

import org.junit.Test;
import ua.lesson.lessons.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MemoryStorageTest {
    private Storage vet=new MemoryStorage();

    @Test
    public void addClientTest() throws Exception {
        assertEquals(0, vet.getClients().size());
        Client client =new Client(Client.generateId(), "John", "123");
        assertEquals(0, vet.getClients().size());
        vet.addClient(client);
        assertEquals(1, vet.getClients().size());
        assertEquals("John", vet.getClient(client.getId()).getName());
        vet.close();
    }

    @Test
    public void editClientTest() throws Exception {
        
        Client c=new Client(Client.generateId(),"John","123");
        vet.addClient(c);
        Client client= vet.findByClientName("John");
        int id=client.getId();
        client.setName("John Cena");
        assertEquals(1,vet.getClients().size());
        vet.editClient(client);
        assertEquals("John Cena", vet.getClient(id).getName());
        vet.close();
    }

    @Test
    public void deleteClientTest() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client =new Client(Client.generateId(), "Jimmy", "123");
        vet.addClient(client);
        assertEquals(1,vet.getClients().size());

        client=vet.findByClientName("Jimmy");
        vet.deleteClient(client.getId());
        assertEquals(0,vet.getClients().size());
        vet.close();
    }


    @Test
    public void getPetsListTest() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        vet.addClient(client);
        assertEquals(1,client.getPets().size());
        assertEquals(1,vet.getClient(client.getId()).getPets().size());
        vet.close();
    }


    @Test
    public void editPetTest() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        vet.addClient(client);
        Pet pet=vet.findByPetName("Betty");
        pet.setName("Bobby");
        vet.editPet(pet);
        client=vet.findByClientName("John");
        assertEquals("Bobby", client.getPets().get(client.getPets().size()-1).getName());
        vet.close();

    }

    @Test
    public void deletePetTest() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        vet.addClient(client);
        Pet pet= vet.findByPetName("Betty");
        vet.deletePet(pet);
        client=vet.findByClientName("John");
        assertEquals(0, client.getPets().size());
        vet.close();
    }

    @Test
    public void getPetTest() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        vet.addClient(client);
        Pet pet= vet.findByPetName("Betty");
        Pet pet1=vet.getPet(pet.getId());
        assertTrue(pet.equals(pet1));
        vet.close();
    }

    @Test
    public void getProceduresListTest() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        Pet pet=client.getPets().get(0);
        vet.addClient(client);
        Pet pet1=vet.findByPetName("Betty");
        pet.addProcedure("testProc", 15);
        assertEquals(1,pet1.getProcedures().size());
        assertEquals(1,vet.getProceduresList(pet.getId()).size());
        vet.close();
    }

    @Test
    public void editProcedureTest() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        Pet pe=client.getPets().get(0);
        pe.addProcedure("testProc1",10);
        vet.addClient(client);
        Pet pet=vet.findByPetName("Betty");
        Procedure proc=vet.findByProcedureName("testProc1");
        proc.setName("test");
        vet.editProcedure(proc);
        assertEquals(1,vet.getProceduresList(pet.getId()).size());

        assertEquals("test", pet.getProcedures().get(pet.getProcedures().size()-1).getName());
        vet.close();

    }

    @Test
    public void deleteProcedureTest() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        Pet pe=client.getPets().get(0);
        pe.addProcedure("test",10);
        vet.addClient(client);
        Procedure proc1= vet.findByProcedureName("test");
        Pet pet=vet.findByPetName("Betty");
        vet.deleteProcedure(proc1);
        assertEquals(0,pet.getProcedures().size());
        assertEquals(0,vet.getProceduresList(pet.getId()).size());
        vet.close();
    }

    @Test
    public void getProcedure() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        Pet pet=client.getPets().get(0);
        pet.addProcedure("testProc",10);
        vet.addClient(client);
        Procedure proc=vet.findByProcedureName("testProc");
        Procedure proc1=vet.getProcedure(proc.getId());
        assertTrue(proc.equals(proc1));
        vet.close();

    }

    @Test
    public void getClientsTest() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        vet.addClient(client);
        ArrayList<Client> clients=(ArrayList<Client>)vet.getClients();
        Client c=clients.get(0);
        assertEquals("John", c.getName());
        assertEquals(1,clients.size());
        vet.close();
    }

    @Test
    public void closeTest(){
        MemoryStorage vet=new MemoryStorage() ;
        try{
            vet.addClient(new Client(Client.generateId(),"N","N"));
        }catch (UserException e){
            e.printStackTrace();
        }
            vet.close();
        assertEquals(0, vet.getClients().size());
    }
}