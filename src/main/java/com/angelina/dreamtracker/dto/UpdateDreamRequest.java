package com.angelina.dreamtracker.dto;

import java.util.UUID;

public record UpdateDreamRequest(UUID dreamId,
                                 String title,
                                 String content,
                                 String category,
                                 String tags,
                                 UUID userId) {
}
