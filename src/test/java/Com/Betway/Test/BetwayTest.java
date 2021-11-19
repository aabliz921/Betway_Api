package Com.Betway.Test;

/*
* Verify the HTTP status is 200
Count the total number of currencies returned within the response
Verify the currency ‘GBP’ is shown within the response
* */


import Com.Betway.Utilities.ConfigurationReader;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import org.junit.jupiter.api.Test;

import java.text.BreakIterator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.testng.Assert.*;

import static io.restassured.RestAssured.given;

public class BetwayTest {


    @Test
    public void verifyPayload() {
        Response response = given().accept(ContentType.JSON)
                .when().get(ConfigurationReader.getProperty("url"));
        Map<String,Object> currencyMap = new HashMap<>();


        JsonPath json = response.jsonPath();

        int size=json.getInt("conversion_rates.size()");

        System.out.print("Number of currencies: "+ json.getMap("conversion_rates"));

        currencyMap=json.getMap("conversion_rates");


        assertEquals(response.statusCode(), 200, "Verify the HTTP status is 200");
        assertEquals(response.contentType(), "application/json", "Verify the contentType");
        assertEquals(size,160,"Verifying total number of currencies");
        assertTrue(currencyMap.containsKey("GBP"),"Verify the GBP in Json budy");



    }

}
