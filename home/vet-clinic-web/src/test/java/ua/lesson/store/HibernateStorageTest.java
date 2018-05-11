package ua.lesson.store;

import org.junit.Assert;
import org.junit.Test;
import ua.lesson.lessons.*;

import static org.junit.Assert.*;

public class HibernateStorageTest {

    private Storage hs = new HibernateStorage();
    private Storage jdbcs = new JdbcStorage();

    @Test
    public void generateIdTest(){
        //System.out.println(hs.generateClientId());
        Assert.assertEquals(jdbcs.generateClientId() , hs.generateClientId());
        Assert.assertEquals(jdbcs.generatePetId() , hs.generatePetId());
        Assert.assertEquals(jdbcs.generateProcedureId() , hs.generateProcedureId());
    }

    @Test
    public void addAndDeleteTest() throws Exception{
        int clId = hs.generateClientId();
        int petId= hs.generatePetId();
        int procId=hs.generateProcedureId();
        Client testClient = new Client();
        testClient.setId(clId);
        testClient.setName("John");
        testClient.setPassword("1234");
        Pet testPet =new Pet(petId, "July", "Cat");
        Procedure testProc = new Procedure(procId, "HealthCare", 100);

        testPet.addProcedure(testProc);
        testClient.addNewPet(testPet);
        clId= hs.addClient(testClient);
       // System.out.println(clId);
        Client getCl= hs.getClient(clId);

       /* System.out.println(testClient);
        System.out.println(getCl);*/

        Assert.assertTrue(testClient.equals(getCl));
         //editing
        testClient.setPassword("123456");
        testClient.getPets().get(0).setType("Catt");
        testClient.getPets().get(0).getProcedures().get(0).setPrice(150);
        hs.editClient(testClient);
        getCl= hs.getClient(clId);

       /* System.out.println(getCl.getPassword());
        System.out.println(getCl.getPets().get(0).getType());
        System.out.println(getCl.getPets().get(0).getProcedures().get(0).getPrice());*/
        assertTrue(testClient.equals(getCl));


        hs.deleteClient(getCl.getId());


    }

    @Test
    public void getClTest(){
        Client m = hs.getClient(3);


        Pet p = hs.getPet(6);
        Assert.assertTrue(p.equals(m.getPets().get(0)));

        Procedure pr = hs.getProcedure(3);
        Assert.assertTrue(pr.equals(m.getPets().get(0).getProcedures().get(0)));

        Client m1 = hs.findByClientName(m.getName());
        //System.out.println("M1= " + m1);
        Assert.assertTrue(m.equals(m1));

        Pet p1 = hs.findByPetName(p.getName());

        boolean b = p1.equals(p);
        boolean b1 = p.equals(p1);


        Assert.assertTrue(b);

        Procedure pr1 = hs.findByProcedureName(pr.getName());

        Assert.assertTrue(pr.equals(pr1));

    }

}