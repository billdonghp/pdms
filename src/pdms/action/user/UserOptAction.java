package pdms.action.user;
import java.sql.Timestamp;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import pdms.hbt.dao.entities.Sysrole;
import pdms.hbt.dao.entities.Userinfo;
import pdms.hbt.dao.util.OperationUtil;
import pdms.hbt.dao.util.QueryUtil;
/**
 * 用户操作：新增、修改
 * @author 程国前
 * 2013-11-26
 */
public class UserOptAction extends ActionSupport {
	//新增
	public String add() throws Exception{
		this.info = "ok";
		if(QueryUtil.getObjectById(Userinfo.class, userId.trim()) != null){
			this.info = "用户已存在！";
			this.success = true;
			return SUCCESS;
		}
		Userinfo ui = new Userinfo();
		ui.setCreateDt(new Timestamp(new Date().getTime()));
		ui.setEmail(email==null||"".equals(email.trim())?"":email.trim());
		ui.setPhoneNum(phoneNum==null||"".equals(phoneNum)?"":phoneNum);
		ui.setRoleId(roleId);
		ui.setRoleNm(((Sysrole)QueryUtil.getObjectById(Sysrole.class, roleId)).getRolenm());
		ui.setStatus(status);
		ui.setUserId(userId.trim());
		ui.setUserNm(userNm.trim());
		if(userPwd == null || "".equals(userPwd.trim()))
			ui.setUserPwd(userId.toUpperCase().trim().replace("IC", ""));
		else
			ui.setUserPwd(userPwd.trim());
		ui.setUptDt(new Timestamp(new Date().getTime()));
		OperationUtil.add(ui);
		this.success = true;
		return SUCCESS;
	}
	/**
	 * 修改用户
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception{
		this.info = "ok";
		this.success = true;
		Userinfo ui = (Userinfo)QueryUtil.getObjectById(Userinfo.class, userId);
		ui.setEmail(email==null||"".equals(email.trim())?"":email.trim());
		ui.setPhoneNum(phoneNum==null||"".equals(phoneNum)?"":phoneNum);
		ui.setRoleId(roleId);
		ui.setRoleNm(((Sysrole)QueryUtil.getObjectById(Sysrole.class, roleId)).getRolenm());
		ui.setStatus(status);
		ui.setUserNm(userNm);
		if(userPwd != null && !"".equals(userPwd.trim()))
			ui.setUserPwd(userPwd.trim());
		ui.setUptDt(new Timestamp(new Date().getTime()));
		OperationUtil.update(ui);
		return SUCCESS;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleNm() {
		return roleNm;
	}
	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	private static final long serialVersionUID = 7186071454910767049L;
	private String userId;
	private String userNm;
	private String userPwd;
	private String roleId;
	private String roleNm;
	private Integer status;
	private String email;
	private String phoneNum;
	private boolean success;
	private String info;
}
