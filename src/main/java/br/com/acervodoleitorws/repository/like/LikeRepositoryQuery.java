package br.com.acervodoleitorws.repository.like;

import org.springframework.stereotype.Component;

import br.com.acervodoleitorws.model.Like;

public interface LikeRepositoryQuery {
	
	public int save(Like like);
	
	public int update(Like like);

	public Like getLikeByIdResourceAndIdProfile(String uidResource, String uidProfile, String resourceType);

	public int like(String uidResource, String uidProfile, String resourceType);
	
	public int dislike(String uidResource, String uidProfile, String resourceType);	
	
	public Integer getIsLike(String uidResource, String uidProfile, String resourceType);
	
	public Integer getCountIsLike(String uidResource, String resourceType);	
}
