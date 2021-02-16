package hse.projectx.petdonate.network.models;

import lombok.Data;

@Data
public class AdoptForm {

    private Long shelter_id;
    private Long animal_id;
    private String name;
    private String Phone;
    private String email;

    public AdoptForm(Long shelter_id, Long animal_id, String name, String phone, String email) {
        this.shelter_id = shelter_id;
        this.animal_id = animal_id;
        this.name = name;
        Phone = phone;
        this.email = email;
    }
}
