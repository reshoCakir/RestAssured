package day2;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Practice {



    static String username  = "user";
    static String password  = "user" ;

@BeforeAll
public static void setup(){

      baseURI  =  "http://34.227.193.123:8000";

    basePath = "/api";

}


    @DisplayName("Get all Spartan")
            @Test
            public void spartanTest (){

/*
        given()
                .auth()
                .preemptive()
                .basic("user", "user").
                when()
                  .get("http://34.227.193.123:8000/api/spartans").
                then()
                .statusCode(200);

 */



    }
    @DisplayName("Deneme")
    @Test
    public void Deneme1(){


        given()
                .auth()
                .basic(username, password)
                .accept(ContentType.JSON).
                when()
                .get("/spartans").
                then()
                .statusCode(200)
                .contentType(ContentType.JSON)
        ;

        given()
                .auth()
                .basic(username, password)
                .accept(ContentType.JSON).
                when()
                .get("/spartans/100").
                then()
                .statusCode(200)
                .contentType(ContentType.JSON)
        ;




    }

    @DisplayName("Check spartan info")
    @Test
    public void checkSpa(){


        /*
        {
    "id": 100,
    "name": "Terence",
    "gender": "Male",
    "phone": 1311814806
}
         */

        given()
                .auth()
                .basic(username, password)
                .accept(ContentType.JSON).
                when()
                .get("/spartans/{id}",100).
                then()
                .statusCode(200)
                .contentType(ContentType.JSON)
        .body("id", is(100))
        .body("name",equalTo("Terence"))
        .body("gender", is(equalTo("Male")))
        .body("phone", is(1311814806))

        ;
        System.out.println(   given()
                .auth()
                .basic(username, password)
                .accept(ContentType.JSON).
                        when()
                .get("/spartans/{id}",100).asString());

    }


}
