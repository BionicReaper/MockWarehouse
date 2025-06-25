package com.warehouse.service;

import com.warehouse.dto.inventory.CreateInventoryDTO;
import com.warehouse.dto.inventory.ResponseInventoryDTO;
import com.warehouse.dto.inventory.UpdateInventoryDTO;
import com.warehouse.dto.mapper.InventoryMapper;
import com.warehouse.dto.reference.ReferenceDTO;
import com.warehouse.entity.Inventory;
import com.warehouse.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing {@link Inventory} entities.
 * Provides CRUD operations and business logic for inventory management.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper mapper;

    /**
     * Constructs an InventoryServiceImpl with the given {@code InventoryRepository}.
     *
     * @param inventoryRepository repository for inventory persistence operations
     */
    public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;
        this.mapper = inventoryMapper;
    }

    /**
     * Retrieves all inventory records.
     *
     * @return list of all inventories
     */
    @Override
    public List<ResponseInventoryDTO> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return mapper.toResponseDto(inventories);
    }

    /**
     * Retrieves an inventory record by its ID.
     *
     * @param id the inventory ID
     * @return an Optional containing the found inventory or empty if not found
     */
    @Override
    public Optional<ResponseInventoryDTO> getInventoryById(Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        return inventory.map(mapper::toResponseDto);
    }

    /**
     * Creates a new inventory record.
     *
     * @param inventoryDTO the inventory entity to create
     * @return the saved inventory entity
     */
    @Override
    public ResponseInventoryDTO createInventory(CreateInventoryDTO inventoryDTO) {
        Inventory inventory = mapper.toEntity(inventoryDTO);
        Inventory saved = inventoryRepository.save(inventory);
        return mapper.toResponseDto(saved);
    }

    /**
     * Updates an existing inventory record by ID.
     * Only quantity, minStock, and maxStock fields are updated.
     *
     * @param id        the ID of the inventory to update
     * @param inventoryDTO the inventory data to update
     * @return the updated inventory entity
     * @throws RuntimeException if the inventory with given ID is not found
     */
    @Override
    public ResponseInventoryDTO updateInventory(Long id, UpdateInventoryDTO inventoryDTO) {
        Inventory existing = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found: " + id));
        Inventory inventory = mapper.toEntity(inventoryDTO);

        existing.setQuantity(inventory.getQuantity());
        existing.setMinStock(inventory.getMinStock());
        existing.setMaxStock(inventory.getMaxStock());

        Inventory saved = inventoryRepository.save(existing);
        return mapper.toResponseDto(saved);
    }

    /**
     * Deletes an inventory record by its ID.
     *
     * @param id the ID of the inventory to delete
     */
    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    /**
     * Finds all inventory records in a warehouse by warehouse ID.
     *
     * @param id the warehouse ID
     * @return list of inventory records in the specified warehouse
     */
    @Override
    public List<ResponseInventoryDTO> findWarehouseInventory(Long id) {
        List<Inventory> inventories = inventoryRepository.findByWarehouseId(id);
        return mapper.toResponseDto(inventories);
    }

    /**
     * Finds all inventory records in a warehouse by warehouse entity.
     *
     * @param warehouseDTO the warehouse entity
     * @return list of inventory records in the specified warehouse
     */
    @Override
    public List<ResponseInventoryDTO> findWarehouseInventory(ReferenceDTO warehouseDTO) {
        return findWarehouseInventory(warehouseDTO.id());
    }

    /**
     * Finds all inventory records containing a specific product by product ID.
     *
     * @param id the product ID
     * @return list of inventory records for the specified product
     */
    @Override
    public List<ResponseInventoryDTO> findProductInInventory(Long id) {
        List<Inventory> inventories = inventoryRepository.findByProductId(id);
        return mapper.toResponseDto(inventories);
    }

    /**
     * Finds all inventory records containing a specific product by product entity.
     *
     * @param productDTO the product entity
     * @return list of inventory records for the specified product
     */
    @Override
    public List<ResponseInventoryDTO> findProductInInventory(ReferenceDTO productDTO) {
        return findProductInInventory(productDTO.id());
    }

    /**
     * Finds inventory records for a specific product in a specific warehouse.
     *
     * @param productId   the product ID
     * @param warehouseId the warehouse ID
     * @return list of matching inventory records
     */
    @Override
    public List<ResponseInventoryDTO> findProductInWarehouseInventory(Long productId, Long warehouseId) {
        List<Inventory> inventories = inventoryRepository.findByWarehouseIdAndProductId(warehouseId, productId);
        return mapper.toResponseDto(inventories);
    }

    /**
     * Finds inventory records for a specific product entity in a specific warehouse by warehouse ID.
     *
     * @param productDTO  the product entity
     * @param warehouseId the warehouse ID
     * @return list of matching inventory records
     */
    @Override
    public List<ResponseInventoryDTO> findProductInWarehouseInventory(ReferenceDTO productDTO, Long warehouseId) {
        return findProductInWarehouseInventory(productDTO.id(), warehouseId);
    }

    /**
     * Finds inventory records for a specific product by product ID in a specific warehouse entity.
     *
     * @param productId    the product ID
     * @param warehouseDTO the warehouse entity
     * @return list of matching inventory records
     */
    @Override
    public List<ResponseInventoryDTO> findProductInWarehouseInventory(Long productId, ReferenceDTO warehouseDTO) {
        return findProductInWarehouseInventory(productId, warehouseDTO.id());
    }

    /**
     * Finds inventory records for a specific product entity in a specific warehouse entity.
     *
     * @param productDTO   the product entity
     * @param warehouseDTO the warehouse entity
     * @return list of matching inventory records
     */
    @Override
    public List<ResponseInventoryDTO> findProductInWarehouseInventory(ReferenceDTO productDTO, ReferenceDTO warehouseDTO) {
        return findProductInWarehouseInventory(productDTO.id(), warehouseDTO.id());
    }

    /**
     * Finds all inventory records with low stock (below minimum stock threshold).
     *
     * @return list of inventory records with low stock
     */
    @Override
    public List<ResponseInventoryDTO> findLowStockInventory() {
        List<Inventory> inventories = inventoryRepository.findLowStockItems();
        return mapper.toResponseDto(inventories);
    }
}
