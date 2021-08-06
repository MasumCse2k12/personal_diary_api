package com.impelitsolutions.service.diary.repository;

import com.impelitsolutions.service.diary.model.NoteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteInfoRepository extends JpaRepository<NoteInfo, Integer> {
    @Query(value = "select * from note_info where category_id ?1", nativeQuery = true)
    List<NoteInfo> findAllByNoteCategory(Integer categoryId);
}
