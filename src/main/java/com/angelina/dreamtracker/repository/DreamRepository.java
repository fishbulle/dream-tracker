package com.angelina.dreamtracker.repository;

import com.angelina.dreamtracker.model.Dream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DreamRepository extends JpaRepository<Dream, UUID> {

    @Query("SELECT j FROM Dream j WHERE j.user.id = :userId")
    List<Dream> findAllByUser(UUID userId);
}
