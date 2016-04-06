package com.candao.www.dataserver.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by ytq on 2016/3/15.
 */
public interface OrderOpMapper {
    void updateBefPrintCount(String orderId);

    void updatePrintCount(String orderId);

    int getZdAmountByOrderId(String orderId);

    List<Map> getOrderJson(@Param("zdAmount") String zdAmount, @Param("orderId") String orderId);

    List<Map> getListJson(String orderId);

    List<Map> getJsJson(String orderId);

    void updateOrderTypeById(@Param("orderId") String orderId, @Param("orderType") String orderType);

    List<Map> getMemberSaleInfo(String orderId);

    List<Map> getOrderRuleByOrderId(String orderId);

    List<Map> getAllOrderInfo2(String workDate);

    void updateParternerPY();

    List<Map> getAllGZDW();

    void saveSettlement(@Param("sDetailId") String sDetailId, @Param("orderId") String orderId,
                        @Param("payJsonArray") String payJsonArray, @Param("userId") String userId);

    List<Map> getSettlementDetailBatch(String orderId);

    void deleteDetailBatch(String sDetailId);
}