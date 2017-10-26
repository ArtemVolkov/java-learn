package ua.lesson.store;

import ua.lesson.lessons.Client;
import ua.lesson.lessons.Pet;
import ua.lesson.lessons.Procedure;
import ua.lesson.lessons.UserException;
import ua.lesson.service.Settings;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;




public class JdbcStorage implements Storage {
    private final Connection connection;

    public JdbcStorage() {
        final Settings settings = Settings.getInstance();
        try { // create connection
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    //
    public Collection<Client> getClients() {
        final List<Client> users = new ArrayList<Client>();
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery("select uid, name,password from client")) {
            while (rs.next()) {
                int clientId=rs.getInt("uid");
                users.add(new Client(clientId,
                                     rs.getString("name"),
                                     (ArrayList<Pet>) this.getPetsList(clientId),
                                     rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    //
    public int addClient(Client client) throws UserException {
        try (final PreparedStatement statement = this.connection.prepareStatement(
                "insert into client (name, password) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getPassword());
            statement.executeUpdate();

            for(Pet p:client.getPetsList()){
                this.addPet(client.getId(), p);
                for(Procedure proc: p.getProcedures()){
                    this.addProcedure(p.getId(), proc);
                }
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new user");
    }

    @Override
    //
    public void editClient(Client client) {
        try (final PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE client SET name=(?), password=(?) WHERE id=(?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getPassword());
            statement.setInt(3, client.getId());
            statement.executeUpdate();
            for(Pet p: client.getPetsList()){

                for(Procedure proc: p.getProcedures()){
                    this.editProcedure(proc);
                }
                this.editPet(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    //
    public void deleteClient(int id) {//check
        try(PreparedStatement preparedStatement=this.connection.prepareStatement(
                "delete from client where uid= ?", Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    //
    public Client getClient(int id) {
        try (final PreparedStatement statement = this.connection.prepareStatement("select * from client where uid=(?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int clientId=rs.getInt("uid");
                    return new Client(clientId,
                            rs.getString("name"),
                            (ArrayList<Pet>) this.getPetsList(clientId),
                            rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User %s does not exists", id));
    }

    @Override
    //
    public Client findByClientName(String login) {
        try(final PreparedStatement statement = this.connection.prepareStatement(
                "select * from client where name=(?)", Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,login);
            try(final ResultSet rs = statement.executeQuery()){
                while (rs.next()){
                    int clientId=rs.getInt("uid");
                    return new Client(clientId,
                                      rs.getString("name"),
                                      (ArrayList<Pet>) this.getPetsList(clientId),
                                      rs.getString("password"));
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User %s does not exists", login));
    }

    @Override
    //
    public int generateClientId() {
        try(Statement statement = connection.createStatement()){
            ResultSet rs=statement.executeQuery("SELECT max(uid)+1 as id FROM client");
                return rs.getInt("id");

        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new IllegalStateException("can`t generateClientId");
    }

    @Override
    //
    public Collection<Pet> getPetsList(int clientId) {
        ArrayList<Pet> petsList=new ArrayList<Pet>();
        try(Statement statement=this.connection.createStatement()){
            ResultSet rs=statement.executeQuery("select * from pet where client_id="+ clientId);
            while (rs.next()){
                int petId=rs.getInt("uid");
                Pet pet=new Pet(petId,
                                rs.getString("name"),
                                rs.getString("type"),
                                (ArrayList<Procedure>) this.getProceduresList(petId),
                                rs.getInt("client_id"),
                                rs.getInt("age"));
                petsList.add(pet);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return petsList;
    }

    @Override
    //
    public int addPet(int clientId, Pet pet) throws UserException {
        try(PreparedStatement statement=this.connection.prepareStatement(
                "insert into pet (client_id, type, name, age) values((?),(?),(?),(?))")){
            statement.setInt(1,pet.getClientId());
            statement.setString(2,pet.getType());
            statement.setString(3,pet.getName());
            statement.setInt(4,pet.getAge());
            statement.executeUpdate();

            for(Procedure p: pet.getProcedures()){
                this.addProcedure(pet.getId(), p);
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new pet");
    }


    @Override
    //
    public void editPet(Pet pet) {
        try(PreparedStatement statement = this.connection.prepareStatement(
                "update pet set client_id=(?), type=(?), name=(?), age=(?) where uid=(?)")){
            statement.setInt(1,pet.getClientId());
            statement.setString(2,pet.getType());
            statement.setString(3,pet.getName());
            statement.setInt(4, pet.getAge());
            statement.setInt(5,pet.getId());

            for(Procedure proc: pet.getProcedures()){
                this.editProcedure(proc);
            }
            statement.executeUpdate();
        }catch (SQLException e){
                    e.printStackTrace();
        }
    }

    @Override
    //
    public void deletePet(Pet pet) {
        //1 cascade deleting pet procedures
        this.deleteProcedures(pet.getId());
        //2 delete pet
        try(PreparedStatement statement = this.connection.prepareStatement(
                "delete from pet where uid=(?)")){
            statement.setInt(1,pet.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Method delete all pets of client
     * @param clientId
     */
    public void deletePets(int clientId){
        for(Pet p: this.getPetsList(clientId)) {
            //1 cascade deleting pet procedures
            this.deleteProcedures(p.getId());
            //2 delete pets
            this.deletePet(p);
        }
    }

    @Override
    //
    public Pet getPet(int id) {
        try (final PreparedStatement statement = this.connection.prepareStatement(
                "select * from pet where uid=(?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int petId=rs.getInt("uid");
                    return new Pet(petId,
                                   rs.getString("name"),
                                   rs.getString("type"),
                                   (ArrayList<Procedure>) this.getProceduresList(petId),
                                   rs.getInt("client_id"),
                                   rs.getInt("age"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("Pet %s does not exists", id));
    }

    @Override
    //
    public Pet findByPetName(String name) {
        try(PreparedStatement statement = this.connection.prepareStatement(
                "select * from pet where name=(?)",Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,name);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                int petId=rs.getInt("uid");
                return new Pet(petId,
                        rs.getString("name"),
                        rs.getString("type"),
                        (ArrayList<Procedure>) this.getProceduresList(petId),
                        rs.getInt("client_id"),
                        rs.getInt("age"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("Pet %s does not exists", name));
    }

    @Override
    //
    public int generatePetId() {
        try(Statement statement= connection.createStatement()){
            ResultSet rs=statement.executeQuery("SELECT max(uid)+1 as id FROM pet");
            return rs.getInt("id");

        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new IllegalStateException("can`t generatePetId");
    }

    @Override
    //
    public Collection<Procedure> getProceduresList(int petId) {
        ArrayList<Procedure> procList=new ArrayList<Procedure>();
        try(Statement statement=this.connection.createStatement()){
            ResultSet rs=statement.executeQuery("select * from procedure where pet_id="+ petId);
            while (rs.next()){
                Procedure proc=new Procedure(rs.getInt("uid"),
                                             rs.getString("name"),
                                             rs.getDouble("coast"),
                                             rs.getDate("date"),
                                             rs.getInt("pet_id"));
                procList.add(proc);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return procList;
    }

    @Override
    public int addProcedure(int petId, Procedure procedure) throws UserException {
        return 0;
    }

    @Override
    public void editProcedure(Procedure procedure) {
        try(PreparedStatement statement = this.connection.prepareStatement(
                "update procedure set pet_id=(?), name=(?), coast=(?), date=(?) where uid=(?)")){
            statement.setInt(1,procedure.getPetId());
            statement.setString(2,procedure.getName());
            statement.setDouble(3,procedure.getPrice());
            statement.setDate(4, (java.sql.Date) procedure.getProcedureDate());
            statement.setInt(5,procedure.getId());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    //
    public void deleteProcedure(Procedure procedure) {
        int procId=procedure.getId();
        try(PreparedStatement statement= this.connection.prepareStatement(
                "delete from procedure where uid=(?)", Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1,procId);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //
    public void deleteProcedures(int petId){
        try(PreparedStatement statement= this.connection.prepareStatement(
                "delete from procedure where pet_id=(?)", Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1,petId);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    //
    public Procedure getProcedure(int id) {
        try (final PreparedStatement statement = this.connection.prepareStatement("select * from procedure where uid=(?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Procedure proc=new Procedure(rs.getInt("uid"),
                            rs.getString("name"),
                            rs.getDouble("coast"),
                            rs.getDate("date"),
                            rs.getInt("pet_id"));
                    return proc;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User %s does not exists", id));
    }

    @Override
    //
    public Procedure findByProcedureName(String name) {
        try (final PreparedStatement statement = this.connection.prepareStatement("select * from procedure where name=(?)")) {
            statement.setString(1, name);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Procedure proc=new Procedure(rs.getInt("uid"),
                            rs.getString("name"),
                            rs.getDouble("coast"),
                            rs.getDate("date"),
                            rs.getInt("pet_id"));
                    return proc;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User %s does not exists", name));
    }

    @Override
    //
    public int generateProcedureId() {
        try(Statement statement= connection.createStatement()){
            ResultSet rs=statement.executeQuery("SELECT max(uid)+1 as id FROM procedure");
            return rs.getInt("id");

        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new IllegalStateException("can`t generateProcedureId");
    }

    @Override
    //
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}