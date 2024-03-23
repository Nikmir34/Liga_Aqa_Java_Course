package pojo;

import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Data
@DisplayName(value = "Домашнее задание №10 API")
public class RolesLombok {

    private String id;
    private String name;

    @Test
    @DisplayName(value = "Оптимизация кода с Lombok")
    public void equalsPropertyTest() throws IOException {

        RolesLombok roles_first = new RolesLombok();
        roles_first.setId("2");
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
    }
}
