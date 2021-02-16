package hse.projectx.petdonate.network.transfer;

import hse.projectx.petdonate.network.models.DataState;
import lombok.Data;

@Data
public class UserDataRequest {
    DataState state;

    public UserDataRequest(DataState state)
    {
        this.state = state;
    }
}
