/*
 * created by max$
 */


package de.tabakshop.restAssured;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static final String accessToken="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwidG9rZW5UeXBlIjoiYWNj" +
            "ZXNzIiwic3ViIjoibGlvbmVsbWVzc2lAZXhhbXBsZS5jb20iLCJpYXQiOjE3MjI4MDkwOTQsImV4cCI6MTcyMzQxMzg5NH0." +
            "iep14DmFnfAlGO8qpQ1bgFfSG0iCehTt3hIFvZubz2Q";
    public static final String refreshToken="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwidG9rZW5UeXBlIjoicm" +
            "VmcmVzaCIsInN1YiI6Imxpb25lbG1lc3NpQGV4YW1wbGUuY29tIiwiaWF0IjoxNzIyODA5MDk0LCJleHAiOjE3MjExMDYxMjZ9." +
            "8Ch1Q40A0iuhS7Uzik4nEpdxmYQRvql7M-iKU4JgzCM";

    public static final String AUTH="Authorization";
    @BeforeMethod
    public void init(){
        RestAssured.baseURI="https://smoke-shop-68y5q.ondigitalocean.app/api";
        RestAssured.basePath="";
    }
}
