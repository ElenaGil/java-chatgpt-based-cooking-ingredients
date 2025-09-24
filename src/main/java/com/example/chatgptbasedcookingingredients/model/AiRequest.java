package com.example.chatgptbasedcookingingredients.model;

import java.util.List;

public record AiRequest(String model, List<AiMessage> messages) {
}
