package bootdemo.service;

import bootdemo.dao.ArticleMapper;
import bootdemo.dao.FavMapper;
import bootdemo.dao.UserMapper;
import bootdemo.entity.*;
import bootdemo.exception.ResultException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2017/5/21.
 */
@Service
public class FavService {

    @Autowired
    private FavMapper favMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;


    public Fav saveFav(int uid,int articleId)throws Exception{
        checkUid(uid);
        Article article = articleMapper.findById(articleId);
        if(article == null){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"没有该文章");
        }
        if(isCheckFav(uid,articleId)){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"您已经收藏了");
        }
        Fav fav = new Fav();
        fav.setFavTime(new Date(System.currentTimeMillis()));
        fav.setArticle(article);
        favMapper.saveFav(fav);
        return fav;
    }

    public int deleteFav(int uid,int articleId)throws Exception{
        checkUid(uid);
        if(!isCheckFav(uid,articleId)){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"您没有收藏");
        }
        favMapper.deleteFav(uid,articleId);
        return 1;
    }

  public List<Fav> getFavesByUserName(int pageNum,int pageSize, int uid)throws Exception {
      checkUid(uid);
      PageHelper.startPage(pageNum,pageSize);
      return favMapper.findFavesByUid(uid);
  }

    public List<User> getFavesByArticle(int pageNum,int pageSize,int articleId)throws Exception {

        PageHelper.startPage(pageNum,pageSize);
        List<Integer> userIDs =  favMapper.getFavByArticleId(articleId);
        List<User> users = new ArrayList<>();
        for(Integer uid :userIDs){
            users.add(userMapper.findById(uid));
        }
        return users;
    }

    public Integer getFavCountByArticle(int articleId)throws Exception {

       return favMapper.getFavCountByarticleId(articleId);
    }

    public boolean isCheckFav(int uid,int articleId){
        return favMapper.checkFavByUid(uid,articleId) >0;
    }



    private void checkUid(int uid) throws Exception{

        int count = userMapper.isCheckUserID(uid);
        if (count == 0){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"没有该用户");
        }
    }
}
