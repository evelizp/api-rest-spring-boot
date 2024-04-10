package com.evelizp.repository;

import com.evelizp.model.entity.CategoryEntity;
import com.evelizp.model.entity.DeletedProduct;
import com.evelizp.model.entity.ProductEntity;
import com.evelizp.model.entity.ProductStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    @Query("from ProductEntity where deleted = com.evelizp.model.entity.DeletedProduct.CREATED and ((:status is null) or (status = :status))")
    List<ProductEntity> findAll(@Param("status") ProductStatus status);

    List<ProductEntity> findByCategoryAndDeleted(CategoryEntity category, DeletedProduct deleted);
}
