package pe.isil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
