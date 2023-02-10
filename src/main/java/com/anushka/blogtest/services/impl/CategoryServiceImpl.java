package com.anushka.blogtest.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anushka.blogtest.entities.Category;
import com.anushka.blogtest.exceptions.ResourceNotFoundException;
import com.anushka.blogtest.payloads.CategoryDto;
import com.anushka.blogtest.repositories.CategoryRepo;
import com.anushka.blogtest.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category=this.modelMapper.map(categoryDto,Category.class);
		Category addedCat=this.categoryRepo.save(category);
		
		return this.modelMapper.map(addedCat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category"," category id ",categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCat=this.categoryRepo.save(cat);
		
		return this.modelMapper.map(updatedCat,CategoryDto.class);
		
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category"," category id ",categoryId));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category"," category id ",categoryId));
		return this.modelMapper.map(cat,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> cats=this.categoryRepo.findAll();
		
		return cats.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
	}

}
