package uz.pdp.universitywithaddress.payload.university;

import lombok.Data;
import uz.pdp.universitywithaddress.model.university.University;

import javax.persistence.*;

@Data
public class FacultyDto {
    private String name;
    private Integer universityId;

}
