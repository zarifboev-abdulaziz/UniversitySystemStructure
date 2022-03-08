package uz.pdp.universitywithaddress.controller.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.universitywithaddress.model.university.Faculty;
import uz.pdp.universitywithaddress.model.university.Groups;
import uz.pdp.universitywithaddress.model.university.Student;
import uz.pdp.universitywithaddress.payload.university.StudentDto;
import uz.pdp.universitywithaddress.repository.university.GroupsRepository;
import uz.pdp.universitywithaddress.repository.university.StudentRepository;
import uz.pdp.universitywithaddress.repository.university.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupsRepository groupsRepository;

    //1. VAZIRLIK
    @GetMapping("/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    //2. UNIVERSITY
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentListForUniversity(@PathVariable Integer universityId, @RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> allByGroup_faculty_universityId = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return allByGroup_faculty_universityId;
    }

    //3. FACULTY DEKANAT
    @GetMapping("/forFaculty/{facultyId}")
    public Page<Student> getStudentListForFaculty(@PathVariable Integer facultyId, @RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> allByGroup_facultyId = studentRepository.findAllByGroup_FacultyId(facultyId, pageable);
        return allByGroup_facultyId;
    }

    //4. GROUP OWNER
    @GetMapping("/forGroup/{groupId}")
    public Page<Student> getStudentListForGroup(@PathVariable Integer groupId, @RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> allByGroupId = studentRepository.findAllByGroupId(groupId, pageable);
        return allByGroupId;
    }

    //GET BY ID
    @GetMapping("/{id}")
    public Student getOneStudent(@PathVariable Integer id){
        return studentRepository.findById(id).get();
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id){

        try {
            studentRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    //CREATE
    @PostMapping
    public String saveStudent(@RequestBody StudentDto studentDto){
        Groups groupById = groupsRepository.findById(studentDto.getGroupId()).get();

        Student student = new Student();

        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setAge(studentDto.getAge());
        student.setGroup(groupById);

        studentRepository.save(student);
        return "Successfully saved";
    }

    //UPDATE
    @PutMapping("/{id}")
    public String editStudent(@PathVariable Integer id, @RequestBody StudentDto studentDto){
        Student editingStudent = studentRepository.findById(id).get();
        Groups groupById = groupsRepository.findById(studentDto.getGroupId()).get();

        editingStudent.setFirstName(studentDto.getFirstName());
        editingStudent.setLastName(studentDto.getLastName());
        editingStudent.setAge(studentDto.getAge());
        editingStudent.setGroup(groupById);

        studentRepository.save(editingStudent);
        return "Successfully edited";
    }

}
