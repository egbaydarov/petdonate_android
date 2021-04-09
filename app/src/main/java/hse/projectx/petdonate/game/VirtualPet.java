package hse.projectx.petdonate.game;

import java.time.LocalDateTime;

import hse.projectx.petdonate.R;
import lombok.Data;

@Data
public class VirtualPet implements java.io.Serializable {
    String Name;
    String OwnerID;

    Integer Health;
    Integer Food;
    Integer Fun;
    Integer Energy;
    Location LocationID;
    LocalDateTime LastHFFEUpdate;

    public VirtualPet()
    {
        this.Name = GameConstants.DEFAULT_PET_NAME;
        Health = 80;
        Food = 80;
        Fun = 80;
        Energy = 80;
        LocationID = Location.Fun;
        OwnerID = GameConstants.DEFAULT_ID;
        LastHFFEUpdate = LocalDateTime.now();
    }


    public void setHealth(Integer Health) {
        if(Health > 100)
            Health = 100;
        else if (Health < 0)
            Health = 0;
        this.Health = Health;
        LastHFFEUpdate = LocalDateTime.now();
    }

    public void setFood(Integer Food) {
        if(Food > 100)
            Food = 100;
        else if (Food < 0)
            Food = 0;
        this.Food = Food;
        LastHFFEUpdate = LocalDateTime.now();
    }

    public void setFun(Integer Fun) {
        if(Fun > 100)
            Fun = 100;
        else if (Fun < 0)
            Fun = 0;
        this.Fun = Fun;
        LastHFFEUpdate = LocalDateTime.now();
    }

    public void setEnergy(Integer Energy) {
        if(Energy > 100)
            Energy = 100;
        else if (Energy < 0)
            Energy = 0;
        this.Energy = Energy;
        LastHFFEUpdate = LocalDateTime.now();
    }
}
