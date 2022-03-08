package uz.pdp.universitywithaddress.payload.university;

import lombok.Data;
import uz.pdp.universitywithaddress.model.university.Groups;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer groupId;

}
