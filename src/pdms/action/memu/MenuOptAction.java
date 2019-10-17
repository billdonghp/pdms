package pdms.action.memu;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import pdms.hbt.dao.entities.Rmtb;
import pdms.hbt.dao.entities.Sysmenu;
import pdms.hbt.dao.menu.SysmenuDao;
import pdms.hbt.dao.util.OperationUtil;
import pdms.hbt.dao.util.QueryUtil;
/**
 * 菜单操作（增、删、改）
 * @author 程国前
 * 2013-11-25
 */
public class MenuOptAction extends ActionSupport {
	private static final long serialVersionUID = -1660052256529712413L;
	/**
	 * 新增系统菜单
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		Sysmenu menu = new Sysmenu();
		menu.setText(text.trim());
		menu.setQtip(qtip.trim());
		menu.setUrl(url.trim());
		menu.setEnabled(enabled);
		menu.setParentId(parentId==null||"".equals(parentId)?0:Integer.parseInt(parentId));
		menu.setLeaf(leaf);
		OperationUtil.add(menu);
		this.success = true;
		return SUCCESS;
	}
	/**
	 * 更新系统菜单
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception{
		Sysmenu menu = sd.getSysmenuById(id);
		if(!"".equals(text.trim()))
			menu.setText(text.trim());
		if(!"".equals(qtip.trim()))
			menu.setQtip(qtip.trim());
		if(url != null)
			menu.setUrl(url.trim());
		if(parentId != null && !"".equals(parentId))
			menu.setParentId(Integer.parseInt(parentId));
		menu.setEnabled(enabled);
		OperationUtil.update(menu);
		this.success = true;
		return SUCCESS;
	}
	/**
	 * 删除系统菜单
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		String[] mids = ids.split(",");
		for(String s : mids){
			Sysmenu psm = sd.getSysmenuById(Integer.parseInt(s));
			if(psm != null){
				List<Sysmenu> cl = sd.getAllChildren(psm.getId());
				//如果有子菜单,先删除
				if(cl != null && cl.size() != 0){
					for(Sysmenu sm : cl){
						OperationUtil.deleteObject(QueryUtil.getObjectByCondition(Rmtb.class, "mid", sm.getId()));
						sd.deleteMenu(sm);
					}
				}
				//删除菜单
				OperationUtil.deleteObject(QueryUtil.getObjectByCondition(Rmtb.class, "mid", psm.getId()));
				sd.deleteMenu(psm);
			}
		}
		return SUCCESS;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getQtip() {
		return qtip;
	}
	public void setQtip(String qtip) {
		this.qtip = qtip;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLeaf() {
		return leaf;
	}

	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	private boolean success;
	private int id;
	private String text;
	private String qtip;
	private String url;
	private int leaf;
	private String parentId;
	private int enabled;
	private String ids;
	private SysmenuDao sd = new SysmenuDao();
}
