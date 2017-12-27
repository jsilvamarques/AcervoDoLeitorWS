package br.com.acervodoleitorws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.acervodoleitorws.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String>{

}
