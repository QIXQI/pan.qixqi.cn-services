package cn.qixqi.pan.user.controller;

import cn.qixqi.pan.user.model.User;
import cn.qixqi.pan.user.model.UserBase;
import cn.qixqi.pan.user.model.UserExt;
import cn.qixqi.pan.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public User register(@RequestBody UserBase userBase){
        userBase = userService.addUserBase(userBase);
        UserExt userExt = new UserExt().
                withUid(userBase.getUid());
        userExt = userService.addUserExt(userExt);
        User user = new User();
        return user
                .withUserBase(userBase)
                .withUserExt(userExt);
    }

    /**
     * REST节点，login
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestParam String authInfo, @RequestParam String password, @RequestParam String authType){
        UserBase userBase = userService.getUserBaseByAuth(authInfo, password, authType);
        if (userBase == null){
            return null;
        }
        UserExt userExt = userService.getUserExt(userBase.getUid());
        User user = new User();
        return user
                .withUserBase(userBase)
                .withUserExt(userExt);
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public User getUser(@PathVariable String uid){
        UserBase userBase = userService.getUserBaseByUid(uid);
        UserExt userExt = userService.getUserExt(uid);
        User user = new User();
        return user
                .withUserBase(userBase)
                .withUserExt(userExt);
    }

    @RequestMapping(value = "/base", method = RequestMethod.PUT)
    public UserBase updateUserBase(@RequestBody UserBase userBase){
        return userService.updateUserBase(userBase);
    }

    @RequestMapping(value = "/ext", method = RequestMethod.PUT)
    public UserExt updateUserExt(@RequestBody UserExt userExt){
        return userService.updateUserExt(userExt);
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteUser(@PathVariable String uid){
        userService.deleteUserBase(uid);
        // userService.deleteUserExt(uid);      // 有外键，不用手动删除
        return String.format("删除成功：%s", uid);
    }
}
