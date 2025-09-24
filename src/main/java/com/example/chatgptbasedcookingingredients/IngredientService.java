package com.example.chatgptbasedcookingingredients;

import com.example.chatgptbasedcookingingredients.model.AiMessage;
import com.example.chatgptbasedcookingingredients.model.AiRequest;
import com.example.chatgptbasedcookingingredients.model.AiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

    private final RestClient restClient;

    public IngredientService(
            RestClient.Builder restClientBuilder,
            @Value("${OPENAI_API_KEY}") String apiKey
    ) {
        this.restClient = restClientBuilder
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public String categorizeIngredient(String ingredient) {
        List<AiMessage> messages = new ArrayList<>(
                List.of(new AiMessage("user", "Is " + ingredient + " vegan, vegetarian or regular?"))
        );
        AiRequest request  = new AiRequest("gpt-5", messages);

        return restClient.post()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(AiResponse.class)
                .choices().get(0).message().content();
    }
}
