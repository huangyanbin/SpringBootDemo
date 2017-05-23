package bootdemo.dao;

import bootdemo.entity.Article;
import bootdemo.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by huang on 2017/5/21.
 * #用户
 CREATE  TABLE User(
 id INT PRIMARY KEY AUTO_INCREMENT,
 userName VARCHAR(12) NOT NULL,
 nickName VARCHAR(50),
 password VARCHAR(15),
 icon VARCHAR(200),
 position VARCHAR(100),
 company  VARCHAR(100),
 intro VARCHAR(200),
 address VARCHAR(100),
 createTime DATE
 );
 */
@Mapper
public interface UserMapper {


    @Select("select id,userName,nickName,icon,position,company,intro,address from user")
    List<User> queryAll();

    @Select("select * from user where userName=#{userName}")
    User findByUserNameByLogin(String userName);

    @Select("select id, userName,nickName,icon,position,company,intro,address from user where userName=#{userName}")
    User findByUserName(String userName);

    @Select("select count(userName) from user where userName=#{userName}")
    int isCheckUserName(String userName);

    @Select("select count(userName) from user where id=#{id}")
    int isCheckUserID(@Param("id") int id);

    @Insert("insert into User(userName,password,createTime) value (#{userName},#{password},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(User user);

    @UpdateProvider(type =UserSql.class,method = "update")
    int update(User user);

    @Update("update user set secretKey=#{secretKey} where userName=#{userName}")
    int updateSecretKey(@Param("userName") String userName,@Param("secretKey") String secretKey);

    @Select("select secretKey from user where userName=#{userName}")
    String findSecretKey(String userName);


    @Select("select id,userName,nickName,icon,position,company,intro,address from user where id=#{id}")
    User findById(@Param("id") int id);


    class UserSql {

        public String update(final User user) {
            return new SQL() {
                {
                    UPDATE("user");

                    //通过条件 判断是否需要更新该字段
                    if (!StringUtils.isEmpty(user.getNickName())) {
                        SET("nickName = #{nickName}");
                    }

                    if (!StringUtils.isEmpty(user.getPassword())) {
                        SET("password = #{password}");
                    }

                    if (!StringUtils.isEmpty(user.getIcon())) {
                        SET("icon = #{icon}");
                    }
                    if (!StringUtils.isEmpty(user.getPosition())) {
                        SET("position = #{position}");
                    }
                    if (!StringUtils.isEmpty(user.getCompany())) {
                        SET("company = #{company}");
                    }
                    if (!StringUtils.isEmpty(user.getIntro())) {
                        SET("intro = #{intro}");
                    }
                    if (!StringUtils.isEmpty(user.getAddress())) {
                        SET("address = #{address}");
                    }

                    WHERE("userName = #{userName}");
                }
            }.toString();
        }
    }
}
