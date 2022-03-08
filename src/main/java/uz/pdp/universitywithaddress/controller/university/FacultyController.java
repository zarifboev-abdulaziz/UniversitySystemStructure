package uz.pdp.universitywithaddress.controller.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.universitywithaddress.model.university.Faculty;
import uz.pdp.universitywithaddress.model.university.Subject;
import uz.pdp.universitywithaddress.model.university.University;
import uz.pdp.universitywithaddress.payload.university.FacultyDto;
import uz.pdp.universitywithaddress.repository.university.FacultyRepository;
import uz.pdp.universitywithaddress.repository.university.UniversityRepository;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;


    //VAZIRLIK UCHUN
    @GetMapping
    public List<Faculty> getAllFaculty(){
        return facultyRepository.findAll();
    }

    //UNIVERSITET XODIMI UCHUN
    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId) {
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }

    //GET BY ID
    @GetMapping("/{id}")
    public Faculty getOneFaculty(@PathVariable Integer id){
        return facultyRepository.findById(id).get();
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id){

        try {
            facultyRepository.deleteById(id);
            return "Successfully deleted";
        }catch (Exception e){
            return "Failed to delete";
        }
    }

    //CREATE
    @PostMapping
    public String saveFaculty(@RequestBody FacultyDto facultyDto){
        boolean existsByNameAndUniversityId = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (existsByNameAndUniversityId)
            return "In this university, such faculty exists";

        University universityById = universityRepository.findById(facultyDto.getUniversityId()).get();

        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        faculty.setUniversity(universityById);
        facultyRepository.save(faculty);

        return "Successfully Saved";
    }

    //UPDATE
    @PutMapping("/{id}")
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDto facultyDto){
        Faculty editingFaculty = facultyRepository.getById(id);
        editingFaculty.setName(facultyDto.getName());

        University universityById = universityRepository.getById(facultyDto.getUniversityId());

        editingFaculty.setUniversity(universityById);

        facultyRepository.save(editingFaculty);
        return "Successfully edited";
    }

}
