package ApiFourteenthHw;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

@DisplayName(value = "Домашнее задание №14 API")
public class CreateNotesTest {
    public String token;
    private List<Notes> notes;
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    @BeforeEach
    public void loginNotesTest() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "nikmir");
        params.put("password", "Qwerty$4xdd");

        JsonPath response = RestAssured.given()
                .queryParams(params)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();

        token = response.get("access_token");
    }

    @BeforeEach
    public void requestSpecCreateNote() {
        requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setBaseUri("http://172.24.120.5:8081")
                .setBasePath("/api/users/nikmir/notes")
                .setContentType("application/json")
                .build();

    }

    @BeforeEach
    public void responseSpecCreateNote() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .setDefaultParser(Parser.JSON)
                .build();
    }

    @Test
    @DisplayName(value = "Создание заметки со всеми параметрами")
    public void createFullNotes() {
        Notes notesFull = new Notes();
        notesFull.setName("TestFull");
        notesFull.setContent("ContentFull");
        notesFull.setColor("#fcba03");
        notesFull.setPriority(0);

        List<Notes> listNotes = new ArrayList<>();
        listNotes.add(notesFull);

        RestAssured.given(requestSpecification)
                .body(listNotes)
                .post()
                .then().log().all().spec(responseSpecification)
                .body("[0].name", equalTo("TestFull"),
                        "[0].content", equalTo("ContentFull"),
                        "[0].color", equalTo("#fcba03"),
                        "[0].priority", equalTo(0));


    }

    @Test
    @DisplayName(value = "Создание заметки только с параметром name")
    public void createNameNotes() {
        Notes notesFull = new Notes();
        notesFull.setName("TestFullName");

        List<Notes> listNotes1 = new ArrayList<>();
        listNotes1.add(notesFull);

        RestAssured.given(requestSpecification)
                .body(listNotes1)
                .post()
                .then().log().all().spec(responseSpecification)
                .body("[0].name", equalTo("TestFullName"));


    }

    @Test
    @DisplayName(value = "Создание заметки только с параметрами name и content")
    public void createNameContentNotes() {
        Notes notesFull = new Notes();
        notesFull.setName("TestNameContentName1");
        notesFull.setContent("ContentNameContent1");

        List<Notes> listNotes2 = new ArrayList<>();
        listNotes2.add(notesFull);

        RestAssured.given(requestSpecification)
                .body(listNotes2)
                .post()
                .then().log().all().spec(responseSpecification)
                .body("[0].name", equalTo("TestNameContentName"),
                        "[0].content", equalTo("ContentNameContent"));


    }
}
