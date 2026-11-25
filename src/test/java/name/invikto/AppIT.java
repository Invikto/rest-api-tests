package name.invikto;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public class AppIT {

    @Test
    void shouldReturnNonEmptyListOfObjects() {
        given().baseUri("https://api.restful-api.dev").log().all().when().get("/objects").then().log().all().statusCode(200).body("$", not(empty()));
    }
}
