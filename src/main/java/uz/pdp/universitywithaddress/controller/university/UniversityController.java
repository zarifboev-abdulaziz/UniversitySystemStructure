package uz.pdp.universitywithaddress.controller.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.universitywithaddress.model.address.Address;
import uz.pdp.universitywithaddress.model.address.District;
import uz.pdp.universitywithaddress.model.university.Subject;
import uz.pdp.universitywithaddress.model.university.University;
import uz.pdp.universitywithaddress.payload.university.UniversityDto;
import uz.pdp.universitywithaddress.repository.address.AddressRepository;
import uz.pdp.universitywithaddress.repository.address.DistrictRepository;
import uz.pdp.universitywithaddress.repository.university.UniversityRepository;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    AddressRepository addressRepository;

    //GET
    @GetMapping
    public List<University> getAllUniversity(){
        return universityRepository.findAll();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public University getOneUniversity(@PathVariable Integer id){
        return universityRepository.findById(id).get();
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteUniversity(@PathVariable Integer id){

        try {
            universityRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    //CREATE
    @PostMapping
    public String saveUniversity(@RequestBody UniversityDto universityDto){
        District districtById = districtRepository.getById(universityDto.getDistrictId());

        Address address = new Address();
        address.setStreet(universityDto.getStreet());
        address.setHomeNumber(universityDto.getHomeNumber());
        address.setDistrict(districtById);
        Address savedAddress = addressRepository.save(address);

        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);
        return "Successfully Saved";
    }

    //UPDATE
    @PutMapping("/{id}")
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto){
        University editingUniversity = universityRepository.getById(id);
        editingUniversity.setName(universityDto.getName());

        Address editingAddress = editingUniversity.getAddress();
        editingAddress.setStreet(universityDto.getStreet());
        editingAddress.setHomeNumber(universityDto.getHomeNumber());
        editingAddress.setDistrict(districtRepository.getById(universityDto.getDistrictId()));
        Address savedAddress = addressRepository.save(editingAddress);

        editingUniversity.setAddress(savedAddress);
        universityRepository.save(editingUniversity);
        return "Successfully Edited";
    }


}
