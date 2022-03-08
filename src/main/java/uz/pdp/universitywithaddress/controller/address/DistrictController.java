package uz.pdp.universitywithaddress.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.universitywithaddress.model.address.District;
import uz.pdp.universitywithaddress.model.address.Region;
import uz.pdp.universitywithaddress.payload.address.DistrictDto;
import uz.pdp.universitywithaddress.repository.address.DistrictRepository;
import uz.pdp.universitywithaddress.repository.address.RegionRepository;

import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    RegionRepository regionRepository;

    @GetMapping
    public List<District> getAllDistrict(){
        return districtRepository.findAll();
    }

    @GetMapping("/{id}")
    public District getOneDistrict(@PathVariable Integer id){
        return districtRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public String deleteDistrict(@PathVariable Integer id){

        try {
            districtRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully deleted";
    }


    @PostMapping
    public String saveDistrict(@RequestBody DistrictDto districtDto){
        Region regionById = regionRepository.getById(districtDto.getRegionId());

        District district = new District();
        district.setName(districtDto.getName());
        district.setRegion(regionById);
        districtRepository.save(district);
        return "Successfully saved";
    }

    @PutMapping("/{id}")
    public String editDistrict(@RequestBody DistrictDto districtDto, @PathVariable Integer id){
        Region regionById = regionRepository.getById(districtDto.getRegionId());

        District districtById = districtRepository.getById(id);
        districtById.setRegion(regionById);
        districtById.setName(districtDto.getName());

        districtRepository.save(districtById);
        return "Successfully edited";
    }


}
