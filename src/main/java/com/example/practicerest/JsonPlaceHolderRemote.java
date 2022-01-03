package com.example.practicerest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class JsonPlaceHolderRemote {

    WebClient webClient;

    public JsonPlaceHolderRemote(){
        webClient = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
    }

    public JsonPlaceholder get(String id){
        return webClient.get()
                .uri("posts/"+id)
                .retrieve()
                .bodyToMono(JsonPlaceholder.class)
                .block();
    }

    @Value
    public static class JsonPlaceholder{
        String id;
        String title;
        String body;
        String userId;

        @JsonCreator
        public JsonPlaceholder(@JsonProperty("id") String id,
                               @JsonProperty("title") String title,
                               @JsonProperty("body") String body,
                               @JsonProperty("userId") String userId) {
            this.id = id;
            this.title = title;
            this.body = body;
            this.userId = userId;
        }


    }


}
