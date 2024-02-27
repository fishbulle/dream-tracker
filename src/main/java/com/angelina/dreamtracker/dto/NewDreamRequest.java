package com.angelina.dreamtracker.dto;

import com.angelina.dreamtracker.model.enums.Type;

import java.util.List;
import java.util.UUID;

public record NewDreamRequest(String title,
                              String content,
                              String category,
                              Type type,
                              UUID userId) {
}
