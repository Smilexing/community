package com.nowcoder.community.demo.service;

import com.nowcoder.community.demo.dao.AlphaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class AlphaDaoMyBatisImpl implements AlphaDao {
    @Override
    public String select() {
        return "MyBatis";
    }
}
