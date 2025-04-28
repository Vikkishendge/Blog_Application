package blogapplication.ServiceImp;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import blogapplication.Service.CloudinaryService;

@Service
public class CloudinaryServiceImp implements CloudinaryService{

	private Cloudinary cloudinary;

	public CloudinaryServiceImp(Cloudinary cloudinary) {
		super();
		this.cloudinary = cloudinary;
	}
	
	public String uploadFile(MultipartFile file) throws IOException
	{
		File uploadedFile = File.createTempFile("tempFile", file.getOriginalFilename());
		
		file.transferTo(uploadedFile);
		
		Map<String, Object> options = ObjectUtils.asMap(
	            "folder", "blogimg"
	        );
		
		Map uploadResult = cloudinary.uploader().upload(uploadedFile,options);
		
		return uploadResult.get("secure_url").toString();
	}
	

}
