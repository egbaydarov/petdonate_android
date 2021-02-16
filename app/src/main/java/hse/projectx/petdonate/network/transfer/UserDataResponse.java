package hse.projectx.petdonate.network.transfer;

import hse.projectx.petdonate.network.models.DataState;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserDataResponse {
    DataState state;

    public UserDataResponse(DataState state, LocalDateTime sentTime) {
        this.state = state;
    }
}