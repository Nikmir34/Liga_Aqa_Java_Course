import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@DisplayName(value = "Домашнее задание №7 API")
public class ApiSeventhHwTest {
    @Test
    @DisplayName(value = "Создание заметки")
    public void queryParams() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "nikmir");
        params.put("password", "Qwerty$4xdd");

        ArrayList<Map<String, String>> arrayList = new ArrayList<>();
        Map<String, String> request = new HashMap<>();
        request.put("name", "Тестовый заголовок");
        request.put("content", "Тестовое содержание");
        request.put("color", "#4287f5");
        request.put("priority", "1");
        arrayList.add(request);

        JsonPath response = RestAssured.given()
                .queryParams(params)
                .get("http://172.24.120.5:8081/api/login")
                .jsonPath();

        String token = response.get("access_token");

        RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .body(arrayList)
                .contentType("application/json")
                .post("http://172.24.120.5:8081/api/users/nikmir/notes")
                .then().log().status()
                .statusCode(201);

    }
}