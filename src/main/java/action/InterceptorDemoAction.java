package action;

import demo.User;
import mint.mvc.annotation.Module;
import mint.mvc.annotation.API;

@Module(url="/interceptorDemo", name="")
public class InterceptorDemoAction {

	@API(urls="")
	public String service(User user) {
		return "当前登录用户：" + user.getEmail();
	}
}
