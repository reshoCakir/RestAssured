package Utility;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtil {


    public static Map<String, Object> getRandomSpartanRequestPayload (){

        Faker faker = new Faker() ;
        Map<String,Object> payloadMap  = new LinkedHashMap<>() ;

        payloadMap.put("name",faker.name().firstName());
        payloadMap.put("gender", faker.demographic().sex());
        payloadMap.put("phone", faker.number().numberBetween(1000000000, 9999999999l));

        return payloadMap ;
    }

    public static void main(String[] args) {

        System.out.println(getRandomSpartanRequestPayload());
    }
}
