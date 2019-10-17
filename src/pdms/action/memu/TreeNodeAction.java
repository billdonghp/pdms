package pdms.action.memu;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import pdms.hbt.dao.entities.Rmtb;
import pdms.hbt.dao.entities.Sysmenu;
import pdms.hbt.dao.entities.Userinfo;
import pdms.hbt.dao.menu.SysmenuDao;
import pdms.hbt.dao.util.QueryUtil;
import pdms.util.beans.MenuTreeNode;
/**
 * 获取系统菜单
 * @author 董海朋
 * 2018-04-27
 */
public class TreeNodeAction extends ActionSupport {
	private static final long serialVersionUID = 2121333611419715019L;
	private List<MenuTreeNode> list = new ArrayList<MenuTreeNode>();
	private HttpServletRequest request = ServletActionContext.getRequest();

	public List<MenuTreeNode> getList() {
		return list;
	}

	public void setList(List<MenuTreeNode> list) {
		this.list = list;
	}
	/**
	 * 执行获取系统菜单
	 */
	public String execute() throws Exception{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		Map<String, Object> session = ActionContext.getContext().getSession();
		SysmenuDao sd = new SysmenuDao();
		List<Sysmenu> ctemp = null;
		if(session.get("ui") == null){//超级管理员
			List<Sysmenu> parents = sd.getAllParent();//获取所有的父菜单
			if(parents != null && parents.size() != 0){
				for(Sysmenu sp : parents){
					MenuTreeNode pn = new MenuTreeNode(sp.getId(), sp.getText(),sp.getQtip(), "", false, sp.getParentId());
					pn.setEnabled(sp.getEnabled()==1?true:false);
					ctemp = sd.getAllChildren(sp.getId());//获取所有的子菜单
					if(ctemp != null && ctemp.size() != 0){
						List<MenuTreeNode> btemp = new ArrayList<MenuTreeNode>();
						for(Sysmenu sc : ctemp){
							MenuTreeNode cn = new MenuTreeNode(sc.getId(),sc.getText(), sc.getQtip(),basePath+sc.getUrl(),true,sc.getParentId());
							btemp.add(cn);
						}
						pn.setChildren(btemp);
					}else{
						pn.setLeaf(true);
					}
					list.add(pn);
				}
			}
		}else{//根据用户所属角色获取系统菜单
			Userinfo ui = (Userinfo)session.get("ui");
			List<Object> lr = QueryUtil.getObjectByCondition(Rmtb.class, "rid", ui.getRoleId());
			if(lr != null && lr.size() != 0){
				//放入所有的子节点用以判断是否属于该角色
				List<Integer> cl = new ArrayList<Integer>();
				for(Object o : lr){
					Rmtb rm = (Rmtb)o;
					Sysmenu sm = (Sysmenu)QueryUtil.getObjectById(Sysmenu.class, rm.getMid());
					if(sm != null)
						if(sm.getParentId() != 0)
							cl.add(rm.getMid());
				}
				//执行菜单加载
				for(Object o : lr){
					Rmtb rm = (Rmtb)o;
					Sysmenu sm = (Sysmenu)QueryUtil.getObjectById(Sysmenu.class, rm.getMid());
					if(sm != null){
						if(sm.getParentId() == 0 && sm.getEnabled() == 1){//加载父节点
							MenuTreeNode pn = new MenuTreeNode(sm.getId(), sm.getText(), sm.getQtip(), "", false, sm.getParentId());
							pn.setEnabled(sm.getEnabled()==1?true:false);
							ctemp = sd.getAllChildren(sm.getId());//获取所有的子菜单
							if(ctemp != null && ctemp.size() != 0){
								List<MenuTreeNode> btemp = new ArrayList<MenuTreeNode>();
								for(Sysmenu sc : ctemp){
									if(cl.contains(sc.getId())){//是否属于此角色的子菜单?
										MenuTreeNode cn = new MenuTreeNode(sc.getId(),sc.getText(), sc.getQtip(),basePath+sc.getUrl(),true,sc.getParentId());
										btemp.add(cn);
									}
								}
								pn.setChildren(btemp);
							}else{
								pn.setLeaf(true);
							}
							list.add(pn);
						}
					}
				}
			}
		}
		return SUCCESS;
	}
}
