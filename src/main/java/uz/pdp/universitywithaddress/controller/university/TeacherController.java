package uz.pdp.universitywithaddress.controller.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.universitywithaddress.model.university.Student;
import uz.pdp.universitywithaddress.model.university.Subject;
import uz.pdp.universitywithaddress.model.university.Teacher;
import uz.pdp.universitywithaddress.payload.university.TeacherDto;
import uz.pdp.universitywithaddress.repository.university.SubjectRepository;
import uz.pdp.universitywithaddress.repository.university.TeacherRepository;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    SubjectRepository subjectRepository;

    //GET
    @GetMapping
    public List<Teacher> getAllTeacher(){
        return teacherRepository.findAll();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public Teacher getOneTeacher(@PathVariable Integer id){
        return teacherRepository.findById(id).get();
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Integer id){

        try {
            teacherRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    //CREATE
    @PostMapping
    public String saveTeacher(@RequestBody TeacherDto teacherDto){
        Subject subjectById = subjectRepository.findById(teacherDto.getSubjectId()).get();

        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setAge(teacherDto.getAge());
        teacher.setSubject(subjectById);

        teacherRepository.save(teacher);
        return "Successfully Saved";
    }

    //UPDATE
    @PutMapping("/{id}")
    public String editTeacher(@PathVariable Integer id, @RequestBody TeacherDto teacherDto){
        Teacher editingTeacher = teacherRepository.findById(id).get();
        Subject subjectById = subjectRepository.findById(teacherDto.getSubjectId()).get();

        editingTeacher.setFirstName(teacherDto.getFirstName());
        editingTeacher.setLastName(teacherDto.getLastName());
        editingTeacher.setAge(teacherDto.getAge());
        editingTeacher.setSubject(subjectById);

        teacherRepository.save(editingTeacher);
        return "Successfully edited;";
    }

}
