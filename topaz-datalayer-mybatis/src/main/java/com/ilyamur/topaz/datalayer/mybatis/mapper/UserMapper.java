package com.ilyamur.topaz.datalayer.mybatis.mapper;

import com.ilyamur.topaz.datalayer.core.entity.Role;
import com.ilyamur.topaz.datalayer.core.entity.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Collection;
import java.util.Set;

public interface UserMapper {

    String SELECT_ALL = "select id, login, email, birthday";
    String FROM_USER = "from user";
    String USER_RESULT = "userResult";
    String ROLE_RESULT = "roleResult";

    @Insert({
            "insert into user (id, login, email, birthday)",
            "values (#{id}, #{login}, #{email}, #{birthday})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update({
            "update user",
            "set login = #{login}, email = #{email}, birthday = #{birthday}",
            "where id = #{id}"
    })
    int update(User user);

    @Delete({
            "delete from user",
            "where id = #{id}"
    })
    void delete(User user);

    @Select({
            SELECT_ALL,
            FROM_USER,
            "where id = #{id}"
    })
    @Results(id = USER_RESULT, value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "login", property = "login"),
            @Result(column = "email", property = "email"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "id", property = "roles", many = @Many(select = "selectRolesById"))
    })
    User selectById(long id);

    @SuppressWarnings("unused")
    @Select({
            "select r.id, r.name",
            "from role r join user_role ur on r.id = ur.id_role",
            "where ur.id_user = #{id}"
    })
    @Results(id = ROLE_RESULT, value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name")
    })
    Set<Role> selectRolesById(long id);

    @Update("delete from user_role where id_user = #{id}")
    void deleteRoles(User user);

    @Update("insert into user_role (id_user, id_role) values (#{user.id}, #{role.id})")
    void insertRole(@Param("user") User user,
                    @Param("role") Role role);

    @Select({
            SELECT_ALL,
            FROM_USER,
            "where login = #{login}"
    })
    @ResultMap(USER_RESULT)
    User selectByLogin(String login);

    @Select({
            SELECT_ALL,
            FROM_USER
    })
    @ResultMap(USER_RESULT)
    Collection<User> selectAll();
}
