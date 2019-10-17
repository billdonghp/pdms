package pdms.action.login;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 检查用户是否已经登录
 * @author 程国前
 * 2013-11-25
 */
public class CheckLoginAction extends ActionSupport {
	private static final long serialVersionUID = -2937613592559859535L;

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * 检查用户是否登录系统
	 */
	public String execute() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(session.get("username") == null)
			login = "N";
		else
			login = "Y";
		return SUCCESS;
	}
	private String login;
}
