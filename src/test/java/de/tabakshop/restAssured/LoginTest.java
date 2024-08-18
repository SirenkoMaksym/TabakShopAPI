/*
 * created by $
 */


package de.tabakshop.restAssured;

import de.tabakshop.dto.AuthRequestDto;
import de.tabakshop.dto.AuthResponseDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTest extends TestBase{
    AuthRequestDto requestDto = AuthRequestDto.builder()
            .email("test34@gmail.com")
            .password("7465HHHY***$64")
            .build();

    @Test
    public void loginSuccessTest() {
        AuthResponseDto dto = given()
                .contentType("application/json")
                .body(requestDto)
                .post("author/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDto.class);

        Assert.assertTrue(dto.getAccessToken() != null);
    }
//    @Test
//    public void loginWrongPasswordPerfectTest(){
//        given()
//                .body(AuthRequestDto.builder()
//                        .username("maxtest@gmail.com")
//                        .password("Maxtest1234!").build())
//                .contentType(ContentType.JSON)
//                .when()
//                .post("user/login/usernamepassword")
//                .then()
//                .assertThat().statusCode(401)
//                .assertThat().body("error", equalTo("Unauthorized"));
//
//    }






}
