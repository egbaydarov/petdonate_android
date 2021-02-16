package hse.projectx.petdonate.network.models;

import lombok.Data;

@Data
public class DataState {
    public String name = "Egor", ID;
    public int cur_HP = 30, cur_Mana = 30, cur_Stamina = 30;
    public String type;
    public int skin;
    public int pts = 0;
    public long transactions_count;

    public DataState(String name, String ID, int cur_HP, int cur_Mana, int cur_Stamina,
                     String type, int skin, int pts, long transactions_count) {
        this.name = name;
        this.ID = ID;
        this.cur_HP = cur_HP;
        this.cur_Mana = cur_Mana;
        this.cur_Stamina = cur_Stamina;
        this.type = type;
        this.skin = skin;
        this.pts = pts;
        this.transactions_count = transactions_count;
    }
}
