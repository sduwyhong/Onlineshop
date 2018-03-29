package factory;

import cn.dsna.util.images.ValidateCode;

public class ValidateCodeFactory {
	
	public static ValidateCode getValidateCode() {
		return new ValidateCode(150, 60, 4, 10);
	}
	
}
