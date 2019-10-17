package pdms.util.beans;

import java.io.Serializable;

public class PersonInfo implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -5871177938030493954L;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getPoliticsStatus() {
		return politicsStatus;
	}
	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}
	
	public String getJiDt() {
		return jiDt;
	}
	public void setJiDt(String jiDt) {
		this.jiDt = jiDt;
	}
	public String getReDt() {
		return reDt;
	}
	public void setReDt(String reDt) {
		this.reDt = reDt;
	}
	public String getCrDt() {
		return crDt;
	}
	public void setCrDt(String crDt) {
		this.crDt = crDt;
	}
	public String getCulture() {
		return culture;
	}
	public void setCulture(String culture) {
		this.culture = culture;
	}
	public String getSts() {
		return sts;
	}
	public void setSts(String sts) {
		this.sts = sts;
	}
	public String getUptDt() {
		return uptDt;
	}
	public void setUptDt(String uptDt) {
		this.uptDt = uptDt;
	}
	private String code;			    //id
	private String pname;				//姓名
	private String sex;					//性别
	private String birthday;			//出生年月日
	private String nation;				//民族
	private String politicsStatus;		//政治面貌
	private String jiDt;			//入伍日期
	private String reDt;		//退伍日期
	private String culture;				//文化程度
	private String sts;					//状态
	private String crDt;			//创建日期
	private String uptDt;			//修改日期
}
