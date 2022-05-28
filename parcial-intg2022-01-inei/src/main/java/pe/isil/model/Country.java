package pe.isil.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor // Nos genera el constructor vacio
@AllArgsConstructor // Nos genera el constructor full
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryId;
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "country")
    private List<City> cities;
}
