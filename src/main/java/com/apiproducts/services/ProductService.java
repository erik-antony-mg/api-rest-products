package com.apiproducts.services;

import com.apiproducts.entities.Product;
import com.apiproducts.entities.dto.ProductDto;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public interface ProductService  {

    List<Product> productList();
    Product findProducById(Long idProduct);
    Product createProduct(ProductDto productDto);
    Product editProduct(ProductDto productDto,Long idProduct);
    void deleteProduct(Long idProduct);
    Product saveImage(Long idProduct,MultipartFile image);
    Resource loadImage(Long idProduct);
}
