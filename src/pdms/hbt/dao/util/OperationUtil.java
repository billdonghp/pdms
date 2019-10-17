package pdms.hbt.dao.util;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pdms.hbt.dao.entities.Dept;
import pdms.hbt.dao.sessionfactory.HibernateSessionFactory;
/**
 * 数据操作
 * @author 董海朋
 * 2018-04-28
 */
public class OperationUtil {
	/**
	 * 新增数据
	 * @param o
	 */
	public static Object add(Object o){
		Object ro = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			logger.info("执行数据新增,新增数据:\t"+o.toString());
			ro = session.save(o);
			t.commit();
		}catch(Exception e){
			if(t != null){
				logger.error("操作异常:\t"+e+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t新增"+o.toString()+"\t出错,执行事务回滚");
				t.rollback();
			}
		}finally{
			session.close();
		}
		return ro;
	}
	/**
	 * 修改数据
	 * @param o
	 */
	public static Object update(Object o){
		Object ro = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			logger.info("执行数据更新,更新数据:\t"+o.toString());
			ro = session.merge(o);
			t.commit();
		}catch(Exception e){
			if(t != null){
				logger.error("操作异常:\t"+e+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t更新"+o.toString()+"\t出错,执行事务回滚");
				t.rollback();
			}
		}finally{
			session.close();
		}
		return ro;
	}
	/**
	 * 删除数据-批量
	 * @param list
	 */
	public static void deleteObject(List<Object> list){
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			logger.info("执行数据批量删除");
			for(Object o : list)
				session.delete(o);
			t.commit();
		}catch(Exception e){
			if(t != null){
				logger.error("操作异常:\t"+e+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t批量删除数据出错,执行事务回滚");
				t.rollback();
			}
		}finally{
			session.close();
		}
	}
	/**
	 * 删除数据-单笔
	 * @param o
	 */
	public static void deleteObject(Object o){
		Session session = HibernateSessionFactory.getSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			logger.info("执行数据删除"+o.toString());
			session.delete(o);
			t.commit();
		}catch(Exception e){
			if(t != null){
				logger.error("操作异常:\t"+e+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t删除数据"+o.toString()+"出错,执行事务回滚");
				t.rollback();
			}
		}finally{
			session.close();
		}
	}
	public static void main(String[] args){
		Dept o = new Dept();
		o.setDept_level("1");
		o.setDeptcd("D0001");
		o.setDeptfullnm("斗山机械");
		o.setDeptnm("斗山机械");
		o.setDeptshortnm("斗山机械");
		o.setRegdt(new Timestamp(new Date().getTime()));
		o.setUpdeptcd("0");
		add(o);
		System.out.println("add ok");
	}
	protected static final Logger logger = Logger.getLogger(QueryUtil.class);
}
