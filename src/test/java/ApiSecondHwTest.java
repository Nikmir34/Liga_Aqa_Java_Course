import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(value = "Домашнее задание №2 API")
public class ApiSecondHwTest {

    @DisplayName(value = "Вызов log")
    @Test
    public void LogTest(){
        RestAssured.given()
                .get("https://randomuser.me/api")
                .then()
                .log().all()
                .statusCode(200);
    }

    @DisplayName(value = "Вызов prettyprint и log")
    @Test
    public void PrettyLogTest(){
        RestAssured.given()
                .log().all()
                .get("https://randomuser.me/api")
                .andReturn()
                .prettyPrint();
    }
}
