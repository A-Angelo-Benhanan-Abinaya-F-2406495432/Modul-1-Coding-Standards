package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NoArgsConstructor
@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();

    public Product create(final Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product edit(final String productId, final Product newProduct) {
        final Product edittedProduct = productData.stream().filter(product -> product.getProductId().equals(productId)).findFirst().orElse(null);
        newProduct.setProductId(productId);
        productData.set(productData.indexOf(edittedProduct), newProduct);
        return newProduct;
    }

    public void delete(final String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
    }
}
