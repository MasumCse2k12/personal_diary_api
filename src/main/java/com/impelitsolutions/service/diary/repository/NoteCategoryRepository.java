package com.impelitsolutions.service.diary.repository;

import com.impelitsolutions.service.diary.model.NoteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteCategoryRepository extends JpaRepository<NoteCategory, Integer> {
}
