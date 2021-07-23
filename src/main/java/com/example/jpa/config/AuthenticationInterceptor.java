package com.example.jpa.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.jpa.customAnnotation.PassToken;
import com.example.jpa.customAnnotation.UserLoginToken;
import com.example.jpa.pojo.Person;
import com.example.jpa.repository.PersonRepository;
import com.example.jpa.service.personService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


@RequiredArgsConstructor // 代替了 @Autowired
public class AuthenticationInterceptor implements HandlerInterceptor {

    personService personService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 从 http 请求头中取出 token
        String token = request.getHeader("token");

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Method method = handlerMethod.getMethod();

        // 检查是否有passtoken注释，如果有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) return true;
        }

        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登陆");
                }
                // 获取 token 中的 user id
                String personId;
                try {
                    personId = JWT.decode(token).getAudience().get(0);
                    System.out.println(Integer.parseInt(personId));
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                Person person = personService.findById(Integer.parseInt(personId));
                System.out.println(person);
                if (person == null) {
                    throw new RuntimeException("用户不存在，请重新登陆");
                }

                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(person.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException j) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
