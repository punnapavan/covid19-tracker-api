package com.covid.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.covid.model.BaseResponse;
import com.covid.model.ErrorCode;

public class CommonUtils {
	
	public static com.covid.model.BaseResponse createBaseResponse(boolean success, String errorMessage, String key) {
		BaseResponse errorModel = new BaseResponse();
		errorModel.setSuccess(success);
		if (!StringUtils.isBlank(errorMessage)) {
			ErrorCode errorCode = new ErrorCode();
			errorCode.setErrorMessage(errorMessage);
			errorCode.setKey(key);
			List<ErrorCode> errorCodeList = new ArrayList<>();
			errorCodeList.add(errorCode);
			errorModel.setErrors(errorCodeList);
		} else {
			errorModel.setErrors(null);
		}
		return errorModel;
	}

}
