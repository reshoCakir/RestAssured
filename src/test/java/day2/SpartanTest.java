package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SpartanTest {

    @BeforeAll
    public static void setup(){
        //RestAssured.baseURI = "http://100.26.101.158:8000";
         baseURI = "http://100.26.101.158:8000" ;

         //RestAssured.basePath = "/api" ;
        basePath = "/api" ;
        // baseURI  + basePath + whatever you provided in http method like get post
        // for example
        // get("/spartans")  ---> get(baseURI + basePath + "/spartans")


    }

    @AfterAll
    public static void tearDown (){
         // resetting the value of baseURI, basePath to original value
        RestAssured.reset();

    }


@DisplayName("Testing/api/spartan endpoint")
    @Test
    public void testGetAllSpartan(){

    // send a get request to above endpoint
    // save the response
    // print out the result
    // try to assert the status code
    // content type header

    Response response = get("/spartans");

    response.prettyPrint();

    assertThat(response.statusCode(),is(200));
    assertThat(response.contentType(),is(ContentType.JSON.toString()));



    }

    @DisplayName("Testing/api/spartan endpoint XML Response")
    @Test
    public void testGetAllSpartanXML(){

        /**
         * given
         *   --- RequestSpecification
         *       use to provide information about the request
         *       header, query params, path variable, body authentication authorization
         *       logging, cookie
         *
         * when
         *     --- this is where you actually send the request with http method
         *          like GET  POST  PUT   DELETE... with the url
         *          we get response response object after sending the request
         * than
         *    --- ValidatableResponse
         *        validate status code, header, payload cookie
         *        responseTime
         *
         */

        given()
                    .header("accept", "application/xml").
                when()
                    .get("/spartans").
                then()
                //.assertThat()   // --> this is not required, but can be added to make it obvious that this is where we start
                    .statusCode(200)
              //  .and()   // --> this is not required at all, for readability, optional
                    .header("Content-Type", "application/xml") ;

        // this will do same exact thing as above in slightly different way
        // since accept header and content type header is so common, RestAssured

        given()
                .header("accept", "application/xml").
                when()
                .get("/spartans").
                then()
                .assertThat()
                .statusCode(is(200))
                .and()
                .contentType(ContentType.XML) ;
    }

}
