package com.coviam.metadata.dto.response;

import com.coviam.metadata.dto.request.Change;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
@Setter
@Getter
@Builder
public class AuditResponse {
    private String assetId; // content id
    private String asset; // IPL/GOT
    private String action; //added/del/change
    private String modifier; //email Id
    private List<Change> changes;

    private Integer flag;
}


/*

    String asset (name of content)
    String assetId (Id of content)
    String action (ADDED/DELETED/MODIFIED)
    String modifier (email id)
    List<Changes> changes (Changes=>fieldChanged,oldValue,newValue)

*/
