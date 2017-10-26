package ua.lesson.store;

import ua.lesson.lessons.Client;
import ua.lesson.lessons.Pet;
import ua.lesson.lessons.Procedure;
import ua.lesson.lessons.UserException;

import java.util.Collection;


/**
 * TODO: comment
 * @author parsentev
 * @since 29.04.2015
 */
public interface Storage {

    public Collection<Client> getClients();

    public int addClient(final Client client) throws UserException;

    public void editClient(final Client client);

    public void deleteClient(final int id);

    public Client getClient(final int id);

    public Client findByClientName(final String login) ;

    public int generateClientId();
    ///////////////////////Pets Methods

    public Collection<Pet> getPetsList(int clientId);

    public int addPet(int clientId,final Pet pet) throws UserException;

    public void editPet(final Pet pet);

    public void deletePet(final Pet pet);

    public Pet getPet(final int id);

    public Pet findByPetName(final String name);

    public int generatePetId();

    ///////////Procedure methods

    public Collection<Procedure> getProceduresList(int petId);

    public int addProcedure(int petId, final Procedure procedure) throws UserException;

    public void editProcedure(final Procedure procedure);

    public void deleteProcedure(Procedure procedure);

    public Procedure getProcedure(final int id);

    public Procedure findByProcedureName(final String name) ;

    public int generateProcedureId();

    public void close();
}
