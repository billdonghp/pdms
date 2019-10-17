package pdms.hbt.dao.entities;
import java.sql.Timestamp;
/**
 * 法人信息 entity
 * @author 程国前
 * @time 2013-11-18
 */
public class Bginfo implements java.io.Serializable {
	// Constructors
	/** default constructor */
	public Bginfo() {
	}

	/** full constructor */
	public Bginfo(String bgcd, String bgennm, String bgzhnm, Timestamp createdt) {
		this.bgcd = bgcd;
		this.bgennm = bgennm;
		this.bgzhnm = bgzhnm;
		this.createdt = createdt;
	}
	public Integer getBgid() {
		return bgid;
	}

	public void setBgid(Integer bgid) {
		this.bgid = bgid;
	}

	public String getBgcd() {
		return bgcd;
	}

	public void setBgcd(String bgcd) {
		this.bgcd = bgcd;
	}

	public String getBgennm() {
		return bgennm;
	}

	public void setBgennm(String bgennm) {
		this.bgennm = bgennm;
	}

	public String getBgzhnm() {
		return bgzhnm;
	}

	public void setBgzhnm(String bgzhnm) {
		this.bgzhnm = bgzhnm;
	}

	public Timestamp getCreatedt() {
		return createdt;
	}

	public void setCreatedt(Timestamp createdt) {
		this.createdt = createdt;
	}
	private static final long serialVersionUID = 7651603711748788287L;
	private Integer bgid;			//ID
	private String bgcd;			//法人代码
	private String bgennm;			//法人英文名称
	private String bgzhnm;			//法人中文名称
	private Timestamp createdt;		//创建时间
}
