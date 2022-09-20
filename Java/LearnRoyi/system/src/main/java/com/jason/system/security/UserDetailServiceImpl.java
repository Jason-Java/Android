package com.jason.system.security;

import com.jason.system.enums.UserStatus;
import com.jason.system.exception.ServiceException;
import com.jason.system.model.domain.SysUser;
import com.jason.system.model.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>描述:用户验证处理类
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/9/18 21:10
 * @see
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private ISysUserService userService;




    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if(Objects.isNull(user)||UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
        else if(UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }
        System.out.println("----->loadUserByUsername");
        return null;
    }


}
