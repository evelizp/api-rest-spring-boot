package com.evelizp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.evelizp.model.entity.CategoryEntity;
import com.evelizp.model.entity.DeletedProduct;
import com.evelizp.model.entity.ProductEntity;
import com.evelizp.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void whenGetAll_ThenReturnAllProduct(){
        var list = productRepository.findAll(null);
        assertEquals(3, list.size());
    }

    @Test
    void whenValidSave_ThenReturnPRoduct() {
        var productEntity = ProductEntity.builder()
                .name("Teclado")
                .stock(Double.valueOf(10))
                .price(BigDecimal.valueOf(300))
                .category(CategoryEntity.builder().id(1L).build())
                .build();
        productRepository.save(productEntity);

        var product = productRepository.findByCategoryAndDeleted(productEntity.getCategory(), DeletedProduct.CREATED);
        assertEquals(4, product.size());
    }
}
