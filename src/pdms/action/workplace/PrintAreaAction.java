package pdms.action.workplace;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import pdms.hbt.dao.util.QueryUtil;
import pdms.util.beans.PersonPrintArea;

import com.opensymphony.xwork2.ActionSupport;

public class PrintAreaAction extends ActionSupport implements Serializable {
	
	/**
	 * 
	 */
	
	public String execute(){
			String sql = "select a.code as code,"
					+ "a.pname as pname,"
					+ "a.sex as sex,"
					+ "a.birthday as birthday,"
					+ "a.nation as nation,"
					+ "b.comview as politicsStatus,"
					+ "a.jiDt as jiDt,"
					+ "a.reDt as reDt,"
					+ "a.filePath as filePath,"
					+ "c.comview as  culture,"
					+ "a.combatant  as combatant,"
					+ "a.nonWar  as nonWar,"
					+ "a.body  as body,"
					+ "a.organization as organization,"
					+ "a.pno as pno,"
					+ "e.comview as troopsType, "
					+ "a.docNum as docNum,"
					+ "a.address as address,"
					+ "a.remark1 as remark1,"
					+ "a.jiSite as jiSite,"
					+ "a.rp as rp,"
					+ "a.remark2 as remark2, "
					+ "dd.comview  sts  "
					+ "from Pregister a "
					+ "left join comboxvalues b on a.politicsStatus = b.comvalue and b.code = 'PLTS' "
					+ "left join comboxvalues c on a.culture = c.comvalue and c.code = 'CULTURE' "
					+ "left join comboxvalues dd on a.sts = dd.comvalue and dd.code = 'STS' "
					+ "left join comboxvalues e on a.troopsType = e.comvalue and e.code = 'TOPT' "
					+ "where a.code ='"+code+"'";
			
			List<Object> list = QueryUtil.getObjectBySQLString(sql);		
			if(list != null && list.size() != 0){
				try{
					Object[] o = (Object[]) list.get(0);
					pinfo = new PersonPrintArea();
					pinfo.setCode(o[0]==null?"":o[0].toString());
					pinfo.setPname(o[1]==null?"":o[1].toString());
					pinfo.setSex(o[2].equals(1)?"男":"女");
					pinfo.setBirthday(o[3]==null?"":new SimpleDateFormat("yyyy-MM-dd").format(o[3]));
					pinfo.setNation(o[4]==null?"":o[4].toString());
					pinfo.setPoliticsStatus(o[5]==null?"":o[5].toString());
					pinfo.setJiDt(o[6]==null?"":new SimpleDateFormat("yyyy-MM-dd").format(o[6]));
					pinfo.setReDt(o[7]==null?"":new SimpleDateFormat("yyyy-MM-dd").format(o[7]));
					pinfo.setFilePath(o[8]==null?"":o[8].toString());
					pinfo.setCulture(o[9]==null?"":o[9].toString());
					pinfo.setCombatant(o[10].equals(1)?"是":"否");
					pinfo.setNonWar(o[11].equals(1)?"是":"否");
					pinfo.setBody(o[12].equals(1)?"健康":"伤残");
					pinfo.setOrganization(o[13]==null?"":o[13].toString());
					pinfo.setPno(o[14]==null?"":o[14].toString());
					pinfo.setTroopsType(o[15]==null?"":o[15].toString());
					pinfo.setDocNum(o[16]==null?"":o[16].toString());
					pinfo.setAddress(o[17]==null?"":o[17].toString());
					pinfo.setRemark1(o[18]==null?"":o[18].toString());
					pinfo.setJiSite(o[19]==null?"":o[19].toString());
					pinfo.setRp(o[20]==null?"":o[20].toString());
					pinfo.setRemark2(o[21]==null?"":o[21].toString());
					pinfo.setSts(o[22]==null?"":o[22].toString());
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			return SUCCESS;
	}
	public PersonPrintArea getPinfo() {
		return pinfo;
	}
	public void setPinfo(PersonPrintArea pinfo) {
		this.pinfo = pinfo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private PersonPrintArea pinfo;
	private String code;
	private static final long serialVersionUID = -4053961758248727505L;
}
	