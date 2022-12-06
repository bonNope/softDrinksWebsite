package zahar.labs.weblab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zahar.labs.weblab.models.Product;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Integer> {
    List<Product> findByNameContains(String name);
}
