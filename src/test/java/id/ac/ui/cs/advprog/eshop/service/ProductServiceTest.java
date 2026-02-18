package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);

        Product result = productService.create(product);
        assertEquals(product, result);
    }

    @Test
    void testFindAll() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.findAll()).thenReturn(List.of(product).iterator());

        List<Product> result = productService.findAll();
        assertEquals(product, result.get(0));
    }

    @Test
    void testFindAllEmpty() {
        when(productRepository.findAll()).thenReturn(Collections.emptyIterator());

        List<Product> result = productService.findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void testEdit() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Sampo Cap Udin");
        updatedProduct.setProductQuantity(200);

        when(productRepository.edit("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct)).thenReturn(updatedProduct);

        Product result = productService.edit("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);
        assertEquals(updatedProduct, result);
        verify(productRepository, times(1)).edit("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productService.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        verify(productRepository, times(1)).delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }
}