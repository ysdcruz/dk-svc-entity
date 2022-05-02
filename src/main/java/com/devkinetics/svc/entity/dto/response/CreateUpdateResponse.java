package com.devkinetics.svc.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateResponse {
    private Long id;
    private List<Long> listId;
    private String message;
    private int returnCode;

    public CreateUpdateResponse(int returnCode) {
        this.returnCode = returnCode;
    }

    public CreateUpdateResponse(String message, int returnCode) {
        this.message = message;
        this.returnCode = returnCode;
    }

    public CreateUpdateResponse(Long id, int returnCode) {
        this.id = id;
        this.returnCode = returnCode;
    }

    public CreateUpdateResponse(List<Long> listId, int returnCode) {
        this.listId = listId;
        this.returnCode = returnCode;
    }

    public CreateUpdateResponse(Long id, String message, int returnCode) {
        this.id = id;
        this.message = message;
        this.returnCode = returnCode;
    }

}
