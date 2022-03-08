package uz.pdp.universitywithaddress.repository.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.universitywithaddress.model.university.Groups;

import java.util.List;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Integer> {

    List<Groups> findAllByFaculty_UniversityId(Integer faculty_university_id);

}
