package ua.lesson.lessons.Lesson_12;

import ua.lesson.lessons.*;

public class MultiVetClinic {
    private VetClinic vet=new VetClinic();
    private static String[] namesList=new String[]{"James", "John", "Jimmy", "Dean", "Abby"};

    /**
     * Client simulation class.
     * Client visit clinic with new pet and do procedure
     */
    class ClientThread extends Thread{
        private String name;
        private Client client;

        public ClientThread(String name){
            this.name=name;
            client=vet.searchClientByName(this.name);
            if(!client.getName().equals("Null")){
                this.start();
            }
        }

        public void run(){
            try{
                Pet pet= new Pet(Pet.generateId(),"Betty", "Cat");
                pet.addProcedure("Temperature", 10);
                pet.addProcedure("Injection", 5);

                client.addNewPet(pet);
                client.addNewPet("Bbb","bbb");
                Pet remove=client.searchByPetNameAndType("Bbb","bbb");
                client.removePet(remove);
            }catch (UserException e){System.err.println("UserException in ClientThread");}
        }
    }

    /**
     * Method add few clients in clients base
     */
    private void fillClinic() throws UserException {
         vet.addNewClient("James");
         vet.addNewClient("John");
         vet.addNewClient("Jimmy");
         vet.addNewClient("Dean");
         vet.addNewClient("Abby");
     }

    /**
     * Method create few Thread of clinic clients
     * @return clients array
     */
    private ClientThread[] createVisitors(){
        ClientThread[] result=new ClientThread[5];
         for(int i=0;i<5;i++){
             result[i]=this.new ClientThread(namesList[i]);
         }
         return result;
    }

    /**
     * Method wait while clients do all things
     * @param clients clients array
     */
    private static void serviceClients(ClientThread[] clients){
        for(ClientThread c:clients){
            try{
                c.join();
            }catch (InterruptedException e){
                System.err.println("Interrupted join!");
            }
        }
    }


    @Override
    public String toString() {
        return vet.toString();
    }

    public static void main (String[] args) throws UserException{
        MultiVetClinic multiClinic=new MultiVetClinic();
        multiClinic.fillClinic();
        ClientThread[] visitors=multiClinic.createVisitors();
        serviceClients(visitors);

        System.out.println(multiClinic);
    }
}


