package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.MyPageDAO;
import com.internousdev.ecsite.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware{

	/**
	 * ���O�C�������i�[
	 */
	public Map<String, Object> session;

	/**
	 * �}�C�y�[�W���擾DAO
	 */
	private MyPageDAO myPageDAO = new MyPageDAO();

	/**
	 * �}�C�y�[�W���i�[DTO
	 */
	public ArrayList<MyPageDTO> myPageList = new ArrayList<MyPageDTO>();

	/**
	 * �폜�t���O
	 */
	private String deleteFlg;

	private String message;

	/**
	 * ���i�����擾���\�b�h
	 *
	 * @author internous
	 */
	public String execute() throws SQLException {

		if (!session.containsKey("id")) {
			return ERROR;
		}

		// ���i�������폜���Ȃ��ꍇ
		if(deleteFlg == null) {
			String item_transaction_id = session.get("id").toString();
			String user_master_id = session.get("login_user_id").toString();

			myPageList = myPageDAO.getMyPageUserInfo(item_transaction_id, user_master_id);

			Iterator<MyPageDTO> iterator = myPageList.iterator();
			if (!(iterator.hasNext())) {
				myPageList = null;
			}
		// ���i�������폜����ꍇ
		} else if(deleteFlg.equals("1")) {
			delete();
		}

		String result = SUCCESS;
		return result;
	}

	/**
	 * ���i�����폜
	 *
	 * @throws SQLException
	 */
	public void delete() throws SQLException {

		String item_transaction_id = session.get("id").toString();
		String user_master_id = session.get("login_user_id").toString();

		int res = myPageDAO.buyItemHistoryDelete(item_transaction_id, user_master_id);

		if(res > 0) {
			myPageList = null;
			setMessage("���i���𐳂����폜���܂����B");
		} else if(res == 0) {
			setMessage("���i���̍폜�Ɏ��s���܂����B");
		}
	}



	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}