package com.angelina.dreamtracker.service;

import com.angelina.dreamtracker.model.Dream;
import com.angelina.dreamtracker.model.User;
import com.angelina.dreamtracker.repository.DreamRepository;
import com.angelina.dreamtracker.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DataInitService {

    private final UserRepository userRepository;
    private final DreamRepository dreamRepository;

    @PostConstruct
    public void initializeData() {

        /* USERS */

        User user1 = new User(
                UUID.randomUUID(),
                "hej@mail.se",
                "Lina",
                "Malmros"
        );

        userRepository.save(user1);

        /* DREAMS */

        Dream dream1 = new Dream(
                UUID.randomUUID(),
                "Havet",
                "Jag drömde att jag simmade i solnedgången.",
                "Lugnt",
                List.of("#ocean", "#calm"),
                user1
        );

        dreamRepository.save(dream1);

    }

}
