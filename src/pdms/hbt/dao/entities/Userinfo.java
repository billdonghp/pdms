package pdms.hbt.dao.entities;
import java.sql.Timestamp;
/**
 * 用户信息 entity 
 * @author 董海朋
 * @time 2018-04-27
 */
public class Userinfo implements java.io.Serializable {


	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return this.userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}


	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public Timestamp getUptDt() {
		return this.uptDt;
	}

	public void setUptDt(Timestamp uptDt) {
		this.uptDt = uptDt;
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
	private static final long serialVersionUID = -6345916210539132473L;
	private String userId;			//用户ID
	private String userNm;			//用户姓名
	private String userPwd;			//用户密码
	private String roleId;			//角色ID
	private String roleNm;			//角色名称
	private Integer status;			//使用状态
	private String email;			//邮件地址
	private String phoneNum;		//电话号码
	private Timestamp createDt;		//创建日期
	private Timestamp uptDt;		//修改日期
}
