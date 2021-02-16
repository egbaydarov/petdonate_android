package hse.projectx.petdonate.network.models;

public class Pet {

    private Long Id;



    private String Name;


    private double Hp;


    private double Food;


    private double Happiness;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getHp() {
        return Hp;
    }

    public void setHp(double hp) {
        Hp = hp;
    }

    public double getFood() {
        return Food;
    }

    public void setFood(double food) {
        Food = food;
    }

    public double getHappiness() {
        return Happiness;
    }

    public void setHappiness(double happiness) {
        Happiness = happiness;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id)
    {
        this.Id = Id;
    }
}