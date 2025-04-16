package blogapplication.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import blogapplication.Service.CloudinaryService;

@RestController
/* @RequestMapping("home/api") */
public class ImageUpload {

	@Autowired
    private CloudinaryService clService;
	
	@GetMapping("/uploadImg")
	public RedirectView  imageForm()
	{
		  return new RedirectView("imageUpload.html");
	}

	@PostMapping("upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile file)
    {
    	try {
			String url = clService.uploadFile(file);
						
			System.out.println("Cloudinary Image Url :"+url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ResponseEntity.ok("Image uploaded...");
    }
}
