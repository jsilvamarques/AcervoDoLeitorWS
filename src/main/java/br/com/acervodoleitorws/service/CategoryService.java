package br.com.acervodoleitorws.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acervodoleitorws.model.Category;
import br.com.acervodoleitorws.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category update(Long uid, Category category){
		Category categorySaved = findCategoryExisting(uid);
		BeanUtils.copyProperties(category, categorySaved, "uid");
		return this.categoryRepository.save(categorySaved);
	}

	private Category findCategoryExisting(Long uid) {
		Category categorySaved = this.categoryRepository.findOne(uid);
		
		if(categorySaved == null){
			throw new IllegalArgumentException();
		}
		
		return categorySaved;
	}

}
