import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@DisplayName(value = "Домашнее задание №6 API")
public class ApiSixthHwTest {
    @Test
    @DisplayName(value = "Передача Headers, cookie, cookies")
    public void loginNotesTest() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "nikmir");
        params.put("password", "Qwerty$4xdd");

        JsonPath response = RestAssured.given()
                .queryParams(params)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();

        String token = response.get("access_token");

        RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .cookie("cookieName", "cookieValue")
                .cookies("cookieName1", "cookieValue1", "cookieName2", "cookieValue2")
                .get("http://172.24.120.5:8081/api/users/nikmir/notes/archive")
                .then()
                .statusCode(200);
    }
}
