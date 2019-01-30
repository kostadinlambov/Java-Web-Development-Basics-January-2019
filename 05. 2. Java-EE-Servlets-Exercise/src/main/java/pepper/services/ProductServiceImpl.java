package pepper.services;

import pepper.domain.entitties.Product;
import pepper.domain.entitties.Type;
import pepper.domain.models.service.ProductServiceModel;
import pepper.repositories.ProductRepository;
import pepper.util.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setType(Type.valueOf(productServiceModel.getType()));

        this.productRepository.save(product);
    }

    @Override
    public List<ProductServiceModel> getAllProducts() {
        List<Product> products = this.productRepository.findAll();

        List<ProductServiceModel> productServiceModelList = products.stream()
                .map(product -> {
                    ProductServiceModel productServiceModel = this.modelMapper.map(product, ProductServiceModel.class);
                    productServiceModel.setType(product.getType().name());

                    return productServiceModel;
                }).collect(Collectors.toList());

        return productServiceModelList;
    }

    @Override
    public ProductServiceModel getByName(String productName) {
        Product product = this.productRepository.findByName(productName);

        if (product != null) {
            return this.modelMapper.map(product, ProductServiceModel.class);
        }

        return null;
    }
}
