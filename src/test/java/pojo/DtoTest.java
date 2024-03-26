package pojo;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DtoTest {

    @Test
    @DisplayName(value = "Домашнее задание №11 API")
    public void createNewUser() {
        User newUser = new User();
        newUser.setLogin("Dtoshnik11");
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
