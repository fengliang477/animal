package app;
// 导入需要的JSON处理库
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
// 导入网络请求相关的类
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
// 导入文件读取相关的类
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.lang.StringBuilder;
// 导入集合类
import java.util.HashMap;
import java.util.Map;

/**
 * 天气查询类
 * 这个类提供了查询天气信息的功能，包括获取实时天气和未来天气预报
 */
public class weather {
    // 创建一个静态的Map集合，用于存储城市名称和对应的城市ID
    // 使用static final确保这个映射表是常量，且只被初始化一次
    private static final Map<String, String> CITY_ID_MAP = new HashMap<>();
    
    // 静态初始化块，在类加载时执行，用于初始化城市ID映射表
    static {
        // 添加一些常用城市的ID
        // 这些ID是天气API使用的城市编码
        CITY_ID_MAP.put("北京", "101010100");
        CITY_ID_MAP.put("上海", "101020100");
        CITY_ID_MAP.put("广州", "101280101");
        CITY_ID_MAP.put("深圳", "101280601");
        CITY_ID_MAP.put("杭州", "101210101");
        CITY_ID_MAP.put("南京", "101190101");
        CITY_ID_MAP.put("天津", "101030100");
        CITY_ID_MAP.put("武汉", "101200101");
        CITY_ID_MAP.put("成都", "101270101");
        CITY_ID_MAP.put("重庆", "101040100");
        CITY_ID_MAP.put("西安", "101110101");
        CITY_ID_MAP.put("长沙", "101250101");
        CITY_ID_MAP.put("沈阳", "101070101");
        CITY_ID_MAP.put("哈尔滨", "101050101");
    }
    
    /**
     * 根据城市名称查找对应的城市ID
     * 如果找不到对应的城市ID，则返回北京的ID作为默认值
     * 
     * @param cityName 城市名称，如"北京"、"上海"等
     * @return 城市ID，如果找不到返回北京的ID（101010100）作为默认值
     */
    public static String findCityId(String cityName) {
        // 检查城市名是否在映射表中
        if (CITY_ID_MAP.containsKey(cityName)) {
            // 如果在映射表中，返回对应的ID
            return CITY_ID_MAP.get(cityName);
        }
        
        // 如果没有找到城市ID，输出提示信息并返回北京的ID作为默认值
        System.out.println("未找到城市 " + cityName + " 的ID，使用北京作为默认值");
        return "101010100"; // 北京的ID
    }
    
    /**
     * 解析并打印天气JSON数据
     * 这个方法用于测试，将天气数据格式化输出到控制台
     * 
     * @param jsonData 包含天气信息的JSON字符串
     */
    public static void json(String jsonData) {
        // 创建JSON解析器
        JSONParser parser = new JSONParser();
        try {
            // 将JSON字符串解析为JSONObject对象
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(jsonData);
            // 获取天气数据数组
            org.json.simple.JSONArray data = (org.json.simple.JSONArray) jsonObject.get("data");

            // 遍历天气数据数组，打印每天的天气信息
            for (Object obj : data) {
                org.json.simple.JSONObject day = (org.json.simple.JSONObject) obj;
                System.out.println("日期：" + day.get("date") + ", 天气：" + day.get("wea") + 
                                 ", 白天气温：" + day.get("tem_day") + "℃, 夜间气温：" + 
                                 day.get("tem_night") + "℃, 风向：" + day.get("win") + 
                                 ", 风力：" + day.get("win_speed"));
            }
        } catch (Exception e) {
            // 如果解析过程中出现错误，打印错误信息
            e.printStackTrace();
        }
    }

    /**
     * 通过HTTP请求获取指定城市的天气数据
     * 使用天气API获取JSON格式的天气信息
     * 
     * @param city 城市名称
     * @return 包含天气信息的JSON字符串，如果请求失败返回null
     */
    public static String getweather(String city) {
        // 创建HTTP客户端
        HttpClient client = HttpClient.newHttpClient();

        // 构建HTTP请求
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v1.yiketianqi.com/free/week?appid=13225961&appsecret=4LrNgAPH&unescape=1&city="+city))
                .build();

        // 发送GET请求并处理响应
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 检查响应状态码
            if (response.statusCode() == 200){
                System.out.println("获取天气成功");
            }else {
                System.out.println("获取天气失败，错误代码"+response.statusCode());
            }
            return (response.body());
        } catch (IOException | InterruptedException e) {
            // 如果请求过程中出现错误，打印错误信息
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从JSON数据中提取指定日期的天气信息
     * 
     * @param date 要查询的日期
     * @param jsonData 包含天气信息的JSON字符串
     * @return 包含天气信息的字符串数组，如果未找到指定日期的数据返回null
     */
    public static String[] dataweather(String date, String jsonData) {
        JSONParser parser = new JSONParser();
        try {
            // 解析JSON数据
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(jsonData);
            org.json.simple.JSONArray data = (org.json.simple.JSONArray) jsonObject.get("data");

            // 遍历天气数据查找指定日期
            for (Object obj : data) {
                org.json.simple.JSONObject day = (org.json.simple.JSONObject) obj;
                if (day.get("date").equals(date)) {
                    // 创建数组存储天气信息
                    String[] weatherInfo = new String[6];
                    weatherInfo[0] = (String) day.get("date");      // 日期
                    weatherInfo[1] = (String) day.get("wea");       // 天气
                    weatherInfo[2] = (String) day.get("tem_day");   // 白天气温
                    weatherInfo[3] = (String) day.get("tem_night"); // 夜间气温
                    weatherInfo[4] = (String) day.get("win");       // 风向
                    weatherInfo[5] = (String) day.get("win_speed"); // 风力
                    return weatherInfo;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询指定日期和城市的天气信息，并将结果追加到StringBuilder中
     * 这个方法使用另一个天气API获取更详细的天气信息
     * 
     * @param date 要查询的日期，格式为yyyy-MM-dd
     * @param city 要查询的城市名称
     * @param result StringBuilder对象，用于存储查询结果
     */
    public static void getdateweather(String date, String city, StringBuilder result) {
        try {
            // 构建天气API的URL
            String weaUrl = "http://t.weather.itboy.net/api/weather/city/";
            weaUrl += findCityId(city);
            URL url = new URL(weaUrl);
            
            // 建立网络连接
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(10000); // 设置连接超时时间为10秒

            // 读取API返回的数据
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            // 解析JSON数据
            JSONObject json = new JSONObject(sb.toString());
            
            // 检查API返回是否成功
            if (json.getString("message").equals("Success !")) {
                JSONObject data = json.getJSONObject("data");
                JSONArray forecast = data.getJSONArray("forecast");
                
                // 添加城市信息
                result.append("城市: ").append(data.getString("city")).append("\n\n");
                
                // 遍历天气预报数据查找指定日期
                for (int i = 0; i < forecast.length(); i++) {
                    JSONObject day = forecast.getJSONObject(i);
                    String forecastDate = day.getString("ymd");
                    
                    if (forecastDate.equals(date)) {
                        // 添加找到的天气信息
                        result.append("日期: ").append(forecastDate).append(" ").append(day.getString("week")).append("\n");
                        result.append("天气: ").append(day.getString("type")).append("\n");
                        result.append("气温: ").append(day.getString("high")).append(" / ").append(day.getString("low")).append("\n");
                        result.append("风向: ").append(day.getString("fx")).append("\n");
                        result.append("风力: ").append(day.getString("fl")).append("\n");
                        result.append("空气指数: ").append(data.optInt("aqi", 0)).append("\n");
                        result.append("提示: ").append(day.getString("notice"));
                        return;
                    }
                }
                
                // 如果未找到指定日期的天气信息
                result.append("未找到 ").append(date).append(" 的天气信息!");
            } else {
                // 如果API返回失败
                result.append("查询失败: ").append(json.getString("message"));
            }
        } catch (Exception e) {
            // 如果发生异常，添加错误信息
            result.append("查询出错: ").append(e.getMessage());
        }
    }

    /**
     * 查询指定日期和城市的天气信息，并直接打印到控制台
     * 这是一个便捷方法，用于快速查看天气信息
     * 
     * @param date 要查询的日期
     * @param city 要查询的城市名称
     */
    public static void getdateweather(String date, String city) {
        // 获取天气数据
        String jsonData = getweather(city);
        // 解析指定日期的天气信息
        String[] weatherInfo = dataweather(date, jsonData);
        if (weatherInfo != null) {
            // 如果找到天气信息，打印到控制台
            System.out.println("日期：" + weatherInfo[0] + ", 天气：" + weatherInfo[1] + 
                             ", 白天气温：" + weatherInfo[2] + "℃, 夜间气温：" + 
                             weatherInfo[3] + "℃, 风向：" + weatherInfo[4] + 
                             ", 风力：" + weatherInfo[5]);
        } else {
            // 如果未找到天气信息，打印提示信息
            System.out.println("未找到指定日期的天气信息。");
        }
    }
}