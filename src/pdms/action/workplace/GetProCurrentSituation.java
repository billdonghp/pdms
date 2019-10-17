package pdms.action.workplace;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import pdms.hbt.dao.util.QueryUtil;
import pdms.util.beans.ProSituationBean;
/**
 * 获取提案现况
 * @author 程国前
 * 20140206
 */
public class GetProCurrentSituation extends ActionSupport implements Serializable{
	/**
	 * 执行统计
	 */
	public String execute() throws Exception{
		ProSituationBean pb1 = new ProSituationBean();
		pb1.setPtype("录入数量");
		pb1.setPstatus(getCount(1)+"名");
		ProSituationBean pb2 = new ProSituationBean();
		pb2.setPtype("管理数量");
		pb2.setPstatus(getCount(2)+"名");
		
		
		list.add(pb1);
		list.add(pb2);
		
		return SUCCESS;
	}
	/**
	 * 获取统计数据
	 * @param type 统计数据类型
	 * @return
	 */
	public int getCount(int type){
		int count = 0;
		String hql = "";
		List<Object> list = new ArrayList<Object>();
		Map<String, Object> s = ActionContext.getContext().getSession();
		if(s.get("ui") != null){
			//获取数据
			switch(type){
				case 1:
					hql = "from Pregister where sts <5 " ;
					list = QueryUtil.getObjectByHQLString(hql);
					break;
				case 2:
					hql = "from Pregister where sts = 4"  ;
					list = QueryUtil.getObjectByHQLString(hql);
					break;
				default:
					break;
			}
		}
		count = list.size();
		return count;		
	}
	public List<ProSituationBean> getList() {
		return list;
	}
	public void setList(List<ProSituationBean> list) {
		this.list = list;
	}
	private static final long serialVersionUID = 5161023589316807733L;
	private List<ProSituationBean> list = new ArrayList<ProSituationBean>();		//录入&进行中提案
}