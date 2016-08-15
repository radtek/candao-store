/**
 * 
 */
package com.candao.www.webroom.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.candao.www.data.model.TorderDetailPreferential;

/**
 * 操作 优惠的时候，返回操作结果
 * 
 * @author YHL
 *
 */
public class OperPreferentialResult {

	/**
	 * 金额优惠总金额
	 */
	private BigDecimal amount = new BigDecimal(0);
	/**
	 * 菜品总价
	 **/
	private BigDecimal menuAmount = new BigDecimal(0);
	/**
	 * 支付金额
	 */
	private BigDecimal payamount = new BigDecimal(0);
	/***
	 * 小费金额
	 */
	private BigDecimal tipAmount = new BigDecimal(0);

	/***
	 * 优免总金额
	 */
	private BigDecimal toalFreeAmount = new BigDecimal(0);
	/***
	 * 挂账总金额
	 */
	private BigDecimal toalDebitAmount = new BigDecimal(0);

	/***
	 * 赠送金额
	 */
	private BigDecimal zdAmount = new BigDecimal(0);
	/***
	 * 优惠调整
	 *
	 */
	private BigDecimal adjAmout = new BigDecimal(0);
	/***
	 * 处理的金额(四舍五入或抹零)
	 */
	private BigDecimal moneyWipeAmount = new BigDecimal(0);
	/**
	 * (处理的名称 ：四舍五入 或 抹零)
	 */
	private String moneyWipeName;
	/***
	 * 挂账多收
	 * */
	private BigDecimal toalDebitAmountMany=new BigDecimal("0");
	private String moneyDisType;
	private List<TorderDetailPreferential> detailPreferentials = new ArrayList<>();

	public BigDecimal getAmount() {
		return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<TorderDetailPreferential> getDetailPreferentials() {
		return detailPreferentials;
	}

	public void setDetailPreferentials(List<TorderDetailPreferential> detailPreferentials) {
		this.detailPreferentials = detailPreferentials;
	}

	public BigDecimal getPayamount() {
		return payamount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setPayamount(BigDecimal payamount) {
		this.payamount = payamount;
	}

	public BigDecimal getMenuAmount() {
		return menuAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setMenuAmount(BigDecimal menuAmount) {
		this.menuAmount = menuAmount;
	}

	public BigDecimal getTipAmount() {
		return tipAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setTipAmount(BigDecimal tipAmount) {
		this.tipAmount = tipAmount;
	}

	public BigDecimal getToalFreeAmount() {
		return toalFreeAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setToalFreeAmount(BigDecimal toalFreeAmount) {
		this.toalFreeAmount = toalFreeAmount;
	}

	public BigDecimal getToalDebitAmount() {
		return toalDebitAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setToalDebitAmount(BigDecimal toalDebitAmount) {
		this.toalDebitAmount = toalDebitAmount;
	}

	public BigDecimal getZdAmount() {
		return zdAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setZdAmount(BigDecimal zdAmount) {
		this.zdAmount = zdAmount;
	}

	public BigDecimal getMoneyWipeAmount() {
		return moneyWipeAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setMoneyWipeAmount(BigDecimal moneyWipeAmount) {
		this.moneyWipeAmount = moneyWipeAmount;
	}

	public String getMoneyWipeName() {
		return moneyWipeName;
	}

	public void setMoneyWipeName(String moneyWipeName) {
		this.moneyWipeName = moneyWipeName;
	}

	public String getMoneyDisType() {
		return moneyDisType;
	}

	public void setMoneyDisType(String moneyDisType) {
		this.moneyDisType = moneyDisType;
	}

	public BigDecimal getAdjAmout() {
		return adjAmout.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setAdjAmout(BigDecimal adjAmout) {
		this.adjAmout = adjAmout;
	}

	public BigDecimal getToalDebitAmountMany() {
		return toalDebitAmountMany.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setToalDebitAmountMany(BigDecimal toalDebitAmountMany) {
		this.toalDebitAmountMany = toalDebitAmountMany;
	}

}
