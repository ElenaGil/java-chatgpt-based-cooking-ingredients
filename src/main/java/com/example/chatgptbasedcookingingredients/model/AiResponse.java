package com.example.chatgptbasedcookingingredients.model;

import java.util.List;

public record AiResponse(String id, String model, List<AiChoice> choices) {
}
