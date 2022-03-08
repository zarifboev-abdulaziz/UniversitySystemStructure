package uz.pdp.universitywithaddress.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.universitywithaddress.model.address.Continent;
import uz.pdp.universitywithaddress.model.address.Country;
import uz.pdp.universitywithaddress.payload.address.CountryDto;
import uz.pdp.universitywithaddress.repository.address.ContinentRepository;
import uz.pdp.universitywithaddress.repository.address.CountryRepository;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    ContinentRepository continentRepository;

    @GetMapping
    public List<Country> getAllCountry(){
        return countryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Country getOneCountry(@PathVariable Integer id){
        return countryRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public String deleteCountry(@PathVariable Integer id){

        try {
            countryRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    @PostMapping
    public String saveCountry(@RequestBody CountryDto countryDto){
        Continent continentById = continentRepository.getById(countryDto.getContinentId());
        Country country = new Country();

        country.setName(countryDto.getName());
        country.setContinent(continentById);

        countryRepository.save(country);
        return "Successfully saved";
    }

    @PutMapping("/{id}")
    public String editCountry(@RequestBody CountryDto countryDto, @PathVariable Integer id){
        Country editingCountry = countryRepository.getById(id);

        Continent continentById = continentRepository.getById(countryDto.getContinentId());

        editingCountry.setName(countryDto.getName());
        editingCountry.setContinent(continentById);

        countryRepository.save(editingCountry);
        return "Successfully edited";
    }


}
