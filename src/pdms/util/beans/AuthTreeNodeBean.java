package pdms.util.beans;
import java.io.Serializable;
import java.util.List;
/**
 * 用于编辑角色权限的树节点
 * @author 程国前
 * 2013-11-26
 */
public class AuthTreeNodeBean implements Serializable{

	public AuthTreeNodeBean(){}
	
	public AuthTreeNodeBean(int id, String text, String qtip, String url, boolean leaf, int parentId){
		this.id = id;
		this.text = text;
		this.qtip = qtip;
		this.url = url;
		this.leaf = leaf;
		this.parentId = parentId;
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
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public List<AuthTreeNodeBean> getChildren() {
		return children;
	}

	public void setChildren(List<AuthTreeNodeBean> children) {
		this.children = children;
	}
	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	private int id;							//id
	private String text;					//节点名称
	private String qtip;					//节点提示
	private String url;						//链接地址URLַ
	private boolean leaf;					//是否是子节点
	private int parentId;					//父节点ID
	private boolean enabled;				//是否使用
	private List<AuthTreeNodeBean> children;	//子节点
	private boolean checked;				//是否选中
	private static final long serialVersionUID = -7913272925490872670L;
}
