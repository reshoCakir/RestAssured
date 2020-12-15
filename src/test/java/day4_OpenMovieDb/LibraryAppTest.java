package day4_OpenMovieDb;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryAppTest {


    String username  = "librarian69@library";
    String password  = "KNPXrm3S" ;
    @BeforeAll
    public static void setup() {

        baseURI = "http://library1.cybertekschool.com";

        basePath = "/rest/v1";


    }
    @AfterAll
    public static void tearDown(){
        reset();
    }
    private static String myToken ;

    @DisplayName("Testing /login Endpoint")
    @Test
    public void testLogin(){
      myToken  =
        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S").
        when()
              .post("/login").
        then()
              .log().all()
              .statusCode(is(200))
              .contentType(ContentType.JSON)
              .body("token", is( not (emptyString() ) ) )
              .extract()
              .jsonPath()
              .getString("token")

        ;
        System.out.println("myToken = " + myToken);

        //How to extract some data out of response object
        // after doing validation in then section
        // without breaking the chain
    }


    @DisplayName("Testing GET /dashboard_stats Endpoint")
    @Test
    public void testzDashboard_stats(){

        given()
                .header("x-library-token", myToken).                            // this is how how we provide header .header("headerName", "headerValue")
           when()
                   .get("/dashboard_stats").
           then()
                .log().all()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                ;

    }
}
