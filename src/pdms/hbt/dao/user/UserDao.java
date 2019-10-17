package pdms.hbt.dao.user;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.opensymphony.xwork2.ActionContext;
import pdms.hbt.dao.entities.Userinfo;
import pdms.hbt.dao.sessionfactory.HibernateSessionFactory;
/**
 * 用户信息查询
 * @author 程国前
 * 2013-11-26
 */
public class UserDao {
	/**
	 * 根据条件获取系统所有的用户
	 * @param userId
	 * @param userNm
	 * @param deptnm
	 * @param teamnm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Userinfo> getAllUser(String userId,String userNm){
		List<Userinfo> list = new ArrayList<Userinfo>();
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		Map<String, Object> s = ActionContext.getContext().getSession();
		try{
			t = session.beginTransaction();
			String sql = "";
			if(s.get("ui") == null){
				sql = "from Userinfo as u where u.userId like '%"+userId+"%' and u.userNm like '%"+userNm+"%'  order by status desc";
			}else{
				sql = "from Userinfo as u where u.userId like '%"+userId+"%' and u.userNm like '%"+userNm+"%'  order by status desc" ;
			}
			Query query = session.createQuery(sql);
			list = query.list();
			t.commit();
		}catch(Exception e){
			if(t != null)
				t.rollback();
		}finally{
			session.close();
		}
		return list;		
	}
}	
