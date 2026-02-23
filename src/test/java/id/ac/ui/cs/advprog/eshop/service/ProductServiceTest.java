package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    Product product;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testCreate() {
        when(productRepository.create(this.product)).thenReturn(this.product);
        Product result = productService.create(this.product);

        assertEquals(this.product, result);
        verify(productRepository, times(1)).create(this.product);
    }

    @Test
    void testFindAll() {
        when(productRepository.findAll()).thenReturn(List.of(this.product).iterator());
        List<Product> result = productService.findAll();

        assertEquals(1, result.size());
        assertEquals(this.product, result.getFirst());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindAllEmpty() {
        when(productRepository.findAll()).thenReturn(Collections.emptyIterator());
        List<Product> result = productService.findAll();

        assertTrue(result.isEmpty());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testEdit() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Sampo Cap Bambang Edited");
        updatedProduct.setProductQuantity(200);

        when(productRepository.edit("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct)).thenReturn(updatedProduct);
        Product result = productService.edit("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertEquals(updatedProduct, result);
        verify(productRepository, times(1)).edit("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete(this.product.getProductId());
        productService.delete(this.product.getProductId());
        verify(productRepository, times(1)).delete(this.product.getProductId());
    }
}