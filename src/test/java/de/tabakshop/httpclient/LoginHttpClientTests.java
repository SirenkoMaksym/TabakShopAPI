package de.tabakshop.httpclient;

import com.google.gson.Gson;
import de.tabakshop.dto.AuthRequestDto;
import de.tabakshop.dto.AuthResponseDto;
import de.tabakshop.dto.ErrorRegDto;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoginHttpClientTests {
    @Test
    public void loginSuccessUser() throws IOException {
        AuthRequestDto requestDTO = AuthRequestDto.builder()
                .email("test34@gmail.com")
                .password("7465HHHY***$64")
                .build();

        Gson gson = new Gson();
        Response response = Request.Post("https://smoke-shop-68y5q.ondigitalocean.app/api/author/login")
                .bodyString(gson.toJson(requestDTO), ContentType.APPLICATION_JSON)
                .execute();
        HttpResponse httpResponse = response.returnResponse();
        System.out.println(httpResponse.getStatusLine().getStatusCode());

        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        AuthResponseDto dto = gson.fromJson(sb.toString(), AuthResponseDto.class);

        Assert.assertNotNull(dto.getAccessToken());
        System.out.println(dto.getAccessToken());
    }

    @Test
    public void loginSuccessAdmin() throws IOException {
        AuthRequestDto requestDTO = AuthRequestDto.builder()
                .email("tobeornottobe@gmail.com")
                .password("myadminpasswordIsStrong***33")
                .build();

        Gson gson = new Gson();
        Response response = Request.Post("https://smoke-shop-68y5q.ondigitalocean.app/api/author/login")
                .bodyString(gson.toJson(requestDTO), ContentType.APPLICATION_JSON)
                .execute();
        HttpResponse httpResponse = response.returnResponse();
        System.out.println(httpResponse.getStatusLine().getStatusCode());

        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        AuthResponseDto dto = gson.fromJson(sb.toString(), AuthResponseDto.class);

        Assert.assertNotNull(dto.getAccessToken());
        System.out.println(dto.getAccessToken());
    }

    @Test
    public void loginWithoutActivate() throws IOException {
        AuthRequestDto requestDTO = AuthRequestDto.builder()
                .email("lionelmessi2@example.com")
                .password("765HHHY***$64")
                .build();

        Gson gson = new Gson();
        Response response = Request.Post("https://smoke-shop-68y5q.ondigitalocean.app/api/author/login")
                .bodyString(gson.toJson(requestDTO), ContentType.APPLICATION_JSON)
                .execute();
        HttpResponse httpResponse = response.returnResponse();
        System.out.println("Status Code: " + httpResponse.getStatusLine().getStatusCode());

        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        ErrorRegDto errorDTO = gson.fromJson(sb.toString(), ErrorRegDto.class);


        Assert.assertEquals(errorDTO.getMessage(),"Аккаунт не активирован");
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 403);
    }


    @Test
    public void loginWithBadPassword() throws IOException {
        AuthRequestDto requestDTO = AuthRequestDto.builder()
                .email("test34@gmail.com")
                .password("765HHHY***$64")
                .build();

        Gson gson = new Gson();
        Response response = Request.Post("https://smoke-shop-68y5q.ondigitalocean.app/api/author/login")
                .bodyString(gson.toJson(requestDTO), ContentType.APPLICATION_JSON)
                .execute();
        HttpResponse httpResponse = response.returnResponse();
        System.out.println(httpResponse.getStatusLine().getStatusCode());

        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        ErrorRegDto errorDTO = gson.fromJson(sb.toString(), ErrorRegDto.class);

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 404);
    }

    @Test
    public void loginWithoutEmail() throws IOException {
        AuthRequestDto requestDTO = AuthRequestDto.builder()
                .email("")
                .password("765HHHY***$64")
                .build();

        Gson gson = new Gson();
        Response response = Request.Post("https://smoke-shop-68y5q.ondigitalocean.app/api/author/login")
                .bodyString(gson.toJson(requestDTO), ContentType.APPLICATION_JSON)
                .execute();
        HttpResponse httpResponse = response.returnResponse();
        System.out.println(httpResponse.getStatusLine().getStatusCode());

        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        ErrorRegDto errorDTO = gson.fromJson(sb.toString(), ErrorRegDto.class);

        Assert.assertEquals(errorDTO.getMessage(),"Поле, email, содержит неверный электронный адрес. Пример: example-email@example.com");
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 400);
    }
    @Test
    public void loginWithoutPassword() throws IOException {
        AuthRequestDto requestDTO = AuthRequestDto.builder()
                .email("test34@gmail.com")
                .password("")
                .build();

        Gson gson = new Gson();
        Response response = Request.Post("https://smoke-shop-68y5q.ondigitalocean.app/api/author/login")
                .bodyString(gson.toJson(requestDTO), ContentType.APPLICATION_JSON)
                .execute();
        HttpResponse httpResponse = response.returnResponse();
        System.out.println(httpResponse.getStatusLine().getStatusCode());

        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        ErrorRegDto errorDTO = gson.fromJson(sb.toString(), ErrorRegDto.class);

        Assert.assertEquals(errorDTO.getMessage(),("Поле, password, нужно заполнить"));
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 400);
    }

}
