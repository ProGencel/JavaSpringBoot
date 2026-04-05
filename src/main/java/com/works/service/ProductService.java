package com.works.service;

import com.works.dto.ProductSaveRequestDto;
import com.works.dto.ProductUpdateRequestDto;
import com.works.entity.Product;
import com.works.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;
    ModelMapper modelMapper = new ModelMapper();

    public Product save(ProductSaveRequestDto productSaveRequestDto)
    {
        Product product = modelMapper.map(productSaveRequestDto, Product.class);
        return productRepository.save(product);
    }

    public List<Product> saveAll(List<ProductSaveRequestDto> productSaveRequestDtoList)
    {
        List<Product> productList = productSaveRequestDtoList.stream()
                .map(dto -> modelMapper.map(dto, Product.class))
                .toList();

        return productRepository.saveAll(productList);
    }

    public ResponseEntity deleteOne(long id)
    {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent())
        {
            productRepository.deleteById(id);
            Map<String, Object> hm = Map.of("success", true, "message", "Product deleted successfully");
            return ResponseEntity.ok().body(hm);
        }
        else
        {
            Map<String, Object> hm = Map.of("success", false, "message", "Product not found " + id);
            return ResponseEntity.status(404).body(hm);
        }
    }

    public ResponseEntity update(ProductUpdateRequestDto productUpdateRequestDto)
    {
        java.util.Optional<Product> productOptional = productRepository.findById(productUpdateRequestDto.getId());
        if(productOptional.isPresent())
        {
            Product product = modelMapper.map(productUpdateRequestDto, Product.class);
            productRepository.save(product);
            Map<String, Object> hm = Map.of("success", true, "message", "Product updated successfully.");
            return ResponseEntity.ok().body(hm);
        }
        else
        {
            Map<String, Object> hm = Map.of("success", false, "message", "Product id cannot find " + productUpdateRequestDto.getId());
            return ResponseEntity.status(404).body(hm);
        }

    }

    public Page<Product> productList(int page)
    {     //ofSize means how much product we want on one page
        Pageable pageable = Pageable.ofSize(10).withPage(page);
        return productRepository.findAll(pageable);
    }

    public Page<Product> search(String q, int page, String price)
    {
        Sort sort = Sort.by(price.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "price");
        Pageable pageable = PageRequest.of(page,10,sort);
        Page<Product> products = productRepository.findByTitleContainsOrDescriptionContainsAllIgnoreCase(q,q,pageable);

        return products;
    }

}
