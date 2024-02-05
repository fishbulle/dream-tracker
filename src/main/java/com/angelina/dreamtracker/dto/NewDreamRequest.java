package com.angelina.dreamtracker.dto;

import com.angelina.dreamtracker.model.User;

import java.util.List;
import java.util.UUID;

public record NewDreamRequest(String title,
                              String content,
                              String category,
                              List<String> tags,
                              UUID userId) {
}
