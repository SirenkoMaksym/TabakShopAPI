package de.tabakshop.okhttp;

import com.google.gson.Gson;
import de.tabakshop.dto.AuthRequestDto;
import de.tabakshop.dto.AuthResponseDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginOkHttpTests {
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");


    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void loginSuccessUser() throws IOException {
        AuthRequestDto requestDTO = AuthRequestDto.builder()
                .email("test34@gmail.com")
                .password("7465HHHY***$64")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url("https://smoke-shop-68y5q.ondigitalocean.app/api/author/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), 200);

        String responseJson = response.body().string();
        AuthResponseDto dto = gson.fromJson(responseJson, AuthResponseDto.class);

        Assert.assertNotNull(dto.getAccessToken());
        System.out.println(dto.getAccessToken());
    }

    @Test
    public void loginSuccessAdmin() throws IOException {
        AuthRequestDto adminRequestDTO = AuthRequestDto.builder()
                .email("tobeornottobe@gmail.com")
                .password("myadminpasswordIsStrong***33")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(adminRequestDTO), JSON);

        Request request = new Request.Builder()
                .url("https://smoke-shop-68y5q.ondigitalocean.app/api/author/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), 200);

        String responseJson = response.body().string();
        AuthResponseDto dto = gson.fromJson(responseJson, AuthResponseDto.class);

        Assert.assertNotNull(dto.getAccessToken());
        System.out.println(dto.getAccessToken());
    }

    @Test
    public void loginWithoutActivate() throws IOException {
        AuthRequestDto requestDTO = AuthRequestDto.builder()
                .email("lionelmessi2@example.com")
                .password("765HHHY***$64")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url("https://smoke-shop-68y5q.ondigitalocean.app/api/author/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), 403);

        String responseJson = response.body().string();
        Assert.assertTrue(responseJson.contains("Аккаунт не активирован"));
    }

    @Test
    public void loginWithBadPassword() throws IOException {
        AuthRequestDto requestDTO = AuthRequestDto.builder()
                .email("test34@gmail.com")
                .password("765HHHY***$64")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url("https://smoke-shop-68y5q.ondigitalocean.app/api/author/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), 404);

    }

    @Test
    public void loginWithoutEmail() throws IOException {
        AuthRequestDto requestDTO = AuthRequestDto.builder()
                .email("")
                .password("765HHHY***$64")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url("https://smoke-shop-68y5q.ondigitalocean.app/api/author/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), 400);

        String responseJson = response.body().string();
        Assert.assertTrue(responseJson.contains("Поле, email, содержит неверный электронный адрес. Пример: example-email@example.com"));
    }

    @Test
    public void loginWithoutPassword() throws IOException {
        AuthRequestDto requestDTO = AuthRequestDto.builder()
                .email("test34@gmail.com")
                .password("")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url("https://smoke-shop-68y5q.ondigitalocean.app/api/author/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), 400);

        String responseJson = response.body().string();
        Assert.assertTrue(responseJson.contains("Поле, password, нужно заполнить"));
    }


}
