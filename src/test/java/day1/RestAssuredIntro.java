package day1;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RestAssuredIntro {

    @DisplayName("Sparta /api/hello Endpoint Test")
    @Test
    public void TestHello(){

     //http://100.26.101.158:8000 -- > public ip for spartan2
    // http://34.227.193.123:8000 -- >my Ip for spartan1

       Response response =  get("http://34.227.193.123:8000/api/hello");
       // get status code out of this response object


        System.out.println("Status code is: "+ response.statusCode() );  //200 OK

        // assert the status code is 200
        assertThat(response.statusCode(),equalTo(200)) ;

        //how to  pretty print entire response object
        // prettyPrint()  -- print and return the body(payload) as String
      String payLoad =    response.prettyPrint() ;

      // assertThat the body is "Hello from Sparta"

        assertThat(payLoad, is("Hello from Sparta"));

        // get the header call contentType from response

        System.out.println("response.getHeader(\"Content-Type\") = " + response.getHeader("Content-Type"));

        System.out.println("response.contentType() = " + response.contentType());

        System.out.println("response.getContentType() = " + response.getContentType());  /// 3 of methods will give same result.


        // assert That Content-Type is text/plain;charset=UTF-8
        assertThat(response.contentType(),is("text/plain;charset=UTF-8"));

        // assert that Content-Type startWith text
        assertThat(response.contentType(), startsWith("text"));

        // easy way to work with Content-type without typing much
        assertThat(response.contentType(), is(not(ContentType.JSON)));  // they are coming from ENUM class
        assertThat(response.contentType(), is(not(ContentType.TEXT)));
    }

@DisplayName("Common Matchers for String")
    @Test
    public void testString (){

        String str = "Rest Assured is cool so far" ;

        // assert the str is "Rest Assured is cool so far"
    assertThat(str, is("Rest Assured is cool so far"));

    // assert the str is"Rest Assured IS COOL so far" in case sensitive manner
    assertThat(str,equalToIgnoringCase("Rest Assured IS COOL so far"));

    //assert the str startWith "Rest"
    assertThat(str, startsWith("Rest"));

    // assert the str is contain "is cool"
    assertThat(str,containsString("is cool"));

    // assert the str contain  "IS COOL" case insensitive manner
    assertThat(str,containsStringIgnoringCase("IS COOL"));


}


}
