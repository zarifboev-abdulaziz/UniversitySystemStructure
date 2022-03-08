package uz.pdp.universitywithaddress.payload.university;

import lombok.Data;
import uz.pdp.universitywithaddress.model.university.Subject;

import javax.persistence.*;
@Data
public class TeacherDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer subjectId;

}
