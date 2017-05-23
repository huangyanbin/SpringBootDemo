package bootdemo.service;

import bootdemo.dao.ArticleMapper;
import bootdemo.dao.ArticleTypeMapper;
import bootdemo.dao.FavMapper;
import bootdemo.dao.UserMapper;
import bootdemo.entity.*;
import bootdemo.exception.ResultException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
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


    public FavType saveFavType(String userName,String favTypeName)throws Exception{
        User user = userMapper.findByUserName(userName);
        if (user == null){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"没有该用户");
        }
        FavType favType = new FavType();
        favType.setCreateTime(new Date(System.currentTimeMillis()));
        favType.setUid(user.getId());
        favType.setType(favTypeName);
        favMapper.saveFavType(favType);
        return favType;
    }

    public Fav saveFav(int favTypeID,int articleId)throws Exception{
        FavType favType = favMapper.findTypeById(favTypeID);
        if(favType == null){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"没有该收藏集");
        }
        Article article = articleMapper.findById(articleId);
        if(article == null){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"没有该文章");
        }
        Fav fav = new Fav();
        fav.setFavTime(new Date(System.currentTimeMillis()));
        fav.setType(favType);
        fav.setArticle(article);

        favMapper.saveFav(fav);
        return fav;
    }

  public List<Fav> getFavByTypeID(int pageNum,int pageSize,int favTypeID)throws Exception {
      PageHelper.startPage(pageNum,pageSize);
      return favMapper.findFavesByType(favTypeID);
  }
    public List<FavType> getFavTypeByUserName(String userName)throws Exception {
        User user = userMapper.findByUserName(userName);
        if (user == null){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"没有该用户");
        }
        return favMapper.findTypeByUid(user.getId());
    }


}
