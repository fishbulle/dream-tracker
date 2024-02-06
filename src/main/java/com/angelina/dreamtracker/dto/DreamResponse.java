package com.angelina.dreamtracker.dto;

import java.util.List;
import java.util.UUID;

public record DreamResponse(UUID dreamId,
                            String title,
                            String content,
                            String category,
                            List<String> tags,
                            UUID userId) {}