package com.angelina.dreamtracker.repository;

import com.angelina.dreamtracker.model.Dream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DreamRepository extends JpaRepository<Dream, UUID> {
}
