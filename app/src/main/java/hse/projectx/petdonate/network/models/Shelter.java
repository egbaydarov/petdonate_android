package hse.projectx.petdonate.network.models;


import java.io.Serializable;

import lombok.Data;

@Data
public class Shelter implements Serializable {
    private Long id;
    private String name;
    private String location;
    private String url;
    private String email;
    private String phone_number;
    private String account;
    private String picture;

    public Shelter(String name, String location, String url, String email, String phone_number, String account, String picture) {
        this.name = name;
        this.location = location;
        this.url = url;
        this.email = email;
        this.phone_number = phone_number;
        this.account = account;
        this.picture = picture;
    }
}
