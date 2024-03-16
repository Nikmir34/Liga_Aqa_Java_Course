package pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@DisplayName(value = "Домашнее задание №8-9 API")
public class Roles {
    private Integer id;
    private String name;

    public Roles() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return Objects.equals(id, roles.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Test
    @DisplayName(value = "Проверка условий equals + Серриализация и десерриализация с Jackson")
    public void equalsPropertyTest() throws IOException {

        Roles roles_first = new Roles();
        roles_first.setId(1);
        roles_first.setName("Test");

        Roles roles_second = new Roles();
        roles_second.setId(1);
        roles_second.setName("Test");

        Roles roles_third = new Roles();
        roles_third.setId(1);
        roles_third.setName("Test");

        // Рефлексивность
        System.out.println("Рефлексивность " + roles_first.equals(roles_first));


        // Симметричность
        System.out.println("Симметричность " + roles_first.equals(roles_second));
        System.out.println("Симметричность " + roles_second.equals(roles_first));

        // Транзитивность
        System.out.println("Транзитивность " + roles_first.equals(roles_second));
        System.out.println("Транзитивность " + roles_second.equals(roles_third));
        System.out.println("Транзитивность " + roles_first.equals(roles_third));

        // Консистентность
        System.out.println("Консистентность " + roles_first.equals(roles_second));

        // Условие NULL
        System.out.println("NULL " + roles_first.equals(null) + "\n");

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File("target/roles.json"), roles_first);
        Roles createRoles = objectMapper.readValue(new File("target/roles.json"), Roles.class);
        System.out.println(createRoles);


    }
}