package ua.lesson.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lesson.lessons.Pet;

@Repository
public interface PetDAO extends JpaRepository<Pet, Integer> {

    public Pet findPetByName(String name);

    @Query(value = "select max(pid) from Pet")
    public int getMaxId();
}
