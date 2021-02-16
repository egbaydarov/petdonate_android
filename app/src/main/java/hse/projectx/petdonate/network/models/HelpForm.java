package hse.projectx.petdonate.network.models;

import lombok.Data;

@Data
public class HelpForm {
    private Long shelter_id;
    private String name;
    private String Phone;
    private String email;
    private String date;
    private String extra;

    public HelpForm(Long shelter_id, String name, String phone, String email, String date, String extra) {
        this.shelter_id = shelter_id;
        this.name = name;
        Phone = phone;
        this.email = email;
        this.date = date;
        this.extra = extra;
    }
}
