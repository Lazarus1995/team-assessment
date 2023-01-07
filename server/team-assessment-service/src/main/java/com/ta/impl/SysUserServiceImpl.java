package com.ta.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ta.SysUserService;
import com.ta.entry.SysUser;
import com.ta.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author qu
* @description 针对表【sys_user(系统用户表)】的数据库操作Service实现
* @createDate 2023-01-07 12:20:13
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService {

}




