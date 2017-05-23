package bootdemo.service;

import bootdemo.dao.FollowMapper;
import bootdemo.dao.UserMapper;
import bootdemo.entity.Follow;
import bootdemo.entity.ResultCode;
import bootdemo.entity.User;
import bootdemo.exception.ResultException;
import bootdemo.utils.AESUtils;
import bootdemo.utils.PatternUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2017/5/23.
 */
@Service
public class FollowService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FollowMapper followMapper;


   public Follow follow(String following,String followed) throws Exception{
       User followingUser = userMapper.findByUserName(following);
       User followedUser = userMapper.findByUserName(followed);
       if(followedUser == null){
           throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"无法关注该用户");
       }
       if(checkFollow(followingUser,followedUser)){
           throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"你已经关注过他（她）");
       }
       Follow follow = new Follow();
       follow.setFollowingUser(followingUser);
       follow.setFollowedUser(followedUser);
       follow.setFollowTime(new Date(System.currentTimeMillis()));
       followMapper.addFollow(follow);
      return  follow;
   }


    public int cancelFollow(String following,String followed){
       User followingUser = userMapper.findByUserName(following);
       User followedUser = userMapper.findByUserName(followed);
       if(followedUser == null){
           throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"无法取消关注该用户");
       }
       if(!checkFollow(followingUser,followedUser)){
           throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"你没有关注过他（她）");
       }
       followMapper.deleteFollow(followingUser.getId(),followedUser.getId());
       return 1;
   }

    private boolean checkFollow(User followingUser, User followedUser){
        int count = followMapper.checkFollowStatus(followingUser.getId(),followedUser.getId());
         return count> 0;
    }

    /**
     * 查找关注自己的人
     * @return
     */
    public List<User> getAllFollowing(String followed){
        User followedUser = userMapper.findByUserName(followed);
        List<Integer> userIDs =  followMapper.findFollowing(followedUser.getId());
        List<User> users = new ArrayList<>();
        for(Integer uid :userIDs){
            users.add(userMapper.findById(uid));
        }
        return users;
    }

    /**
     * 查找自己关注的人
     * @return
     */
    public List<User> getAllFollowed(String following){

        User followingUser = userMapper.findByUserName(following);
        List<Integer> userIDs =  followMapper.findFollowed(followingUser.getId());
        List<User> users = new ArrayList<>();
        for(Integer uid :userIDs){
            users.add(userMapper.findById(uid));
        }
        return users;
    }
}
