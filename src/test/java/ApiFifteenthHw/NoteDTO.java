package ApiFifteenthHw;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import pojo.Notes;

import java.util.List;

import static ApiFifteenthHw.Properties.BASE_URI;
import static ApiFifteenthHw.Properties.CREATE_NOTE_ENDPOINT;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class  NoteDTO {
    private Integer id;
    private String name;
    private String content;
    private String color;
    private Integer priority;
}


