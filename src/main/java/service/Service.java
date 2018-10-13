package service;

import model.Product;
import repo.ProductRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Service {

    @Inject
    private ProductRepository productRepository;

    public void save(Product product){

        productRepository.save(product);
        System.out.println("Saved " + product);
    }
}
