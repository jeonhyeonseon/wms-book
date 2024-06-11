package com.sh.Inventory.model.service;

import com.sh.Inventory.model.dao.InventoryMapper;
import com.sh.Inventory.model.dto.InventoryDto;
import org.apache.ibatis.session.SqlSession;

import static com.sh.common.MyBatisTemplate.getSqlSession;



//    특정주문을 선택하면 주문아이템별로 출고된 내역만큼 차감처리되어야 합니다.

//    모든 출고가 정상적으로 처리된 경우, 해당 주문의 상태는 발송완료로 변경되어야 합니다.

public class InventoryService {
    public InventoryDto ObInventory(int orderId) {
        SqlSession sqlSession = getSqlSession();
        InventoryMapper inventoryMapper = sqlSession.getMapper(InventoryMapper.class);
        try {





            return inventoryMapper.ObInventory(orderId);
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
}
