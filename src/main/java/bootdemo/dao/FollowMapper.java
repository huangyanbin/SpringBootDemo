package bootdemo.dao;

import bootdemo.entity.Follow;
import bootdemo.entity.User;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

/**
 * Created by David on 2017/5/23.
 *  id INT PRIMARY KEY AUTO_INCREMENT,
 followingUid INT, ##关注者
 followedUid INT, ##被关注者
 followTime DATE,
 */
@Mapper
public interface FollowMapper {

    @Insert("insert into FOLLOW (followingUid,followedUid,followTime) values(#{followingUser.id},#{followedUser.id},#{followTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addFollow(Follow follow);

    @Select("select count(followingUid) from FOLLOW where followingUid=#{following} and followedUid=#{followed}")
    int checkFollowStatus(@Param("following") int following, @Param("followed") int followed);

    @Delete("delete from FOLLOW where followingUid=#{following} and followedUid=#{followed}")
    int deleteFollow(@Param("following") int following, @Param("followedUid") int followed);

    //查找自己关注的人
    @Select("select followedUid  from FOLLOW  where followingUid=#{following}")
    List<Integer> findFollowed(@Param("following") int Following);

    //查找关注自己的人
    @Select("select followingUid from FOLLOW  where followedUid=#{followed}")
    List<Integer> findFollowing(@Param("followed") int Followed);
}
