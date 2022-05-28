package pe.isil.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.isil.model.City;
import pe.isil.model.Country;
import pe.isil.repository.CityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService implements BaseService<City, Integer> {

    @Autowired
    CityRepository cityRepository;

    @Override
    public void create(City city) {
        cityRepository.save(city);
    }

    @Override
    public List<City> read() {
        return cityRepository.findAll();
    }

    @Override
    public void delete(City city) {
        cityRepository.delete(city);
    }

    @Override
    public Optional<City> readById(Integer id) {
        return cityRepository.findById(id);
    }

    public void update(City city) {
        cityRepository.save(city);
    }
}
