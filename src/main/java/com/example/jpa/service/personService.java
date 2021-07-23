package com.example.jpa.service;

import com.example.jpa.pojo.Person;
import org.springframework.stereotype.Service;

@Service
public interface personService {

    /**
     * 查询所有 person
     * @return person 列表
     */
    String findAll();

    /**
     * 按照用户名模糊搜索
     * @param KeyName 模糊搜索的关键字
     * @return 根据关键字查询到的用户信息
     */
    String findByNameLike(String KeyName);

    /**
     * 按照 id 进行查询 person
     * @param id 要查询的 id
     * @return 该 id 的 person 数据
     */
    Person findById(Integer id);

    /**
     * 用户登陆，通过用户名和密码进行验证
     * @param name 用户名
     * @param password 密码
     * @return 查询到的 person 结果
     */
    String findPeopleWithLogin(String name, String password);

    /**
     * 添加 person
     * @param p 要添加的 person 对象
     * @return 添加结果的消息提示
     */
    String savePerson(Person p);

    /**
     * 更新 person
     * @param p 要更新的 person 对象
     * @return 更新结果的消息提示
     */
    String updatePerson(Person p);

    /**
     * 根据 id 删除 person
     * @param id 要删除的 id
     * @return 删除结果的消息提示
     */
    String deletePersonById(Integer id);

    /**
     * 删除所有 person
     * @return 删除结果的消息提示
     */
    String deleteAll();
}
