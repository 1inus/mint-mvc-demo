package interceptor;

import javax.servlet.http.HttpServletRequest;

import demo.User;
import mint.mvc.annotation.InterceptorMapping;
import mint.mvc.annotation.InterceptorOrder;
import mint.mvc.core.ActionContext;
import mint.mvc.core.Interceptor;
import mint.mvc.core.InterceptorChain;

/** 
 * 拦截器的定义和用法
 * @author LiangWei(cnliangwei@foxmail.com)
 * @date 2015年5月2日 下午1:51:58 
 */
@InterceptorOrder(0)
@InterceptorMapping(urls={"/**"})
public class GlobalInterceptor extends Interceptor{
	
    public void intercept(ActionContext ctx, InterceptorChain chain) throws Exception {
    	
    	/*
    	 * 在action被调用之前，调用request.setAttribute设置的数据，
    	 * 可以在action里声明同类型同名的参数接收到
    	 */
        HttpServletRequest request = ctx.getHttpServletRequest();
        
        User user = new User();
        user.setId("8888");
        user.setEmail("cnliangwei@foxmail.com");
        request.setAttribute("user", user);
        
        chain.doInterceptor(ctx);
    }
}