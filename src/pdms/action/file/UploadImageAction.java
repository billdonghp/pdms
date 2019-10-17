package pdms.action.file;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 上传图片
 * @author 程国前
 * 2013-12-24
 */
public class UploadImageAction extends ActionSupport implements Serializable {
	
	/**
	 * 执行上传
	 */
	public String execute() throws Exception{
		this.success = true;
		String tkpath = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		savePath = savePath + "\\" + tkpath;
		File file = new File(savePath);
		if(!file.exists())
			file.mkdir();
		FileOutputStream fos = new FileOutputStream(savePath + "\\"
			    + getUploadFileName());
		FileInputStream fis = new FileInputStream(getUpload());
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		uploadFileName = tkpath + "/" +uploadFileName;
		fos.close();
		fis.close();
		return SUCCESS;
	}
	
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	private boolean success;
	private String savePath;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private static final long serialVersionUID = 5350282995766808822L;
}
