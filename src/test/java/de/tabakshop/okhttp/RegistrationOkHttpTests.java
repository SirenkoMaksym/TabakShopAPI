/*
 * created by $
 */


package de.tabakshop.okhttp;

import com.google.gson.Gson;
import de.tabakshop.dto.*;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationOkHttpTests {
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");


    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void registrationSuccessTestOkHttp() throws IOException {
        RegRequestDto requestDto = RegRequestDto.builder()
                .email("test345@gmail.com")
                .password("Password123!")
                .isAdult(true)
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto), JSON);


        Request request = new Request.Builder()
                .url("https://smoke-shop-68y5q.ondigitalocean.app/api/author/reg")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        RegResponseDto dto = gson.fromJson(responseJson, RegResponseDto.class);
        Assert.assertTrue(dto.getId() > 0);
    }

    @Test
    public void registrationExistsUserOkHttp() throws IOException {
        RegRequestDto requestDto = RegRequestDto.builder()
                .email("test34@gmail.com")
                .password("Password123!")
                .isAdult(true)
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto), JSON);


        Request request = new Request.Builder()
                .url("https://smoke-shop-68y5q.ondigitalocean.app/api/author/reg")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        ErrorRegDto dto = gson.fromJson(responseJson, ErrorRegDto.class);
        Assert.assertTrue(dto == null);
    }
    @Test
    public void registrationWithWrongEmailOkHttp() throws IOException {
        RegRequestDto requestDto = RegRequestDto.builder()
                .email("test34gmail.com")
                .password("Password123!")
                .isAdult(true)
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto), JSON);


        Request request = new Request.Builder()
                .url("https://smoke-shop-68y5q.ondigitalocean.app/api/author/reg")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        ErrorRegDto dto = gson.fromJson(responseJson, ErrorRegDto.class);
        Assert.assertEquals(dto.getMessage(),"Поле, email, содержит неверный электронный адрес. Пример: example-email@example.com");
    }
    @Test
    public void registrationWithWrongPasswordOkHttp() throws IOException {
        RegRequestDto requestDto = RegRequestDto.builder()
                .email("test345@gmail.com")
                .password("")
                .isAdult(true)
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto), JSON);


        Request request = new Request.Builder()
                .url("https://smoke-shop-68y5q.ondigitalocean.app/api/author/reg")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        ErrorRegDto dto = gson.fromJson(responseJson, ErrorRegDto.class);
        Assert.assertEquals(dto.getMessage(),"Поле, password, нужно заполнить");
    }
    @Test
    public void registrationWithoutConfirmAgeOkHttp() throws IOException {
        RegRequestDto requestDto = RegRequestDto.builder()
                .email("test34@gmail.com")
                .password("Password123!")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto), JSON);


        Request request = new Request.Builder()
                .url("https://smoke-shop-68y5q.ondigitalocean.app/api/author/reg")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();

        ErrorRegDto dto = gson.fromJson(responseJson, ErrorRegDto.class);
        Assert.assertEquals(dto.getMessage(),"Подтвердите, что вам уже 18 лет");
    }
}
