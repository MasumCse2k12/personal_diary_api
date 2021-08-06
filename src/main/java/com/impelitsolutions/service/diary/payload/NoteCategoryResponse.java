package com.impelitsolutions.service.diary.payload;

import com.impelitsolutions.service.common.payload.ApiResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NoteCategoryResponse extends ApiResponse implements Serializable {
    private List<NoteCategory> categoryList;
    private Integer total;

    public NoteCategoryResponse(boolean success, String message) {
        super(success, message);
    }
}
