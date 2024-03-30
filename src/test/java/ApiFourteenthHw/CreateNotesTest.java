package ApiFourteenthHw;

import com.fasterxml.jackson.annotation.JsonInclude;
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


        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setBaseUri("http://172.24.120.5:8081")
                .setBasePath("/api/users/nikmir/notes")
                .setContentType("application/json")
                .setBody(listNotes)
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .setDefaultParser(Parser.JSON)
                .expectBody("[0].name", equalTo("TestFull"))
                .expectBody("[0].content", equalTo("ContentFull"))
                .expectBody("[0].color", equalTo("#fcba03"))
                .expectBody("[0].priority", equalTo(0))
                .build();

        RestAssured.given(requestSpecification)
                .post()
                .then().log().all().spec(responseSpecification);


    }

    @Test
    @DisplayName(value = "Создание заметки только с параметром name")
    public void createNameNotes() {
        Notes notesFull = new Notes();
        notesFull.setName("TestFullName");

        List<Notes> listNotes1 = new ArrayList<>();
        listNotes1.add(notesFull);


        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setBaseUri("http://172.24.120.5:8081")
                .setBasePath("/api/users/nikmir/notes")
                .setContentType("application/json")
                .setBody(listNotes1)
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .setDefaultParser(Parser.JSON)
                .expectBody("[0].name", equalTo("TestFullName"))
                .build();

        RestAssured.given(requestSpecification)
                .post()
                .then().log().all().spec(responseSpecification);


    }

    @Test
    @DisplayName(value = "Создание заметки только с параметрами name и content")
    public void createNameContentNotes() {
        Notes notesFull = new Notes();
        notesFull.setName("TestNameContentName");
        notesFull.setContent("ContentNameContent");

        List<Notes> listNotes2 = new ArrayList<>();
        listNotes2.add(notesFull);


        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .setBaseUri("http://172.24.120.5:8081")
                .setBasePath("/api/users/nikmir/notes")
                .setContentType("application/json")
                .setBody(listNotes2)
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .setDefaultParser(Parser.JSON)
                .expectBody("[0].name", equalTo("TestNameContentName"))
                .expectBody("[0].content", equalTo("ContentNameContent"))
                .build();

        RestAssured.given(requestSpecification)
                .post()
                .then().log().all().spec(responseSpecification);


    }
}
