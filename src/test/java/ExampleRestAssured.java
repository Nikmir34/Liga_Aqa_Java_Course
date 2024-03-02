import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class ExampleRestAssured {

    @Test
    public void exampleRestAssuredTest(){
        RestAssured.when()
                .get("http://172.24.120.5:8081/api/users/login_user_lesson/notes")
                .then()
                .statusCode(200);
    }
}
