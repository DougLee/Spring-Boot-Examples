package com.example.multidatasource.repo.mysql;

import com.example.multidatasource.domain.mysql.MysqlUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Douglee on 2018/9/20.
 */
@Repository
public interface MySQLUserRepository extends JpaRepository<MysqlUser, Long> {
}
