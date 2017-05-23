package bootdemo.dao;

import bootdemo.entity.Article;
import bootdemo.entity.Fav;
import bootdemo.entity.FavType;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 #收藏集
 CREATE TABLE FavType(
 id INT PRIMARY KEY AUTO_INCREMENT,
 uid INT,
 type VARCHAR(100),
 createTime DATE,
 FOREIGN KEY (uid) REFERENCES User(id)
 );
 id INT PRIMARY KEY AUTO_INCREMENT,
 uid INT,
 articleID INT,
 typeID INT,
 favTime DATE,
 */
@Mapper
public interface FavMapper {



    @Select("select * from favType where uid=#{uid}")
    List<FavType> findTypeByUid(@Param("uid") int uid);


    @Insert("insert into favType(uid,type,createTime) value (#{uid},#{type},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveFavType(FavType favType);

    @Select("select * from favType where type like '%${title}%'")
    List<FavType> findFavTypeByName(String title);

    @Select("select * from favType where id =#{id}")
    FavType findTypeById(@Param("id") int id);

    @Insert("insert into fav(articleID,typeID,favTime) value (#{article.id},#{type.id},#{favTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveFav(Fav fav);


    @Insert("delete from fav where favId=#{favId}")
    int deleteFav(@Param("favId") int favId);

    @Select("select * from fav where typeID =#{favTypeId}")
    @ResultMap("favResult")
    List<Fav> findFavesByType(@Param("favTypeId") int favTypeId);

}
