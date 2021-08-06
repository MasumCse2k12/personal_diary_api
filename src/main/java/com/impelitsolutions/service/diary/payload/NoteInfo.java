package com.impelitsolutions.service.diary.payload;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoteInfo implements Serializable {
    Integer id;
    String title;
    String content;
    NoteCategory noteCategory;
    Timestamp createdAt;
    Timestamp updatedAt;
    Integer createdBy;
    Integer updatedBy;

    public NoteInfo(com.impelitsolutions.service.diary.model.NoteInfo noteInfoEo) {
        if(noteInfoEo != null){
            this.id = noteInfoEo.getId();
            this.title = noteInfoEo.getTitle();
            this.content = noteInfoEo.getContent();
            this.createdAt = noteInfoEo.getCreatedAt();
            this.createdBy = noteInfoEo.getCreatedBy();
            this.updatedAt = noteInfoEo.getUpdatedAt();
            this.updatedBy = noteInfoEo.getUpdatedBy();
            if(noteInfoEo.getNoteCategory() != null){
                NoteCategory categoryDto = new NoteCategory();
                BeanUtils.copyProperties(noteInfoEo.getNoteCategory(), categoryDto);
                this.noteCategory = categoryDto;
            }
        }
    }
}
