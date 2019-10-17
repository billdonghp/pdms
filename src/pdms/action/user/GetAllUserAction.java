package pdms.action.user;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import pdms.hbt.dao.entities.Userinfo;
import pdms.hbt.dao.user.UserDao;
/**
 * 获取系统所有的用户
 * @author 程国前
 * 2013-11-26
 */
public class GetAllUserAction extends ActionSupport {
	/**
	 * 执行查询
	 */
	public String execute() throws Exception{
		List<Userinfo> list = UserDao.getAllUser(userId, userNm);
		if(list != null && list.size() != 0){
			this.totalProperty = list.size();
			int end = (start+limit)>this.totalProperty?this.totalProperty:start+limit;
			for(int i = start; i < end; i++)
				root.add(list.get(i));
		}
		return SUCCESS;
	}
	public List<Userinfo> getRoot() {
		return root;
	}
	public void setRoot(List<Userinfo> root) {
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getDeptnm() {
		return deptnm;
	}
	public void setDeptnm(String deptnm) {
		this.deptnm = deptnm;
	}
	public String getTeamnm() {
		return teamnm;
	}
	public void setTeamnm(String teamnm) {
		this.teamnm = teamnm;
	}
	private static final long serialVersionUID = -8956280954149360117L;
	private List<Userinfo> root = new ArrayList<Userinfo>();
	private int start;
	private int limit;
	private int totalProperty;
	private String userId;
	private String userNm;
	private String deptnm;
	private String teamnm;
}
