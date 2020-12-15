package day5;

import Utility.ConfigurationReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AssertingCollectionTheChain {

    static String username = ConfigurationReader.getProperty("spartan.admin.username");
    static String password = ConfigurationReader.getProperty("spartan.admin.password");

    @BeforeAll
    public static void setup() {

        baseURI = ConfigurationReader.getProperty("spartan.base_url") ;

        basePath = ConfigurationReader.getProperty("spartan.basePath");


    }

    @AfterAll
    public static void tearDown() {
        reset();
    }


    @DisplayName("testing Get /api/spartans/search with basic auth")
    @Test
    public void testSearchAndExtractData() {


        // search for nameContains : a , gender Female
// verify status code is 200
// extract jsonPath object after validation
// use that jsonPath object to get the list of all results
// and get the numberOfElements field value
// compare those 2

       given()
                .auth().basic(username,password)
                .queryParam("nameContains", "a")
                .queryParam("gender", "Female")
                .log().all().
                        when()
                .get("/spartans/search").
                        then()
                .log().all()
                .assertThat()
                .statusCode(is(200))
                .body("numberOfElements", equalTo(34))
               .body("content.name", hasSize(34) )
       .body("content.name", everyItem(containsStringIgnoringCase("a")))
       .body("content.gender", everyItem(is("Female")))

                ;

    }
}
