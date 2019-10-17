package pdms.action.login;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 登出系统
 * @author 董海朋
 * 2018-04-28
 */
public class LogoutAction extends ActionSupport {
	private static final long serialVersionUID = -429503518118159405L;
	/**
	 * 执行登出
	 */
	public String execute() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("username");
		session.remove("usertmnm");
		session.remove("userdeptcd");
		session.remove("userdeptnm");
		session.remove("ui");
		session.clear();
		return SUCCESS;
	}
}
