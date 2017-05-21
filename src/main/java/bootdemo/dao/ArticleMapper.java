package bootdemo.dao;

import bootdemo.entity.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by huang on 2017/5/21.
 */
@Mapper
public interface ArticleMapper {


    @Select("select * from article")
    List<Article> queryAll();
    @Insert("insert into article(author,title,url,content,favCount) value (#{author},#{title},#{url},#{content},#{favCount})")
    int save(Article article);
}
