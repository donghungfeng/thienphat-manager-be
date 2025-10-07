package com.example.zalo_manager.model.dto.zalo.user.detail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagsAndNotesInfo {

    @JsonProperty("notes")
    private List<String> notes;

    @JsonProperty("tag_names")
    private List<String> tagNames;
}
