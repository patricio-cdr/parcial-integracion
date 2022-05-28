package pe.isil.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.isil.model.Country;
import pe.isil.repository.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService implements BaseService<Country, Integer> {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public void create(Country country) {
        countryRepository.save(country);
    }

    @Override
    public List<Country> read() {
        return countryRepository.findAll();
    }

    @Override
    public void delete(Country country) {
        countryRepository.delete(country);
    }

    @Override
    public Optional<Country> readById(Integer id) {
        return countryRepository.findById(id);
    }

    public void update(Country country) {
        countryRepository.save(country);
    }
}
