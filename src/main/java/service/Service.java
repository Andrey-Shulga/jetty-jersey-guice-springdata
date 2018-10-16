package service;

import model.Product;
import repo.ProductRepository;

import javax.inject.Inject;

@org.springframework.stereotype.Service
public class Service {

    private ProductRepository productRepository;

    @Inject
    public Service(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product) {

        productRepository.save(product);
        System.out.println("Saved " + product);
    }
}
