package pdms.hbt.dao.entities;

/**
 * 系统角色菜单映射表 entity
 * @author 董海朋
 * 2018-04-28
 */
public class Rmtb implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Rmtb() {
	}

	/** full constructor */
	public Rmtb(String rid, Integer mid) {
		this.rid = rid;
		this.mid = mid;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRid() {
		return this.rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}
	private Integer id;
	private String rid;
	private Integer mid;
	private static final long serialVersionUID = 309693081321133737L;
}
