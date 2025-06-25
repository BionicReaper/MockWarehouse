package com.warehouse.service;

import com.warehouse.dto.mapper.ProductMapper;
import com.warehouse.dto.product.CreateProductDTO;
import com.warehouse.dto.product.ResponseProductDTO;
import com.warehouse.dto.product.UpdateProductDTO;
import com.warehouse.entity.Product;
import com.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link ProductService} that provides CRUD and business operations
 * for managing products in the warehouse system.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    /**
     * Constructs a new {@code ProductServiceImpl} with the given product repository.
     *
     * @param productRepository the repository used to access product data
     */
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.mapper = productMapper;
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    @Override
    public List<ResponseProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return mapper.toResponseDto(products);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product
     * @return an {@link Optional} containing the product if found, or empty if not found
     */
    @Override
    public Optional<ResponseProductDTO> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(mapper::toResponseDto);
    }

    /**
     * Creates a new product.
     *
     * @param productDTO the product to create
     * @return the created product
     */
    @Override
    public ResponseProductDTO createProduct(CreateProductDTO productDTO) {
        Product product = mapper.toEntity(productDTO);
        Product saved = productRepository.save(product);
        return mapper.toResponseDto(saved);
    }

    /**
     * Updates an existing product identified by its ID.
     *
     * @param id         the ID of the product to update
     * @param productDTO the product data to update
     * @return the updated product
     * @throws RuntimeException if the product with the specified ID does not exist
     */
    @Override
    public ResponseProductDTO updateProduct(Long id, UpdateProductDTO productDTO) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found: " + id));
        Product product = mapper.toEntity(productDTO);

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setCategory(product.getCategory());
        existing.setWeight(product.getWeight());

        Product saved = productRepository.save(existing);
        return mapper.toResponseDto(saved);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     */
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Finds products by category.
     *
     * @param category the category to filter by
     * @return a list of products in the specified category
     */
    @Override
    public List<ResponseProductDTO> findProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        return mapper.toResponseDto(products);
    }

    /**
     * Finds products whose names contain the specified string (case-insensitive).
     *
     * @param name the name substring to search for
     * @return a list of products whose names contain the specified string
     */
    @Override
    public List<ResponseProductDTO> findProductsByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return mapper.toResponseDto(products);
    }

    /**
     * Finds products with prices between the specified range.
     *
     * @param low  the minimum price (inclusive)
     * @param high the maximum price (inclusive)
     * @return a list of products within the price range
     */
    @Override
    public List<ResponseProductDTO> findProductsByPriceBetween(BigDecimal low, BigDecimal high) {
        List<Product> products = productRepository.findByPriceBetween(low, high);
        return mapper.toResponseDto(products);
    }

    /**
     * Searches for products based on optional criteria: category, name, and price range.
     * Only one criterion is applied at a time.
     *
     * @param category the category to filter by (optional)
     * @param name     the product name substring to filter by (optional)
     * @param minPrice the minimum price (optional)
     * @param maxPrice the maximum price (optional)
     * @return a list of products matching the search criteria
     * @throws RuntimeException if multiple or no criteria are provided (bad request)
     */
    @Override
    public List<ResponseProductDTO> search(String category, String name, BigDecimal minPrice, BigDecimal maxPrice) {
        if (category != null && name == null && minPrice == null && maxPrice == null)
            return this.findProductsByCategory(category);
        else if (category == null && name != null && minPrice == null && maxPrice == null)
            return this.findProductsByName(name);
        else if (category == null && name == null && minPrice != null && maxPrice != null)
            return this.findProductsByPriceBetween(minPrice, maxPrice);
        else
            throw new RuntimeException("Bad Request");
    }

    /**
     * Retrieves all distinct product categories.
     *
     * @return a list of all product categories
     */
    @Override
    public List<String> findAllCategories() {
        return productRepository.getAllCategories();
    }
}
