package blogapplication.Configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {
	
	@Bean
	public Cloudinary cloudinary() {
		Map<String,String> config=new HashMap<>();
		
		config.put("cloud_name","blogapplicatin");
		config.put("api_key","724345369346844");
		config.put("api_secret","A2BcD-slrqd2RYLTc5J9rFrRNwk");
		
		return new Cloudinary(config);
	}

}
