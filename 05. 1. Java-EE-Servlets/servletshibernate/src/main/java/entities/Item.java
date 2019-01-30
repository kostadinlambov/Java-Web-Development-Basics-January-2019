package entities;

import javax.jms.Message;
import javax.persistence.*;
import java.text.MessageFormat;

@Entity
public class Item {
    private int id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String result = String.format(
                "{" +
                        "\"id\": %d," +
                        "\"name\": \"%s\"" +
                        "}",
                getId(),
                getName());

        return result;
    }
}
