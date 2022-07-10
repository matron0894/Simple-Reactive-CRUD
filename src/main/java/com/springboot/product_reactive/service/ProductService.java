package com.springboot.product_reactive.service;

import com.springboot.product_reactive.domain.Product;
import com.springboot.product_reactive.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Flux<Product> getAllProducts() {
        return this.repository.findAll();
    }

    public Mono<Product> getProductById(int productId) {
        return this.repository.findById(Long.valueOf(productId));
    }

    public Mono<Product> createProduct(final Product product) {
        return this.repository.save(product);
    }

    public Mono<Product> updateProduct(int productId, final Mono<Product> productMono) {
        return this.repository.findById(Long.valueOf(productId))
                .flatMap(p ->
                        productMono.map(u -> {
                            p.setDescription(u.getDescription());
                            p.setPrice(u.getPrice());
                            return p;
                        }))
                .flatMap(this.repository::save);
    }

    @Transactional
    public Mono<Product> updateProductWithId(final Product product) {
        return this.repository.findById(Long.valueOf(product.getId()))
                .flatMap(p -> {
                    p.setDescription(product.getDescription());
                    p.setPrice(product.getPrice());
                    return this.repository.save(p);
                })
                .switchIfEmpty(this.repository.save(product.setAsNew())); // save if the product with id is not present
    }

    public Mono<Void> deleteProduct(final int id) {
        return this.repository.deleteById(Long.valueOf(id));
    }


}