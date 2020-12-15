package day3;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestGitHubRestAPITest {



    /// create a test for testing github rest api users/user endpoint

    @DisplayName("Test GFitHub get/users/{username}")
    @Test
    public void testGithub(){

        // provide your username as path variable in given section of the chain

        given()
                .pathParam("username", "reshoCakir").
        when()
                .get("https://api.github.com/users/{username}").
        then()
        .assertThat()
        .statusCode(is(200))
        .contentType(ContentType.JSON)
        .header("server", "GitHub.com")
            .body("login", is("reshoCakir"))
        ;
    }

}
