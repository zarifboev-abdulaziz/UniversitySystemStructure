package uz.pdp.universitywithaddress.repository.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.universitywithaddress.model.university.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    List<Faculty> findAllByUniversityId(Integer university_id);

    boolean existsByNameAndUniversityId(String name, Integer university_id);
}
