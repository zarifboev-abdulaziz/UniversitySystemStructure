package uz.pdp.universitywithaddress.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.universitywithaddress.model.address.Continent;
import uz.pdp.universitywithaddress.repository.address.ContinentRepository;

import java.util.List;

@RestController
@RequestMapping("/continent")
public class ContinentController {
    @Autowired
    ContinentRepository continentRepository;

    @GetMapping
    public List<Continent> getAllContinent(){
        return continentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Continent getOneContinent(@PathVariable Integer id){
        return continentRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public String deleteContinent(@PathVariable Integer id){

        try {
            continentRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    @PostMapping
    public String saveContinent(@RequestBody Continent continent){
        continentRepository.save(continent);
        return "Successfully saved";
    }

    @PutMapping("/{id}")
    public String editSchool(@PathVariable Integer id, @RequestBody Continent continent){
        Continent continentById = continentRepository.getById(id);
        continentById.setName(continent.getName());

        continentRepository.save(continentById);
        return "Successfully Edited";
    }



}
