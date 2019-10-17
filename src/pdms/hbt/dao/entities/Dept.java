package pdms.hbt.dao.entities;
import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 部门信息表Entity
 * @author 董海朋
 * @time 2018-04-28
 */
public class Dept implements Serializable {
	
	public Dept(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getDeptshortnm() {
		return deptshortnm;
	}
	public void setDeptshortnm(String deptshortnm) {
		this.deptshortnm = deptshortnm;
	}
	public String getDeptfullnm() {
		return deptfullnm;
	}
	public void setDeptfullnm(String deptfullnm) {
		this.deptfullnm = deptfullnm;
	}
	public String getUpdeptcd() {
		return updeptcd;
	}
	public void setUpdeptcd(String updeptcd) {
		this.updeptcd = updeptcd;
	}
	public Timestamp getRegdt() {
		return regdt;
	}
	public void setRegdt(Timestamp regdt) {
		this.regdt = regdt;
	}
	public String getDept_level() {
		return dept_level;
	}
	public void setDept_level(String dept_level) {
		this.dept_level = dept_level;
	}



	private static final long serialVersionUID = 1702067462045643346L;
	private Integer id;			//主键
	private String deptcd;		//部门代码
	private String deptnm;		//部门名称
	private String deptshortnm;	//部门简称
	private String deptfullnm;	//部门全称
	private String updeptcd;	//上级部门代码
	private Timestamp regdt;	//系统登记日期
	private String dept_level;	//部门等级
}
