package action;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import demo.User;
import mint.mvc.annotation.Module;
import mint.mvc.annotation.API;
import mint.mvc.annotation.Required;

/**
 * <p>在action方法执行之前，它的参数的会被mint-mvc初始化，这样就不用开发者从request中获取请求参数，一个个处理成action方法的参数。</p>
 * <p>mint-mvc初始化action参数的机制有五种：</p>
 * <ol>
 * <li>用url初始化action参数</li>
 * <li>用请求参数初始化action参数</li>
 * <li>用HttpServletRequest对象的属性初始化action参数</li>
 * <li>“内置参数”初始化action参数（request，response，session，cookies）</li>
 * <li>采用Cookie初始化参数</li>
 * </ol>
 * 
 * @author liangwei
 */

@Module(url="/paramDemo")
public class ParamDemoAction {
	
	/**
	 * <h2>接收url参数</h2>
	 * <p>在声明action方法时，可以在Mapping注解的urls参数中，用"{xxx}"指明url中某个部分作为参数，“xxx”指的是action方法的参数名字。
	 * 参数名后面和添加正则表达式对参数的格式作简单限制，参数名和正则之间用“:”隔开，比如{param2:\\\\d+}，表示只接受数字格式的参数</p>
	 * 
	 * @param param1 	接收一个字符串类型的参数
	 * @param params2	接收一个整型参数，并且这个参数用简单正则进行限制
	 * @return
	 */
	@API(urls="/{param1}/{param2:\\\\d+}")
	public String urlParams(String param1, Integer param2){
		return String.format("url参数：param1=%s;param2=%d", 
				param1, param2);
	}
	
	/**
	 * <h2>接收请求参数</h2>
	 * <p>这种方式背后的原理就是，使用ServletRequest的getParameter()方法获取参数。
	 * 请求参数来源包括url查询参数和表单提交的参数。mint-mvc会根据请求参数名和action
	 * 方法参数名的对应关系，来初始化action参数。采用该方式能初始化较复杂的参数。</p>
	 * 
	 * <p>能初始化action参数类型目前包括：基础类型的包装类（包括String），
	 * 数组以及简单bean类。这里有一个约定敬请留意：所有基础类型参数必须使用
	 * 对应的包装类，比如int类型必须写成Integer，否则该方法将是无效的action方法</p>
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 */
	@API(urls="/requestParams")
	public String requestParams(String name, String pwd){
		return String.format("接受到参数：name=%s;password=%s",
				name, pwd);
	}
	
	/**
	 * <h2>初始化一个bean类的action参数</h2>
	 * <p>想初始化一个bean类的后台参数，前台的表单参数名应该是这样的："paramName.fieldName"</p>
	 * <p>@Required 注解可以声明一个参数不能为空，若该参数为空，则这个action将无法被访问，返回403错误</p>
	 * 
	 * @param u
	 * @return
	 */
	@API(urls="/initBean")
	public String initBean(@Required User u){
		return String.format("接受到参数：id=%s;email=%s",
				u.getId(), u.getEmail());
	}
	
	/**
	 * <p>接收一个数组</p>
	 * @param arr 能接收参数名为arr 或者 arr[] 的参数
	 * @return
	 */
	@API(urls="/initArray")
	public String arrayParams(Integer[] arr){
		return String.format("接受到参数：arr[0]=%d;arr[1]=%d",
				arr[0], arr[1]);
	}
	
	
	/**
	 * <h2>用request对象的属性(attribute)初始化action参数</h2>
	 * <p>request.setAttribute()方法可以在request对象中缓存一些自定义的数据， 
	 * 这些数据在web容器内部流转，在整个请求处理阶段都有效，在需要的地方可以根据属
	 * 性名字调用getAttribute()方法取出。mint-mvc中也采用request内缓存的属性来
	 * 初始化action的参数。</p>
	 * 
	 * <p>在整个请求的数据流当中，如果在action的上游（往往是Filter和拦截器）
	 * 往request添加了属性，那么action中就可以 声明同名且同类型的参数来获取到。</p>
	 * 
	 * @param user
	 * @return
	 */
	@API(urls="/getAttribute")
	public String getAttribute(User user){
		return String.format("接受到参数：id=%s;email=%s", 
				user.getId(), user.getEmail());
	}
	
	/**
	 * 设置cookie
	 * @param resp
	 */
	@API(urls="/setCookie", method="get")
	public String setCookie(HttpServletResponse resp){
		String c0 = "for auto login" + new Date().getTime()/1000;
		String c1 = new Date().getTime()/1000+"";
		
		Cookie cookie0 = new Cookie("UID", c0);
		cookie0.setPath("/");
		cookie0.setMaxAge(3600);
		cookie0.setHttpOnly(true);
		resp.addCookie(cookie0);
		
		Cookie cookie1 = new Cookie("DATE", c1);
		cookie1.setPath("/");
		cookie1.setMaxAge(3600);
		cookie1.setHttpOnly(true);
		resp.addCookie(cookie1);
		
		return String.format("两个cookie：UID=%s;DATE=%s", c0, c1);
	}
	
	/**
	 * <h2>"内置参数"初始化action参数</h2>
	 * <p>jsp中有内置参数，mint-mvc也借用了这种做法，将一些使用频率很高的参数设置为内置参数，以简化获取过程。
	 * 目前支持的内置参数有四个：HttpServletRequest、HttpServletResponse、HttpSession和Cookie[]。</p>
	 * 
	 * @param resp
	 * @param req
	 * @param session
	 * @param cookies
	 */
	@API(urls="/buildIdParams")
	public String buildIdParams(
			HttpServletResponse resp, 
			HttpServletRequest req, 
			HttpSession session, 
			Cookie[] cookies){
		
		return String.format("接受到参数：<br/>"
			+ "response=%s;<br/>request=%s;"
			+ "<br/>session=%s;<br/>cookies=%s", 
			resp.toString(), req.toString(), 
			session.toString(), cookies.toString());
	}
	
	
	/**
	 * <h2>采用Cookie初始化参数</h2>
	 * <p>通过cookie名字获取cookie</p>
	 * @param UID
	 * @return
	 */
	@API(urls="/getCookie")
	public String getCookie(Cookie UID, Cookie DATE){
		return String.format("获取到cookie：cookie(UID)=%s;cookie(DATE)=%s", 
				UID.getValue(), DATE.getValue());
	}
	
	@API(urls="/test")
	public void test(String key, String pageSize, String page){
		System.out.println("key:"+key+"pageSize:"+pageSize+"page:"+page);
	}
}
