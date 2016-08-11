package reportor;

import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mint.mvc.annotation.InterceptorConfig;
import mint.mvc.annotation.ServiceConfig;
import mint.mvc.core.ComponentReportor;
import mint.mvc.core.ModuleConfig;

public class MyComponentReportor implements ComponentReportor{

	@Override
	public void report(Set<ModuleConfig> modules, Set<ServiceConfig> services, Set<InterceptorConfig> interceptors) {
		// TODO Auto-generated method stub
		
		/*ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(modules));
			System.out.println(mapper.writeValueAsString(services.size()));

			for(InterceptorConfig ic : interceptors){
				//System.out.println(ic.desc());
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
}
