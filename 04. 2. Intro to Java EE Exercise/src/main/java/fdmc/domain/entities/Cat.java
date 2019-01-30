package fdmc.domain.entities;

public class Cat {
    private String name;
    private String breed;
    private String color;
    private int age;

    public Cat() {
    }

    public Cat(String name, String breed, String color, int age) {
        this.setName(name);
        this.setBreed(breed);
        this.setColor(color) ;
        this.setAge(age);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Breed: %s, Color: %s, Age: %d\n\r", this.getName(), this.getBreed(), this.getColor(), this.getAge());
    }
}

