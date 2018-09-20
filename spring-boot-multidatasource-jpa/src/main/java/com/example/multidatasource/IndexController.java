package com.example.multidatasource;

import com.example.multidatasource.repo.mssql.MsUserRepository;
import com.example.multidatasource.repo.mysql.MySQLUserRepository;
import com.example.multidatasource.domain.mssql.MsUser;
import com.example.multidatasource.domain.mysql.MysqlUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Douglee on 2018/9/20.
 */
@RestController
public class IndexController {

    @Autowired
    private MsUserRepository msUserRepository;

    @Autowired
    private MySQLUserRepository mySQLUserRepository;

    @GetMapping("/")
    public String index(){
        return "Spring Boot + Spring data jpa 多数据源示例";
    }

    @GetMapping("/ms/{id}")
    public MsUser getmsUser(@PathVariable Long id){
        MsUser user = msUserRepository.findById(id).get();

        return user;
    }

    @GetMapping("/mysql/{id}")
    public MysqlUser getmysqlUser(@PathVariable Long id){
        MysqlUser user = mySQLUserRepository.getOne(id);

        return user;
    }
}
