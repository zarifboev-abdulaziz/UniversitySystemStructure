package uz.pdp.universitywithaddress.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.universitywithaddress.model.address.Continent;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Integer> {
}
