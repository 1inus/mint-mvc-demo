package action;

import demo.User;
import mint.mvc.annotation.API;
import mint.mvc.annotation.Module;
import mint.mvc.annotation.ServiceNames;

@Module(url="/interceptorDemo", id="userTest", name="")
public class InterceptorDemoAction {

	@ServiceNames({"$auth", "$login"})
	@API(urls="")
	public String service(User user) {
		return "当前登录用户：" + user.getEmail();
	}
}
