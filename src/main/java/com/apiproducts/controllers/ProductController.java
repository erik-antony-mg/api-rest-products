package com.apiproducts.controllers;

import com.apiproducts.entities.Product;
import com.apiproducts.entities.dto.ProductDto;
import com.apiproducts.services.ProductService;

import org.springframework.core.io.Resource;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> listProducts(){
        return ResponseEntity.ok(productService.productList());
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> findProductById(@PathVariable Long idProduct){
        return ResponseEntity.ok(productService.findProducById(idProduct));
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@Validated @RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.createProduct(productDto),HttpStatus.CREATED);
    }

    @PutMapping("/edit/{idProduct}")
    public  ResponseEntity<?> editProduct(@Validated @RequestBody ProductDto productDto,@PathVariable Long idProduct){

        return ResponseEntity.ok(productService.editProduct(productDto,idProduct));
    }

    @DeleteMapping("/delete/{idProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long idProduct){
        Map<Object,Object> resp= new HashMap<>();
        productService.deleteProduct(idProduct);
        resp.put("mensaje","el producto con el id "+ idProduct+" ha sido eliminado !!");

        return ResponseEntity.ok(resp);
    }

    @PostMapping("/image/create/{idProduct}")
    public ResponseEntity<?> saveImage(@PathVariable Long idProduct,@RequestParam MultipartFile image){
        return new ResponseEntity<>(productService.saveImage(idProduct,image),HttpStatus.CREATED);
    }

    @GetMapping("/image/{idProduct}")
    public ResponseEntity<?> genereteImage(@PathVariable Long idProduct) throws IOException {
        Resource resource=productService.loadImage(idProduct);
        String contentType = Files.probeContentType(resource.getFile().toPath());
        return ResponseEntity
                .ok()
                .header("Content-Type", contentType)
                .body(resource);
    }

}
