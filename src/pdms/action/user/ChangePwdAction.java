package pdms.action.user;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import pdms.hbt.dao.entities.Userinfo;
import pdms.hbt.dao.util.OperationUtil;
/**
 * 更改用户密码
 * @author 程国前
 * 2013-11-26
 */
public class ChangePwdAction extends ActionSupport {
	/**
	 * 执行更改
	 */
	public String execute() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		this.success = true;
		if(session.get("ui") == null){//超级管理员
			this.info = "超级管理员密码无权修改！";
			return SUCCESS;
		}else{
			Userinfo ui = (Userinfo)session.get("ui");
			String pwd = ui.getUserPwd();
			if(!pwd.equals(opwd)){//原始密码不正确,给出提示,不执行更改
				this.info = "原始密码错误";
				return SUCCESS;
			}else{//原始密码正确,执行修改
				ui.setUserPwd(npwd);
				OperationUtil.update(ui);//执行更改
			}
		}	
		this.info = "密码修改成功！";
		return SUCCESS;
	}
	
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getOpwd() {
		return opwd;
	}
	public void setOpwd(String opwd) {
		this.opwd = opwd;
	}
	public String getNpwd() {
		return npwd;
	}
	public void setNpwd(String npwd) {
		this.npwd = npwd;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	private static final long serialVersionUID = 6807885615759570127L;
	private boolean success = false;
	private String opwd;
	private String npwd;
	private String info;
}
