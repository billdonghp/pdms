package pdms.util.tools;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import org.apache.log4j.Logger;

import pdms.hbt.dao.entities.Pregister;
import pdms.hbt.dao.util.QueryUtil;


/**
 * 系统通用工具类
 * @author 程国前
 * 2013-11-20
 */
public class UtilTool {
	
	
	/**
	 * 生成VTB编号
	 * @return
	 */
	public static String generatePersonNo(){
		String code = "";
		Integer [] a;
		String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		code = "PINFO" + dateStr;
		List<Object> pList =  QueryUtil.getObjectByLikeCondition(Pregister.class, "code", code);
		if(pList.size() == 0){
			code += "0001";
		}else{
			a = new Integer[pList.size()];
			for(int i = 0; i < pList.size(); i++){
				Pregister per = (Pregister)pList.get(i);
				String maxNo = per.getCode().substring(per.getCode().length()-4, per.getCode().length());
				a[i] = Integer.valueOf(maxNo);
			}
			Arrays.sort(a);
			int no = a[a.length-1]+ 1;
			if(no < 10){
				code += "000" + no;
			}else if(no >= 10 && no < 100){
				code += "00" + no;
			}else{
				code += "0" + no;
			}
		}
		return code;
	}
	
	protected static final Logger logger = Logger.getLogger(UtilTool.class);
}
