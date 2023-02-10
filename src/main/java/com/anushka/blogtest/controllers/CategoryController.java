package com.anushka.blogtest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anushka.blogtest.payloads.ApiResponse;
import com.anushka.blogtest.payloads.CategoryDto;
import com.anushka.blogtest.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer categoryId){
		CategoryDto cat=this.categoryService.updateCategory(categoryDto,categoryId);
		return new ResponseEntity<CategoryDto>(cat,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully", true),HttpStatus.OK);
	}
	//get
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer categoryId) {
		CategoryDto cat=this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(cat,HttpStatus.OK);
	}
	
	//getall
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<CategoryDto> cats=this.categoryService.getAllCategory();
		return ResponseEntity.ok(cats);
	}
	

}
