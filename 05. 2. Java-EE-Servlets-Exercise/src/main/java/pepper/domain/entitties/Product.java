package pepper.domain.entitties;

import javax.persistence.*;

@Entity(name = "products")
public class Product extends BaseEntity {
    private String name;
    private String description;
    private Type type;

    public Product() {
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String descriptions) {
        this.description = descriptions;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
