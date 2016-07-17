package action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import mint.mvc.annotation.BaseMapping;
import mint.mvc.annotation.Mapping;
import mint.mvc.annotation.MultipartConfig;
import mint.mvc.annotation.Required;
import mint.mvc.core.upload.TempFile;

@BaseMapping("/uploadDemo")
public class UploadDemoAction {
	/**
	 * <p>上传图片</p>
	 * @throws IOException 
	 */
	@Mapping(urls="/upload", method="post")
	@MultipartConfig(attributeName="imgs", limitSize = 1024*1024)
	public String uploadAction(@Required TempFile[] imgs, HttpSession session) throws Exception{
		TempFile file = imgs[0];
		
		ServletContext application = session.getServletContext(); 
		String savePath = application.getRealPath("/uploads/"+file.getName());
		
		//将文件保存在应用的目录(uploads)下（生产环境中不建议保存在应用目录下）
		file.saveAs(savePath);
		
		return String.format("<a href='%s'>upload success! view image</a>", 
				"../uploads/"+file.getName());
	}
}
