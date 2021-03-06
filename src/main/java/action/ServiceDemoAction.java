package action;

import demo.User;
import mint.mvc.annotation.API;
import mint.mvc.annotation.Module;
import mint.mvc.annotation.ServiceNames;

@Module(url="/serviceDemo", tags={"admin"}, id="", desc="")
public class ServiceDemoAction {
	
	/**
	 * <p>Service（服务）的作用类似于Interceptor（拦截器），都是在action执行之前，对请求进行拦截和处理。
	 * 但是和拦截器不同的是，action可以通过@ServiceNames注解来主动指定由哪些服务来拦截请求，因此服务
	 * 使用起来更加的灵活。</p>
	 * 
	 * <p>一个合法服务必须继承Service抽象类并且实现service方法，同时还要使用@ServiceName注解为服务起一个名字</p>
	 * 
	 * <p>服务特别的方便有用，常见的使用场合比如验证码验证，文件上传等，也可以用来做自动登陆</p>
	 * 
	 * @param user
	 * @return
	 */
	@ServiceNames({"$login"})
	@API(urls="/login/{username}/{age}", id="", desc="", protocol="https" )
	public String service(User user, String username, Integer age) {
		
		
		return "当前登录用户：" + user.getEmail();
	}
}