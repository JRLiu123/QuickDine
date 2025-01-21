package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * ClassName: UserService
 * Package: com.sky.service
 * Description:
 *
 * @Author Jingran Liu
 * @Create 2025/1/20 20:57
 * @Version 1.0
 */
public interface UserService {
    /**
     * weixin login
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
