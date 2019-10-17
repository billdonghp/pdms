package pdms.hbt.dao.entities;
import java.sql.Timestamp;
/**
 * 登录历史表 Entity
 * @author 程国前
 * @time 2013-11-22
 */
public class Loginhistory implements java.io.Serializable {

	/** default constructor */
	public Loginhistory() {
		
	}

	/** full constructor */
	public Loginhistory(String loginid, String loginnm, Timestamp logindt) {
		this.loginid = loginid;
		this.loginnm = loginnm;
		this.logindt = logindt;
	}
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginid() {
		return this.loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getLoginnm() {
		return this.loginnm;
	}

	public void setLoginnm(String loginnm) {
		this.loginnm = loginnm;
	}

	public Timestamp getLogindt() {
		return this.logindt;
	}

	public void setLogindt(Timestamp logindt) {
		this.logindt = logindt;
	}
	private Integer id;			//ID
	private String loginid;		//登录账号
	private String loginnm;		//登录者姓名
	private Timestamp logindt;	//登录日期
	private static final long serialVersionUID = 4267506867585198387L;
}
