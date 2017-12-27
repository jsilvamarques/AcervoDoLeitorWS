package br.com.acervodoleitorws.storage;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.acervodoleitorws.dto.ImageDTO;


@Component
public interface ImageStorage {

	public ImageDTO saveImage(MultipartFile[] files, String resourceDirectory);

	public byte[] getImage(String name, String resourceDirectory);
	
}
