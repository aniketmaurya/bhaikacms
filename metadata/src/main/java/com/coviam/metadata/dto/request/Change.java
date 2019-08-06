package com.coviam.metadata.dto.request;


import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Change {

    private String fieldChanged;
    private String oldValue;
    private String newValue;
}


//Changes=>fieldChanged,oldValue,newValue