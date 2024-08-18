/*
 * created by $
 */


package de.tabakshop.restAssured;

import de.tabakshop.dto.AddProductRequestDto;
import de.tabakshop.dto.AddProductResponseDto;
import de.tabakshop.dto.AuthRequestDto;
import de.tabakshop.dto.AuthResponseDto;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AdminPanelTest extends TestBase {
    String adminToken;

    @BeforeMethod
    public void precondition() {
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("tobeornottobe@gmail.com")
                .password("myadminpasswordIsStrong***33")
                .build();

        adminToken = given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .post("author/login")
                .then()
                .extract().response().as(AuthResponseDto.class)
                .getAccessToken();
    }


    @Test
    public void addNeuProduct() {

        AddProductRequestDto requestProductDto = AddProductRequestDto.builder()
                .title("Tabak-Test4")
                .price(57)
                .quantity(33)
                .active(true)
                .description("string")
                .characteristics("string")
                .build();


        AddProductResponseDto dto = given()
                .header("Authorization", "Bearer " + adminToken)
                .body(requestProductDto)
                .contentType(ContentType.JSON)
                .when()
                .post("products")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AddProductResponseDto.class);
        ;


        Assert.assertTrue(dto.getId() > 0);

        given()
                .header("Authorization", "Bearer " + adminToken)
                .when()
                .delete("products/" + dto.getId());

    }

    @Test
    public void deleteProduct() {
        AddProductRequestDto requestProductDto = AddProductRequestDto.builder()
                .title("Tabak-Test5")
                .price(56)
                .quantity(36)
                .active(true)
                .description("string")
                .characteristics("string")
                .build();


        AddProductResponseDto dto = given()
                .header("Authorization", "Bearer " + adminToken)
                .body(requestProductDto)
                .contentType(ContentType.JSON)
                .when()
                .post("products")
                .then()
                .extract().response().as(AddProductResponseDto.class);
        ;

        given()
                .header("Authorization", "Bearer " + adminToken)
                .when()
                .delete("products/" + dto.getId())
                .then()
                .assertThat().statusCode(204);
    }

    @Test
    public void showProducts() {
        given()
                .when()
                .get("products")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body(notNullValue());
        ;

    }

    @Test
    public void showProduct() {
        AddProductRequestDto requestProductDto = AddProductRequestDto.builder()
                .title("Tabak-Test6")
                .price(26)
                .quantity(16)
                .active(true)
                .description("string")
                .characteristics("string")
                .build();

        AddProductResponseDto dto = given()
                .header("Authorization", "Bearer " + adminToken)
                .body(requestProductDto)
                .contentType(ContentType.JSON)
                .when()
                .post("products")
                .then()
                .extract().response().as(AddProductResponseDto.class);

        given()
                .when()
                .get("products/" + dto.getId())
                .then()
                .assertThat().statusCode(200)
                .assertThat().body(notNullValue());

        given()
                .header("Authorization", "Bearer " + adminToken)
                .when()
                .delete("products/" + dto.getId());

    }

    @Test
    public void updateProduct() {
        AddProductRequestDto requestProductDto = AddProductRequestDto.builder()
                .title("Tabak-Test7")
                .price(21)
                .quantity(13)
                .active(true)
                .description("string")
                .characteristics("string")
                .build();

        AddProductResponseDto dto = given()
                .header("Authorization", "Bearer " + adminToken)
                .body(requestProductDto)
                .contentType(ContentType.JSON)
                .when()
                .post("products")
                .then()
                .extract().response().as(AddProductResponseDto.class);
        ;

        AddProductRequestDto requestUpdateProductDto = AddProductRequestDto.builder()
                .title("Tabak-Test7")
                .price(25)
                .quantity(14)
                .active(true)
                .description("string")
                .characteristics("string")
                .build();

        given()
                .header("Authorization", "Bearer " + adminToken)
                .body(requestUpdateProductDto)
                .contentType(ContentType.JSON)
                .when()
                .put("products/" + dto.getId())
                .then()
                .assertThat().statusCode(200)
                .assertThat().body(notNullValue())
                .extract().response().as(AddProductResponseDto.class);

        given()
                .header("Authorization", "Bearer " + adminToken)
                .when()
                .delete("products/" + dto.getId());

    }

    @Test
    public void addToCartProduct() {

        AddProductRequestDto requestProductDto = AddProductRequestDto.builder()
                .title("Tabak-Test9")
                .price(57)
                .quantity(33)
                .active(true)
                .description("string")
                .characteristics("string")
                .build();


        AddProductResponseDto dto = given()
                .header("Authorization", "Bearer " + adminToken)
                .body(requestProductDto)
                .contentType(ContentType.JSON)
                .when()
                .post("products")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AddProductResponseDto.class);

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("test34@gmail.com")
                .password("7465HHHY***$64")
                .build();

        String userToken = given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when()
                .post("author/login")
                .then()
                .extract().response().as(AuthResponseDto.class)
                .getAccessToken();


         given()
                .header("Authorization", "Bearer " + userToken)
                .when()
                .post("products/" + dto.getId() + "/addition-to-cart")
                .then()
                .extract().response();

        given()
                .header("Authorization", "Bearer " + adminToken)
                .when()
                .delete("products/" + dto.getId());

    }

}
