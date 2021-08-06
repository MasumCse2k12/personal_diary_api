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
public class NoteInfoResponse extends ApiResponse implements Serializable {
    private List<NoteInfo> noteInfoList;
    private Integer total;

    public NoteInfoResponse(boolean success, String msg){
        super(success, msg);
    }
}
