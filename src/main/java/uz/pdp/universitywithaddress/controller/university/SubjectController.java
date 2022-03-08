package uz.pdp.universitywithaddress.controller.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.universitywithaddress.model.address.Continent;
import uz.pdp.universitywithaddress.model.university.Subject;
import uz.pdp.universitywithaddress.repository.university.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;


    //GET
    @GetMapping
    public List<Subject> getAllSubject(){
        return subjectRepository.findAll();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public Subject getOneSubject(@PathVariable Integer id){
        return subjectRepository.findById(id).get();
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id){

        try {
            subjectRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    //CREATE
    @PostMapping
    public String saveSubject(@RequestBody Subject subject){
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "This subject already exists";
        subjectRepository.save(subject);
        return "Successfully Saved";
    }

    //UPDATE
    @PutMapping("/{id}")
    public String editSubject(@PathVariable Integer id, @RequestBody Subject subject){
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "This subject already exists";

        Subject subjectById = subjectRepository.getById(id);
        subjectById.setName(subject.getName());
        subjectRepository.save(subjectById);
        return "Successfully Saved";
    }

}
