package ua.lesson.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lesson.lessons.Client;

@Repository
public interface ClientDAO extends JpaRepository<Client, Integer> {

    public Client findClientByName(String name);

    @Query(value = "select max(uid) from Client")
    public int getMaxId();
}
