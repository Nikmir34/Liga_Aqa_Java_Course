import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@DisplayName(value = "Домашнее задание №4 API")
public class ApiFourthHwTest {
    @Test
    @DisplayName(value = "Запрос с использованием queryParams")
    public void queryParams() {
        Map<String, String> params = new HashMap<>();
        params.put("name", "Заголовок");
        params.put("count", "4");

        RestAssured.given()
                .log().uri()
                .queryParams(params)
                .get("http://172.24.120.5:8081/api/users/nikmir/notes")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    @DisplayName(value = "Запрос с использованием formParams")
    public void formParams() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "nikmir");
        params.put("password", "Qwerty$4xdd");

        RestAssured.given()
                .log().uri()
                .queryParams(params)
                .get("http://172.24.120.5:8081/api/login")
                .then()
                .statusCode(200)
                .log().all();

    }
}
