package bootdemo.dao;

import bootdemo.entity.Fav;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**

 id INT PRIMARY KEY AUTO_INCREMENT,
 uid INT,
 articleID INT,
 favTime DATE,
 */
@Mapper
public interface FavMapper {


    @Insert("insert into fav(articleID,uid,favTime) value (#{article.id},#{user.id},#{favTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveFav(Fav fav);


    @Delete("delete from fav where where uid =#{uid} and articleId=#{articleId}")
    int deleteFav(@Param("uid") int uid,@Param("articleId") int articleId);

    @Select("select count(id) from fav where uid =#{uid} and articleId=#{articleId}")
    int checkFavByUid(@Param("uid") int uid,@Param("articleId") int articleId);

    @Select("select count(id) from fav where articleId=#{articleId}")
    int getFavCountByarticleId(@Param("articleId") int articleId);

    @Select("select uid from fav where articleId=#{articleId}")
    List<Integer> getFavByArticleId(@Param("articleId") int articleId);

    @Select("select * from fav where uid =#{uid}")
    @ResultMap("FavResult")
    List<Fav> findFavesByUid(@Param("uid") int uid);

}
