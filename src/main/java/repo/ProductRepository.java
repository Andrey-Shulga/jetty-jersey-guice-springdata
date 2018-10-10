package repo;

import model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.name = :name")
    public List<Product> findByNameIs(@Param("name") String name);

    public List<Product> findByNameContainingIgnoreCase(String searchString);

}
