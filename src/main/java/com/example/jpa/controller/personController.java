package com.example.jpa.controller;


import com.example.jpa.customAnnotation.UserLoginToken;
import com.example.jpa.pojo.Person;
import com.example.jpa.service.personService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // 代替了 @Autowired
@RestController
public class personController {

    final personService personService;

    /**
     * 查询所有 person 接口
     * @return 查询到的所有 person 数据
     */
    @UserLoginToken
    @GetMapping("/person")
    public String findAll() {
        return personService.findAll();
    }

    /**
     * 验证用户登陆接口
     * @param p 封装的用户信息
     * @return 返回查询到的用户信息，如果没有，则返回 null
     */
    @PostMapping("/person/login")
    public String Login(@RequestBody Person p) {

        return personService.findPeopleWithLogin(p.getName(), p.getPassword());
    }

    /**
     * 按照用户名模糊搜索的接口
     * @param KeyName 模糊搜索的关键字
     * @return 根据关键字查询到的用户信息
     */
//    @PreAuthorize("#KeyName=='admin'")
    @GetMapping("/person/{KeyName}")
    public String findByNameLike(@PathVariable String KeyName) {
        return personService.findByNameLike(KeyName);
    }

    /**
     * 添加 person 信息的接口
     * @param p 提交的人员信息
     * @return 添加成功/失败的消息提示
     */
    @PostMapping("/person")
    public String savePerson(@RequestBody Person p) {
        return personService.savePerson(p);
    }

    /**
     * 更新 person 信息的接口
     * @param p 提交的人员信息
     * @return 更新成功/失败的消息提示
     */
    @PutMapping("/person")
    public String updatePerson(@RequestBody Person p) {
        return personService.updatePerson(p);
    }

    /**
     * 按 id 删除 person 的接口
     * @param id 解析的要删除 person 的参数 id
     * @return 删除成功/失败的消息提示
     */
    @DeleteMapping("/person/{id}")
    public String deletePersonById(@PathVariable Integer id) {
        return personService.deletePersonById(id);
    }

    /**
     * 删除全部 person 数据的接口
     * @return 删除成功/失败的消息提示
     */
    @DeleteMapping("/person")
    public String deleteAll() {
        return personService.deleteAll();
    }

}
