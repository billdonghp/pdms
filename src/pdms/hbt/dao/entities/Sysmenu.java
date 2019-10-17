package pdms.hbt.dao.entities;
/**
 * 系统菜单 entity
 * @author 董海朋
 * @time 2018-04-28
 */
public class Sysmenu implements java.io.Serializable {

	// Constructors
	
	/** default constructor */
	public Sysmenu() {
	}

	/** minimal constructor */
	public Sysmenu(String text, String qtip, String url, Integer leaf,
			Integer parentId, Integer enabled) {
		this.text = text;
		this.qtip = qtip;
		this.url = url;
		this.leaf = leaf;
		this.parentId = parentId;
		this.enabled = enabled;
	}

	/** full constructor */
	public Sysmenu(String text, String qtip, String url, Integer leaf,
			Integer parentId, Integer enabled, String str1, String str2,
			String str3) {
		this.text = text;
		this.qtip = qtip;
		this.url = url;
		this.leaf = leaf;
		this.parentId = parentId;
		this.enabled = enabled;
		this.str1 = str1;
		this.str2 = str2;
		this.str3 = str3;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}
	private static final long serialVersionUID = -380474100353781053L;
	private Integer id;			//ID
	private String text;		//菜单名称
	private String qtip;		//提示信息
	private String url;			//链接URL地址	
	private Integer leaf;		//是否是叶子节点
	private Integer parentId;	//父节点ID
	private Integer enabled;	//是否在用
	private String str1;		//备用字段一
	private String str2;		//备用字段二
	private String str3;		//备用字段三
}
