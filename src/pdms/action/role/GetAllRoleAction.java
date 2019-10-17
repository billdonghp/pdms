package pdms.action.role;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import pdms.hbt.dao.entities.Sysrole;
import pdms.hbt.dao.util.QueryUtil;
/**
 * 获取系统角色列表
 * @author 程国前
 * 2013-11-26
 */
public class GetAllRoleAction extends ActionSupport {
	private static final long serialVersionUID = 4657163061700985161L;
	
	public List<Sysrole> getRoot() {
		return root;
	}
	public void setRoot(List<Sysrole> root) {
		this.root = root;
	}
	public String execute() throws Exception{
		List<Object> list = QueryUtil.getAll(Sysrole.class);
		if(list != null && list.size() != 0){
			for(Object o : list)
				root.add((Sysrole)o);
		}
		return SUCCESS;
	}
	List<Sysrole> root = new ArrayList<Sysrole>();
}
