package com.example.jpa.service.Impl;

import com.alibaba.fastjson.JSON;
import com.example.jpa.pojo.Person;
import com.example.jpa.repository.PersonRepository;
import com.example.jpa.service.personService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class personServiceImpl implements personService {

    final PersonRepository personRepository;

    /**
     * 查询所有 person 数据
     * @return 返回所有数据
     */
    @Override
    public String findAll() {

        List<Person> users = personRepository.findAllAndOrderBy();

        Map<String, Object> map = new HashMap<>();

        map.put("data", users);

        return JSON.toJSONString(map);
    }

    /**
     * 按照用户名进行模糊查询
     * @param KeyName 关键字
     * @return 根据关键字查询到的所有数据
     */
    @Override
    public String findByNameLike(String KeyName) {

        List<Person> users = personRepository.findByNameLike(KeyName);

        Map<String, Object> map = new HashMap<>();

        map.put("data", users);

        return JSON.toJSONString(map);
    }

    /**
     * 根据 id 查找
     * @param id 查找的 id
     * @return 对应 id 的 person
     */
    @Override
    public Person findById(Integer id) {

        Person person = personRepository.findById(id).get();

        Map<String, Object> map = new HashMap<>();

        map.put("data", person);

        return person;
    }

    /**
     * 用户登陆验证
     * @param name 登陆用户名
     * @param password 登陆密码
     * @return 查询到该用户的信息，如果用户名或密码错误，则返回 null
     */
    @Override
    public String findPeopleWithLogin(String name, String password) {

        Map<String, Object> map = new HashMap<>();

        Person personBase = personRepository.findPeopleWithLogin(name, password);

        if (personBase == null) {
            map.put("msg", "登陆失败，用户不存在");
            return JSON.toJSONString(map);
        } else {
            if (!personBase.getPassword().equals(password)) {
                map.put("msg", "登陆失败，密码错误");
                return JSON.toJSONString(map);
            } else {
                String token = Person.getToken(personBase);
                map.put("token", token);
                map.put("person", personBase);
                return JSON.toJSONString(map);
            }
        }
    }

    /**
     * 添加用户信息
     * @param p 用户数据
     * @return 添加结果的提示信息
     */
    @Override
    public String savePerson(Person p) {

        Person save = personRepository.save(p);

        Map<String, Object> map = new HashMap<>();

        map.put("data", save);

        map.put("msg", "添加成功");

        return JSON.toJSONString(map);
    }

    /**
     * 更新用户信息
     * @param p 用户
     * @return 更新结果的提示信息
     */
    @Override
    public String updatePerson(Person p) {
        Person save = personRepository.save(p);

        Map<String, Object> map = new HashMap<>();

        map.put("data", save);

        map.put("msg", "更新成功");

        return JSON.toJSONString(map);
    }

    /**
     *
     * @param id 删除用户的 id
     * @return 删除结果的提示信息
     */
    @Override
    public String deletePersonById(Integer id) {
        personRepository.deleteById(id);
        Map<String, Object> map = new HashMap<>();

        map.put("msg", "删除成功");

        return JSON.toJSONString(map);
    }

    /**
     *
     * @return
     */
    @Override
    public String deleteAll() {

        personRepository.deleteAll();

        Map<String, Object> map = new HashMap<>();

        map.put("msg", "删除全部数据成功");

        return JSON.toJSONString(map);
    }
}
