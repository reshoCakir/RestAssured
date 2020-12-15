package day4_OpenMovieDb;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanUpdatingTest {


    static String username = "admin";
    static String password = "admin";

    @BeforeAll
    public static void setup() {

        baseURI = "http://34.227.193.123:8000";

        basePath = "/api";


    }
    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing Put /api/spartans/{id} with string body")
    @Test
    public void updatingSingleSpartanStringBody(){

        String updatePayload = "{\n" +
                "        \"id\": 192,\n" +
                "        \"name\": \"Rojbin\",\n" +
                "        \"gender\": \"Female\",\n" +
                "        \"phone\": 9876543210\n" +
                "    }" ;
        given()
                .auth()
                .basic(username,password)
                .log().all()
                .contentType(ContentType.JSON)
                 .pathParam("id", 101)
                .body(updatePayload).
        when()
               .put("/spartans/{id}").
        then()
              .statusCode(is(204))
              .log().all()
              .header("Date", is(notNullValue()))

              ;
    }

    @DisplayName("Testing PARTIAL /api/spartans/{id} with string body")
    @Test
    public void partialUpdatingSingleSpartanStringBody(){

        String patchPayload = "{\"name\":\"Belcim\"" +

                "   }" ;
        given()
                .auth()
                .basic(username,password)
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", 99)
                .body(patchPayload).
                when()
                .patch("/spartans/{id}").
                then()
                .statusCode(is(204))
                .log().all()
                .header("Date", is(notNullValue()))

        ;
    }

    @DisplayName("Testing DELETE /api/spartans/{id} with string body")
    @Test
    public void deleteSingleSpartanStringBody(){

        given()
                .auth()
                .basic(username,password)
                .pathParam("id", 54)
                .log().all().
        when()
                .delete("/spartans/{id}").

        then()
                .log().all()
                .assertThat()
                .statusCode(is(204))
                .body(is(notNullValue()))
                ;
    }
}
