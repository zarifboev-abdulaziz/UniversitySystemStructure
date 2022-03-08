package uz.pdp.universitywithaddress.controller.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.universitywithaddress.model.university.Faculty;
import uz.pdp.universitywithaddress.model.university.Groups;
import uz.pdp.universitywithaddress.payload.university.GroupsDto;
import uz.pdp.universitywithaddress.repository.university.FacultyRepository;
import uz.pdp.universitywithaddress.repository.university.GroupsRepository;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupsController {
    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    FacultyRepository facultyRepository;

    //VAZIRLIK UCHUN
    //READ
    @GetMapping
    public List<Groups> getAllGroups(){
        return groupsRepository.findAll();
    }

    //UNIVERSITET MAS'UL XODIMI UCHUN
    @GetMapping("/byUniversityId/{universityId}")
    public List<Groups> getGroupsByUniversityId(@PathVariable Integer universityId) {
        return groupsRepository.findAllByFaculty_UniversityId(universityId);
    }

    //GET BY ID
    @GetMapping("/{id}")
    public Groups getOneGroup(@PathVariable Integer id){
        return groupsRepository.findById(id).get();
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Integer id){

        try {
            groupsRepository.deleteById(id);
        }catch (Exception e){
            return "Failed to delete";
        }
        return "Successfully deleted";
    }

    @PostMapping
    public String saveGroup(@RequestBody GroupsDto groupsDto){
        Faculty facultyById;
        try {
             facultyById = facultyRepository.findById(groupsDto.getFacultyId()).get();
        }catch (Exception e){
            return "Faculty Not Found";
        }

        Groups groups = new Groups();
        groups.setName(groupsDto.getName());
        groups.setFaculty(facultyById);

        groupsRepository.save(groups);
        return "Successfully Saved";
    }

    @PutMapping("/{id}")
    public String editGroup(@RequestBody GroupsDto groupsDto, @PathVariable Integer id){
        Groups editingGroup = groupsRepository.getById(id);
        editingGroup.setName(groupsDto.getName());

        Faculty facultyById = facultyRepository.getById(groupsDto.getFacultyId());
        editingGroup.setFaculty(facultyById);

        groupsRepository.save(editingGroup);
        return "Successfully edited";
    }

}
