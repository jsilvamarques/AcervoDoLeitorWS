package br.com.acervodoleitorws.resource;

import java.nio.file.FileSystems;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.acervodoleitorws.ConfigProperties;
import br.com.acervodoleitorws.dto.ImageDTO;
import br.com.acervodoleitorws.event.ResourceCreatedEvent;
import br.com.acervodoleitorws.model.Profile;
import br.com.acervodoleitorws.repository.ProfileRepository;
import br.com.acervodoleitorws.service.ProfileService;
import br.com.acervodoleitorws.util.ImageUtil;

@RestController
@RequestMapping("/profile")
public class ProfileResource {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ImageUtil imageUtil;
	
	@Autowired
	private ConfigProperties configProperties;
	
	@PostMapping
	public ResponseEntity<Profile> save (@Valid @RequestBody Profile profile, HttpServletResponse response){
		Profile profileSaved = this.profileRepository.save(profile);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, profile.getUid()));
		return ResponseEntity.status(HttpStatus.CREATED).body(profileSaved); 
	}
	
	@PutMapping("/{uid}")
	public ResponseEntity<Profile> update(@PathVariable String uid, @Valid @RequestBody Profile profile){
		try {
			Profile profileSaved = this.profileService.update(uid, profile);
			return ResponseEntity.ok(profileSaved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<Profile> findByUid(@PathVariable String uid){
		Profile profile = this.profileRepository.findOne(uid);
		return profile != null ? ResponseEntity.ok(profile) : ResponseEntity.notFound().build();
	}
	
	@PostMapping("/upload")
	public DeferredResult<ImageDTO> upload(@RequestParam("files[]") MultipartFile[] files, HttpServletRequest request){
		String uidProfile = request.getHeader("uidProfile");
		DeferredResult<ImageDTO> resultado = new DeferredResult<>();
		resultado = imageUtil.uploadImage(files, configProperties.getResourcePerfil() + FileSystems.getDefault().getSeparator() + uidProfile);		
		return resultado;
	}
	
	@GetMapping("/image/{name:.*}")
	public byte[] getImage(@PathVariable String name, HttpServletRequest request) {
		String uidProfile = request.getHeader("uidProfile");
		return imageUtil.getImage(name, configProperties.getResourcePerfil() + FileSystems.getDefault().getSeparator() + uidProfile);
	}

}
