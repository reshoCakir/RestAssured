package day5;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestCollectionSupport {



    @Test
    public void testList(){

        List<Integer> numList  = Arrays.asList(4,6,7,9,5,88,90)  ;

        // use hamcrest matcher to test he size of this list

        assertThat(numList,hasSize(7)) ;

        // assetThat this list contains 9
        assertThat(numList, hasItem(9));

        // assetThat this list contains 9, 88
        assertThat(numList, hasItems(9, 88));

        // assertThat this list items more than 3
        assertThat(numList, everyItem(greaterThan(3)));

        assertThat(numList, everyItem(is(greaterThan(3))));


        List<String> allNames = Arrays.asList("Rory Hogan", "Mariana", "Olivia","Gulbadan", "Ayxamgul","Kareem","Virginia","Tahir Zohra") ;

        assertThat(allNames,hasSize(8));

        assertThat(allNames, hasItems("Virginia", "Ayxamgul", "Rory Hogan"));
        // check every item has letter a,

        assertThat(allNames, everyItem(  containsString( "a") ));

        // check every items has letter a in case insensitive manner

        assertThat(allNames, everyItem(containsStringIgnoringCase( "a") ) );


        // how to do and or in hamcrest syntax
        // allOf ---> and anyOf ---> or

        assertThat("Got Murat", allOf(startsWith("Go"), containsString("ot")));

        // anyOf -- > or
        assertThat("Got Ramazan", anyOf(is("Ramazan"), endsWith("zan")));



    }
}
