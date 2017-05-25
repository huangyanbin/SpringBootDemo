package bootdemo.service;

import bootdemo.dao.ArticleMapper;
import bootdemo.dao.CollectionMapper;
import bootdemo.dao.UserMapper;
import bootdemo.entity.*;
import bootdemo.exception.ResultException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by huang on 2017/5/21.
 */
@Service
public class CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @CacheEvict(value = "getCollectionTypeByUserId",allEntries = true)
    public CollectionType saveCollectionType(int uid, String collectionTypeName)throws Exception{
        checkUid(uid);
        CollectionType collectionType = new CollectionType();
        collectionType.setCreateTime(new Date(System.currentTimeMillis()));
        collectionType.setType(collectionTypeName);
        collectionMapper.saveCollectionType(collectionType);
        return collectionType;
    }
    @CacheEvict(value = "getCollectionByTypeID",allEntries = true)
    public TCollection saveCollection(int collectionTypeID, int articleId)throws Exception{
        CollectionType collectionType = collectionMapper.findTypeById(collectionTypeID);
        if(collectionType == null){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"没有该收藏集");
        }
        Article article = articleMapper.findById(articleId);
        if(article == null){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"没有该文章");
        }
        TCollection collection = new TCollection();
        collection.setCollectionTime(new Date());
        collection.setType(collectionType);
        collection.setArticle(article);
        collectionMapper.saveCollection(collection);
        return collection;
    }
    @Cacheable(value = "getCollectionByTypeID")
  public List<TCollection> getCollectionByTypeID(int pageNum, int pageSize, int collectionTypeID)throws Exception {
      PageHelper.startPage(pageNum,pageSize);
      return collectionMapper.findCollectionsByType(collectionTypeID);
  }
    @Cacheable(value = "getCollectionTypeByUserId")
    public List<CollectionType> getCollectionTypeByUserId(int uid)throws Exception {
        checkUid(uid);
        return collectionMapper.findTypeByUid(uid);
    }

    private void checkUid(int uid) throws Exception{

        int count = userMapper.isCheckUserID(uid);
        if (count == 0){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"没有该用户");
        }
    }
}
