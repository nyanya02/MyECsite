
package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BuyItemAction extends ActionSupport implements SessionAware {

	/**
	 * �A�C�e���w����
	 */
	private int count;

	/**
	 * �x�������@
	 */
	private String pay;

	/**
	 * �A�C�e�������i�[
	 */
	public Map<String, Object>  session;

	/**
	 * ���i���擾���\�b�h
	 *
	 * @author internous
	 */
	public String execute() {
		String result = SUCCESS;
		session.put("count", count);
		int intCount = Integer.parseInt(session.get("count").toString());
		int intPrice = Integer.parseInt(session.get("buyItem_price").toString());

		session.put("total_price", intCount * intPrice);
		String payment;

		if(pay.equals("1")) {

			payment = "��������";
			session.put("pay", payment);
		} else {

			payment = "�N���W�b�g�J�[�h";
			session.put("pay", payment);
		}
		return result;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}