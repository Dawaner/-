package code;

public class StringUtil {

//	判断是否为空
	public static boolean isempty(String str) {
		if(str==null || "".equals(str.trim())) {
			return true;
		}
		else {
			return false;
		}
	}
//	判断是否不为空	
	public static boolean isnotempty(String str) {
		if(str!=null && !"".equals(str.trim())) {
			return true;
		}
		else {
			return false;
		}
	}

}