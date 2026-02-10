package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Bambang Edited");
        updatedProduct.setProductQuantity(200);

        Product result = productRepository.edit("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertNotNull(result);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", result.getProductId());
        assertEquals("Sampo Cap Bambang Edited", result.getProductName());
        assertEquals(200, result.getProductQuantity());

        List<Product> productList = new ArrayList<>();
        Iterator<Product> productIterator = productRepository.findAll();
        productIterator.forEachRemaining(productList::add);
        Product savedProduct = productList.stream().filter(product1 -> product1.getProductId().equals("eb558e9f-1c39-460e-8860-71af6af63bd6")).toList().getFirst();

        assertNotNull(savedProduct);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", savedProduct.getProductId());
        assertEquals("Sampo Cap Bambang Edited", savedProduct.getProductName());
        assertEquals(200, savedProduct.getProductQuantity());
    }

    @Test
    void testEditProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Bambang Edited");
        updatedProduct.setProductQuantity(200);

        assertThrows(IndexOutOfBoundsException.class, () -> productRepository.edit("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct));
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        List<Product> productList = new ArrayList<>();
        Iterator<Product> productIterator = productRepository.findAll();
        productIterator.forEachRemaining(productList::add);

        assertThrows(NoSuchElementException.class, () -> productList.stream().filter(product1 -> product1.getProductId().equals("eb558e9f-1c39-460e-8860-71af6af63bd6")).toList().getFirst());
    }

    @Test
    void testDeleteProductNotFound() {
        Product product1 = new Product();
        product1.setProductId("temp. id");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        List<Product> productList = new ArrayList<>();
        Iterator<Product> productIterator = productRepository.findAll();
        productIterator.forEachRemaining(productList::add);
        Product product2 = productList.stream().filter(product -> product.getProductId().equals("temp. id")).toList().getFirst();
        assertNotNull(product2);
    }
}
