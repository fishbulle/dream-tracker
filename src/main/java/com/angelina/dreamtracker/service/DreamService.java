package com.angelina.dreamtracker.service;

import com.angelina.dreamtracker.dto.DreamResponse;
import com.angelina.dreamtracker.dto.NewDreamRequest;
import com.angelina.dreamtracker.dto.UpdateDreamRequest;
import com.angelina.dreamtracker.model.Dream;
import com.angelina.dreamtracker.model.User;
import com.angelina.dreamtracker.repository.DreamRepository;
import com.angelina.dreamtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DreamService {

    private final DreamRepository dreamRepository;
    private final UserRepository userRepository;

    public void newDream(NewDreamRequest request) throws Exception {
        User user = validateUserId(request.userId());
        Dream newDream = new Dream(null, request.title(), request.content(), request.category(),
                 user);

        dreamRepository.save(newDream);
    }

    public List<DreamResponse> getAllDreams(UUID userId) {
        return dreamRepository
                .findAllByUser(userId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public void updateDream(UpdateDreamRequest request) throws Exception {
        if (isValidUpdate(request)) {
            authorizeUpdate(request);
        }
    }

    public void deleteDream(UUID dreamId, UUID userId) throws Exception {
        if (isValidDelete(dreamId, userId)) {
            dreamRepository.deleteById(dreamId);
        }
    }

    private User validateUserId(UUID id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("No user found with ID: " + id));
    }

    private Dream validateDreamId(UUID id) throws Exception {
        return dreamRepository.findById(id)
                .orElseThrow(() -> new Exception("No dream found with ID: " + id));
    }

    private void authorizeUpdate(UpdateDreamRequest request) throws Exception {
        Dream dream = validateDreamId(request.dreamId());

            if (request.title() != null) {
                dream.setTitle(request.title());
            }
            if (request.content() != null) {
                dream.setContent(request.content());
            }
            if (request.category() != null) {
                dream.setCategory(request.category());
            }
/*            if (request.tags() != null) {
               List<String> newTags = request.tags();
               List<String> existingTags = dream.getTags();

               if (existingTags != null) {
                   newTags.addAll(existingTags);
               }

               dream.setTags(newTags);
            }*/

            dreamRepository.save(dream);
    }

    private boolean isValidUpdate(UpdateDreamRequest request) throws Exception {
        Dream dream = validateDreamId(request.dreamId());
        User user = validateUserId(request.userId());

        if (!Objects.equals(user.getId(), dream.getUser().getId()))
            throw new Exception("Only the user who dreamt this dream can update it.");
        return true;
    }

    public boolean isValidDelete(UUID dreamId, UUID userId) throws Exception {
        Dream dream = validateDreamId(dreamId);
        User user = validateUserId(userId);

        if (dream.getUser() != user) {
            throw new Exception("You don't have the authority to delete this dream.");
        }

        return true;
    }

    private DreamResponse toDTO(Dream dream) {
        return new DreamResponse(
                dream.getId(),
                dream.getTitle(),
                dream.getContent(),
                dream.getCategory(),
                // dream.getTags(),
                dream.getUser().getId()
        );
    }
}
