package pdms.action.login;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import pdms.hbt.dao.entities.Loginhistory;
import pdms.hbt.dao.entities.Userinfo;
import pdms.hbt.dao.util.OperationUtil;
import pdms.hbt.dao.util.QueryUtil;
/**
 * 用户登录
 * @author 程国前
 * 2013-11-25
 */
public class LoginAction extends ActionSupport {
	/**
	 *	执行登录
	 */
	public String execute() throws Exception{
		this.success= true;
		this.info = "ok";
		Map<String, Object> session = ActionContext.getContext().getSession();
		Userinfo ui = null;
		//判断是否是系统超级管理员
		if("administrator".equals(name)){
			if(!getAdminpwd().equals(getPassword())){
				this.info = "密码错误，请重新输入密码！";
				return SUCCESS;
			}
		}else{
			ui = QueryUtil.getObjectById(Userinfo.class, name)==null?null:(Userinfo)QueryUtil.getObjectById(Userinfo.class, name);
			if(ui == null){//用户不存在
				this.info = "用户不存在！";
				return SUCCESS;
			}else{
				if(ui.getStatus() == 0){//用户已停用
					this.info = "用户已停用！";
					return SUCCESS;
				}
				if(!password.equals(ui.getUserPwd())){//密码不正确
					this.info = "密码错误，请重新输入密码！";
					return SUCCESS;
				}
			}
		}		
		//判断是否是第一次登录
		List<Object> l = QueryUtil.getObjectByCondition(Loginhistory.class, "loginid", name);
		if(l.size() == 0)
			session.put("logininfo", "这是您首次登录本系统，欢迎使用！");
		else{
			Loginhistory qlh = (Loginhistory)l.get(l.size()-1);
			session.put("logininfo", "上次登录："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(qlh.getLogindt()));
		}
		//写入一条登录记录
		Loginhistory lh = null;
		if("administrator".equals(name)){
			session.put("username", "administrator");
			session.put("ui", ui);
			session.put("userrole", "");
			lh = new Loginhistory("administrator", "administrator", new Timestamp(new Date().getTime()));
		}else{
			session.put("username", ui.getUserNm());
			session.put("userrole", ui.getRoleId());
			session.put("ui", ui);
			lh = new Loginhistory(name, ui.getUserNm(),new Timestamp(new Date().getTime()));
		}
		OperationUtil.add(lh);
		return SUCCESS;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getAdminpwd() {
		return adminpwd;
	}
	public void setAdminpwd(String adminpwd) {
		this.adminpwd = adminpwd;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	private String name;
	private String password;
	private String adminpwd;
	private String info;
	private boolean success;
	private static final long serialVersionUID = -6230218503870450793L;
}
