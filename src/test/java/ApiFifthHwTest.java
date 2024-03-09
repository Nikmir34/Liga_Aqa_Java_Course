import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.path.json.JsonPath;

import java.util.HashMap;
import java.util.Map;

@DisplayName(value = "Домашнее задание №5 API")
public class ApiFifthHwTest {
    @Test
    @DisplayName(value = "Получение refresh_token")
    public void refreshToken() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "nikmir");
        params.put("password", "Qwerty$4xdd");

        JsonPath response = RestAssured.given()
                .queryParams(params)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();

        String token = response.get("refresh_token");
        System.out.println("Полученный токен:\n" + token);

    }

    @Test
    @DisplayName(value = "Обработка ошибки refresh_token")
    public void refreshTokenAssert() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "nikmir");
        params.put("password", "Qwerty$4xdd");

        JsonPath response = RestAssured.given()
                .queryParams(params)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();

        String token = response.get("refresh_token");
        Assertions.assertNotNull(token, "Refresh_token не найден");
        System.out.println("Полученный токен:\n" + token);

    }
}
