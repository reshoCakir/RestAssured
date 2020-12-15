package day5;

import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//                            .Random --> mix it every time
//               (MethodName.class) -- >default options
//                          .DisplayName.class ---

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// these are all available option for ordering your tests
//@TestMethodOrder(OrderAnnotation.class)
//@TestMethodOrder(Random.class)
//@TestMethodOrder(MethodName.class) // default options
//@TestMethodOrder(MethodOrderer.DisplayName.class)
public class TestOrderingInJunit5 {
    @Order(3)
    @DisplayName("3. Test A method")
    @Test
    public void testA(){
        System.out.println("running test A");
    }
    @Order(1)
    @DisplayName("1. Test C method")
    @Test
    public void testC(){
        System.out.println("running test C");
    }
    @Order(4)
    @DisplayName("4. Test D method")
    @Test
    public void testD(){
        System.out.println("running test D");
    }
    @Order(2)
    @DisplayName("2. Test B method")
    @Test
    public void testB(){
        System.out.println("running test B");
    }
}
