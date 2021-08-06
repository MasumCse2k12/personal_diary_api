package com.impelitsolutions.service.diary.payload;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoteCategory implements Serializable {
    Integer id;
    String name;
    Timestamp createdAt;
    Timestamp updatedAt;
    Integer createdBy;
    Integer updatedBy;

    public NoteCategory(com.impelitsolutions.service.diary.model.NoteCategory categoryEo) {
        if(categoryEo != null){
            this.id = categoryEo.getId();
            this.name = categoryEo.getName();
            this.createdAt = categoryEo.getCreatedAt();
            this.createdBy = categoryEo.getCreatedBy();
            this.updatedAt = categoryEo.getUpdatedAt();
            this.updatedBy = categoryEo.getUpdatedBy();
        }
    }
}
