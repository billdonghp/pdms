package pdms.action.workplace;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import pdms.hbt.dao.entities.Pregister;
import pdms.hbt.dao.util.OperationUtil;
import pdms.hbt.dao.util.QueryUtil;

import com.opensymphony.xwork2.ActionSupport;

public class WorkListControllerAction extends ActionSupport implements
		Serializable {
	/**
	 * 
	 */
	

	public String execute(){
		String[] codes = code.split(",");
		for(int i = 0; i < codes.length; i++){
			String hql = "From Pregister where code= '" + codes[i] + "'" ;
			List<Object> prs = QueryUtil.getObjectByHQLString(hql);	
			if(prs != null && prs.size()>0){
				for(int j=0; j<prs.size(); j++){
					Pregister pr = (Pregister)prs.get(j);
					if(sign.equals("Approval")){
						pr.setSts(4);
					}else {
						pr.setSts(3);
					}
					
					pr.setUptDt(new Timestamp(new Date().getTime()));
					OperationUtil.update(pr);
				}
			}
		}
		return SUCCESS;
	}
	
	private String code;
	private String sign;
	private static final long serialVersionUID = -7790773563869324954L;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
