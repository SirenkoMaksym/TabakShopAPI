/*
 * created by $
 */


package de.tabakshop.httpclient;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import de.tabakshop.dto.AuthRequestDto;
import de.tabakshop.dto.ErrorRegDto;
import de.tabakshop.dto.RegRequestDto;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RegistrationHttpClientTests {

    @Test
    public void registrationSuccessTestHttpClient() throws IOException {

        Response response = Request.Post("https://smoke-shop-68y5q.ondigitalocean.app/api/author/reg")
                .bodyString("{\n" +
                        "  \"email\": \"test346@gmail.com\",\n" +
                        "  \"password\": \"Password123!\",\n" +
                        "  \"isAdult\": \"true\" \n}", ContentType.APPLICATION_JSON)
                .execute();

        String responseJson = response.returnContent().asString();


        JsonElement element = JsonParser.parseString(responseJson);
        JsonElement id = element.getAsJsonObject().get("id");

        Assert.assertTrue(id.getAsString() != null);
    }
    @Test
    public void registrationWithWrongEmailHttpClient() throws IOException {
        RegRequestDto requestDto = RegRequestDto.builder()
                .email("test34gmail.com")
                .password("Maxtest123!")
                .isAdult(true)
                .build();

        Gson gson = new Gson();
        Response response = Request.Post("https://smoke-shop-68y5q.ondigitalocean.app/api/author/reg")
                .bodyString(gson.toJson(requestDto), ContentType.APPLICATION_JSON)
                .execute();

        HttpResponse httpResponse = response.returnResponse();


        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;

        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        ErrorRegDto errorDto = gson.fromJson(sb.toString(), ErrorRegDto.class);

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),400);
        Assert.assertEquals(errorDto.getMessage(), "Поле, email, содержит неверный электронный адрес. Пример: example-email@example.com");
    }
    @Test
    public void registrationWithWrongPasswordHttpClient() throws IOException {
        RegRequestDto requestDto = RegRequestDto.builder()
                .email("test347@gmail.com")
                .password("")
                .isAdult(true)
                .build();

        Gson gson = new Gson();
        Response response = Request.Post("https://smoke-shop-68y5q.ondigitalocean.app/api/author/reg")
                .bodyString(gson.toJson(requestDto), ContentType.APPLICATION_JSON)
                .execute();

        HttpResponse httpResponse = response.returnResponse();


        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;

        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        ErrorRegDto errorDto = gson.fromJson(sb.toString(), ErrorRegDto.class);

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),400);
        Assert.assertEquals(errorDto.getMessage(), "Поле, password, нужно заполнить");
    }
    @Test
    public void registrationWithoutConfirmAgeHttpClient() throws IOException {
        RegRequestDto requestDto = RegRequestDto.builder()
                .email("test347@gmail.com")
                .password("Maxtest123!")
                .build();

        Gson gson = new Gson();
        Response response = Request.Post("https://smoke-shop-68y5q.ondigitalocean.app/api/author/reg")
                .bodyString(gson.toJson(requestDto), ContentType.APPLICATION_JSON)
                .execute();

        HttpResponse httpResponse = response.returnResponse();


        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;

        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        ErrorRegDto errorDto = gson.fromJson(sb.toString(), ErrorRegDto.class);

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),400);
        Assert.assertEquals(errorDto.getMessage(), "Подтвердите, что вам уже 18 лет");
    }
}
