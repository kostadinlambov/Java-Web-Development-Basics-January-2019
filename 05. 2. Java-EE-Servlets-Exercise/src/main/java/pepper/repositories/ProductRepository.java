package pepper.repositories;

import pepper.domain.entitties.Product;

public interface ProductRepository extends GenericRepository<Product, String> {
    Product findByName(String name);
}
