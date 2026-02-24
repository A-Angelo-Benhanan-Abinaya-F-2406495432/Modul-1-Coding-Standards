package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    Product product;

    @Mock
    Model model;

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @InjectMocks
    HomeController homeController;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testCreateProductPage_GET() {
        String view = productController.createProductPage(model);

        assertEquals("CreateProduct", view);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPage_POST() {
        when(productService.create(product)).thenReturn(product);
        String view = productController.createProductPost(product);

        assertEquals("redirect:list", view);
        verify(productService, times(1)).create(product);
    }

    @Test
    void testEditProductPage_GET() {
        when(productService.findAll()).thenReturn(List.of(product));
        String view = productController.editProductPage("eb558e9f-1c39-460e-8860-71af6af63bd6", model);

        assertEquals("EditProduct", view);
        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    void testEditProductPage_POST() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Sampo Cap Bambang Edited");
        updatedProduct.setProductQuantity(200);

        when(productService.edit("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct)).thenReturn(updatedProduct);
        String view = productController.editProductPost("eb558e9f-1c39-460e-8860-71af6af63bd6",updatedProduct);

        assertEquals("redirect:/product/list", view);
        verify(productService, times(1)).edit("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);
    }

    @Test
    void testEditProductPage_NoProduct() {
        when(productService.findAll()).thenReturn(List.of());
        String view = productController.editProductPage("12345", model);

        assertEquals("EditProduct", view);
        verify(model, times(1)).addAttribute("product", null);
    }

    @Test
    void testProductListPage_GET() {
        List<Product> productList = List.of(product);
        when(productService.findAll()).thenReturn(productList);
        String view = productController.productListPage(model);

        assertEquals("ProductList", view);
        verify(model, times(1)).addAttribute("products", productList);
    }

    @Test
    void testProductListPage_NoProduct() {
        List<Product> productList = List.of();
        when(productService.findAll()).thenReturn(productList);
        String view = productController.productListPage(model);

        assertEquals("ProductList", view);
        verify(model, times(1)).addAttribute("products", productList);
    }

    @Test
    void testDeleteProduct_POST() {
        doNothing().when(productService).delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        String view = productController.deleteProductPost("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertEquals("redirect:/product/list", view);
        verify(productService, times(1)).delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }
}
