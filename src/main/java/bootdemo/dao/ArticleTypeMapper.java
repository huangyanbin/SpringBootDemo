package bootdemo.dao;

import bootdemo.entity.ArticleType;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by huang on 2017/5/21.
 *   id INT PRIMARY KEY AUTO_INCREMENT,
 type VARCHAR(100),
 createTime DATE
 */
@Mapper
public interface ArticleTypeMapper {


    @Select("select * from ArticleType")
    List<ArticleType> queryAll();

    @Insert("insert into ArticleType(title,createTime) value (#{title},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(ArticleType articleType);

    @Select("select * from ArticleType where title like '%${title}%'")
    List<ArticleType> findArticleTypeByName(String title);

    @Select("select * from ArticleType where id=#{id}")
    ArticleType findById(@Param("id") int id);



}
