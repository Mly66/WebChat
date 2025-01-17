package com.mly.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author mly
 * @create 2024-06-24 16:40
 */
@Component
public class Constants {

    // 好友状态
    public static final Integer FRIEND_NO = 0;
    public static final Integer FRIEND_YES = 1;
    public static final Integer FRIEND_APPLYING = 2;

    // websocket
    public static final String ENDPOINT = "/ws/mly";
    public static final String ALLOWED_ORIGINS = "*";
    public static final String SIMPLE_BROKER = "/topic";
    public static final String DESTINATION_PREFIXES = "/ClientToServer";

}
