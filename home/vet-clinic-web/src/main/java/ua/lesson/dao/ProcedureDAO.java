package ua.lesson.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lesson.lessons.Procedure;

@Repository
public interface ProcedureDAO extends JpaRepository<Procedure, Integer> {

    public Procedure findProcedureByName(String name);

    @Query(value = "select max(pcid) from Procedure")
    public int getMaxId();
}
