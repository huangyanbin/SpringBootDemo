package bootdemo.dao;

import bootdemo.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by huang on 2017/5/21.
 *  id INT PRIMARY KEY AUTO_INCREMENT,
 uid INT,
 title VARCHAR(200),
 url VARCHAR(200),
 icon VARCHAR(200),
 content VARCHAR(200),
 createTime Date,
 typeID INT,
 */
@Mapper
public interface ArticleMapper {


    @Select("select * from article")
    @ResultMap("articleResult")
    List<Article> queryAll();
    @Insert("insert into article(uid,title,url,icon,content,createTime,typeID) value (#{user.id},#{title},#{url},#{icon},#{content},#{createTime},#{type.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(Article article);

    @Select("select * from Article where title like '%${title}%'")
    List<Article> findArticleByName(String title);

    @Select("select * from article where id =#{id}")
    @ResultMap("articleResult")
    Article findById(@Param("id")int id);



}
