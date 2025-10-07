package com.example.zalo_manager.model.dto.zalo.user.detail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZaloUserData {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_id_by_app")
    private String userIdByApp;

    @JsonProperty("user_external_id")
    private String userExternalId;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("user_alias")
    private String userAlias;

    @JsonProperty("is_sensitive")
    private boolean sensitive;

    @JsonProperty("user_last_interaction_date")
    private String userLastInteractionDate;

    @JsonProperty("user_is_follower")
    private boolean userIsFollower;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("avatars")
    private Avatars avatars;

    @JsonProperty("dynamic_param")
    private String dynamicParam;

    @JsonProperty("tags_and_notes_info")
    private TagsAndNotesInfo tagsAndNotesInfo;

    @JsonProperty("shared_info")
    private SharedInfo sharedInfo;
}
