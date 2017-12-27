package br.com.acervodoleitorws.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.acervodoleitorws.enumtype.ResourceType;

@JsonInclude(Include.NON_NULL)
public class Like implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String uidResource;
	private String uidProfile;
	private Enum<ResourceType> resourceType;
	private Integer isLike;
	private Integer totalLike;	
	
	public Like() {
	}

	public String getUidResource() {
		return uidResource;
	}

	public void setUidResource(String uidResource) {
		this.uidResource = uidResource;
	}

	public String getUidProfile() {
		return uidProfile;
	}

	public void setUidProfile(String uidProfile) {
		this.uidProfile = uidProfile;
	}

	public Enum<ResourceType> getResourceType() {
		return resourceType;
	}

	public void setResourceType(Enum<ResourceType> resourceType) {
		this.resourceType = resourceType;
	}

	public Integer getIsLike() {
		return isLike;
	}

	public void setIsLike(Integer isLike) {
		this.isLike = isLike;
	}

	public Integer getTotalLike() {
		return totalLike;
	}

	public void setTotalLike(Integer totalLike) {
		this.totalLike = totalLike;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isLike == null) ? 0 : isLike.hashCode());
		result = prime * result + ((resourceType == null) ? 0 : resourceType.hashCode());
		result = prime * result + ((uidProfile == null) ? 0 : uidProfile.hashCode());
		result = prime * result + ((uidResource == null) ? 0 : uidResource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Like other = (Like) obj;
		if (isLike == null) {
			if (other.isLike != null)
				return false;
		} else if (!isLike.equals(other.isLike))
			return false;
		if (resourceType == null) {
			if (other.resourceType != null)
				return false;
		} else if (!resourceType.equals(other.resourceType))
			return false;
		if (uidProfile == null) {
			if (other.uidProfile != null)
				return false;
		} else if (!uidProfile.equals(other.uidProfile))
			return false;
		if (uidResource == null) {
			if (other.uidResource != null)
				return false;
		} else if (!uidResource.equals(other.uidResource))
			return false;
		return true;
	}
}
