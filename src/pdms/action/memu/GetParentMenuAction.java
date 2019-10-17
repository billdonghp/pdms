package pdms.action.memu;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import pdms.hbt.dao.entities.Sysmenu;
import pdms.hbt.dao.menu.SysmenuDao;
import pdms.util.beans.ParentMenuBean;
/**
 * 获取所有的父菜单
 * @author 程国前
 * 2013-11-25
 */
public class GetParentMenuAction extends ActionSupport {
	private static final long serialVersionUID = -6125865890175806183L;
	
	public List<ParentMenuBean> getRoot() {
		return root;
	}

	public void setRoot(List<ParentMenuBean> root) {
		this.root = root;
	}

	/**
	 * 获取所有的父菜单节点
	 */
	public String execute() throws Exception{
		List<Sysmenu> list = new SysmenuDao().getAllParent();
		if(list != null && list.size() != 0)
			for(Sysmenu s : list)
				root.add(new ParentMenuBean(s.getId(),getText(s.getText())));
		return SUCCESS;
	}
	private List<ParentMenuBean> root = new ArrayList<ParentMenuBean>();
}
