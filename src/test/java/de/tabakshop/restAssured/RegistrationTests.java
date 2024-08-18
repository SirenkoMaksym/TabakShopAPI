/*
 * created by $
 */


package de.tabakshop.restAssured;

import de.tabakshop.dto.RegRequestDto;
import de.tabakshop.dto.RegResponseDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationTests extends TestBase {
    RegRequestDto requestDto = RegRequestDto.builder()
            .email("lionelmessi18@example.com")
            .password("securePassword123!")
            .isAdult(true)
            .subscribeNews(true)
            .build();

    @Test
    public void registrationSuccessTest() {
        RegResponseDto dto = given()
                .contentType("application/json")
                .body(requestDto)
                .post("author/reg")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(RegResponseDto.class)
                ;

        Assert.assertTrue(dto.getId()>0);
    }
    @Test
    public void registrationExistsUser() {
        given()
                .contentType("application/json")
                .body(RegRequestDto.builder()
                        .email("test34@gmail.com")
                        .password("Password123!")
                        .isAdult(true).build())
                .post("author/reg")
                .then()
                .assertThat().statusCode(409)
        ;
    }
    @Test
    public void registrationWithWrongEmail() {
        given()
                .contentType("application/json")
                .body(RegRequestDto.builder()
                        .email("test34gmail.com")
                        .password("Password123!")
                        .isAdult(true).build())
                .post("author/reg")
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message", equalTo("Поле, email, содержит неверный электронный адрес. Пример: example-email@example.com"))
        ;
    }
    @Test
    public void registrationWithWrongPassword() {
        given()
                .contentType("application/json")
                .body(RegRequestDto.builder()
                        .email("test34@gmail.com")
                        .password("")
                        .isAdult(true).build())
                .post("author/reg")
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message", equalTo("Поле, password, нужно заполнить"))
        ;
    }
    @Test
    public void registrationWithWithoutConfirmAge() {
        given()
                .contentType("application/json")
                .body(RegRequestDto.builder()
                        .email("test3@gmail.com")
                        .password("Password123!")
                        .build())
                .post("author/reg")
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message", equalTo("Подтвердите, что вам уже 18 лет"))

        ;
    }
}
