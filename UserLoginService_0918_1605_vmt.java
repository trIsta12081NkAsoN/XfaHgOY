// 代码生成时间: 2025-09-18 16:05:28
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Service
public class UserLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**<ol>
     * 用户登录验证
     * @param username 用户名
     * @param password 密码
     * @return Authentication 认证信息
     * @throws AuthenticationException 认证异常
     */
    public Authentication login(String username, String password) throws AuthenticationException {
        try {
            // 从数据库获取用户信息
            var user = userDetailsService.loadUserByUsername(username);
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found", null);
            }
            // 密码校验
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password", null);
            }
            // 创建认证令牌
            var authRequest = new UsernamePasswordAuthenticationToken(username, password);
            // 进行认证
            return authenticationManager.authenticate(authRequest);
        } catch (UsernameNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during authentication", ex);
        }
    }
}
