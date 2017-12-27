package br.com.acervodoleitorws.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acervodoleitorws.model.Profile;
import br.com.acervodoleitorws.repository.ProfileRepository;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	public Profile update(String uid, Profile profile) {
		Profile profileSaved = this.profileRepository.findOne(uid);
		if(profileSaved == null) {
			throw new IllegalArgumentException();
		}
		BeanUtils.copyProperties(profile, profileSaved, "uid");
		return this.profileRepository.save(profileSaved);
	}

}
