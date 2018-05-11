package ua.lesson.store;


import org.hibernate.Session;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import ua.lesson.lessons.Client;
import ua.lesson.lessons.Pet;
import ua.lesson.lessons.Procedure;
import ua.lesson.lessons.UserException;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class HibernateStorage implements Storage {

    private SessionFactory sessionFactory; //= HibernateUtil.getSessionFactory();

    HibernateStorage(){
        sessionFactory= new Configuration().configure().buildSessionFactory();
    }

    private <T> T transaction(final Function<Session, T> command) {
        final Session session = sessionFactory.getCurrentSession();
        final Transaction tr = session.beginTransaction();
        T res=null;
        try{
            res= command.apply(session);
            tr.commit();
        }catch (Throwable e){
            tr.rollback();
        }
        /*finally {
            session.close();
        }*/
        return res;
    }

    @Override
    public Collection<Client> getClients() {
        return transaction((Session session) -> session.createQuery("from Client ").list());
    }

    @Override
    public int addClient(Client client) throws UserException {
        return transaction((Session session) ->{
            session.save(client);
            return client.getId();
        });
    }

    @Override
    public void editClient(Client client) {
        transaction((Session session) -> {
            session.update(client);
            return null;
        });
    }

    @Override
    public void deleteClient(int id) {
        transaction((Session session) -> {
            Client cl = session.get(Client.class, id);
            session.delete(cl);
            return null;
        });
    }

    @Override
    public Client getClient(int id) {
        return transaction((Session session) ->
             session.get(Client.class, id)
        );
    }

    @Override
    public Client findByClientName(String login) {
        return transaction((Session session) -> {
            Client cl = (Client) session.createQuery("from Client as client where client.name=:name1")
                    .setParameter("name1", login)
                    .getSingleResult();
            cl.getId();
            return cl;
        });

    }

    @Override
    public int generateClientId() {
        return 1 + transaction((Session session) ->
                (Integer) session.createSQLQuery("select max(client.uid) from client")
                        .getSingleResult()

        );
    }

    @Override
    public Collection<Pet> getPetsList(int clientId) {
        return transaction((Session session) ->
            session.createQuery("from Pet where client.id=:clId")
                    .setParameter("clId", clientId)
                    .list()
        );
    }

    @Override
    public int addPet(int clientId, Pet pet) throws UserException {
        return transaction((Session session) ->{
            pet.setClient(this.getClient(clientId));
            session.save(pet);
            return pet.getId();
        });
    }

    @Override
    public void editPet(Pet pet) {
        transaction((Session session) ->{
            session.update(pet);
            return null;
        });
    }

    @Override
    public void deletePet(Pet pet) {
        transaction((Session session) -> {
            session.delete(pet);
            return null;
        });
    }

    @Override
    public Pet getPet(int id) {
        return transaction((Session session) ->
                (Pet) session.get(Pet.class, id)
        );
    }

    @Override
    public Pet findByPetName(String name) {
        return transaction((Session session) ->{
            Pet p= (Pet) session.createQuery("from Pet as pet where pet.name =:petName")
                    .setParameter("petName" , name)
                    .iterate().next();
            p.getId();
            return p;
        });
    }

    @Override
    public int generatePetId() {
        return 1 + transaction((Session session) ->
                (Integer) session.createSQLQuery("select max(pet.pid) from pet")
                        .getSingleResult()
        );
    }

    @Override
    public Collection<Procedure> getProceduresList(int petId) {
        return transaction((Session session) ->
                session.createQuery("from Procedure where pet.id=:ptId")
                        .setParameter("ptId", petId)
                        .list()
        );
    }

    @Override
    public int addProcedure(int petId, Procedure procedure) throws UserException {
        return transaction((Session session) ->{
            procedure.setPet(this.getPet(petId));
            session.save(procedure);
            return procedure.getId();
        });
    }

    @Override
    public void editProcedure(Procedure procedure) {
        transaction((Session session) ->{
            session.update(procedure);
            return null;
        });
    }

    @Override
    public void deleteProcedure(Procedure procedure) {
        transaction((Session session) ->  {
            session.delete(procedure);
            return null;
        });
    }

    @Override
    public Procedure getProcedure(int id) {
        return transaction((Session session) ->
                (Procedure) session.get(Procedure.class, id)
        );
    }

    @Override
    public Procedure findByProcedureName(String name) {
       return transaction((Session session) ->{
           Procedure p= (Procedure) session.createQuery(
                   "from Procedure as pr where pr.name=:prName")
                   .setParameter("prName", name)
                   .getSingleResult();
           p.getId();
           return p;
       });
    }

    @Override
    public int generateProcedureId() {
        return 1 +  transaction((Session session) ->
                (Integer) session.createSQLQuery("select max(procedure.pcid) from procedure ")
                .getSingleResult()
        );
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}
