package pdms.action.combox;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.opensymphony.xwork2.ActionSupport;

import pdms.hbt.dao.entities.ComboxValues;
import pdms.hbt.dao.util.QueryUtil;
/**
 * 根据code获取Combox值
 * @author 程国前
 * 2013-12-7
 */
public class GetComboxeValues extends ActionSupport implements Serializable {
	/**
	 * 获取combox值
	 */
	public String execute() throws Exception{
		//CODE-DIFF
		String sql = "from ComboxValues where code = '" + code + "' " + (filter==null?"":filter);
		List<Object> list = QueryUtil.getObjectByHQLString(sql);
		ComboxValues b = null;
		if(list != null && list.size() != 0){
			for(int i = 0; i < list.size(); i++){
				try{
					b = (ComboxValues)list.get(i);
				}catch(Exception e){
					e.printStackTrace();
				}
				root.add(b);
			}
		}
		return SUCCESS;
	}
	@Test
	public static void main(String[] args){
		String sql = "SELECT * FROM COMBOXVALUES WHERE CODE = 'politicsStatus'";
		List<Object> list = QueryUtil.getObjectBySQLString(sql);
		if(list != null){
			System.out.println("SIZE:\t"+list.size());
		}else{
			System.out.println("NULL...");
		}
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public List<ComboxValues> getRoot() {
		return root;
	}
	public void setRoot(List<ComboxValues> root) {
		this.root = root;
	}

	private static final long serialVersionUID = 5619779523354617188L;
	private String code;
	private String filter;
	List<ComboxValues> root = new ArrayList<ComboxValues>();
}
