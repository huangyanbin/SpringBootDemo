package bootdemo.dao;

import bootdemo.entity.TCollection;
import bootdemo.entity.CollectionType;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 #收藏集
 CREATE TABLE CollectionType(
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
 CollectionTime DATE,
 */
@Mapper
public interface CollectionMapper {



    @Select("select * from CollectionType where uid=#{uid}")
    @ResultMap("CollectionTypeResult")
    List<CollectionType> findTypeByUid(@Param("uid") int uid);


    @Insert("insert into CollectionType(uid,type,createTime) value (#{user.id},#{type},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveCollectionType(CollectionType collectionType);

    @Select("select * from CollectionType where type like '%${title}%'")
    List<CollectionType> findCollectionTypeByName(String title);

    @Select("select * from CollectionType where id =#{id}")
    @ResultMap("CollectionTypeResult")
    CollectionType findTypeById(@Param("id") int id);

    @Insert("insert into tab_Collection(articleID,typeID,CollectionTime) value (#{article.id},#{type.id},#{collectionTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveCollection(TCollection collection);


    @Delete("delete from tab_Collection where CollectionId=#{collectionId}")
    int deleteCollection(@Param("collectionId") int collectionId);


    @Select("select * from tab_Collection where typeID =#{collectionTypeId}")
    @ResultMap("CollectionResult")
    List<TCollection> findCollectionsByType(@Param("collectionTypeId") int collectionTypeId);

}
