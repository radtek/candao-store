package com.candao.www.dataserver.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

/**
 * Created by lenovo on 2016/4/7.
 */
public interface TellerCashMapper {
    Map<String, Object> selectInsertDate(@Param("userId") String userId, @Param("ip") String ip);

    Integer selectShiftid(@Param("openDate") String openDate, @Param("userId") String userId, @Param("ip") String ip);

    String selectCashAmount(@Param("openDate") String openDate, @Param("userId") String userId, @Param("ip") String ip);

    String selectBeginPeople(@Param("beginTime") String beginTime);

    int selectBeginTableTotal(@Param("beginTime") String beginTime);

    int selectNonClosingTable(@Param("beginTime") String beginTime);

    int selectClosingTable(@Param("endTime") String endTime);

    String selectClosingPeople(@Param("endTime") String endTime);

    int selectLastNonTable(@Param("beginTime") String beginTime);

    String selectNotClosingMoney(@Param("beginTime") String beginTime);

    String selectFoodMoney(@Param("beginTime") String beginTime);

    String selectItemMoney(@Param("beginTime") Date beginTime,@Param("userId")String userId);

    Map<String,Object> selectPreferenceMoney(@Param("beginTime") Date beginTime,@Param("userId")String userId);

    String selectRemoveMoney(@Param("beginTime") Date beginTime,@Param("userId")String userId);

    String selectTotalMoney(@Param("beginTime") Date beginTime, @Param("userId") String userId);

    String selectIncludedTotalMoney(@Param("beginTime") Date beginTime, @Param("userId") String userId);

    int updateStatus(@Param("ip") String ip, @Param("userId") String userId);

    int selectNotClear(@Param("openDate") Date openDate);

    int selectNotEndTable();

    Map<String, Object> selectTodayInfo(@Param("openDate") String openDate, @Param("ip") String ip, @Param("userId") String userId);

    int insert(Map<String, Object> param);
}
