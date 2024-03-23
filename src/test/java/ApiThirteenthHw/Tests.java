package ApiThirteenthHw;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Notes;
import pojo.RolesLombok;
import pojo.User;
import pojo.UserCreationDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DisplayName(value = "Домашнее задание №13 API")
public class Tests {
    public String token;
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
        defaultNotes.setName("TestNotes12");
        defaultNotes.setContent("TestContent12");
        defaultNotes.setColor("#7CFC55");
        defaultNotes.setPriority(0);

        List<Notes> defaultListNotes = new ArrayList<>();
        defaultListNotes.add(defaultNotes);

        this.notes = defaultListNotes;
    }

    @BeforeEach
    public void loginNotesTest() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "12APIUser");
        params.put("password", "Qwerty$4");

        JsonPath response = RestAssured.given()
                .queryParams(params)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();

        token = response.get("access_token");
    }

    @Test
    @DisplayName(value = "Создание пользователя")
    public void createUser() {
        User newUser = new User();
        newUser.setDefaultNotes();
        newUser.setDefaultRoles();

        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setLogin("12APIUser+54");
        createUserDTO.setPassword("Qwerty$4");
        createUserDTO.setEmail("test@ya.ru");
        createUserDTO.setRoles(newUser.getRoles());
        createUserDTO.setNotes(newUser.getNotes());

        RestAssured.given().log().all()
                .body(createUserDTO)
                .contentType("application/json")
                .post("http://172.24.120.5:8081/api/registration")
                .then().log().all()
                .statusCode(201);
    }

    @Test
    @DisplayName(value = "Проверка пользователя через DTO")
    public void checkUsers() {
        GetMeDTO getMeDTO = new GetMeDTO();
        getMeDTO.setId(60);
        getMeDTO.setLogin("12APIUser");
        getMeDTO.setEmail("test@ya.ru");

        GetMeDTO getMeDTOactual = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get("http://172.24.120.5:8081/api/users/me")
                .then().log().all()
                .statusCode(200).extract().body().as(GetMeDTO.class);

        Assertions.assertEquals(getMeDTO,getMeDTOactual);

    }

    @Test
    @DisplayName(value = "Проверка заметок через JSON Schema")
    public void checkNotes() {
        RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get("http://172.24.120.5:8081/api/users/12APIUser/notes")
                .then()
                .statusCode(200).assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
    }
}
