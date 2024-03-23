package pojo;

import io.restassured.RestAssured;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class User {
    private String login;
    private String password;
    private String email;
    private List<RolesLombok> roles;
    private List<Notes> notes;


    public void setDefaultRoles() {
        RolesLombok defaultRole = new RolesLombok();
        defaultRole.setId("2");
        defaultRole.setName("ROLE_USER");

        List<RolesLombok> defaultListRole = new ArrayList<>();
        defaultListRole.add(defaultRole);

        this.roles = defaultListRole;
    }

    public void setDefaultNotes() {
        Notes defaultNotes = new Notes();
        defaultNotes.setName("TestNotes");
        defaultNotes.setContent("TestContent");
        defaultNotes.setColor("#7CFC00");
        defaultNotes.setPriority(0);

        List<Notes> defaultListNotes = new ArrayList<>();
        defaultListNotes.add(defaultNotes);

        this.notes = defaultListNotes;
    }

    @Test
    @DisplayName(value = "Домашнее задание №11 API")
    public void createNewUser() {
        User newUser = new User();
        newUser.setLogin("Dtoshnik10");
        newUser.setPassword("Qwerty$4");
        newUser.setEmail("hotboyxxx228@ya.ru");
        newUser.setDefaultNotes();
        newUser.setDefaultRoles();

        UserCreationDTO userCreationDTO = new UserCreationDTO();
        userCreationDTO.setLogin(newUser.getLogin());
        userCreationDTO.setPassword(newUser.getPassword());
        userCreationDTO.setEmail(newUser.getEmail());
        userCreationDTO.setRoles(newUser.getRoles());
        userCreationDTO.setNotes(newUser.getNotes());

        RestAssured.given().log().all()
                .body(userCreationDTO)
                .contentType("application/json")
                .post("http://172.24.120.5:8081/api/registration")
                .then().log().all()
                .statusCode(201);
    }

}
