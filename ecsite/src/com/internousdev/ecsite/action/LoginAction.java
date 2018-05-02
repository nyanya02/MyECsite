package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dao.LoginDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.internousdev.ecsite.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ���O�C���F�؏���
 * Login.jsp���烍�O�C��ID�A���O�C���p�X���[�h���󂯎��
 * DB�֖₢���킹���s���܂��B
 *
 * @author internous
 * @param loginUserId
 * @param loginPassword
 *
 * @return result
 */
public class LoginAction extends ActionSupport implements SessionAware{

	/**
	 * ���O�C��ID
	 */
	private String loginUserId;

	/**
	 * ���O�C���p�X���[�h
	 */
	private String loginPassword;

	/**
	 * ���O�C�������i�[
	 */
	public Map<String, Object> session;

	/**
	 * ���O�C�����擾DAO
	 */
	private LoginDAO loginDAO = new LoginDAO();

	/**
	 * ���O�C�����i�[IDTO
	 */
	private LoginDTO loginDTO = new LoginDTO();

	/**
	 * �A�C�e�������擾
	 */
	private BuyItemDAO buyItemDAO = new BuyItemDAO();

	/**
	 * ���s���\�b�h
	 */
	public String execute() {

		String result = ERROR;

		// ���O�C�����s
		loginDTO = loginDAO.getLoginUserInfo(loginUserId, loginPassword);

		session.put("loginUser", loginDTO);

		// ���O�C�������r
		if(((LoginDTO) session.get("loginUser")).getLoginFlg()) {
			result = SUCCESS;

			// �A�C�e�������擾
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
			session.put("login_user_id",	loginDTO.getLoginId());
			session.put("id", buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price", buyItemDTO.getItemPrice());

			return result;
		}

		return result;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}