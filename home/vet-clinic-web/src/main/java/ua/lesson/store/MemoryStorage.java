package ua.lesson.store;

import ua.lesson.lessons.*;
import ua.lesson.models.VetClinicHolder;

import java.util.ArrayList;
import java.util.Collection;

public class MemoryStorage implements Storage {

    private VetClinic vet= VetClinicHolder.getInstance();



    @Override
    public Collection<Client> getClients() {
        return vet.getClients();
    }

    @Override
    public int addClient(Client client) throws UserException{
        vet.addNewClient(client);
        return client.getId();
    }

    @Override
    public void editClient(Client client) {

        ArrayList<Client> clients=(ArrayList<Client>)this.getClients();
        for(Client c: clients){
            if(client.getId() == c.getId()){
                clients.set(clients.indexOf(c), client);
                return;
            }
        }
    }

    @Override
    public void deleteClient(int id) {
       /* if(id<0 || id>= vet.clientCount())
            throw new IllegalArgumentException("Invalid id!");
        vet.getClients().remove(id);*/
       for(Client c: this.getClients()){
           if(id == c.getId()){
               this.getClients().remove(c);
               return;
           }
       }
    }

    @Override
    public Client getClient(int id) {
        /*if(id<0 || id>= vet.clientCount())
            throw new IllegalArgumentException("Invalid id!");
        return vet.getClients().get(id);*/
        for(Client c:this.getClients()){
            if(id == c.getId())
                return c;
        }
        throw new IllegalArgumentException("Invalid id!");
    }

    @Override
    public Client findByClientName(String login) {
        return vet.searchClientByName(login);
    }

    @Override
    public int generateClientId() {
        return Client.generateId();
    }


    @Override
    public Collection<Pet> getPetsList(int clientId) {
        return this.getClient(clientId).getPets();
    }

    @Override
    public int addPet(int clientId, Pet pet) throws UserException {
        this.getClient(clientId).addNewPet(pet);
        return pet.getId();
    }

    @Override
    public void editPet(Pet pet) {
        int clientId=pet.getClientId();
        if(clientId==-1) throw new  IllegalStateException("This pet doesn`t have clienId");
        int listIndex=-1;
        for(Pet p: this.getClient(clientId).getPets()){
            if(p.getId() == pet.getId()){
                listIndex=this.getClient(clientId).getPets().indexOf(p);
                break;
            }
        }

        this.getClient(clientId).getPets().set(listIndex, pet);
    }

    @Override
    public void deletePet(Pet pet) {
        int clientId=pet.getClientId();
        if(clientId==-1) throw new  IllegalStateException("This pet doesn`t have clienId");
        this.getClient(clientId).removePet(pet);

    }

    @Override
    public Pet getPet(int id) {
        for(Client c: vet.getClients()){
            for(Pet p: c.getPets()){
                if(p.getId() == id){
                    return p;
                }
            }
        }
        throw new IllegalStateException("Pet not found");
    }

    @Override
    public Pet findByPetName(String name) {
        for(Client c: vet.getClients()){
            if(!c.searchByPetName(name).getName().equals("Null")){
                return c.searchByPetName(name);
            }
        }
        throw new IllegalStateException("Pet doesn`t exist!");
    }

    @Override
    public int generatePetId() {
        return Pet.generateId();
    }

    @Override
    public Collection<Procedure> getProceduresList(int petId) {
        return this.getPet(petId).getProcedures();
    }

    @Override
    public int addProcedure(int petId, Procedure procedure) throws UserException {
        for(Client c: vet.getClients()){
            for(Pet p: c.getPets()){
                if(p.getId()== petId){
                    p.addProcedure(procedure.getName(), procedure.getPrice());
                    return p.getProcedures().size()-1;
                }
            }
        }
        throw new IllegalStateException("Can`t find pet to add procedure");
    }

    @Override
    public void editProcedure(Procedure procedure) {
        if(procedure.getPetId()!=-1){
            Pet p=this.getPet(procedure.getPetId());
            for(Procedure proc: p.getProcedures()){
                if(proc.getId()==procedure.getId()){
                    p.getProcedures().set(p.getProcedures().indexOf(proc),procedure);
                    return;
                }
            }
        }
        else {//if petId == -1
            for(Client c: vet.getClients()){
                for(Pet p: c.getPets()){
                    for(Procedure proc: p.getProcedures()){
                        if(proc.getId()==procedure.getId()){
                            procedure.setPet(p);
                            p.getProcedures().set(p.getProcedures().indexOf(proc),procedure);
                        }
                    }
                }
            }
        }

    }

    @Override
    public void deleteProcedure(Procedure procedure) {
        for(Client c: vet.getClients()){
            for(Pet p: c.getPets()){
                for(Procedure pr: p.getProcedures()){
                    if(pr.getId()==procedure.getId()){
                        p.getProcedures().remove(procedure);
                        return;
                    }
                }
            }
        }

    }

    @Override
    public Procedure getProcedure(int id) {
        for(Client c: vet.getClients()){
            for(Pet p: c.getPets()){
                for(Procedure proc: p.getProcedures()){
                    if(id==proc.getId()){
                        return proc;
                    }
                }
            }
        }
        throw new IllegalStateException("Procedure does not exist");
    }

    @Override
    public Procedure findByProcedureName(String name) {
        for(Client c: vet.getClients()){
            for(Pet p: c.getPets()){
                for(Procedure proc: p.getProcedures()){
                    if(name.equals(proc.getName())){
                        return proc;
                    }
                }
            }
        }
        throw new IllegalStateException("Procedure does not exist");
    }

    @Override
    public int generateProcedureId() {
        return Procedure.generateId();
    }

    @Override
    public void close() {
        this.getClients().clear();
    }


}
