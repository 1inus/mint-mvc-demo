package reportor;

import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mint.mvc.core.ComponentReportor;
import mint.mvc.core.InterceptorConfig;
import mint.mvc.core.ModuleConfig;
import mint.mvc.core.ServiceConfig;

public class MyComponentReportor implements ComponentReportor{

	@Override
	public void report(Set<ModuleConfig> modules, Set<ServiceConfig> services, Set<InterceptorConfig> interceptors) {
		// TODO Auto-generated method stub
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(modules));;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
