package pdms.action.workplace;
import java.io.Serializable;
import java.util.Map;


import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * Workplace操作
 * @author 董海朋
 * 2018-05-03
 */
public class ProOptAction extends ActionSupport implements Serializable {


	/**
	 * 获取用户角色
	 * @return
	 * @throws Exception
	 */
	public String execute(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		this.role = session.get("userrole").toString();
		return SUCCESS;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

	private static final long serialVersionUID = 2538873894808257973L;
	private String  role;
	
	protected static final Logger logger = Logger.getLogger(ProOptAction.class);
}