package code;

public class StringUtil {

//	�ж��Ƿ�Ϊ��
	public static boolean isempty(String str) {
		if(str==null || "".equals(str.trim())) {
			return true;
		}
		else {
			return false;
		}
	}
//	�ж��Ƿ�Ϊ��	
	public static boolean isnotempty(String str) {
		if(str!=null && !"".equals(str.trim())) {
			return true;
		}
		else {
			return false;
		}
	}

}