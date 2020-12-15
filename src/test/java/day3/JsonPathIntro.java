package day3;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class JsonPathIntro {

static String username = "user";
static String password = "user";


        @BeforeAll
        public static void setUp(){
            baseURI = "http://34.227.193.123:8000";
            basePath = "/api" ;
        }

        @AfterAll
        public static void tearDown(){
            reset();
        }

        @DisplayName("Exracting data out of Spartan Json Object")
    @Test
    public void test1SpartanPayload(){


            //send a request to get 1 spartan
            // by providing path params with valid id
            // save it into Response object
            // New: create an object with type JsonPath
            // by calling the method JsonPath() on response object
            // extract id, name gender, phone
            // and save it into variable of correct type

          Response response = given()
                                    .auth()
                                    .basic(username, password)
                                     .pathParam("id", 99).
                  when()
                                      .get("/spartans/{id}")
                                     .prettyPeek()   /// print with unnecessary information
                  ;

        //response.prettyPrint() ;

            //JsonPath is used to navigate trough the Json payload
            // and extract the value according to the valid  "jsonpath" provided

            JsonPath jp = response.jsonPath() ;

            int myId = jp.getInt("id") ;
            String myName  = jp.getString("name") ;
            String myGender = jp.getString("gender") ;
            long myPhone = jp.getLong("phone")  ;

            System.out.println("myId = " + myId);
            System.out.println("myName = " + myName);
            System.out.println("myGender = " + myGender);
            System.out.println("myPhone = " + myPhone);


        }

    @DisplayName("Extracting data from Json Array Response ")
    @Test
    public void getAllSpartanExtractData(){

        //Response response = get("/spartans");
        //JsonPath jp = response.jsonPath();
JsonPath jp = given()
                    .auth()
                    .basic(username,password).
        when()
              .get("/spartans")
              .jsonPath()  ;

     //   JsonPath jp = get("/spartans").jsonPath();

        // get the first json object name field and phone field
        System.out.println("jp.getString(\"name[0]\") = "
                + jp.getString("name[0]"));

        System.out.println("jp.getLong(\"phone[0]\") = "
                + jp.getLong("phone[0]"));


        // get the 7th json object gender field from json array
        System.out.println("jp.getString(\"gender[6]\") = "
                + jp.getString("gender[6]"));

        // getting all the name fields  from the jsonArray response
        // and storing as a list

        List<String> allNames =  jp.getList("name") ;
        System.out.println("allNames = " + allNames);

        // getting all the phone fields  from the jsonArray response
        // and storing as a list

      List<Long> allPhone =   jp.getList("phone") ;
        System.out.println("allPhone = " + allPhone);

    }
    // send request to this request url
    // http://100.26.101.158:8000/api/spartans/search?nameContains=de&gender=Male
    // get the name of first guy in the result
    // get the phone of 3rd guy in the result
    // get all names , all phones save it as list
    // save the value of field called empty under pageable in the response
    // print it out
    @DisplayName("Testing /spartans/search and extracting data")
    @Test
    public void testSearch(){

        JsonPath jp = given()
                .queryParam("nameContains","de")
                .queryParam("gender","Male").
                        when()
                .get("/spartans/search")
                .jsonPath();

        System.out.println("first guy name " +
                jp.getString("content[0].name")    );

        System.out.println("Third guy phone number :" +
                jp.getLong("content[2].phone"));

        System.out.println("AllNames: "+ jp.getList("content.name"));
        System.out.println("AllPhones: "+ jp.getList("content.phone"));

        System.out.println("value of field empty " +
                jp.getBoolean("pageable.sort.empty") );
    }

}
