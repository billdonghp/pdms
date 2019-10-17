package pdms.action.combox;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import pdms.hbt.dao.entities.ComboxValues;
import pdms.hbt.dao.util.OperationUtil;
import pdms.hbt.dao.util.QueryUtil;
/**
 * 系统下拉列表操作
 * @author 程国前
 * 2013-12-5
 */
public class ComboxOptAction extends ActionSupport implements Serializable {
	/**
	 * 新增下拉列表
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		ComboxValues cv = new ComboxValues(code, comvalue, comview);
		cv.setCreatedt(new Timestamp(new Date().getTime()));
		OperationUtil.add(cv);
		this.rlt = "ok";
		this.success = true;
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	public String update() throws Exception{
		ComboxValues cv =  null;
		Object o = QueryUtil.getObjectById(ComboxValues.class, id);
		if(o != null){
			cv = (ComboxValues)o;
		}else{
			System.out.println("Didn't find the Combox!");
		}
		if(cv != null){
			cv.setCode(code);
			cv.setComvalue(comvalue);
			cv.setComview(comview);
			OperationUtil.update(cv);
			this.rlt = "ok";
		}
		this.success = true;
		return SUCCESS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getComvalue() {
		return comvalue;
	}
	public void setComvalue(Integer comvalue) {
		this.comvalue = comvalue;
	}
	public String getComview() {
		return comview;
	}
	public void setComview(String comview) {
		this.comview = comview;
	}
	public String getRlt() {
		return rlt;
	}

	public void setRlt(String rlt) {
		this.rlt = rlt;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	private static final long serialVersionUID = 5934903246558051556L;
	private Integer id;
	private String code;
	private Integer comvalue;
	private String comview;
	private String rlt;
	private boolean success;
}
