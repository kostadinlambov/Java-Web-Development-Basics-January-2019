package fdmc.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "cats")
public class Cat extends BaseEntity {
    private String name;
    private String breed;
    private String color;
    private Integer age;
    private String gender;
    private BigDecimal price;
    private LocalDate date;
    private Boolean hasPassport;

    public Cat() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "breed", nullable = false)
    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Column(name = "color", nullable = false)
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "age", nullable = false)
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "gender", nullable = false)
    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "date", nullable = false)
    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column(name = "has_passport", nullable = false)
    public Boolean getHasPassport() {
        return this.hasPassport;
    }

    public void setHasPassport(Boolean hasPassport) {
        this.hasPassport = hasPassport;
    }
}
