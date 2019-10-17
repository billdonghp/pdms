package pdms.util.beans;
import java.io.Serializable;
/**
 * 角色Bean
 * @author 程国前
 * 2013-11-26
 */
public class RoleBean implements Serializable {
	private static final long serialVersionUID = -4197647092469374896L;
	private String id;
	private String name;
	
	public RoleBean(){}
	
	public RoleBean(String id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
