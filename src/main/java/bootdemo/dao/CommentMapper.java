package bootdemo.dao;

import bootdemo.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CommentMapper {


    @Insert("insert into comment(articleID,uid,content,commitTime) value (#{article.id},#{user.id},#{content},#{commitTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int saveComment(Comment comment);


    @Delete("delete from comment where commentId=#{commentId} and uid =#{uid}")
    int deleteComment(@Param("commentId") int commentId,@Param("uid") int uid);

    @Select("select * from comment where uid =#{uid} and articleId=#{articleId}")
    @ResultMap("CommentResult")
    List<Comment> checkCommentByUid(@Param("uid") int uid, @Param("articleId") int articleId);

    @Select("select count(id) from comment where articleId=#{articleId}")
    int getCommentCountByArticleId(@Param("articleId") int articleId);

    @Select("select * from comment where articleId=#{articleId}")
    @ResultMap("CommentResult")
    List<Comment> getCommentByArticleId(@Param("articleId") int articleId);

    @Select("select * from comment where uid =#{uid}")
    @ResultMap("CommentResult")
    List<Comment> findCommentsByUid(@Param("uid") int uid);

}
