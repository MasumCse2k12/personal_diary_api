package com.impelitsolutions.service.diary.payload;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NoteSearchCriteria  implements Serializable {
    private Integer noteId;
    private Integer category;
    private String title;
}
