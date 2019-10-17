package pdms.action.register;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import pdms.hbt.dao.entities.Pregister;
import pdms.hbt.dao.util.OperationUtil;
import pdms.hbt.dao.entities.Userinfo;
import pdms.hbt.dao.util.QueryUtil;
import pdms.util.tools.UtilTool;

public class PersonInfoControllerAction extends ActionSupport implements
		Serializable {
	
	
	/**
	 * 
	 */
	
	public String saveOrUpdate(){
		this.success = true;
		Map<String, Object> s = ActionContext.getContext().getSession();
		if(s.get("ui") == null){
			this.result = "当前账号不能创建或更新军人档案!";
		}else{
			Pregister pr = null;
			Userinfo ui = (Userinfo)s.get("ui");
			String hql = "From Pregister where code = '" + code + "'" ;
			List<Object> prs = QueryUtil.getObjectByHQLString(hql);	
			boolean add = (prs != null && prs.size() == 1) ? false : true;
			if(add){
				logger.info("新增VTB信息,新增人员:"+ui.getUserId()+",新增时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pr = new Pregister();
			}else{
				logger.info("更新VTB("+code+")信息,更新人员:"+ui.getUserId()+",更新时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				pr = (Pregister)prs.get(0);
			}
			
			pr.setPname(pname);
			pr.setSex(sex);
			pr.setBirthday(birthday);
			pr.setNation(nation);
			pr.setPoliticsStatus(politicsStatus);
			pr.setJiDt(jiDt);
			pr.setReDt(reDt);
			pr.setFilePath(filePath);
			pr.setCulture(culture);
			pr.setCombatant(combatant);
			pr.setNonWar(nonWar);
			pr.setBody(body);
			pr.setOrganization(organization);
			pr.setPno(pno);
			pr.setTroopsType(troopsType);
			pr.setDocNum(docNum);
			pr.setAddress(address);
			pr.setRemark1(remark1);
			pr.setJiSite(jiSite);
			pr.setRp(rp);
			pr.setRemark2(remark2);
			
			if(add){
				code = UtilTool.generatePersonNo();
				pr.setCode(code);
				pr.setSts(2);
				pr.setCrDt(new Timestamp(new  Date().getTime()));
				pr.setUptDt(new Timestamp(new  Date().getTime()));
				OperationUtil.add(pr);
			}else {
				pr.setUptDt(new Timestamp(new  Date().getTime()));
				OperationUtil.update(pr);
			}
			this.result = "ok";
		}
		
		return SUCCESS;
	}
	//提交审核
	public String submit(){
		this.result = "ok";
		String hql = "From Pregister where code = '" + code  + "'";
		List<Object> prs = QueryUtil.getObjectByHQLString(hql);	
		Pregister pr = (Pregister)prs.get(0);
		pr.setSts(3);
		OperationUtil.update(pr);
		
		return SUCCESS;
	}
	//撤销提交
	public String undo(){
		this.result = "ok";
		String hql = "From Pregister where code = '" + code + "'" ;
		List<Object> prs = QueryUtil.getObjectByHQLString(hql);	
		Pregister pr = (Pregister)prs.get(0);
		pr.setSts(2);
		OperationUtil.update(pr);
		
		return SUCCESS;
	}
	//删除档案信息
	public String del(){
		String[] codes = code.split(",");
		for(int i = 0; i < codes.length; i++){
			OperationUtil.deleteObject(QueryUtil.getObjectById(Pregister.class, codes[i]));
		}
		return SUCCESS;
	}
	//获取Pinfo信息
	public String getPR(){
		this.get_result = "ok";
		String hql = "From Pregister where code = '" + code + "'";
		List<Object> prs = QueryUtil.getObjectByHQLString(hql);	
		pinfo = (Pregister)prs.get(0);
		return SUCCESS;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getGet_result() {
		return get_result;
	}
	public void setGet_result(String get_result) {
		this.get_result = get_result;
	}
	public Pregister getPinfo() {
		return pinfo;
	}
	public void setPinfo(Pregister pinfo) {
		this.pinfo = pinfo;
	}
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	private boolean success;
	private String result;
	private String get_result;
	private Pregister pinfo;
	private String code;
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
	private static final long serialVersionUID = 612501448144531226L;
	protected static final Logger logger = Logger.getLogger(PersonInfoControllerAction.class);
	
	
}
