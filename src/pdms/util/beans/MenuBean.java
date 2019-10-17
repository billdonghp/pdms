package pdms.util.beans;
import java.io.Serializable;
/**
 * 菜单Bean
 * @author 程国前
 * 2013-11-25
 */
public class MenuBean implements Serializable {
	private static final long serialVersionUID = 3469886990217453374L;
	public MenuBean(){}
	public MenuBean(int id, String text, String qtip, String url){
		this.id = id;	
		this.text = text;
		this.qtip = qtip;
		this.url = url;
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
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getPmName() {
		return pmName;
	}
	public void setPmName(String pmName) {
		this.pmName = pmName;
	}
	public String getBeUsed() {
		return beUsed;
	}
	public void setBeUsed(String beUsed) {
		this.beUsed = beUsed;
	}
	private int id;			//ID
	private String text;	//菜单名称
	private String qtip;	//提示信息
	private String url;		//链接地址
	private String isLeaf;	//是否是子菜单
	private String pmName;	//父菜单名称
	private String beUsed;	//是否在用
}
