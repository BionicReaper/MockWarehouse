package com.warehouse.service;

import com.warehouse.entity.Warehouse;
import com.warehouse.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse updateWarehouse(Long id, Warehouse warehouse) {
        return warehouseRepository.findById(id)
                .map(existing -> {
                    existing.setName(warehouse.getName());
                    existing.setAddress(warehouse.getAddress());
                    existing.setCapacity(warehouse.getCapacity());
                    existing.setManagerName(warehouse.getManagerName());
                    return warehouseRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Warehouse not found: " + id));
    }

    @Override
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    @Override
    public List<Warehouse> findWarehousesByName(String name) {
        return warehouseRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Warehouse> findWarehousesByCapacity(BigDecimal minCapacity) {
        return warehouseRepository.findByCapacityGreaterThan(minCapacity);
    }

    @Override
    public List<Warehouse> search(String name, BigDecimal minCapacity){
        if(name != null && minCapacity == null)
            return this.findWarehousesByName(name);
        else if(name == null && minCapacity != null)
            return this.findWarehousesByCapacity(minCapacity);
        else
            throw new RuntimeException("Bad Request");
    }
}
