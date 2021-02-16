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
}
