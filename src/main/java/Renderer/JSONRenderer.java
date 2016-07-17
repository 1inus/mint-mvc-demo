package Renderer;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mint.mvc.renderer.Renderer;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * <p>当一个action方法的返回值是Render的子类对象时，
 * mint-mvc会调用该返回值的render方法。在render方法
 * 里开发者可以根据自己的需求，生成输出结果，通过操作输
 * 出流，将数据 返回给客户端的</p>
 * 
 * <p>自定义渲染器非常的简单，只要继承Renderer抽象类之后，
 * 实现render方法就可以了。</p>
 * 
 * <p>自定义渲染器的功能很强大，可以返回任何形式的内容，
 * 包括上面的提到的形式。</p>
 * 
 * <p>JSONRenderer的作用渲染器返回json数据。</p>
 * 
 * <p>附Renderer抽象类的源码：</p>
 * <pre>
 * 	package mint.mvc.renderer;
 * 
 * 	import javax.servlet.ServletContext;
 *	import javax.servlet.http.HttpServletRequest;
 *	import javax.servlet.http.HttpServletResponse;
 *	
 *	public abstract class Renderer {
 *	    protected String contentType;
 *	
 *	    public String getContentType() {
 *	        return contentType;
 *	    }
 *	
 *	    public void setContentType(String contentType) {
 *	        this.contentType = contentType;
 *	    }
 *	
 *	    public abstract void render(ServletContext context, HttpServletRequest request, HttpServletResponse response) throws Exception;
 *	
 *	}
 * </pre>
 */
public class JSONRenderer extends Renderer{
	private Object obj;
	
	public JSONRenderer (Object obj){
		this.obj = obj;
	}
	
	@Override
	public void render(ServletContext ctx, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		resp.setContentType("application/json;charset=UTF-8");
		
		PrintWriter pw = resp.getWriter();
		//将数据转换成JSON字符串，然后输出。这里采用了jackson JSON库
		ObjectMapper mapper = new ObjectMapper();
		pw.write(mapper.writeValueAsString(obj));
		pw.flush();
	}
}