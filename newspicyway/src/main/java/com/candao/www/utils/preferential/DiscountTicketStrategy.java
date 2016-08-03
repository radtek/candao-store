package com.candao.www.utils.preferential;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.candao.common.utils.PropertiesUtils;
import com.candao.www.data.dao.TbDiscountTicketsDao;
import com.candao.www.data.dao.TbPreferentialActivityDao;
import com.candao.www.data.dao.TdishDao;
import com.candao.www.data.dao.TorderDetailMapper;
import com.candao.www.data.dao.TorderDetailPreferentialDao;
import com.candao.www.data.model.TbNoDiscountDish;
import com.candao.www.data.model.Tdish;
import com.candao.www.data.model.TorderDetail;
import com.candao.www.data.model.TorderDetailPreferential;
import com.candao.www.dataserver.util.IDUtil;

/**
 * 
 * @author Candao 折扣优惠卷
 */
public class DiscountTicketStrategy extends CalPreferentialStrategy {

	@Override
	public Map<String, Object> calPreferential(Map<String, Object> paraMap,
			TbPreferentialActivityDao tbPreferentialActivityDao, TorderDetailMapper torderDetailDao,
			TorderDetailPreferentialDao orderDetailPreferentialDao, TbDiscountTicketsDao tbDiscountTicketsDao,
			TdishDao tdishDao) {
		String orderid = (String) paraMap.get("orderid"); // 账单号
		String preferentialid = (String) paraMap.get("preferentialid"); //优惠活动id
		String branchid = PropertiesUtils.getValue("current_branch_id");
		BigDecimal bd = new BigDecimal((String) paraMap.get("preferentialAmt"));
		Map tempMap = this.discountInfo(preferentialid, branchid, tbPreferentialActivityDao);
		BigDecimal discount = (BigDecimal) tempMap.get("discount");

		// 获取当前账单的 菜品列表
		Map<String, Object> result = new HashMap<>();
		Map<String, String> orderDetail_params = new HashMap<>();
		orderDetail_params.put("orderid", orderid);
		List<TorderDetail> orderDetailList = torderDetailDao.find(orderDetail_params);

		// 将菜单表转换为菜品编号(key):菜品交易详情（Value）
		Map<String, TorderDetail> orderDetailMap = new HashMap<>();
		for (TorderDetail detail : orderDetailList) {
			orderDetailMap.put(detail.getDishid(), detail);
		}

		// 获取特价券 不参与折扣的菜品。并放入map
		Map<String, Object> noDisMap = new HashMap<>();
		noDisMap.put("discountId", preferentialid);
		noDisMap.put("orderDetail", orderDetailList);
		List<TbNoDiscountDish> noDiscountDishlist = tbDiscountTicketsDao.getNoDiscountDishsByDish(noDisMap);
		// 用map方式存放不参与折扣的菜品
		Map<String, String> noDiscountDishMap = new HashMap<>();
		// 根据不优惠的菜品获取菜品库的菜品信息
		for (TbNoDiscountDish t : noDiscountDishlist) {
			// 处理套餐，处理组合如果套餐不再优惠中，那么套餐菜品一样不享受优惠
			Tdish tdish = tdishDao.get(t.getDish());
			if (tdish.getDishtype() == 1 && orderDetailMap.containsKey(t.getDish())) {
				List<Tdish> tdishList = tbDiscountTicketsDao.getDishidList(t.getDish());
				if (tdishList.size() > 0) {
					for (Tdish dish : tdishList) {
						noDiscountDishMap.put(dish.getDishid(), dish.getDishid());
					}
				}
			}
			noDiscountDishMap.put(t.getDish(), t.getDish());
		}

		/** 当前订单使用优惠记录 **/
		Map<String, TorderDetailPreferential> preferInfoMap = new HashMap<>();
		for (TorderDetailPreferential detailPreferential : orderDetailPreferentialDao.queryDetailPreBy(orderid)) {
			preferInfoMap.put(detailPreferential.getDishid(), detailPreferential);

		}

		// 4.遍历账单的菜品，如果输入不进行折扣的菜品，则不处理。否则，认为是需要计算优惠。
		String nd = null;
		BigDecimal orignalprice = null;
		BigDecimal amount = new BigDecimal(0); // 最终优惠金额
		// 菜品原价
		BigDecimal amountCount = new BigDecimal(0.0);
		// 优惠折扣菜单个数
		double tempDishNum = 0;
		for (TorderDetail d : orderDetailList) {
			nd = noDiscountDishMap.get(d.getDishid());
			if (null == nd) {
				// 如果在不优惠的列表中没有找到这个菜品，则认为这个菜品是可以优惠打折的。
				// 判断价格，如果菜品价格存在null的问题，
				if (null != d.getOrderprice()) {
					orignalprice = d.getOrderprice().multiply(discount.divide(new BigDecimal(10))); // 设置优惠后的金额
					// 如果此菜品是多份，则计算多份总的优惠价格
					BigDecimal numOfDish = new BigDecimal("0");
					if (new BigDecimal(d.getDishnum()).compareTo(new BigDecimal("0")) > 0) {
						numOfDish = new BigDecimal(d.getDishnum());
					}
					orignalprice = orignalprice.multiply(numOfDish);
					amountCount = amountCount.add(d.getOrderprice().multiply(numOfDish));
					tempDishNum += Double.valueOf(d.getDishnum());
				}
			}
		}
		// 如果需要折扣的菜品的总价不大于0或者小于已经折扣掉的金额，则不计算本次折扣金额
		if (amountCount.compareTo(BigDecimal.ZERO) > 0 && (amountCount.subtract(bd).compareTo(BigDecimal.ZERO)) != -1) {
			amount = amountCount.subtract(bd)
					.multiply(new BigDecimal("1").subtract(discount.divide(new BigDecimal(10))));
			//是重新计算优惠还是，新加优惠
		   String updateId=paraMap.containsKey("updateId")?(String)paraMap.get("updateId"):IDUtil.getID();
			 TorderDetailPreferential addPreferential = new TorderDetailPreferential(updateId, orderid, "", preferentialid,
						amount, String.valueOf(tempDishNum), 1, 1, discount, 0);
			 List<TorderDetailPreferential> detailPreferentials = new ArrayList<>();
			 detailPreferentials.add(addPreferential);
			result.put("detailPreferentials",detailPreferentials);
			result.put("amount", amount);
		}
		return result;
	}

}