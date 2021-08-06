package com.impelitsolutions.service.diary.service;

import com.impelitsolutions.service.common.payload.ApiResponse;
import com.impelitsolutions.service.common.utils.Utils;
import com.impelitsolutions.service.diary.payload.*;
import com.impelitsolutions.service.diary.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class NoteInfoService {
    public static final Logger LOGGER = LoggerFactory.getLogger(NoteInfoService.class);

    @Autowired
    private NoteDao noteDao;
    @Autowired
    private NoteInfoRepository noteInfoRepository;
    @Autowired
    private NoteCategoryRepository categoryRepository;
    @Autowired
    BaseRepository baseRepository;

    public ApiResponse addNoteCategory(NoteCategory noteCategory, Integer userId){
        if(noteCategory == null){
            return new ApiResponse(false, "Empty Note Category request!");
        }
        if(!Utils.isOk(noteCategory.getName())){
            return new ApiResponse(false, "Note  Category Name required!");
        }

        com.impelitsolutions.service.diary.model.NoteCategory savedCategoryEO = new com.impelitsolutions.service.diary.model.NoteCategory();

        BeanUtils.copyProperties(noteCategory, savedCategoryEO);

        if(Utils.isOk(noteCategory.getId())){
            Optional<com.impelitsolutions.service.diary.model.NoteCategory> categoryEo = categoryRepository.findById(noteCategory.getId());
            if(categoryEo.isPresent()){
                savedCategoryEO.setUpdatedBy(userId);
                savedCategoryEO.setUpdatedAt(Timestamp.from(Instant.now()));
                savedCategoryEO.setCreatedAt(categoryEo.get().getCreatedAt());
                savedCategoryEO.setCreatedBy(categoryEo.get().getCreatedBy());
            }else{
                return new ApiResponse(false, "Given Note Category not found!");
            }
        }else{
            savedCategoryEO.setCreatedBy(userId);
            savedCategoryEO.setCreatedAt(Timestamp.from(Instant.now()));
        }
        categoryRepository.save(savedCategoryEO);
        return new ApiResponse(true, "Records Saved Successfully!");
    }


    public ApiResponse addNote(NoteRegisterRequest noteInfo, Integer userId){
        if(noteInfo == null){
            return new ApiResponse(false, "Empty Note request!");
        }
        if(!Utils.isOk(noteInfo.getTitle())){
            return new ApiResponse(false, "Note Title required!");
        }
        if(!Utils.isOk(noteInfo.getCategory())){
            return new ApiResponse(false, "Note Category required!");
        }

        com.impelitsolutions.service.diary.model.NoteInfo savedNoteInfoEo = new com.impelitsolutions.service.diary.model.NoteInfo();

        savedNoteInfoEo.setTitle(noteInfo.getTitle());
        savedNoteInfoEo.setContent(noteInfo.getContent());

//        com.impelitsolutions.service.diary.model.NoteCategory savedCategoryEo = new com.impelitsolutions.service.diary.model.NoteCategory();

        if(Utils.isOk(noteInfo.getCategory())) {
            Optional<com.impelitsolutions.service.diary.model.NoteCategory> noteCategoryEO = categoryRepository.findById(noteInfo.getCategory());
            if(noteCategoryEO.isPresent()) {
//                savedCategoryEo.setId(noteCategoryEO.get().getId());
                savedNoteInfoEo.setNoteCategory(noteCategoryEO.get());
            }else{
                return new ApiResponse(false, "Given Note Category not found!");
            }
        }

        if(Utils.isOk(noteInfo.getId())){
            Optional<com.impelitsolutions.service.diary.model.NoteInfo> infoEo = noteInfoRepository.findById(noteInfo.getId());
            if(infoEo.isPresent()){
                savedNoteInfoEo.setUpdatedBy(userId);
                savedNoteInfoEo.setUpdatedAt(Timestamp.from(Instant.now()));
                savedNoteInfoEo.setCreatedAt(infoEo.get().getCreatedAt());
                savedNoteInfoEo.setCreatedBy(infoEo.get().getCreatedBy());
            }else{
                return new ApiResponse(false, "Given Note not found!");
            }
        }else{
            savedNoteInfoEo.setCreatedBy(userId);
            savedNoteInfoEo.setCreatedAt(Timestamp.from(Instant.now()));
        }
        baseRepository.merge(savedNoteInfoEo);
        return new ApiResponse(true, "Records Saved Successfully!");
    }

    public ApiResponse deleteNoteInfo(NoteInfo noteInfo, Integer userId){
        if(noteInfo == null){
            return new ApiResponse(false, "Empty Note request!");
        }
        if(!Utils.isOk(noteInfo.getId())){
            return new ApiResponse(false, "Note Id required!");
        }
        Optional<com.impelitsolutions.service.diary.model.NoteInfo> infoEo = noteInfoRepository.findById(noteInfo.getId());
        if(infoEo.isPresent()){
            if(infoEo.get().getCreatedBy().intValue() != userId.intValue()){
                return new ApiResponse(false, "You are not authorized user to remote the note item!");
            }else {
                noteInfoRepository.delete(infoEo.get());
            }
        }else{
            return new ApiResponse(false, "Given Note not found!");
        }

        return new ApiResponse(true, "Records Deleted Successfully!");
    }

    public NoteCategoryResponse searchNoteCategory(NoteCategory criteria, Integer userId){
        return noteDao.getNoteCategory(criteria, userId);
    }

    public NoteInfoResponse searchNoteInfo(NoteSearchCriteria criteria, Integer userId){
        return noteDao.getNoteInfos(criteria, userId);
    }
}
