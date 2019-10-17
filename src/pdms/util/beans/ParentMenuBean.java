package pdms.util.beans;
import java.io.Serializable;
/**
 * 父菜单BEAN
 * @author 程国前
 * @time 2013-11-25
 */
public class ParentMenuBean implements Serializable{
	private static final long serialVersionUID = -6253013082903267600L;
	private int id;
	private String name;
	
	public ParentMenuBean(){}
	
	public ParentMenuBean(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
