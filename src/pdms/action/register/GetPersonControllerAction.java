package pdms.action.register;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import pdms.util.beans.PersonInfo;
import pdms.hbt.dao.util.QueryUtil;
/**
 * 获取档案信息
 * @author 董海朋
 * 2018-04-30
 */
public class GetPersonControllerAction extends ActionSupport implements Serializable {

	/**
	 * 执行查询
	 */
	public String execute() throws Exception{
		String sql = "select "
				+ "a.code code,"
				+ "a.pname pname,"
				+ "a.sex sex,"
				+ "a.birthday birthday,"
				+ "a.nation nation,"
				+ "b.comview politicsStatus,"
				+ "c.comview culture,"
				+ "a.jiDt as jiDt,"
				+ "a.reDt as reDt, "
				+ "a.sts sts, "
				+ "a.crDt as crDt,"
				+ "a.uptDt as  uptDt "
				+ "from Pregister a "
				+ "left join COMBOXVALUES b "
				+ "on a.politicsStatus = b.comvalue "
				+ "and b.code = 'PLTS' "
				+ "left join COMBOXVALUES c "
				+ "on a.culture = c.comvalue "
				+ "and c.code = 'CULTURE' "
				+ "where 1=1 ";
		 sql +=  ((sts == null || "".equals(sts))?"":" and a.sts in ("+sts+")")+
				((code == null || "".equals(code))?"":" and a.code like '%"+code+"%' ")+
				((pname == null || "".equals(pname))?"":" and a.pname like '%" +pname+"%' ")+
				((docNum ==null || "".equals(docNum)) ?"":" and a.docNum like '%"+docNum+"%' ")+
				((pno==null || "".equals(pno)) ?"":" and a.pno like '%"+pno+"%' ")+
				((troopsType==null || "".equals(troopsType))?"":" and a.troopsType = '"+troopsType+"' ")+
				((sex ==null || "0".equals(sex))?"":" and a.sex = '"+sex+"' ")+
				((jiSite ==null || "".equals(jiSite)) ?"":" and a.jiSite = '"+jiSite+"' ")+
				" order by sts ";
		System.out.println("SQL String is : " + sql);
		List<Object> list = QueryUtil.getObjectBySQLString(sql);		
		if(list != null && list.size() != 0){
			this.totalProperty = list.size();
			int end = (start+limit)>this.totalProperty?this.totalProperty:start+limit;
			for(int i = start; i < end; i++){
				try{
					Object[] o = (Object[]) list.get(i);
					PersonInfo pinfo = new PersonInfo();
					pinfo.setCode(o[0]==null?"":o[0].toString());
					pinfo.setPname(o[1]==null?"":o[1].toString());
					pinfo.setSex(o[2]==null?"":o[2].toString());
					pinfo.setBirthday(o[3]==null?"":new SimpleDateFormat("yyyy-MM-dd").format(o[3]));
					pinfo.setNation(o[4]==null?"":o[4].toString());
					pinfo.setPoliticsStatus(o[5]==null?"":o[5].toString());
					pinfo.setCulture(o[6]==null?"":o[6].toString());
					pinfo.setJiDt(o[7]==null?"":new SimpleDateFormat("yyyy-MM-dd").format(o[7]));
					pinfo.setReDt(o[8]==null?"":new SimpleDateFormat("yyyy-MM-dd").format(o[8]));
					
					pinfo.setSts(o[9]==null?"":o[9].toString());
					pinfo.setCrDt(o[10]==null?"":new SimpleDateFormat("yyyy-MM-dd").format(o[10]));
					pinfo.setUptDt(o[11]==null?"":new SimpleDateFormat("yyyy-MM-dd").format(o[11]));
					root.add(pinfo);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return SUCCESS;
	}
	
	public List<PersonInfo> getRoot() {
		return root;
	}
	public void setRoot(List<PersonInfo> root) {
		this.root = root;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getTotalProperty() {
		return totalProperty;
	}
	public void setTotalProperty(int totalProperty) {
		this.totalProperty = totalProperty;
	}
	
	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDocNum() {
		return docNum;
	}

	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}

	public String getTroopsType() {
		return troopsType;
	}

	public void setTroopsType(String troopsType) {
		this.troopsType = troopsType;
	}

	public String getJiSite() {
		return jiSite;
	}

	public void setJiSite(String jiSite) {
		this.jiSite = jiSite;
	}

	private List<PersonInfo> root = new ArrayList<PersonInfo>();
	private int start;
	private int limit;
	private int totalProperty;
	private String sts;	//档案状态,多个状态以逗号隔开
	private String code;
	private String pname;
	private String sex;
	private String docNum;
	private String pno;
	private String troopsType;
	private String jiSite;
	private static final long serialVersionUID = 8195199728640930980L;
}
