package br.com.acervodoleitorws.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.acervodoleitorws.dto.ImageDTO;
import br.com.acervodoleitorws.storage.ImageStorage;
import br.com.acervodoleitorws.storage.ImageStorageRunnable;


@Component
public class ImageUtil {
	
	@Autowired
	private ImageStorage imageStorage;	
	
	public DeferredResult<ImageDTO> uploadImage(MultipartFile[] files, String resourceDirectory) {
		DeferredResult<ImageDTO> result = new DeferredResult<>();				
		Thread thread = new Thread(new ImageStorageRunnable(files, result, imageStorage, resourceDirectory));
		thread.start();		
		return result;
	}	
	
	public byte[] getImage(String name, String resourceDirectory) {
		return imageStorage.getImage(name, resourceDirectory);
	}
	
}