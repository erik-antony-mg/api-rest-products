package com.apiproducts.entities.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotEmpty
    @Size(min = 2,message = "El nombre del producto deberia tener al menos 2 caracteres")
    private String nameProduct;
    @NotEmpty
    @Size(min = 2,message = "La descripcion del producto deberia tener al menos 2 caracteres")
    private String descriptionProduct;
    private String imageProduct;
    @NotNull
    @DecimalMin(value = "0.01", message = "El precio mínimo permitido es 0.01")
    @DecimalMax(value = "10000.00", message = "El precio máximo permitido es 10000.00")
    private BigDecimal priceProduct;
    @NotNull
    private Long barCodeProduct;
}
