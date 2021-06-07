package com.example.northwind.dataAccess.abstracts;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.northwind.entities.concretes.Product;
import com.example.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product, Integer>{
	
	Product getByProductName(String productName);
	
	Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);
	
	List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);

	List<Product> getByCategory_CategoryIdIn(List<Integer> categories);
	
	List<Product> getByProductNameContains(String productName);
	
	List<Product> getByProductNameStartsWith(String productName);
	
	
	
	// burdaki Product entitymiz o yüzden büyük harfle başladı
	// parametreyi : tan sonra yazıyoruz
	// sanki veri tabanı Product entity'siymiş gibi davranıyoruz
	@Query("From Product where productName =: productName and category.categoryId=: categoryId")
	List<Product> getByNameAndCategory(String productName, int categoryId);
	
	
	@Query("Select new com.example.northwind.entities.dtos.ProductWithCategoryDto(p.id, p.productName, c.categoryName) from Category c Inner Join c.products p")
	List<ProductWithCategoryDto> getProductWithCategoryDetails();
	
}
