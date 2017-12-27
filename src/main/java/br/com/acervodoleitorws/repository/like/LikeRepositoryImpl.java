package br.com.acervodoleitorws.repository.like;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.acervodoleitorws.model.Like;

public class LikeRepositoryImpl implements LikeRepositoryQuery{

	@Autowired
	private EntityManager em;
	
	@Override
	public int save(Like like) {
		Query query = em.createNativeQuery("insert into resource_like (uid_resource, uid_profile, resource_type, is_like) values (:uidResource, :uidProfile, :resourceType, :isLike)");
		query.setParameter("uidResource", like.getResourceType());
		query.setParameter("uidProfile", like.getUidProfile());
		query.setParameter("resourceType", like.getResourceType());
		query.setParameter("isLike", like.getIsLike());				
		return query.executeUpdate();
	}

	@Override
	public int update(Like like) {
		Query query = em.createNativeQuery("update resource_like set is_like = :isLike where uid_Resource = :uidResource and uid_profile = :uidProfile and resource_type = :resourceType");
		query.setParameter("uidResource", like.getResourceType());
		query.setParameter("uidProfile", like.getUidProfile());
		query.setParameter("resourceType", like.getResourceType());
		query.setParameter("isLike", like.getIsLike());				
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Like getLikeByIdResourceAndIdProfile(String uidResource, String uidProfile, String resourceType) {
		Query query = em.createNativeQuery("select uid_resource, uid_profile, resource_type, is_like from resource_like where uid_Resource = :uidResource and uid_profile = :uidProfile and resource_type = :resourceType");
		query.setParameter("uidResource", uidResource);
		query.setParameter("uidProfile", uidProfile);
		query.setParameter("resourceType", resourceType);
		
		List<Object[]> result = query.getResultList();		
		Like like = new Like();
		
		for (int i = 0; i < result.size(); i++) {
			Object[] pesquisaResultados = result.get(i);
			
			like.setUidResource(pesquisaResultados[0].toString());
			like.setUidProfile(pesquisaResultados[1].toString());
			like.setIsLike(Integer.parseInt(pesquisaResultados[3].toString()));
			
		}
		
		like.setTotalLike(this.getCountIsLike(uidResource, resourceType));
		return like;
	}

	@Override
	public int like(String uidResource, String uidProfile, String resourceType) {
		Like exist = getLikeByIdResourceAndIdProfile(uidResource, uidProfile, resourceType);
		Like like = new Like();
		like.setUidResource(uidResource);
		like.setUidProfile(uidProfile);
		like.setIsLike(1);
		
		if(exist.getUidProfile() == null){
			return this.save(like);
		}else{
			return this.update(like);
		}
	}

	@Override
	public int dislike(String uidResource, String uidProfile, String resourceType) {
		Like exist = getLikeByIdResourceAndIdProfile(uidResource, uidProfile, resourceType);
		Like like = new Like();
		like.setUidResource(uidResource);
		like.setUidProfile(uidProfile);
		like.setIsLike(0);
		
		if(exist.getUidProfile() == null){
			return this.save(like);
		}else{
			return this.update(like);
		}
	}

	@Override
	public Integer getIsLike(String uidResource, String uidProfile, String resourceType) {
		Query query = em.createNativeQuery("select count(is_like) from resource_like where uid_Resource = :uidResource and resource_type = :resourceType and is_like = 1;");
		query.setParameter("uidResource", uidResource);
		query.setParameter("uidProfile", uidProfile);
		query.setParameter("resourceType", resourceType);		
		return Integer.parseInt(query.getSingleResult().toString());
	}

	@Override
	public Integer getCountIsLike(String uidResource, String resourceType) {
		Query query = em.createNativeQuery("select count(is_like) is_like_count from resource_like where uid_Resource = :uidResource and resource_type = :resourceType and is_like = 1;");
		query.setParameter("uidResource", uidResource);
		query.setParameter("resourceType", resourceType);
		return Integer.parseInt(query.getSingleResult().toString()); 
	}

}
