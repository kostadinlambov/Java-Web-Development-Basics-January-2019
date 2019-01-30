package pepper.services;

import pepper.domain.entitties.Product;
import pepper.domain.models.service.ProductServiceModel;
import java.util.List;

public interface ProductService {

    void saveProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> getAllProducts();

    ProductServiceModel getByName(String productName);
}
