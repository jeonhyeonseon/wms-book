package com.sh.Inventory.model.dao;

import com.sh.Inventory.model.dto.InventoryDto;

public interface InventoryMapper {
    InventoryDto ObInventory(int orderId) ;
    InventoryDto selectOrderById(int orderId);
    void updateOrderStatus(int orderId);
}
