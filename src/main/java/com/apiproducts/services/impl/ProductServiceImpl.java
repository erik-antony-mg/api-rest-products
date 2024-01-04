package com.apiproducts.services.impl;

import com.apiproducts.entities.Product;
import com.apiproducts.entities.dto.ProductDto;
import com.apiproducts.exceptions.MediaFileNotFoundException;
import com.apiproducts.exceptions.ProductNotFountException;
import com.apiproducts.repositories.ProductRepository;
import com.apiproducts.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Value("${ruta.disco.c}")
    private String ruta;

    @Override
    public List<Product> productList() {
        return productRepository.findAll();
    }

    @Override
    public Product findProducById(Long idProduct) {
        Optional<Product> optionalProduct =productRepository.findById(idProduct);
         return  optionalProduct
                 .orElseThrow(()->new ProductNotFountException("producto con el id : "+idProduct+" no encontrado"));
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Product productNew=modelMapper.map(productDto,Product.class);
        return productRepository.save(productNew);
    }

    @Override
    public Product editProduct(ProductDto productDto, Long idProduct) {

        Product product = obtenerProducto(idProduct);
        String image= product.getImageProduct();
            // Mapear los campos del DTO al producto existente en lugar de crear uno nuevo
            modelMapper.map(productDto, product);
            product.setIdProduct(idProduct);
            product.setImageProduct(image);
            return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long idProduct) {
        Product product = obtenerProducto(idProduct);
        productRepository.deleteById(product.getIdProduct());
    }

    @Override
    public Product saveImage(Long idProduct,MultipartFile image) {
        Product product = obtenerProducto(idProduct);
        // guardar imagen
        if (!image.isEmpty()){
//            Path directorioImg= Paths.get("src//main//resources//static/images");
//            String rutaAbsoluta= directorioImg.toFile().getAbsolutePath();
            String rutaAbsoluta=ruta;

            try {
                byte[] bytesImg=image.getBytes();
                Path rutacompleta=Paths.get(rutaAbsoluta+"//"+ image.getOriginalFilename());
                Files.write(rutacompleta,bytesImg);

                product.setImageProduct(image.getOriginalFilename());
                productRepository.save(product);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //fin guardar imagen
        return product;
    }

    @Override
    public Resource loadImage(Long idProduct) {
        String nombreArchivo = obtenerNombreArchivoImagen(idProduct); // Método que obtiene el nombre del archivo
        try {
            Path path=Paths.get(ruta).resolve(nombreArchivo);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new MediaFileNotFoundException("El archivo no ha sido encontrado: " + nombreArchivo);
            }
        } catch (MalformedURLException e) {
            throw new MediaFileNotFoundException("El archivo no ha sido encontrado: " + nombreArchivo);
        }

    }

    private String obtenerNombreArchivoImagen(Long idProduct) {
        Product product = obtenerProducto(idProduct);
        return product.getImageProduct();
    }

    private Product obtenerProducto(Long idProduct){
        return productRepository.findById(idProduct)
                .orElseThrow(() -> new ProductNotFountException("Producto con el ID: " + idProduct + " no encontrado"));

    }
}
