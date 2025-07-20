package com.vision.ecomm.controller;


import com.vision.ecomm.model.Product;
import com.vision.ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.getAllProducts(pageable);
    }


    @GetMapping("/category/{category}")
    public Page<Product> getProductsByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) { // Default size is now 3
        Pageable pageable = PageRequest.of(page, size);
        return productService.getProductsByCategory(category, pageable);
    }

    @GetMapping("/{id}")
   public Product getProductById(@PathVariable Long id){
           return productService.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){

        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
