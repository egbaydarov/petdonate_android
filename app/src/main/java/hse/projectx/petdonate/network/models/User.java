package hse.projectx.petdonate.network.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private String picUrl;
    private LocalDateTime lastVisit;
    private Long transactionsCount;
}