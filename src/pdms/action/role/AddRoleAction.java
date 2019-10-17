package pdms.action.role;
import com.opensymphony.xwork2.ActionSupport;
import pdms.hbt.dao.entities.Rmtb;
import pdms.hbt.dao.entities.Sysrole;
import pdms.hbt.dao.util.OperationUtil;
import pdms.hbt.dao.util.QueryUtil;
/**
 * 新增系统角色
 * @author 程国前
 * 2013-11-26
 */
public class AddRoleAction extends ActionSupport {
	private static final long serialVersionUID = 3082296023791511889L;	
	/**
	 * 执行添加系统角色
	 */
	public String execute() throws Exception{
		Sysrole sr = null;
		int menuId = 0;
		if(sign == 0){//新增角色
			if(QueryUtil.getObjectById(Sysrole.class, id) != null)
				return SUCCESS;
			sr = new Sysrole(id.trim(), rolenm.trim(), rolerm.trim());
			OperationUtil.add(sr);
			String[] ids = sltNodes.split(",");
			for(String s : ids){
				try{
					menuId = Integer.parseInt(s);
					Rmtb rm = new Rmtb(id, menuId);
					OperationUtil.add(rm);
				}catch(Exception e){
					System.out.println("Only Parent Node, don't add it.\t" + e);
				}
			}
		}else{//修改角色
			sr = (Sysrole)QueryUtil.getObjectById(Sysrole.class, id);
			sr.setRolenm(rolenm.trim());
			sr.setRolerm(rolerm.trim());
			OperationUtil.update(sr);//更新
			//先删除之前的菜单映射数据,再新增
			OperationUtil.deleteObject(QueryUtil.getObjectByCondition(Rmtb.class, "rid", id));
			//更新菜单映射数据
			String[] ids = sltNodes.split(",");
			for(String s : ids){
				try{
					menuId = Integer.parseInt(s);
					Rmtb rm = new Rmtb(id, menuId);
					OperationUtil.add(rm);
				}catch(Exception e){
					System.out.println("Only Parent Node, don't add it.\t" + e);
				}
			}
		}
		this.success = true;
		return SUCCESS;
	}
	public String getSltNodes() {
		return sltNodes;
	}
	public void setSltNodes(String sltNodes) {
		this.sltNodes = sltNodes;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRolenm() {
		return rolenm;
	}
	public void setRolenm(String rolenm) {
		this.rolenm = rolenm;
	}
	public String getRolerm() {
		return rolerm;
	}
	public void setRolerm(String rolerm) {
		this.rolerm = rolerm;
	}
	private String id ;			//角色ID
	private String rolenm;		//角色名称
	private String rolerm;		//备注
	private String sltNodes;	//对应权限菜单的ID
	private boolean success;	//标识表单是否提交成功
	private int sign;			//标识动作 新增还是修改
}
