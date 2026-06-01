package test;

import dao.ProductDAO;
import model.Product;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductDAOTest {

    private ProductDAO dao;

    @Before
    public void setUp() {
        dao = new ProductDAO();

        dao.deleteProduct("T1001");
        dao.deleteProduct("T1002");
        dao.deleteProduct("T1003");
        dao.deleteProduct("T1004");
        dao.deleteProduct("T1005");
    }

    @Test
    public void testAddProduct() {
        Product product = new Product("T1001", "Test Keyboard", "Test", 10.0, 5);

        boolean result = dao.addProduct(product);

        assertTrue(result);
    }

    @Test
    public void testSearchProductById() {
        Product product = new Product("T1002", "Test Mouse", "Test", 15.0, 8);
        dao.addProduct(product);

        Product found = dao.searchProductById("T1002");

        assertNotNull(found);
        assertEquals("Test Mouse", found.getName());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product("T1003", "Old Name", "Test", 20.0, 10);
        dao.addProduct(product);

        Product updatedProduct = new Product("T1003", "New Name", "Test", 25.0, 12);
        boolean updated = dao.updateProduct(updatedProduct);

        Product found = dao.searchProductById("T1003");

        assertTrue(updated);
        assertNotNull(found);
        assertEquals("New Name", found.getName());
        assertEquals(25.0, found.getPrice(), 0.01);
        assertEquals(12, found.getQuantity());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product("T1004", "Delete Item", "Test", 5.0, 2);
        dao.addProduct(product);

        boolean deleted = dao.deleteProduct("T1004");

        assertTrue(deleted);
        assertNull(dao.searchProductById("T1004"));
    }

    @Test
    public void testUpdateStockQuantity() {
        Product product = new Product("T1005", "Stock Item", "Test", 30.0, 10);
        dao.addProduct(product);

        boolean updated = dao.updateStockQuantity("T1005", -3);

        Product found = dao.searchProductById("T1005");

        assertTrue(updated);
        assertNotNull(found);
        assertEquals(7, found.getQuantity());
    }

    @Test
    public void testCannotReduceStockBelowZero() {
        Product product = new Product("T1001", "Low Stock Test", "Test", 10.0, 5);
        dao.addProduct(product);

        boolean updated = dao.updateStockQuantity("T1001", -100);

        Product found = dao.searchProductById("T1001");

        assertFalse(updated);
        assertNotNull(found);
        assertEquals(5, found.getQuantity());
    }

    @Test
    public void testGetLowStockProducts() {
        Product product = new Product("T1002", "Low Stock Item", "Test", 12.0, 3);
        dao.addProduct(product);

        ArrayList<Product> lowStockProducts = dao.getLowStockProducts(5);

        boolean foundLowStockProduct = false;

        for (Product p : lowStockProducts) {
            if (p.getId().equals("T1002")) {
                foundLowStockProduct = true;
                break;
            }
        }

        assertTrue(foundLowStockProduct);
    }
}