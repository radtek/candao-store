package com.candao.www.data.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.candao.common.exception.SysException;



public interface BranchDataSynDao {
	public final static String PREFIX = BranchDataSynDao.class.getName();
	
	public  int checkBizData();
	
	public int checkSynDataFinish();
	/**
	 * 
	 * @Description:检查最近一次同步是否有未同步的记录
	 * @create: 余城序
	 * @Modification:
	 * @return int 1-有,0-没有
	 */
	public int checkLastSynDataFinish();
	
	public int insertSynRecord(Map<String, String> mapValue) ;
	
	public int updateSynRecord(Map<String, Object> mapValue) ;
	/**
	 * 
	 * @Description:更新数据是否上传状态
	 * @create: 余城序
	 * @Modification:
	 * @param mapValue
	 * @return int
	 */
	public int updateSynRecords(Map<String, Object> mapValue) ;
	/**
	 * 
	 * @Description:更新同步数据的记录
	 * @create: 余城序
	 * @Modification:
	 * @param mapValue
	 * @return int
	 */
	public int updateSynData(Map<String, Object> mapValue);
	
	public Integer getMaxId();

	public void transferToHistory();
	
	public String getSynSql(String tableName,String sqlCondition);
	/**
	 * 
	 * @Description:获取需要同步的数据
	 * @create: 余城序
	 * @Modification:
	 * @param tableName 表名
	 * @param sqlCondition 查询需要同步的数据的sql
	 * @return List 结果集
	 */
	public List<Map<String,String>> getSynData(String tableName,String sqlCondition) throws SysException;

	public void deleteSynRecord();

	public void updateBizLog();

	public Map<String, String> getBizDate();
}
