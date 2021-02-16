package hse.projectx.petdonate.network.transfer;

import hse.projectx.petdonate.network.models.DataState;
import lombok.Data;

@Data
public class UserStateRequest {
    DataState state;

    public UserStateRequest(DataState state)
    {
        this.state = state;
    }
}
