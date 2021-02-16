package hse.projectx.petdonate.network.models;

import lombok.Data;

@Data
public class Transaction {
    String user_id;
    Long micros;
    Long shelter_id;
}