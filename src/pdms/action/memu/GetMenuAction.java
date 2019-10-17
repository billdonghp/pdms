package pdms.action.memu;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import pdms.hbt.dao.entities.Sysmenu;
import pdms.hbt.dao.util.QueryUtil;
import pdms.util.beans.MenuBean;
/**
 * 获取所有的系统菜单
 * @author 程国前
 * 2013-11-25
 */
public class GetMenuAction extends ActionSupport {
	private static final long serialVersionUID = -3790333308901835120L;
	
	public List<MenuBean> getRoot() {
		return root;
	}
	public void setRoot(List<MenuBean> root) {
		this.root = root;
	}
	public String execute() throws Exception{
		List<Object> list = QueryUtil.getAll(Sysmenu.class);
		if(list != null && list.size() != 0){
			for(Object o : list){
				Sysmenu s = (Sysmenu)o;//菜单
				Sysmenu p = (Sysmenu)QueryUtil.getObjectById(Sysmenu.class, s.getParentId());//父菜单
				MenuBean bean = new MenuBean(s.getId(), getText(s.getText()), getText(s.getQtip()), s.getUrl());
				bean.setIsLeaf(s.getLeaf()==1?"否":"是");
				bean.setPmName(p==null?"":getText(p.getText()));
				bean.setBeUsed(s.getEnabled()==1?"是":"否");
				root.add(bean);
			}
		}
		return SUCCESS;
	}
	private List<MenuBean> root = new ArrayList<MenuBean>();
}
