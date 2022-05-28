package pe.isil.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.model.City;
import pe.isil.model.Country;
import pe.isil.service.CityService;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class CityResource {

    private final CityService cityService;

    // Obtener todas las cities
    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCities(){

        List<City> cities = cityService.read();
        if (cities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    // Obtener un city por su id
    @GetMapping("/cities/{cityId}")
    public ResponseEntity<City> getCityById(@PathVariable Integer cityId){

        return cityService.readById(cityId)
                .map(country -> new ResponseEntity<>(country, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    // Guardar un nuevo city
    @PostMapping("/cities")
    public ResponseEntity<City> postCity(@RequestBody City city){
        cityService.create(city);
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }

    // Actualizar un city
    @PutMapping("/cities/{cityId}")
    public ResponseEntity<City> putCountry(@RequestBody City city,
                                              @PathVariable Integer cityId){

        return cityService.readById(cityId)
                .map(currentCountry -> {
                    city.setCityId(cityId);
                    cityService.update(city);
                    return new ResponseEntity<>(city, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    // Borrar un city
    @DeleteMapping("/cities/{cityId}")
    public ResponseEntity deleteCity(@PathVariable Integer cityId){

        return cityService.readById(cityId)
                .map(s -> {
                    cityService.delete(s);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

}
