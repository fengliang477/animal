package app;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.util.Iterator;

public class JsonSimpleExample {
    public static void main(String[] args) {
        String jsonData = "{\"nums\":0,\"cityid\":\"101060401\",\"city\":\"四平\",\"update_time\":\"2024-12-25 07:40:44\",\"data\":[{\"date\":\"2024-12-25\",\"wea\":\"雾\",\"wea_img\":\"wu\",\"tem_day\":\"-1\",\"tem_night\":\"-18\",\"win\":\"西南风\",\"win_speed\":\"3-4级转<3级\"},{\"date\":\"2024-12-26\",\"wea\":\"晴\",\"wea_img\":\"qing\",\"tem_day\":\"-8\",\"tem_night\":\"-19\",\"win\":\"西风\",\"win_speed\":\"<3级\"},{\"date\":\"2024-12-27\",\"wea\":\"晴\",\"wea_img\":\"qing\",\"tem_day\":\"-9\",\"tem_night\":\"-18\",\"win\":\"西风\",\"win_speed\":\"<3级\"},{\"date\":\"2024-12-28\",\"wea\":\"晴\",\"wea_img\":\"qing\",\"tem_day\":\"-8\",\"tem_night\":\"-18\",\"win\":\"西风\",\"win_speed\":\"<3级\"},{\"date\":\"2024-12-29\",\"wea\":\"晴\",\"wea_img\":\"qing\",\"tem_day\":\"-5\",\"tem_night\":\"-15\",\"win\":\"西\n" +
                "南风\",\"win_speed\":\"<3级\"},{\"date\":\"2024-12-30\",\"wea\":\"晴\",\"wea_img\":\"qing\",\"tem_day\":\"-3\",\"tem_night\":\"-16\",\"win\":\"西南风\",\"win_speed\":\"<3级\"},{\"date\":\"2024-12-31\",\"wea\":\"晴\",\"wea_img\":\"qing\",\"tem_day\":\"-4\",\"tem_night\":\"-15\",\"win\":\"西南风\",\"win_speed\":\"<3级\"}]}";

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
}