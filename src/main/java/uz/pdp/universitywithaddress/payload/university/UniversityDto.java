package uz.pdp.universitywithaddress.payload.university;

import lombok.Data;
import uz.pdp.universitywithaddress.model.address.Address;
import uz.pdp.universitywithaddress.model.address.District;

import javax.persistence.*;

@Data
public class UniversityDto {
    private String name;

    //AddressFields
    private String street;
    private String homeNumber;
    private Integer districtId;



}
