package ua.lesson.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lesson.dao.ClientDAO;
import ua.lesson.dao.PetDAO;
import ua.lesson.dao.ProcedureDAO;
import ua.lesson.lessons.Client;
import ua.lesson.lessons.Pet;
import ua.lesson.lessons.Procedure;
import ua.lesson.lessons.UserException;

import java.util.Collection;

@Service
public class SpringDataStorage implements Storage {

    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private PetDAO petDAO;
    @Autowired
    private ProcedureDAO procedureDAO;


    @Override
    public Collection<Client> getClients() {
        return clientDAO.findAll();
    }

    @Override
    @Transactional
    public int addClient(Client client) throws UserException {
        if(clientDAO.existsById(client.getId())) throw new UserException("User already exists in db");
        clientDAO.save(client);
        return client.getId();
    }

    @Override
    @Transactional
    public void editClient(Client client) {
        clientDAO.save(client);
    }

    @Override
    @Transactional
    public void deleteClient(int id) {
        clientDAO.deleteById(id);
    }

    @Override
    public Client getClient(int id) {
        return clientDAO.getOne(id);
    }

    @Override
    public Client findByClientName(String login) {
        return clientDAO.findClientByName(login);
    }

    @Override
    public int generateClientId() {
        return 1+ clientDAO.getMaxId();
    }

    @Override
    public Collection<Pet> getPetsList(int clientId) {
        return petDAO.findAll();
    }

    @Override
    @Transactional
    public int addPet(int clientId, Pet pet) throws UserException {
        if(petDAO.existsById(pet.getId())) throw new UserException("Pet already exists in db");
        if(!clientDAO.existsById(clientId)) throw new IllegalStateException("Client does not exist");
        pet.setClient(clientDAO.getOne(clientId));
        petDAO.save(pet);
        return pet.getId();
    }

    @Override
    @Transactional
    public void editPet(Pet pet) {
        petDAO.save(pet);
    }

    @Override
    @Transactional
    public void deletePet(Pet pet) {
        petDAO.delete(pet);
    }

    @Override
    public Pet getPet(int id) {
        return petDAO.getOne(id);
    }

    @Override
    public Pet findByPetName(String name) {
        return petDAO.findPetByName(name);
    }

    @Override
    public int generatePetId() {
        return 1 + petDAO.getMaxId();
    }

    @Override
    public Collection<Procedure> getProceduresList(int petId) {
        return procedureDAO.findAll();
    }

    @Override
    @Transactional
    public int addProcedure(int petId, Procedure procedure) throws UserException {
        if(procedureDAO.existsById(procedure.getId())) throw new UserException("Procedure already exists in db");
        if(!petDAO.existsById(petId)) throw new IllegalStateException("Pet does not exist");
        procedure.setPet(petDAO.getOne(petId));
        procedureDAO.save(procedure);
        return procedure.getId();
    }

    @Override
    @Transactional
    public void editProcedure(Procedure procedure) {
        procedureDAO.save(procedure);
    }

    @Override
    @Transactional
    public void deleteProcedure(Procedure procedure) {
        procedureDAO.delete(procedure);
    }

    @Override
    public Procedure getProcedure(int id) {
        return procedureDAO.getOne(id);
    }

    @Override
    public Procedure findByProcedureName(String name) {
        return procedureDAO.findProcedureByName(name);
    }

    @Override
    public int generateProcedureId() {
        return 1 + procedureDAO.getMaxId();
    }

    @Override
    public void close() {
        clientDAO.flush();
        petDAO.flush();
        procedureDAO.flush();
    }


}
