package day1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;



@DisplayName("Day 1 Hello Test") //  will change class name in console

public class HelloTest {

/*
// Junit Annotations
    // @BeforeAll : run before all test
     @AfterAll: run after all test
     @BeforeEach: run before each step
     @AfterEach: run after ech step
 */


    @BeforeAll
    public static void setup(){

        System.out.println("@BeforeAll is running");

    }



    @AfterAll
    public static void tearDown(){
        System.out.println("@AfterAll is running ");
    }


    @BeforeEach
    public void setupTest (){

        System.out.println("@BeforeEach is running");
    }


    @AfterEach
    public void tearDownTest () {

        System.out.println("@AfterEach is running");
    }

@DisplayName("test 1+3 = 4")  //  will change method name in console
    @Test
    public void test (){

        Assertions.assertEquals(4,3+1);
        System.out.println("Test1 is running");
    }

@Disabled   // it will stop the test basically it will ignore it
@DisplayName("test 3*4 = 12") //  will change method name in console
    @Test
    public void test2(){

        // assert 4 times 3 is 12
        assertEquals(12,4*3);    /// we don't need to put Assertion bc we import static automatic call it
        System.out.println("Test2 is running ");

    }
}
