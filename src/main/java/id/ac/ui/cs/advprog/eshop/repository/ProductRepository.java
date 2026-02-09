package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product edit(String id, Product newProduct) {
        Product edittedProduct = productData.stream().filter(product -> product.getProductId().equals(id)).findFirst().orElse(null);
        newProduct.setProductId(id);
        productData.set(productData.indexOf(edittedProduct), newProduct);
        return newProduct;
    }

    public void delete(String id) {
        productData.removeIf(product -> product.getProductId().equals(id));
    }
}
