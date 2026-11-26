package name.invikto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;

public class GetListOfObjectsIT {

    private static final String BASE_URI = "https://api.restful-api.dev";
    private static final String OBJECTS_PATH = "/objects";

    @Test
    void shouldReturnOkStatusCode() {
        given().baseUri(BASE_URI).log().all().when().get(OBJECTS_PATH).then().log().all().statusCode(SC_OK);
    }

    @Test
    void shouldReturnJson() {
        given().baseUri(BASE_URI).log().all().when().get(OBJECTS_PATH).then().log().all().header(CONTENT_TYPE, equalToIgnoringCase("application/json;charset=UTF-8"));
    }

    @Test
    void shouldReturnListOfObjects() {
        given().baseUri(BASE_URI).log().all().when().get(OBJECTS_PATH).then().log().all().body("$", instanceOf(List.class));
    }

    @Test
    void shouldReturnAllRequiredObjectProperties() {
        given().baseUri(BASE_URI).log().all().when().get(OBJECTS_PATH).then().log().all().body("[0]", allOf(hasKey("id"), hasKey("name"), hasKey("data"))).body("[0].id", not(blankOrNullString())).body("[0].name", not(blankOrNullString()));
    }

    @Test
    void shouldRespondQuickly() {
        given().baseUri(BASE_URI).log().all().when().get(OBJECTS_PATH).then().log().all().time(lessThanOrEqualTo(2000L));
    }
}
