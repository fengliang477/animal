package app;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class weather {
    public static void json(String jsonData) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(jsonData);
            JSONArray data = (JSONArray) jsonObject.get("data");

            for (Object obj : data) {
                JSONObject day = (JSONObject) obj;
                System.out.println("日期：" + day.get("date") + ", 天气：" + day.get("wea") + ", 白天气温：" + day.get("tem_day") + "℃, 夜间气温：" + day.get("tem_night") + "℃, 风向：" + day.get("win") + ", 风力：" + day.get("win_speed"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getweather(String city) {
        // 创建HttpClient实例
        HttpClient client = HttpClient.newHttpClient();

        // 创建HttpRequest实例，指定URL
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v1.yiketianqi.com/free/week?appid=13225961&appsecret=4LrNgAPH&unescape=1&city="+city)) // 替换为你的API URL
                .build();

        // 发送GET请求
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 打印响应状态码和响应体
            //System.out.println("Status Code: " + response.statusCode());
            if (response.statusCode() == 200){
                System.out.println("获取天气成功");
            }else {
                System.out.println("获取天气失败，错误代码"+response.statusCode());
            }
            return (response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String[] dataweather(String date, String jsonData) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(jsonData);
            JSONArray data = (JSONArray) jsonObject.get("data");

            for (Object obj : data) {
                JSONObject day = (JSONObject) obj;
                if (day.get("date").equals(date)) {
                    String[] weatherInfo = new String[6];
                    weatherInfo[0] = (String) day.get("date");
                    weatherInfo[1] = (String) day.get("wea");
                    weatherInfo[2] = (String) day.get("tem_day");
                    weatherInfo[3] = (String) day.get("tem_night");
                    weatherInfo[4] = (String) day.get("win");
                    weatherInfo[5] = (String) day.get("win_speed");
                    return weatherInfo;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getdateweather(String date, String city) {
        // 获取天气数据并直接处理
        String jsonData = getweather(city);
        String[] weatherInfo = dataweather(date, jsonData);
        if (weatherInfo != null) {
            System.out.println("日期：" + weatherInfo[0] + ", 天气：" + weatherInfo[1] + ", 白天气温：" + weatherInfo[2] + "℃, 夜间气温：" + weatherInfo[3] + "℃, 风向：" + weatherInfo[4] + ", 风力：" + weatherInfo[5]);
        } else {
            System.out.println("未找到指定日期的天气信息。");
        }
    }
}