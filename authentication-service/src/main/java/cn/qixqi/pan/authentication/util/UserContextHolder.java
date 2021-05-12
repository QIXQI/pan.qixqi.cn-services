package cn.qixqi.pan.authentication.util;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>();

    public static final UserContext get(){
        UserContext context = userContext.get();
        if (context == null){
            context = new UserContext();
            userContext.set(context);
        }
        return userContext.get();
    }

    public static final void set(UserContext context){
        if (context == null){
            // 只能设置非空值
            return;
        }
        userContext.set(context);
    }
}
