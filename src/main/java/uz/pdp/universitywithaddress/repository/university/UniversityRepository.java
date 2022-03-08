package uz.pdp.universitywithaddress.repository.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.universitywithaddress.model.university.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
