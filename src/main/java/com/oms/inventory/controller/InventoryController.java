package com.oms.inventory.controller;

import com.oms.inventory.model.InventoryProduct;
import com.oms.inventory.model.InventoryRequest;
import com.oms.inventory.model.InventoryResponse;
import com.oms.inventory.model.InventoryUpdate;
import com.oms.inventory.service.InventoryService;
import com.oms.inventory.service.InventoryServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryServiceImpl inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/add")
    public InventoryResponse addAvailableProduct(@RequestBody InventoryRequest inventoryRequest){
        return inventoryService.addAvailableProductToInventory(inventoryRequest);
    }

    @GetMapping("/{productId}")
    public int getProductCount(@PathVariable String productId){
        return inventoryService.getAvailableProductCountFromInventory(productId);
    }

    @DeleteMapping("/{productId}")
    public void deleteProductFromInventory(@RequestBody List<String> productIdList){
        inventoryService.deleteProductFromInventory(productIdList);
    }

    @PatchMapping("/{update}")
    public void updateProductFromInventory(@RequestBody List<InventoryUpdate> inventoryUpdateList){
        inventoryService.updateInventory(inventoryUpdateList);
    }

    @PostMapping("/addProduct")
    public List<InventoryProduct> addProduct(@RequestBody List<InventoryProduct> inventoryProducts){
        return inventoryService.addProductToRepository(inventoryProducts);
    }
}
