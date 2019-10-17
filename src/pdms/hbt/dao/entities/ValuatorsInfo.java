package pdms.hbt.dao.entities;
import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 评价者信息 entity
 * @author 董海朋
 * 2018-04-27
 */ 
public class ValuatorsInfo implements Serializable {
	
	public ValuatorsInfo(){
		
	}
	
	public ValuatorsInfo(String bg,String userid, String usernm, String deptcd,
			String deptnm, String register, Timestamp retdt) {
		super();
		this.bg = bg;
		this.userid = userid;
		this.usernm = usernm;
		this.deptcd = deptcd;
		this.deptnm = deptnm;
		this.register = register;
		this.retdt = retdt;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsernm() {
		return usernm;
	}
	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
	public String getDeptcd() {
		return deptcd;
	}
	public void setDeptcd(String deptcd) {
		this.deptcd = deptcd;
	}
	public String getDeptnm() {
		return deptnm;
	}
	public void setDeptnm(String deptnm) {
		this.deptnm = deptnm;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public Timestamp getRetdt() {
		return retdt;
	}
	public void setRetdt(Timestamp retdt) {
		this.retdt = retdt;
	}
	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Timestamp getUptdt() {
		return uptdt;
	}

	public void setUptdt(Timestamp uptdt) {
		this.uptdt = uptdt;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDptNm() {
		return dptNm;
	}

	public void setDptNm(String dptNm) {
		this.dptNm = dptNm;
	}
	public String getUdeptCd() {
		return udeptCd;
	}

	public void setUdeptCd(String udeptCd) {
		this.udeptCd = udeptCd;
	}
	public String getUdeptNm() {
		return udeptNm;
	}

	public void setUdeptNm(String udeptNm) {
		this.udeptNm = udeptNm;
	}
	private Integer id;				//ID(自增)
	private String bg;				//法人所属
	private String code;			//被评价者部门代码
	private String dptNm;			//被评价者部门名称
	private Integer level;			//评审级别
	private String userid;			//评价者代码
	private String usernm;			//评价者姓名
	private String deptcd;			//评价者部门代码
	private String deptnm;			//评价者部门名称
	private String udeptCd;			//评价者所属部-部门代码
	private String udeptNm;			//评价者所属部-部门名称
	private String register;		//创建者
	private Timestamp retdt;		//创建日期
	private String updator;			//更新人员
	private Timestamp uptdt;		//更新日期
	private Integer status;			//系统使用状态 1:表示使用 0：表示停用
	private static final long serialVersionUID = 520401055388449079L;
}
