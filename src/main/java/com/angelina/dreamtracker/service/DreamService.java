package com.angelina.dreamtracker.service;

import com.angelina.dreamtracker.dto.DreamResponse;
import com.angelina.dreamtracker.dto.NewDreamRequest;
import com.angelina.dreamtracker.model.Dream;
import com.angelina.dreamtracker.model.User;
import com.angelina.dreamtracker.repository.DreamRepository;
import com.angelina.dreamtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DreamService {

    private final DreamRepository dreamRepository;
    private final UserRepository userRepository;

    public void newDream(NewDreamRequest request) throws Exception {
        User user = validateUserId(request.userId());
        Dream newDream = new Dream(null, request.title(), request.content(), request.category(),
                request.tags(), user);

        dreamRepository.save(newDream);
    }

    public List<DreamResponse> getAllDreams(UUID userId) {

        return dreamRepository
                .findAllByUser(userId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private User validateUserId(UUID id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("No user found with ID: " + id));
    }

    private DreamResponse toDTO(Dream dream) {
        return new DreamResponse(
                dream.getId(),
                dream.getTitle(),
                dream.getContent(),
                dream.getCategory(),
                dream.getTags(),
                dream.getUser().getId()
        );
    }
}
