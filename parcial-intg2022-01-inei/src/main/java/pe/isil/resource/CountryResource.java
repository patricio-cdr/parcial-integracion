package pe.isil.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.model.Country;
import pe.isil.service.CountryService;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class CountryResource {

    private final CountryService countryService;

    // Obtener todos los countries
    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getCountries(){

        List<Country> countries = countryService.read();
        if (countries.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    // Obtener un country por su id
    @GetMapping("/countries/{countryId}")
    public ResponseEntity<Country> getCountryById(@PathVariable Integer countryId){

        return countryService.readById(countryId)
                .map(country -> new ResponseEntity<>(country, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    // Guardar un nuevo country
    @PostMapping("/countries")
    public ResponseEntity<Country> postCountry(@RequestBody Country country){
        countryService.create(country);
        return new ResponseEntity<>(country, HttpStatus.CREATED);
    }

    // Actualizar un country
    @PutMapping("/countries/{countryId}")
    public ResponseEntity<Country> putCountry(@RequestBody Country country,
                                        @PathVariable Integer countryId){

        return countryService.readById(countryId)
                .map(currentCountry -> {
                    country.setCountryId(countryId);
                    countryService.update(country);
                    return new ResponseEntity<>(country, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    // Borrar un Country
    @DeleteMapping("/countries/{countryId}")
    public ResponseEntity deleteCountry(@PathVariable Integer countryId){

        return countryService.readById(countryId)
                .map(s -> {
                    countryService.delete(s);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }
}
