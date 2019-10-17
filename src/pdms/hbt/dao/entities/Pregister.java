package pdms.hbt.dao.entities;

import java.sql.Timestamp;

public class Pregister {
	
	/**
	 * 档案信息 entity 
	 * @author 董海朋
	 * @time 2018-04-27
	 */
	
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public int getPoliticsStatus() {
		return politicsStatus;
	}
	public void setPoliticsStatus(int politicsStatus) {
		this.politicsStatus = politicsStatus;
	}
	public Timestamp getJiDt() {
		return jiDt;
	}
	public void setJiDt(Timestamp jiDt) {
		this.jiDt = jiDt;
	}
	public Timestamp getReDt() {
		return reDt;
	}
	public void setReDt(Timestamp reDt) {
		this.reDt = reDt;
	}
	public int getCulture() {
		return culture;
	}
	public void setCulture(int culture) {
		this.culture = culture;
	}
	public int getCombatant() {
		return combatant;
	}
	public void setCombatant(int combatant) {
		this.combatant = combatant;
	}
	public int getNonWar() {
		return nonWar;
	}
	public void setNonWar(int nonWar) {
		this.nonWar = nonWar;
	}
	public int getBody() {
		return body;
	}
	public void setBody(int body) {
		this.body = body;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public int getTroopsType() {
		return troopsType;
	}
	public void setTroopsType(int troopsType) {
		this.troopsType = troopsType;
	}
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getJiSite() {
		return jiSite;
	}
	public void setJiSite(String jiSite) {
		this.jiSite = jiSite;
	}
	public String getRp() {
		return rp;
	}
	public void setRp(String rp) {
		this.rp = rp;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public int getSts() {
		return sts;
	}
	public void setSts(int sts) {
		this.sts = sts;
	}
	public Timestamp getCrDt() {
		return crDt;
	}
	public void setCrDt(Timestamp crDt) {
		this.crDt = crDt;
	}
	public Timestamp getUptDt() {
		return uptDt;
	}
	public void setUptDt(Timestamp uptDt) {
		this.uptDt = uptDt;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	private String code;			    //id
	private String pname;				//姓名
	private int sex;					//性别
	private Timestamp birthday;			//出生年月日
	private String nation;				//民族
	private int politicsStatus;		//政治面貌
	private Timestamp jiDt;			//入伍日期
	private Timestamp reDt;		//退伍日期
	private String filePath;			//附件
	private int culture;				//文化程度
	private int combatant;				//参战情况
	private int nonWar;					//参加非战争军事行动
	private int body;					//身体状况
	private String organization;		//原单位
	private String pno;					//档案编号
	private int troopsType;			//军种
	private String docNum;				//身份证号码
	private String address;				//联系方式
	private String remark1;				//备注1
	private String jiSite;			//入伍地
	private String rp;					//奖罚情况
	private String remark2;				//备注2
	private int sts;					//状态
	private Timestamp crDt;			//创建日期
	private Timestamp uptDt;			//修改日期
}
