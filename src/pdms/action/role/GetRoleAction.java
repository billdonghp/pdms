package pdms.action.role;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import pdms.hbt.dao.entities.Sysrole;
import pdms.hbt.dao.util.QueryUtil;
import pdms.util.beans.RoleBean;
/**
 * 获取所有的系统角色(用于下拉列表)
 * @author 程国前
 * 2013-11-26
 */
public class GetRoleAction extends ActionSupport {
	private static final long serialVersionUID = -3118854860539977839L;
	private List<RoleBean> root = new ArrayList<RoleBean>();
	
	public List<RoleBean> getRoot() {
		return root;
	}

	public void setRoot(List<RoleBean> root) {
		this.root = root;
	}

	/**
	 * 获取所有的父菜单节点
	 */
	public String execute() throws Exception{
		List<Object> list = QueryUtil.getAll(Sysrole.class);
		for(Object o : list){
			Sysrole rs = (Sysrole)o;
			root.add(new RoleBean(rs.getId(), rs.getRolenm()));
		}
		return SUCCESS;
	}
}
