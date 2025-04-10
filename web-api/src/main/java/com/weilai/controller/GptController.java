package com.weilai.controller;

import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.weilai.common.Result;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.ImageResult;
import io.github.asleepyfish.service.openai.OpenAiService;
import io.github.asleepyfish.util.OpenAiUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("chatgpt")
public class GptController {
    /**
     * chatgpt
     * @param key
     * @return
     * @throws IOException
     */
    @PostMapping()
    public Result chat(@RequestParam("key") String key)throws IOException {
        OkHttpClient client = new OkHttpClient();
        String json = "{\"model\": \"gpt-3.5-turbo-0613\",\"stream\": " +
                "false,\"messages\": [{\"role\": \"user\",\"content\": \""
        +key+"使用markdown的格式返回"+"\"}],\"temperature\":1}\"";;
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .url("https://api.chatanywhere.com.cn/v1/chat/completions")
                .addHeader("Authorization", "Bearer sk-Am9q8vNvAaPNy2ZGTd8UltyTQFGloD16EfLKuZqg7For6gyC")

                .post(body)

                .build();
        Call call = client.newCall(request);

        Response response = call.execute();
        String responseBody = response.body().string();
//        System.out.println(responseBody);
        return Result.ok(responseBody);
    }

    /**
     * 流式的请求
     * @param key
     * @return
     * @throws IOException
     */
    @PostMapping("stream")
    public Result chatStream(@RequestParam("key") String key)throws IOException {
        OkHttpClient client = new OkHttpClient();
        String json = "{\"model\": \"gpt-3.5-turbo-0613\",\"stream\": " +
                "true,\"messages\": [{\"role\": \"user\",\"content\": \""
                +key+"使用markdown的格式返回"+"\"}],\"temperature\":1}\"";;
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .url("https://api.chatanywhere.com.cn/v1/chat/completions")
                .addHeader("Authorization", "Bearer sk-Am9q8vNvAaPNy2ZGTd8UltyTQFGloD16EfLKuZqg7For6gyC")

                .post(body)

                .build();
        Call call = client.newCall(request);

        Response response = call.execute();
        String responseBody = response.body().string();
//        System.out.println(responseBody);
        return Result.ok(responseBody);
    }
    /**
     * 图片模块
     * @param key
     * @return
     * @throws IOException
     */
    @PostMapping("img")
    public Result chatImg(@RequestParam("key") String key )throws IOException {
        OkHttpClient client = new OkHttpClient();
        String json = "{\"model\": \"gpt-3.5-turbo\",\"stream\": \" +\n" +
                "                \"false,\"prompt\": \"A colorful sunset over the mountains\",  \"n\": 2" +
                ",  \"size\": \"1024x1024\"\n}";

        RequestBody body = RequestBody.create(MediaType.parse("application/json"),json );
        Request request = new Request.Builder()
                .url("https://api.chatanywhere.com.cn/v1/images/generations")
                .addHeader("Authorization", "Bearer sk-Am9q8vNvAaPNy2ZGTd8UltyTQFGloD16EfLKuZqg7For6gyC")
//                .method("Post",body)
                .method("POST",body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        String responseBody = response.body().string();
        return Result.ok(responseBody);
    }
}
