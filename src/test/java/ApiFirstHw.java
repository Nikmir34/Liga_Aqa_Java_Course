import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(value = "Домашнее задание №1 API")
public class ApiFirstHw {
    @DisplayName(value = "Проверка 403 кода")
    @Test
    public void statuscodeTest(){
        RestAssured.when()
                .get("http://172.24.120.5:8081/api/users/nikmir/notes/archive")
                .then()
                .statusCode(403);
    }
}
