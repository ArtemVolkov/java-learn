package ua.lesson.store;

import org.junit.Test;
import ua.lesson.lessons.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class MemoryStorageTest {


    @Test
    public void addClient() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        synchronized (vet){
        assertEquals(0, vet.getClients().size());
        Client client =new Client(Client.generateId(), "John", "123");
        assertEquals(0, vet.getClients().size());
        vet.addClient(client);
        assertEquals(1, vet.getClients().size());
        assertEquals("John", vet.getClient(client.getId()).getName());
        vet.close();
        }

    }

    @Test
    public void editClient() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client c=new Client(Client.generateId(),"John","123");
        vet.addClient(c);
        Client client= vet.findByClientName("John");
        int id=client.getId();
        client.setName("John Cena");
        assertEquals(1,vet.getClients().size());
        vet.editClient(client);
        for(Client ce: vet.getClients()){
            System.out.println(ce.getName());
        }
        assertEquals("John Cena", vet.getClient(id).getName());
        vet.close();
    }

    @Test
    public void deleteClient() throws Exception {
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
    public void getPetsList() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        vet.addClient(client);
        assertEquals(1,client.getPetsList().size());
        assertEquals(1,vet.getClient(client.getId()).getPetsList().size());
        vet.close();
    }


    @Test
    public void editPet() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        vet.addClient(client);
        Pet pet=vet.findByPetName("Betty");
        pet.setName("Bobby");
        vet.editPet(pet);
        Client client1=vet.findByClientName("John");
        assertEquals("Bobby", client.getPetsList().get(client.getPetsList().size()-1).getName());
        vet.close();

    }

    @Test
    public void deletePet() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        vet.addClient(client);
        Pet pet= vet.findByPetName("Betty");
        vet.deletePet(pet);
        Client client1=vet.findByClientName("John");
        assertEquals(0, client.getPetsList().size());
        vet.close();
    }

    @Test
    public void getPet() throws Exception {
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
    public void getProceduresList() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        Pet pet=client.getPetsList().get(0);
        vet.addClient(client);
        Pet pet1=vet.findByPetName("Betty");
        pet.addProcedure("testProc", 15);
        assertEquals(1,pet1.getProcedures().size());
        assertEquals(1,vet.getProceduresList(pet.getId()).size());
        vet.close();
    }

    @Test
    public void editProcedure() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        Pet pe=client.getPetsList().get(0);
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
    public void deleteProcedure() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        Pet pe=client.getPetsList().get(0);
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
        Pet pet=client.getPetsList().get(0);
        pet.addProcedure("testProc",10);
        vet.addClient(client);
        Procedure proc=vet.findByProcedureName("testProc");
        Procedure proc1=vet.getProcedure(proc.getId());
        assertTrue(proc.equals(proc1));
        vet.close();

    }

    @Test
    public void getClients() throws Exception {
        MemoryStorage vet=new MemoryStorage();
        Client client = new Client(Client.generateId(),"John","123");
        client.addNewPet("Betty", "Cat");
        Pet pet=client.getPetsList().get(0);
        vet.addClient(client);
        ArrayList<Client> clients=(ArrayList<Client>)vet.getClients();
        Client c=clients.get(0);
        assertEquals("John", c.getName());
        assertEquals(1,clients.size());
        vet.close();
    }

    @Test
    public void close(){
        MemoryStorage vet=new MemoryStorage() ;
        try{
            vet.addClient(new Client(Client.generateId(),"N","N"));
        }catch (UserException e){}
            vet.close();
        assertEquals(0, vet.getClients().size());
    }
}