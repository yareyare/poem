package com.ivy.service;

import com.ivy.model.BaseException;
import com.ivy.model.po.User;

/**
 * Created by admin on 2017/11/22.
 */
public interface UserService {
    User getUserByParam(String param) throws BaseException;

    int addUser(User user) throws BaseException;

    User getUserByParam(String email, String password) throws BaseException;

    int modifyUser(User user) throws BaseException;
}
