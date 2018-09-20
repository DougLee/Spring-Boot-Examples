package com.example.multidatasource.repo.mssql;

import com.example.multidatasource.domain.mssql.MsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Douglee on 2018/9/20.
 */
@Repository
public interface MsUserRepository extends JpaRepository<MsUser, Long> {
}
