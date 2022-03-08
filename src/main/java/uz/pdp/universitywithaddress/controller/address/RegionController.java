package uz.pdp.universitywithaddress.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.universitywithaddress.model.address.Country;
import uz.pdp.universitywithaddress.model.address.Region;
import uz.pdp.universitywithaddress.payload.address.RegionDto;
import uz.pdp.universitywithaddress.repository.address.CountryRepository;
import uz.pdp.universitywithaddress.repository.address.RegionRepository;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    CountryRepository countryRepository;

    @GetMapping
    public List<Region> getAllRegion(){
        return regionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Region getOneRegion(@PathVariable Integer id){
        return regionRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public String deleteRegion(@PathVariable Integer id){

        try {
            regionRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    @PostMapping
    public String saveRegion(@RequestBody RegionDto regionDto){
        Country countryById = countryRepository.getById(regionDto.getCountryId());

        Region region = new Region();
        region.setName(regionDto.getName());
        region.setCountry(countryById);

        regionRepository.save(region);
        return "Successfully saved";
    }

    @PutMapping("/{id}")
    public String editRegion(@RequestBody RegionDto regionDto, @PathVariable Integer id){
        Country countryById = countryRepository.getById(regionDto.getCountryId());

        Region regionById = regionRepository.getById(id);
        regionById.setCountry(countryById);
        regionById.setName(regionDto.getName());

        regionRepository.save(regionById);
        return "Successfully edited";
    }



}
