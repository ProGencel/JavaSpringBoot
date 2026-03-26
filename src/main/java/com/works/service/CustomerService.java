package com.works.service;

import com.works.entity.Customer;
import com.works.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;

    public ResponseEntity register(Customer customer){
        List<Customer> customerList = customerRepository.findByEmailEqualsOrPhoneEqualsAllIgnoreCase(customer.getEmail(), customer.getPhone());
        if(customerList.size() > 0){
            // daha önceden bu email veya phone kullanılmış demektir.
            Map<String, Object> hm = Map.of("success", false, "message", "This email or phone number is already in use.");
            return ResponseEntity.badRequest().body(hm);
        }
        customerRepository.save(customer);
        return ResponseEntity.ok().body(customer);
    }


}
