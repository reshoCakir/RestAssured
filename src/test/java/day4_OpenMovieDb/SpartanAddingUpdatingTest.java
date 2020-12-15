package day4_OpenMovieDb;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanAddingUpdatingTest {


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
    @DisplayName("All Spartan with Basic auth")
    @Test
    public void allSpartans() {


        given()
                .auth()
                .basic(username, password)
                .accept(ContentType.JSON)
                .log().all().
                when()
                .get("/spartans").
                then()
                .log().all()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
        ;

    }

    @DisplayName("Add 1 data with raw Json String POST/api/spartans")
    @Test
    public void testAddOneData (){

        String newSpartan = "{\n" +
                "        \"id\": 192,\n" +
                "        \"name\": \"Merle\",\n" +
                "        \"gender\": \"Female\",\n" +
                "        \"phone\": 9876543210\n" +
                "    }" ;

        System.out.println(newSpartan);

        given()
                .auth()
                .basic(username,password)
                .contentType(ContentType.JSON)
                .body(newSpartan)
                .log().all().

         when()
               .post("/spartans").
          then()
                .log().all()
                .assertThat()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Merle"))
               .body("data.gender", is("Female"))
               .body("data.phone", is(9876543210L))
                ;
    }

    @DisplayName("Add 1 data with Map object POST/api/spartans")
    @Test
    public void testAddWithMapAsOneData (){

        Map<String,Object> payload = new LinkedHashMap<>();
        payload.put("name", "Sidar");
        payload.put("gender", "Female");
        payload.put("phone", 2332532323l);

        System.out.println("payload = " + payload);

        given()
                .auth()
                .basic(username,password)
                .log().all()
                .contentType(ContentType.JSON)
                .body(payload).
        when()
               .post("/spartans").
        then()
                .log().all()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Sidar"))
                .body("data.gender", is("Female"))
                .body("data.phone", is(2332532323l))

                ;
    }


    @DisplayName("Add 1 data with external  Json file POST/api/spartans")
    @Test
    public void testAddWithJsonFileAsData (){

        // Create a file called singleSpartan.json right under root directory
        // with below content

        /*
        {

        "name": "Simge",
        "gender": "Female",
        "phone": 2332532323

        add below code to  point File object to this Single_Spartan.json
    }
         */

        File externalJson = new File("Single_Spartan.json") ;



        given()
                .auth()
                .basic(username,password)
                .log().all()
                .contentType(ContentType.JSON)
                .body(externalJson).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Simge"))
                .body("data.gender", is("Female"))
                .body("data.phone", is(2335532323l))

        ;

    }

    }