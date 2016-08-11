package interceptor;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.User;
import mint.mvc.annotation.InterceptorConfig;
import mint.mvc.core.APIConfig;
import mint.mvc.core.ActionContext;
import mint.mvc.core.Interceptor;
import mint.mvc.core.InterceptorChain;
import mint.mvc.core.ModuleConfig;

/** 
 * 拦截器的定义和用法
 * @author LiangWei(cnliangwei@foxmail.com)
 * @date 2015年5月2日 下午1:51:58 
 */
@InterceptorConfig(urls={"/**"}, id="i2899176", desc="全局拦截器，进行统一的授权处理", tags={"admin", "auth"})
public class GlobalInterceptor extends Interceptor{
	
    public void intercept(ActionContext ctx, ModuleConfig module, APIConfig api, InterceptorChain chain) throws Exception {
    	
    	/*
    	 * 在action被调用之前，调用request.setAttribute设置的数据，
    	 * 可以在action里声明同类型同名的参数接收到
    	 */
        HttpServletRequest request = ctx.getHttpServletRequest();
        
        ObjectMapper mapper = new ObjectMapper();
       /* System.out.println(mapper.writeValueAsString(module));
		System.out.println(mapper.writeValueAsString(api));*/
        
        User user = new User();
        user.setId("8888");
        user.setEmail("cnliangwei@foxmail.com");
        request.setAttribute("user", user);
        
        chain.doInterceptor(ctx);
    }
}