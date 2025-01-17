package com.mly.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mly.domain.ResponseResult;
import com.mly.domain.entity.ChatUser;
import com.mly.domain.entity.Friend;
import com.mly.enums.AppHttpCodeEnum;
import com.mly.exception.SystemException;
import com.mly.service.ChatUserService;
import com.mly.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author mly
 * @create 2024-06-23 0:13
 */
@RestController
@RequestMapping("/FriendController")
public class FriendController {

    @Autowired
    private FriendService friendService;

    /**
     * @Description: 申请好友
     * @Date: 2023/6/24 0:11
     * @Author: mly
     */
    @PostMapping("/applyForFriends")
    public ResponseResult applyForFriends(@RequestBody Friend friend) {
        boolean saveOrUpdate = friendService.saveOrUpdate(friend, Wrappers.<Friend>lambdaQuery()
                .eq(Friend::getFromUsername, friend.getFromUsername())
                .eq(Friend::getToUsername, friend.getToUsername()));
        if (!saveOrUpdate) {
            throw new SystemException(AppHttpCodeEnum.APPLY_FAIL);
        }
        return ResponseResult.okResult().setMsg("申请成功");
    }

}
