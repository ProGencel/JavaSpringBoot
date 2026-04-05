package com.works.service;

import com.works.dto.ProductSaveRequestDto;
import com.works.entity.Product;
import com.works.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
