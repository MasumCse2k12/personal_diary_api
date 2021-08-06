package com.impelitsolutions.service.diary.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class NoteRegisterRequest implements Serializable {
    Integer id;
    Integer category;
    String title;
    String content;
}
