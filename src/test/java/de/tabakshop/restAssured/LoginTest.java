/*
 * created by $
 */


package de.tabakshop.restAssured;

import de.tabakshop.dto.AuthRequestDto;
import de.tabakshop.dto.AuthResponseDto;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest extends TestBase {
    AuthRequestDto requestDto = AuthRequestDto.builder()
            .email("test34@gmail.com")
            .password("7465HHHY***$64")
            .build();
    AuthRequestDto adminRequestDto = AuthRequestDto.builder()
            .email("tobeornottobe@gmail.com")
            .password("myadminpasswordIsStrong***33")
            .build();

    @Test
    public void loginSuccessUser() {
        String responseToken = given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .body(containsString("accessToken"))
                .extract().path("accessToken");

        System.out.println(responseToken);
    }
    @Test
    public void loginSuccessAdmin() {
        String adminToken = given()
                .contentType(ContentType.JSON)
                .body(adminRequestDto)
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .body(containsString("accessToken"))
                .extract().path("accessToken");

        System.out.println("Admin Token: " + adminToken);
    }

    @Test
    public void loginWithoutActivate() {
        given()
                .body(AuthRequestDto.builder().email("lionelmessi2@example.com")
                        .password("765HHHY***$64").build())
                .contentType(ContentType.JSON)
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(403)
                .assertThat().body("message", equalTo("Аккаунт не активирован"));
    }

    @Test
    public void loginWithBadPassword() {
        given()
                .body(AuthRequestDto.builder().email("test34@gmail.com")
                        .password("765HHHY***$64").build())
                .contentType(ContentType.JSON)
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(404);


    }

    @Test
    public void loginWithoutEmail() {
        given()
                .body(AuthRequestDto.builder().email("")
                        .password("765HHHY***$64").build())
                .contentType(ContentType.JSON)
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message", equalTo("Поле, email, содержит неверный электронный адрес. Пример: example-email@example.com"));


    }
    @Test
    public void loginWithoutPassword() {
        given()
                .body(AuthRequestDto.builder().email("test34@gmail.com")
                        .password("").build())
                .contentType(ContentType.JSON)
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message", equalTo("Поле, password, нужно заполнить"));


    }

}
