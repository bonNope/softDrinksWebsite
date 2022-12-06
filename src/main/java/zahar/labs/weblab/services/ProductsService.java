package zahar.labs.weblab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zahar.labs.weblab.models.Product;
import zahar.labs.weblab.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll(){
        return productsRepository.findAll();
    }

    public Product findOne(int productId){
        return productsRepository.findById(productId).orElse(null);
    }

    public List<Product> searchByName(String query){
        return productsRepository.findByNameContains(query);
    }

    @Transactional
    public void save(Product product){
        productsRepository.save(product);
    }

    @Transactional
    public void delete(int id){
        productsRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Product product){
        product.setId(id);
        productsRepository.save(product);
    }


}
