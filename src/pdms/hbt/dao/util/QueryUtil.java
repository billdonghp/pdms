package pdms.hbt.dao.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pdms.hbt.dao.sessionfactory.HibernateSessionFactory;
/**
 * 通用查询
 * @author 程国前
 * 2013-11-18
 */
public class QueryUtil {
	/**
	 * 获取所有的记录
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> getAll(Class<?> c){
		List<Object> list = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			logger.info("执行数据查询,查询数据:\t"+c.getName());
			Criteria ct = session.createCriteria(c);
			list = ct.list();
			t.commit();
		}catch(Exception e){
			if(t != null){
				logger.error("执行数据:\t"+c.getName()+"\t查询出错,执行事务回滚");
				t.rollback();
			}
		}finally{
			session.close();
		}
		return list;
	}
	/**
	 * 根据ID获取数据记录
	 * @param c
	 * @param id
	 * @return
	 */
	public static Object getObjectById(Class<?> c, Object id){
		Object o = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			logger.info("执行数据查询,查询数据:\t"+c.getName());
			Criteria ct = session.createCriteria(c);
			ct.add(Restrictions.idEq(id));//根据ID获取数据
			o = ct.uniqueResult();
			t.commit();
		}catch(Exception e){
			if(t != null){
				logger.error("执行数据:\t"+c.getName()+"\t查询出错,执行事务回滚");
				t.rollback();
			}
		}finally{
			session.close();
		}
		return o;
	}
	
	/**
	 * 根据No获取数据记录
	 * @param c
	 * @param id
	 * @return
	 */
	public static Object getObjectByNo(Class<?> c,String property, String no){
		Object o = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			logger.info("执行数据查询,查询数据:\t"+c.getName());
			Criteria ct = session.createCriteria(c);
			ct.add(Restrictions.eq(property,no));//根据no获取数据
			o = ct.uniqueResult();
			t.commit();
		}catch(Exception e){
			if(t != null){
				logger.error("执行数据:\t"+c.getName()+"\t查询出错,执行事务回滚");
				t.rollback();
			}
		}finally{
			session.close();
		}
		return o;
	}
	/**
	 * 根据条件获取数据记录
	 * @param c
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> getObjectByCondition(Class<?> c, String property, Object value){
		List<Object> list = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			logger.info("执行数据查询,查询数据:\t"+c.getName());
			Criteria ct = session.createCriteria(c);
			ct.add(Restrictions.eq(property, value));//根据某一条件获取数据
			list = ct.list();
			t.commit();
		}catch(Exception e){
			if(t != null){
				logger.error("执行数据:\t"+c.getName()+"\t查询出错,执行事务回滚");
				t.rollback();
			}
		}finally{
			session.close();
		}
		return list;
	}
	/**
	 * 根据条件进行模糊查询获取数据记录
	 * @param c
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> getObjectByLikeCondition(Class<?> c, String property, Object value){
		List<Object> list = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			logger.info("执行数据查询,查询数据:\t"+c.getName());
			Criteria ct = session.createCriteria(c);
			ct.add(Restrictions.like(property, "%"+value+"%"));//根据某一条件获取数据
			list = ct.list();
			t.commit();
		}catch(Exception e){
			if(t != null){
				logger.error("执行数据:\t"+c.getName()+"\t查询出错,执行事务回滚");
				t.rollback();
			}
		}finally{
			session.close();
		}
		return list;
	}
	/**
	 * 根据HQL语句进行查询 - 执行HQL语句进行查询
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> getObjectByHQLString(String hql){
		List<Object> list = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			logger.info("使用HQL语句执行数据查询,查询数据");
			Query query = session.createQuery(hql);
			list = query.list();
			t.commit();
		}catch(Exception e){
			if(t != null){
				logger.error("使用HQL语句执行数据查询出错,执行事务回滚");
				t.rollback();
			}
		}finally{
			session.close();
		}
		return list;
	}
	/**
	 * 根据SQL语句进行查询 - 执行SQL语句进行查询
	 * 返回的每条结果不是对象,而是数组:Object[]
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> getObjectBySQLString(String sql){
		List<Object> list = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			logger.info("使用SQL语句执行数据查询,查询数据");
			Query query = session.createSQLQuery(sql);
			list = query.list();
			t.commit();
		}catch(Exception e){
			if(t != null){
				logger.error("使用SQL语句执行数据查询出错,执行事务回滚");
				t.rollback();
			}
		}finally{
			session.close();
		}
		return list;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//		for(int i = 0; i < 10000; i++){
		String sql = "select a.bg,a.deptcd,a.deptnm,b.bgennm,b.bgzhnm from DEPT as a left join BGINFO as b on a.bg = b.bgcd".toUpperCase();
		sql = "SELECT COUNT(*) FROM PROIDE WHERE BG = 'DC' AND DPTCD = 'G1111'";
		System.out.println("SQL:\t" + sql);
		List<Object> list = QueryUtil.getObjectBySQLString(sql);
		System.out.println("List is null?" + (list == null?0:list.size()));
		if(list != null && list.size() != 0){
//			Object[] os = null;
			System.out.println("-----------------------------------");
			for(int i = 0; i < list.size(); i++){
//				os = (Object[])list.get(i);
				System.out.println("PRO COUNT:\t" + list.get(i));
//				System.out.println("DEPT CODE:\t" + os[1]);
//				System.out.println("DEPT NAME:\t" + os[2]);
//				System.out.println("BG   EN NAME:\t" + os[3]);
//				System.out.println("BG   ZH NAME:\t" + os[4]);
				System.out.println("-----------------------------------");
			}
		}
//		}
		System.out.println("Begin time:\t" + b);
		System.out.println("End time:\t" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		sql = "SELECT COUNT(*) FROM PROIDE WHERE BG = 'DC' AND DPTCD = 'G1111'";
		list = QueryUtil.getObjectBySQLString(sql);
//		TempTb bi = null;
//		if(list != null && list.size() != 0){
//			for(int i = 0; i < list.size(); i++){
//				bi = (TempTb)list.get(i);
//				System.out.println("BG ID Name:\t" + bi.getBgid());
//				System.out.println("BG Code Name:\t" + bi.getBgennm());
//				System.out.println("BG English Name:\t" + bi.getBgennm());
//				System.out.println("BG Chinese Name:\t" + bi.getBgzhnm());
//				System.out.println("BG Chinese Name:\t" + bi.getMm());
//			}
//		}
	}
	protected static final Logger logger = Logger.getLogger(QueryUtil.class);
}
