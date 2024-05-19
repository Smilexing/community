package com.nowcoder.community.dao;

import com.nowcoder.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * @author Tom Smile
 * @version 1.0
 * @description: TODO
 * @date 2024/5/19 20:11
 */
@Mapper
public interface LoginTicketMapper {
    // 使用@Option表明主键的生成策略
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({"insert into login_ticket (user_id,ticket,status,expired)", "values (#{userId},#{ticket},#{status},#{expired})"})
    public int insertTicket(LoginTicket loginTicket);

    @Select({"select id,user_id,ticket,status,expired",
            " from login_ticket where ticket=#{ticket}"})
    public LoginTicket selectByTicket(String ticket);

    @Update({"update login_ticket set status=#{status} where ticket=#{ticket}"})
    public int updateTicket( String ticket,int status);
}
