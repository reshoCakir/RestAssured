package day4_OpenMovieDb;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OpenMovieDBTest {

//http://www.omdbapi.com/?t=Bakur&apikey=3076aead

    @BeforeAll
    public static void setUp (){

       RestAssured.baseURI = "http://www.omdbapi.com" ;

    }
@AfterAll
    public static void tearDown(){
        reset();
}

@DisplayName("Test Search Movie or OpenMovieDB test")
    @Test
    public void testMovie (){

        given()
                .queryParam("apikey", "3076aead")
                .queryParam("t", "Bakur").
        when()
                .get().prettyPeek().   // we didn't put any think bc we already get our url
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("Title" ,is("Bakur"))
                .body("Ratings[0].Source", is("Internet Movie Database") )  /// bunu "find Json path" sitesinden aldik
                ;
}



@DisplayName("Getting the log of request and response")
    @Test
    public void testSendingRequestAndGetTheLog(){
    given()
            .queryParam("apikey", "3076aead")
            .queryParam("t", "Black Hawk Down")
            // logging the request should be in given section
             .log().all().
            when()
            .get().   // we didn't put any think bc we already get our url
            then()
    // logging the request should be in given section
               .log().all()
                .statusCode(is(200))
                .body("Plot", containsString("160 elite U.S."))
            .body("Ratings[0].Source",is("Internet Movie Database"))

            ;

}
}
