package app;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
public class kimipost {

    // 替换为你自己的API密钥
    private static final String API_KEY = "sk-OuDzEA1HB3gB9I5DYaLfdrCBDhfTQdtkC5qijx7YgNdEgOk1";

    // 替换为实际的API接口地址
    private static final String API_URL = "https://api.moonshot.cn/v1/chat/completions";

        private static final String USER_AGENT = "Mozilla/5.0";

        public static String sendChatRequest(String message, String conversationId, String model, int maxTokens) throws Exception {
            int retries = 0;
           while (retries <= 3) {
               try {


                   yanchi();
                   // 构造请求参数（确保 messages 是一个 JSON 数组）
                   JSONArray messages = new JSONArray();
                   messages.put(new JSONObject().put("role", "user").put("content", message));

                   JSONObject requestBody = new JSONObject();
                   requestBody.put("model", model);
                   requestBody.put("messages", messages);
                   requestBody.put("max_tokens", maxTokens);

                   // 发送POST请求
                   URL url = new URL(API_URL);
                   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                   conn.setRequestMethod("POST");
                   conn.setRequestProperty("User-Agent", USER_AGENT);
                   conn.setRequestProperty("Authorization", "Bearer " + API_KEY); // 添加认证头
                   conn.setRequestProperty("Content-Type", "application/json"); // 设置请求体为JSON格式
                   conn.setDoOutput(true);

                   // 发送请求参数
                   byte[] postData = requestBody.toString().getBytes(StandardCharsets.UTF_8);
                   conn.getOutputStream().write(postData);

                   // 获取响应
                   int responseCode = conn.getResponseCode();
                   if (responseCode == HttpURLConnection.HTTP_OK) {
                       // 读取响应内容
                       BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                       String inputLine;
                       StringBuilder response = new StringBuilder();

                       while ((inputLine = in.readLine()) != null) {
                           response.append(inputLine);
                       }
                       in.close();

                       // 返回响应内容
                       return response.toString();
                   } else {
                       // 解析错误信息
                       BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                       String errorLine;
                       StringBuilder errorMessage = new StringBuilder();
                       while ((errorLine = errorReader.readLine()) != null) {
                           errorMessage.append(errorLine);
                       }
                       errorReader.close();

                       throw new RuntimeException("API调用失败，错误码：" + responseCode + "，错误信息：" + errorMessage.toString());
                   }
               } catch (RuntimeException e) {
                   if (retries == 3) {
                       throw e; // 超过最大重试次数，抛出异常
                   }
                   retries++;
                   System.out.println("请求失败，正在重试第 " + retries + " 次...");
                   TimeUnit.SECONDS.sleep(retries); // 指数退避策略
               }
           }
           return null; // 不会执行到这里，但为了编译通过，添加一个返回值
        }

       /* public static void main(String[] args) {
            try {
                // 示例：模拟多轮对话
                String conversationId = "initial_conversation_id"; // 初始会话ID（替换为实际的会话ID）
                String model = "moonshot-v1-8k"; // 模型名称
                int maxTokens = 1024; // 最大返回Token数

                // 第一轮对话
                String response1 = sendChatRequest("你好，我今天很开心！", conversationId, model, maxTokens);
                System.out.println("用户提问：你好，我今天很开心！");
                System.out.println("AI回复：" + json(response1));



                // 第二轮对话
                String response2 = sendChatRequest("那你觉得我会开心多久？", conversationId, model, maxTokens);
                System.out.println("用户提问：那你觉得我会开心多久？");
                System.out.println("AI回复：" + json(response2));

                // 第三轮对话
                String response3 = sendChatRequest("好啦，我玩过了，下次再见！", conversationId, model, maxTokens);
                System.out.println("用户提问：好啦，我玩过了，下次再见！");
                System.out.println("AI回复：" + json(response3));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        public static String json(String jsonString) {
            try {
                // 创建一个 JSON 对象
                JSONObject jsonObject = new JSONObject(jsonString);

                // 从 JSON 对象中提取 'choices' 数组
                if (jsonObject.has("choices") && jsonObject.getJSONArray("choices").length() > 0) {
                    // 获取第一个 'choice' 对象
                    JSONObject choice = jsonObject.getJSONArray("choices").getJSONObject(0);

                    // 从 'choice' 中提取 'message' 对象
                    if (choice.has("message")) {
                        JSONObject message = choice.getJSONObject("message");

                        // 从 'message' 中提取 'content' 字段
                        if (message.has("content")) {
                            return message.getString("content");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null; // 如果未找到内容，返回 null
        }
        public static void yanchi() {
            try {
                // 延时 1 秒
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
