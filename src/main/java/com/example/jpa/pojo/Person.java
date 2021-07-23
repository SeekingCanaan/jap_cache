package com.example.jpa.pojo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    // 设置 id 自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // person id
    private Integer id;
    // person 名称
    private String name;
    // person 密码
    private String password;
    // person 权限
    private String auth;

    public static String getToken(Person person) {
        String token = "";
        token = JWT.create().withAudience(person.getId().toString()).sign(Algorithm.HMAC256(person.getPassword()));
        return token;
    }
}
