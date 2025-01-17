package com.mly.domain.vo;

import com.mly.domain.entity.ChatUser;
import com.mly.domain.entity.Friend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author mly
 * @create 2024-06-24 15:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllUsersVo {

    private ChatUser chatUser;

    private List<Friend> friends;

    private Integer userNumber;

    private List<String> usernameOnlineList;

}
