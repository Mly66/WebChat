package com.mly.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mly.domain.entity.Friend;
import com.mly.mapper.FriendMapper;
import com.mly.service.FriendService;
import org.springframework.stereotype.Service;

/**
 * (Friend)表服务实现类
 *
 * @author makejava
 * @since 2023-06-23 21:44:13
 */
@Service("friendService")
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {
}
