package cn.cps.springbootexample.entity;

/**
 * @author _Cps
 * @create 2019-02-14 10:12
 * @Description: 全局常量与枚举
 */
public class GlobalConstants {

	//性别映射
	public enum GenderCode{

		WO_MAN(0,"女"),//女
		MAN(1,"男");//男

		int code;
		String value;

		GenderCode(int code,String value){
			this.code = code;
			this.value = value;
		}

		public int getCode(){
			return this.code;
		}
		public String getValue(){
			return this.value;
		}
	}

}
