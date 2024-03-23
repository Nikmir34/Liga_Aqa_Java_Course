import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

@DisplayName(value = "Домашнее задание №12 API")
public class ApiTwelfthHw {

    public String token;

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
    @DisplayName(value = "Hamcrest проверки")
    public void getNoteTest() {
        RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get("http://172.24.120.5:8081/api/users/nikmir/notes/")
                .then().log().body()
                .statusCode(200)
                .body("[0].name", equalTo("Новая заметка №1"),
                        "[0].name", containsString("заметка"),
                        "[0].name", equalToIgnoringWhiteSpace("Новая       заметка    №1"),
                        "[0].color", startsWith("#c"),
                        "[0].id", equalTo(194),
                        "[0].id", not(greaterThan(194)));
    }
}
