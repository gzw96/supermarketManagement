package com.project.supermarket.management.supplier.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.supermarket.management.supplier.entity.Supplier;

@Repository
public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Long>,JpaSpecificationExecutor<Supplier>{

}
