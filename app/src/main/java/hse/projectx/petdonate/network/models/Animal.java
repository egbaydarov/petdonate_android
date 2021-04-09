package hse.projectx.petdonate.network.models;


import java.io.Serializable;

import lombok.Data;
@Data
public class Animal implements Serializable {

    Long id;
    Long shelter_id;
    String type;
    String name;
    String appear;
    String behavior;
    String gender;
    String picture;

    public Animal(String type, String name, String appear, String behavior, String gender, String picture) {
        this.type = type;
        this.name = name;
        this.appear = appear;
        this.behavior = behavior;
        this.gender = gender;
        this.picture = picture;
    }

    public Animal() {
    }
}
