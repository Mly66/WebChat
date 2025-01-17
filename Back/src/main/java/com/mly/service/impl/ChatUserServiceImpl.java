package com.mly.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mly.domain.entity.ChatUser;
import com.mly.mapper.ChatUserMapper;
import com.mly.service.ChatUserService;
import org.springframework.stereotype.Service;

/**
 * (ChatUser)表服务实现类
 *
 * @author makejava
 * @since 2023-06-23 00:09:31
 */
@Service("chatUserService")
public class ChatUserServiceImpl extends ServiceImpl<ChatUserMapper, ChatUser> implements ChatUserService {
}
