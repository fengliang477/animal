package app;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
/*
 * 该代码文件用于api调用
 * 请勿直接调用该类，否则会造成api调用次数过多
 */
public class GetApiExample {

    public static void getweather(String[] args) {
        // 创建HttpClient实例
        HttpClient client = HttpClient.newHttpClient();

        // 创建HttpRequest实例，指定URL
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v1.yiketianqi.com/free/week?appid=13225961&appsecret=4LrNgAPH&unescape=1&city=%E5%9B%9B%E5%B9%B3")) // 替换为你的API URL
                .build();

        // 发送GET请求
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 打印响应状态码和响应体
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        getweather(args);
    }

}
