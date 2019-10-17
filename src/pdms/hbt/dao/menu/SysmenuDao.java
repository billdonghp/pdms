package pdms.hbt.dao.menu;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pdms.hbt.dao.entities.Sysmenu;
import pdms.hbt.dao.sessionfactory.HibernateSessionFactory;
/**
 * 系统菜单管理类
 * @author 董海朋
 * 2018-04-28
 */
public class SysmenuDao {
	/**
	 * 获取所有的节点
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sysmenu> getAll(){
		List<Sysmenu> list = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			String sql="from Sysmenu";
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
	/**
	 * 获取所有的父节点
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sysmenu> getAllParent(){
		List<Sysmenu> list = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			String sql="from Sysmenu as s where s.parentId = 0 and s.enabled = 1";
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
	/**
	 * 获取对应的子节点
	 * @param parentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sysmenu> getAllChildren(int parentId){
		List<Sysmenu> list = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			String sql="from Sysmenu as s where s.enabled = 1 and s.parentId = " + parentId;
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
	/**
	 * 根据ID获取菜单
	 * @param id
	 * @return
	 */
	public Sysmenu getSysmenuById(int id){
		Sysmenu m = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			String sql="from Sysmenu as s where s.id = " + id;
			Query query = session.createQuery(sql);
			m = (Sysmenu)query.uniqueResult();
			t.commit();
		}catch(Exception e){
			if(t != null)
				t.rollback();
		}finally{
			session.close();
		}
		return m;
	}
	/**
	 * 新增/修改系统菜单
	 * @param node
	 */
	public void addOrUptMenu(Sysmenu menu){
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			session.saveOrUpdate(menu);
			t.commit();
		}catch(Exception e){
			if(t != null)
				t.rollback();
		}finally{
			session.close();
		}
	}
	/**
	 * 删除菜单
	 * @param menu
	 */
	public void deleteMenu(Sysmenu menu){
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			session.delete(menu);
			t.commit();
		}catch(Exception e){
			if(t != null)
				t.rollback();
		}finally{
			session.close();
		}
	}
	public static void main(String[] args) throws Exception{
		
	}
}
