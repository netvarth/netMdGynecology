/**
 * 
 */
package com.nv.netmd.settings.bl.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.ReportDetail;
/**
 * @author Nithesh Mohanan
 *
 */
public class ReportValidator {
	public void validateReport(ReportDetail report) throws ServiceException {
		try
		{
			String effectiveFromDate = report.getFromDate();
			String effectiveToDate = report.getToDate();	

			if(effectiveFromDate.equals("") && effectiveToDate.equals(""))
				throw new ServiceException(ErrorCodeEnum.DateNull);
			if(effectiveFromDate.equals(""))
				throw new ServiceException(ErrorCodeEnum.FromDateNull);
			if(effectiveToDate.equals(""))
				throw new ServiceException(ErrorCodeEnum.ToDateNull);
			if(!effectiveFromDate.matches("\\d{4}-\\d{2}-\\d{2}"))
				throw new ServiceException(ErrorCodeEnum.InvalidDateFormat);
			if(!effectiveToDate.matches("\\d{4}-\\d{2}-\\d{2}"))
				throw new ServiceException(ErrorCodeEnum.InvalidDateFormat);
			DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
			Date fromDate=null;
			Date toDate = null;
			try {
				fromDate = df.parse(effectiveFromDate);
			} catch (ParseException e) {
				throw new ServiceException(ErrorCodeEnum.InvalidDateFormat);
			}
			try {
				toDate = df.parse(effectiveToDate);
			} catch (ParseException e) {
				throw new ServiceException(ErrorCodeEnum.InvalidDateFormat);
			}
			if(fromDate.after(toDate))
				throw new ServiceException(ErrorCodeEnum.InvalidFromToDate);
		}// end try 
		catch (RuntimeException e) {
			throw e;
		} 
	}
	public void validateDate(String date){
//		if(date==null || !date.matches("\\d{4}-\\d{2}-\\d{2}"))
//			throw new ServiceException(ErrorCodeEnum.InvalidDateFormat);
//		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
//		try {
//			df.parse(date);
//		} catch (ParseException e) {
//			throw new ServiceException(ErrorCodeEnum.InvalidDateFormat);
//		}		
	}
}
