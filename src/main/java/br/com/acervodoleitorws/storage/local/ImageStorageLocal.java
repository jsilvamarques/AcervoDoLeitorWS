package br.com.acervodoleitorws.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.acervodoleitorws.ConfigProperties;
import br.com.acervodoleitorws.dto.ImageDTO;
import br.com.acervodoleitorws.storage.ImageStorage;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@Component
public class ImageStorageLocal  implements ImageStorage {

	private static final Logger logger = LoggerFactory.getLogger(ImageStorageLocal.class);
	
	@Autowired
	private ConfigProperties configProperties;
	
	private Path local;
	private Path localImages;

	public ImageStorageLocal() {
		this(getDefault().getPath(System.getenv("USERPROFILE"), ".acervodoleitorfotos"));
	}
	
	public ImageStorageLocal(Path path) {
		this.local = path;
		createFolds();
	}

	@Override
	public ImageDTO saveImage(MultipartFile[] files, String resourceDirectory) {
		String newName = null;
		String pathResource = this.localImages.toAbsolutePath().toString() + getDefault().getSeparator() + resourceDirectory + getDefault().getSeparator();
			
		if(files != null && files.length > 0){
			try {
				if(!new File(pathResource).exists()){
					Files.createDirectories(getDefault().getPath(pathResource));
				}
				newName = renameFile(files[0].getOriginalFilename());
				files[0].transferTo(new File(pathResource + newName));
				Thumbnails.of(getDefault().getPath(pathResource).resolve(newName).toString()).size(400, 580).toFiles(Rename.PREFIX_HYPHEN_THUMBNAIL);
				
			} catch (IOException e) {
				throw new RuntimeException("Erro salvando a foto na pasta temporária", e);
			}		
		}
		return new ImageDTO(newName, configProperties.getPrefixThumbnail() + newName, files[0].getContentType());
	}
	
	@Override
	public byte[] getImage(String name, String resourceDirectory) {
		String fullDirectory = resourceDirectory.concat(getDefault().getSeparator().concat(name));
		try {
			return Files.readAllBytes(this.localImages.resolve(fullDirectory));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto temporária", e);
		}
	}
		
	private void createFolds() {
		try {
			Files.createDirectories(this.local);
			this.localImages = getDefault().getPath(this.local.toString(), "images");
			Files.createDirectories(this.localImages);
			
			if (logger.isDebugEnabled()) {
				logger.debug("Pastas criadas para salvar fotos.");
				logger.debug("Pasta default: " + this.local.toAbsolutePath());
				logger.debug("Pasta Image: " + this.localImages.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para salvar foto", e);
		}
	}
	
	private String renameFile(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Nome original: %s, novo nome: %s", nomeOriginal, novoNome));
		}
		
		return novoNome;
		
	}

}