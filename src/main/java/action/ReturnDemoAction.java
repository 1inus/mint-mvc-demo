package action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import Renderer.JSONRenderer;
import demo.User;
import mint.mvc.annotation.BaseMapping;
import mint.mvc.annotation.Mapping;
import mint.mvc.renderer.TemplateRenderer;

@BaseMapping("/returnDemo")
public class ReturnDemoAction {

	/**
	 * <p>返回字符串等普通数据</p>
	 * @return
	 */
	@Mapping(urls="/returnString", method="get")
	public String returnString(){
		return "return string";
	}
	
	/**
	 * <p>模板渲染器一般包括两个要素：模板路径和渲染所需数据。模板路径是指页面模板
	 * （比如jsp，velocity模板等）的路径；渲染所需数据会被传递到模板里，供模板渲染
	 * 时使用。有两种方式可以把渲染数据输送到模板：</p>
	 * 
	 * <ol>
	 * <li>所有渲染数据放到一个Map对象中，把Map对象作为模板渲染器的构造参数，这样使用者就
	 * 可以在模板内用Map对象的key 获取到对应的数据。</li>
	 * 
	 * <li>此外还可以通过request对象把渲染数据传递到模板，像这样：request.setAttribute("user", user)。</li>
	 * </ol>
	 * 
	 * @return
	 */
	@Mapping(urls="/returnJsp", method="get")
	public TemplateRenderer returnJsp(){
		User user = new User();
		user.setId(new Date().getTime()+"");
		user.setEmail("cnliangwei@foxmail.com");
		
		//request.setAttribute("user", user);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user", user);
		return new TemplateRenderer("/render.jsp", param);
	}
	
	/**
	 * <p>自定义渲染器非常的简单，只要继承Renderer抽象类之后，
	 * 实现render方法就可以了。自定义渲染器的功能很强大，可以
	 * 返回任何形式的内容，包括上面的提到的形式。下面演示一下自
	 * 己定义渲染染器的整个过程，看看如何用渲染器返回json数据。</p>
	 */
	@Mapping(urls="/returnRenderer0", method="get")
	public JSONRenderer returnRenderer(){
		User user = new User();
		user.setId(new Date().getTime()+"");
		user.setEmail("cnliangwei@foxmail.com");
		
		return new JSONRenderer(user);
	}
	
	/**
	 * <p>手动调用JSONRenderer的render方法给客户端返回数据</p>
	 * @throws Exception 
	 */
	@Mapping(urls="/returnRenderer1", method="get")
	public void returnRenderer1(HttpServletResponse resp) throws Exception{
		User user = new User();
		user.setId(new Date().getTime()+"");
		user.setEmail("cnliangwei@foxmail.com");
		
		new JSONRenderer(user).render(null, null, resp);
	}
}
