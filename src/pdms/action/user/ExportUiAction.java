package pdms.action.user;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import com.opensymphony.xwork2.ActionSupport;
import pdms.hbt.dao.entities.Userinfo;
import pdms.hbt.dao.user.UserDao;
/**
 * 导出用户信息
 * @author 程国前
 * 2013-11-26
 */ 
public class ExportUiAction extends ActionSupport {
    public String execute(){
        WritableWorkbook workbook;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
        	List<Userinfo> list = UserDao.getAllUser(userId, userNm);
            workbook = Workbook.createWorkbook(os);
            WritableSheet sheet = workbook.createSheet("用户信息", 0);
            sheet.addCell(new Label(0, 0, "用户ID"));
            sheet.addCell(new Label(1, 0, "用户姓名"));
            sheet.addCell(new Label(2, 0, "电子邮件"));
            sheet.addCell(new Label(3, 0, "所属名称"));
            sheet.addCell(new Label(4, 0, "所属代码"));
            sheet.addCell(new Label(5, 0, "部门代码"));
            sheet.addCell(new Label(6, 0, "部门名称"));
            sheet.addCell(new Label(7, 0, "角色名称"));
            sheet.addCell(new Label(8, 0, "联系电话"));
            sheet.addCell(new Label(9, 0, "系统状态"));
            for(int i = 0; i < list.size(); i++){
            	Userinfo u = list.get(i);
            	sheet.addCell(new Label(0, i+1, u.getUserId()));
                sheet.addCell(new Label(1, i+1, u.getUserNm()));
                sheet.addCell(new Label(2, i+1, u.getEmail()));
                sheet.addCell(new Label(7, i+1, u.getRoleNm()));
                sheet.addCell(new Label(8, i+1, u.getPhoneNum()));
                sheet.addCell(new Label(9, i+1, u.getStatus()==1?"在用":"停用"));
            }
            workbook.write();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        is = new ByteArrayInputStream(os.toByteArray());
        return SUCCESS;
        
    }
    public InputStream getIs() {
        return is;
    }
    public void setIs(InputStream is) {
        this.is = is;
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
    private static final long serialVersionUID = 812474412040748528L;
	private InputStream is;
	private String userId;
	private String userNm;
}
