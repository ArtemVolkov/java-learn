package ua.lesson.store;

import ua.lesson.lessons.*;
import ua.lesson.models.VetClinicHolder;

import java.util.Collection;

public class MemoryStorage implements Storage {

    private VetClinic vet= new VetClinic();



    @Override
    public Collection<Client> getClients() {
        return vet.getClients();
    }

    @Override
    public int addClient(Client client) throws UserException{
        vet.addNewClient(client);
        //This is not a magic number. Add in last position. last position is size-1.
        int id=vet.getClients().size()-1;
        return id;
    }

    @Override
    public void editClient(Client client) {

    }

    @Override
    public void deleteClient(int id) {
        if(id<0 || id>= vet.clientCount())
            throw new IllegalArgumentException("Invalid id!");
        vet.getClients().remove(id);
    }

    @Override
    public Client getClient(int id) {
        if(id<0 || id>= vet.clientCount())
            throw new IllegalArgumentException("Invalid id!");
        return vet.getClients().get(id);
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
        return vet.getClients().get(clientId).getPetsList();
    }

    @Override
    public int addPet(int clientId, Pet pet) throws UserException {
        vet.getClients().get(clientId).addNewPet(pet);
        return pet.getId();
    }

    @Override
    public void editPet(Pet pet) {
        int clientId=-1;
        int listIndex=-1;
        for(Client c: vet.getClients()){
            for(Pet p: c.getPetsList()){
                if(p.getId() == pet.getId()){
                    clientId=c.getId();
                    listIndex=c.getPetsList().indexOf(p);
                    break;
                }
            }
        }

        vet.getClients().get(clientId).getPetsList().set(listIndex, pet);
    }

    @Override
    public void deletePet(Pet pet) {
        int clientId=pet.getClientId();
        if(clientId==-1) throw new  IllegalStateException("This pet doesn`t have clienId");

        vet.getClients().get(clientId).getPetsList().remove(pet);

    }

    @Override
    public Pet getPet(int id) {
        for(Client c: vet.getClients()){
            for(Pet p: c.getPetsList()){
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
        for(Client c: vet.getClients()){
            for(Pet p: c.getPetsList()){
                if(petId==p.getId())
                    return p.getProcedures();
            }
        }
        throw new IllegalStateException();
    }

    @Override
    public int addProcedure(int petId, Procedure procedure) throws UserException {
        for(Client c: vet.getClients()){
            for(Pet p: c.getPetsList()){
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
        for(Client c: vet.getClients()){
            for(Pet p: c.getPetsList()){
                for(Procedure proc: p.getProcedures()){
                    if(proc.getId()==procedure.getId()){
                        p.getProcedures().set(p.getProcedures().indexOf(proc),procedure);
                    }
                }
            }
        }
    }

    @Override
    public void deleteProcedure(Procedure procedure) {
        for(Client c: vet.getClients()){
            for(Pet p: c.getPetsList()){
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
            for(Pet p: c.getPetsList()){
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
            for(Pet p: c.getPetsList()){
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
