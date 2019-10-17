package pdms.action.combox;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import pdms.hbt.dao.entities.ComboxValues;
import pdms.hbt.dao.util.QueryUtil;
/**
 * 获取系统所有的下拉列表
 * @author 程国前
 * 2013-12-5
 */
public class GetAllComboxes extends ActionSupport implements Serializable {

	public String execute() throws Exception{
		List<Object> list = QueryUtil.getAll(ComboxValues.class);
		if(list != null && list.size() != 0){
			for(Object o : list)
				root.add((ComboxValues)o);
		}
		return SUCCESS;
	}
	public List<ComboxValues> getRoot() {
		return root;
	}

	public void setRoot(List<ComboxValues> root) {
		this.root = root;
	}
	private List<ComboxValues> root = new ArrayList<ComboxValues>();
	private static final long serialVersionUID = 1243273316132754227L;
}
