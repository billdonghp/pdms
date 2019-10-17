package pdms.action.role;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import pdms.hbt.dao.entities.Rmtb;
import pdms.hbt.dao.entities.Sysmenu;
import pdms.hbt.dao.menu.SysmenuDao;
import pdms.hbt.dao.util.QueryUtil;
import pdms.util.beans.AuthTreeNodeBean;
/**
 * 获取角色所控制的菜单
 * @author 程国前
 *	2013-11-26
 */
public class AuthTreeEditAction extends ActionSupport {
	private static final long serialVersionUID = -8588786891673104267L;
	
	private List<AuthTreeNodeBean> list = new ArrayList<AuthTreeNodeBean>();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private String roleId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<AuthTreeNodeBean> getList() {
		return list;
	}

	public void setList(List<AuthTreeNodeBean> list) {
		this.list = list;
	}
	/**
	 * 执行获取系统菜单
	 */
	public String execute() throws Exception{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		SysmenuDao sd = new SysmenuDao();
		List<Sysmenu> parents = sd.getAllParent();//获取所有的父菜单
		List<Sysmenu> ctemp = null;
		if(parents != null && parents.size() != 0){
			List<Integer> tl = new ArrayList<Integer>();
			List<Object> qr = new ArrayList<Object>();
			if(roleId != null)
				qr = QueryUtil.getObjectByCondition(Rmtb.class, "rid", roleId);
			for(Object o : qr)
				if(o != null)
					tl.add(((Rmtb)o).getMid());
			for(Sysmenu sp : parents){
				AuthTreeNodeBean pn = new AuthTreeNodeBean(sp.getId(), getText(sp.getText()), getText(sp.getQtip()), "", false, sp.getParentId());
				if(tl.contains(sp.getId()))
					pn.setChecked(true);
				pn.setEnabled(sp.getEnabled()==1?true:false);
				//pn.setChecked(true);
				ctemp = sd.getAllChildren(sp.getId());//获取所有的子菜单
				if(ctemp != null && ctemp.size() != 0){
					List<AuthTreeNodeBean> btemp = new ArrayList<AuthTreeNodeBean>();
					for(Sysmenu sc : ctemp){
						AuthTreeNodeBean cn = new AuthTreeNodeBean(sc.getId(),getText(sc.getText()), getText(sc.getQtip()),basePath+sc.getUrl(),true,sc.getParentId());
						if(tl.contains(sc.getId()))
							cn.setChecked(true);
						btemp.add(cn);
					}
					pn.setChildren(btemp);
				}else{
					pn.setLeaf(true);
				}
				list.add(pn);
			}
		}
		return SUCCESS;
	}
}
