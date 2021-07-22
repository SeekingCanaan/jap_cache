package com.example.jpa.repository;

import com.example.jpa.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    /**
     * 按照名称模糊查询
     * @param KeyName 模糊名
     * @return name 字段含有 KeyName 的所有 person 数据
     */
    @Query(value = "select p from Person p where p.name like %?1%")
    List<Person> findByNameLike(String KeyName);

    /**
     * 按照 id 字段升序返回所有 person 数据
     * @return 所有 person 数据
     */
    @Query(value = "select p from Person p order by p.id")
    List<Person> findAllAndOrderBy();

    /**
     * 验证用户登陆
     * @param name 用户名
     * @param password 用户密码
     * @return 查询登陆用户信息的结果
     */
    @Query(value = "select p from Person p where p.name = ?1 and p.password = ?2")
    Person findPeopleWithLogin(String name, String password);

}
