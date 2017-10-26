package ua.lesson.store;

import ua.lesson.lessons.Client;
import ua.lesson.lessons.Pet;
import ua.lesson.lessons.Procedure;
import ua.lesson.lessons.UserException;

import java.util.Collection;

public class UserCache implements Storage {
    private static final UserCache INSTANCE = new UserCache();
    private Storage storage=new MemoryStorage();

    private UserCache(){}

    public static UserCache getInstance(){
        return INSTANCE;
    }


    @Override
    public Collection<Client> getClients() {
        return storage.getClients();
    }

    @Override
    public int addClient(Client client)throws UserException {
        return storage.addClient(client);
    }

    @Override
    public void editClient(Client client) {
        storage.editClient(client);
    }

    @Override
    public void deleteClient(int id) {
        storage.deleteClient(id);
    }

    @Override
    public Client getClient(int id) {
        return storage.getClient(id);
    }

    @Override
    public Client findByClientName(String login) {
        return storage.findByClientName(login);
    }

    @Override
    public int generateClientId() {
        return storage.generateClientId();
    }

    @Override
    public Collection<Pet> getPetsList(int clientId) {
        return storage.getPetsList(clientId);
    }

    @Override
    public int addPet(int clientId, Pet pet) throws UserException {
        return storage.addPet(clientId,pet);
    }

    @Override
    public void editPet(Pet pet) {
        storage.editPet(pet);
    }

    @Override
    public void deletePet(Pet pet) {
        storage.deletePet(pet);
    }

    @Override
    public Pet getPet(int id) {
        return storage.getPet(id);
    }

    @Override
    public Pet findByPetName(String name) {
        return storage.findByPetName(name);
    }

    @Override
    public int generatePetId() {
        return storage.generatePetId();
    }

    @Override
    public Collection<Procedure> getProceduresList(int petId) {
        return storage.getProceduresList(petId);
    }

    @Override
    public int addProcedure(int petId, Procedure procedure) throws UserException {
        return storage.addProcedure(petId, procedure);
    }

    @Override
    public void editProcedure(Procedure procedure) {
        storage.editProcedure(procedure);
    }

    @Override
    public void deleteProcedure(Procedure procedure) {

    }

    @Override
    public Procedure getProcedure(int id) {
        return storage.getProcedure(id);
    }

    @Override
    public Procedure findByProcedureName(String name) {
        return storage.findByProcedureName(name);
    }

    @Override
    public int generateProcedureId() {
        return storage.generateProcedureId();
    }

    @Override
    public void close() {
        this.storage.close();
    }
}
