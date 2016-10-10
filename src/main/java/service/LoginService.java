package service;

import javax.servlet.http.HttpServletRequest;

import demo.User;
import mint.mvc.annotation.ServiceConfig;
import mint.mvc.core.APIConfig;
import mint.mvc.core.RequestContext;
import mint.mvc.core.ModuleConfig;
import mint.mvc.core.Service;
import mint.mvc.core.ServiceChain;

/**
 * <p>Service（服务）的作用类似于Interceptor（拦截器），都是在action执行之前，对请求进行拦截和处理。
 * 但是和拦截器不同的是，action可以通过@ServiceNames注解来主动指定由哪些服务来拦截请求，多个服务的执
 * 行顺序和@ServiceNames中的呼叫顺序一致，因此服务使用起来更加的灵活。</p>
 * 
 * <p>一个合法服务必须继承Service抽象类并且实现service方法，同时还要使用@ServiceName注解为服务起一个名字</p>
 * 
 * <p>服务特别的方便有用，常见的使用场合比如验证码验证，文件上传等，也可以用来做自动登陆</p>
 */
@ServiceConfig(name = "$login")
public class LoginService extends Service{

	@Override
	public void service(RequestContext ctx, ModuleConfig module, APIConfig api, ServiceChain chain) throws Exception {
		HttpServletRequest request = ctx.getHttpServletRequest();
		
		//在Service中进行基础认证
		User user = new User();
        user.setId("8888");
        user.setEmail("cnliangwei@foxmail.com");
        
        //然后用request把认证结果传递到action中
        request.setAttribute("user", user);
        
        chain.doService(ctx);
	}
}