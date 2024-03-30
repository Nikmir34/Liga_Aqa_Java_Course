package ApiFifteenthHw;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
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

import static ApiFifteenthHw.Properties.BASE_URI;
import static ApiFifteenthHw.Properties.CREATE_NOTE_ENDPOINT;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.equalTo;

@DisplayName(value = "Домашнее задание №15 API")
public class ApiTest {
    public String token;
    private NoteDTO noteDTO;
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

    @Test
    public void createNoteTest() {
        noteDTO = NoteDTO.builder()
                .name("1")
                .content("1")
                .color("#fcba03")
                .priority(0)
                .build();

        requestSpecCreateNote(noteDTO);
        responseSpecCreateNote(201);
        postNotes();

    }

    @Test
    public void editNoteTest() {
        noteDTO = NoteDTO.builder().name("1")
                .content("1")
                .color("#fcba03")
                .priority(0)
                .build();

        requestSpecCreateNote(noteDTO);
        responseSpecCreateNote(200);
        putNotes();

    }

    @Test
    public void archiveNoteTest() {
        noteDTO = NoteDTO.builder().id(379)
                .name("1")
                .content("1")
                .color("#fcba03")
                .priority(0)
                .build();

        requestSpecCreateNote(noteDTO);
        responseSpecCreateNote(204);
        putNotes();
    }

    private void requestSpecCreateNote(NoteDTO noteDTO) {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(CREATE_NOTE_ENDPOINT)
                .addHeader("Authorization", "Bearer " + token)
                .setContentType(ContentType.JSON)
                .setBody(noteDTO)
                .build();
    }

    private void responseSpecCreateNote(int status) {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    private void postNotes() {
        RestAssured.given(requestSpecification).log().all()
                .post()
                .then().log().all().spec(responseSpecification)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createNote.json"))
                .body("[0].name", equalTo("1"))
                .body("[0].priority", equalTo(0));
    }

    private void putNotes() {
        RestAssured.given(requestSpecification).log().all()
                .put()
                .then().log().all().spec(responseSpecification);
    }

    private void deleteNotes() {
        RestAssured.given(requestSpecification).log().all()
                .delete()
                .then().log().all().spec(responseSpecification);
    }

}
