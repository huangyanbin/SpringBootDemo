package bootdemo.service;

import bootdemo.dao.UserMapper;
import bootdemo.entity.ResultCode;
import bootdemo.entity.User;
import bootdemo.exception.ResultException;
import bootdemo.utils.AESUtils;
import bootdemo.utils.PatternUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by David on 2017/5/23.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User register(String userName, String password) throws Exception {
        checkNameAndPwd(userName, password);
        int count = userMapper.isCheckUserName(userName);
        if (count > 0) {
            throw new ResultException(ResultCode.HAS_DATA_ERROR, "该用户已注册！");
        }
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setCreateTime(new Date(System.currentTimeMillis()));
        userMapper.save(user);
        return user;
    }

    public User login(String userName, String password) throws Exception {
        checkNameAndPwd(userName, password);
        User user = userMapper.findByUserNameByLogin(userName);
        if (user == null) {
            throw new ResultException(ResultCode.HAS_DATA_ERROR, "该用户不存在！");
        }else if(!password.equals(user.getPassword())){
            throw new ResultException(ResultCode.HAS_DATA_ERROR, "密码错误！");
        }
        String secretKey = AESUtils.getKeyByPass(userName);
        userMapper.updateSecretKey(userName,secretKey);
        user.setSecretKey(secretKey);
        return user;
    }

    public int update(User user)throws Exception{
        if(userMapper.isCheckUserName(user.getUserName()) == 0){
            throw new ResultException(ResultCode.HAS_DATA_ERROR, "没有该用户!");
        }else {
            try {
                userMapper.update(user);
            }catch (Exception e){
                throw new ResultException(ResultCode.HAS_DATA_ERROR, "更新失败！");
            }
        }
        return 1;
    }

    public String getSecretKey(int uid){

        return  userMapper.findSecretKey(uid);
    }

    public List<User>  getUserList(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return userMapper.queryAll();
    }


    private void checkNameAndPwd(String userName, String password) throws Exception {

        if (StringUtils.isEmpty(userName)) {
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR, "用户名不能为空！");
        } else if (!PatternUtils.isPhoneLegal(userName)) {
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR, "手机格式不正确！");
        } else if (StringUtils.isEmpty(password) || password.length() < 6) {
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR, "密码格式不正确！");
        }
    }

}
