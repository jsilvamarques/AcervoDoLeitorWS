package br.com.acervodoleitorws.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.acervodoleitorws.dto.ImageDTO;

public class ImageStorageRunnable implements Runnable {

	private MultipartFile[] files;
	private DeferredResult<ImageDTO> result;
	private ImageStorage imageStorage;
	private String resourceDirectory;
	
	public ImageStorageRunnable(MultipartFile[] files, DeferredResult<ImageDTO> result, ImageStorage imageStorage, String resourceDirectory) {
		this.files = files;
		this.result = result;
		this.imageStorage = imageStorage;
		this.resourceDirectory = resourceDirectory;
	}

	@Override
	public void run() {
		result.setResult(this.imageStorage.saveImage(files, resourceDirectory));
	}

}
