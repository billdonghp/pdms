package pdms.hbt.dao.entities;

/**
 * 系统角色表 entity
 * @author 程国前
 * 2013-11-25
 */
public class Sysrole implements java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = 213344143083776099L;

	/** default constructor */
	public Sysrole() {
	}

	/** minimal constructor */
	public Sysrole(String id, String rolenm) {
		this.id = id;
		this.rolenm = rolenm;
	}

	/** full constructor */
	public Sysrole(String id, String rolenm, String rolerm) {
		this.id = id;
		this.rolenm = rolenm;
		this.rolerm = rolerm;
	}
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRolenm() {
		return this.rolenm;
	}

	public void setRolenm(String rolenm) {
		this.rolenm = rolenm;
	}

	public String getRolerm() {
		return this.rolerm;
	}

	public void setRolerm(String rolerm) {
		this.rolerm = rolerm;
	}

	private String id;
	private String rolenm;
	private String rolerm;
}
