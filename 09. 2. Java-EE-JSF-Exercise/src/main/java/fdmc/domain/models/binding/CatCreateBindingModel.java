package fdmc.domain.models.binding;

import javax.faces.convert.DateTimeConverter;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class CatCreateBindingModel {
    private String name;
    private String breed;
    private String color;
    private Integer age;
    private String gender;
    private BigDecimal price;
    private Date date;
    private Boolean hasPassport;

    public CatCreateBindingModel() {
    }

    @NotNull
    @Size(min = 2, max = 10)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min = 5, max = 20)
    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @NotNull
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @NotNull
    @Min(1)
    @Max(31)
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @NotNull
    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @NotNull
    @DecimalMin(value = "0.01")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull
    public Date getDate() {
        return  this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @NotNull
    public Boolean getHasPassport() {
        return this.hasPassport;
    }

    public void setHasPassport(Boolean hasPassport) {
        this.hasPassport = hasPassport;
    }
}
