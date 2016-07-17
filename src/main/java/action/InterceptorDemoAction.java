package action;

import demo.User;
import mint.mvc.annotation.BaseMapping;
import mint.mvc.annotation.Mapping;

@BaseMapping("/interceptorDemo")
public class InterceptorDemoAction {

	@Mapping(urls="")
	public String service(User user) {
		return "当前登录用户：" + user.getEmail();
	}
}
