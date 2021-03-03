package com.anz.accounts.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * This is a utility class
 * @author Vasavi
 *
 */
public class AccountsUtil {
/**
 *  This method will take LocalDate object and format it to the dd/MM/yyyy  and return the string.
 * @param date
 * @return String
 */
	public static String formatDate(LocalDate date) {
		if (null != date) {
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String formattedDate = date.format(pattern);
			return formattedDate;
		}
		return null;
	}
	/**
	 *  This method will take LocalDateTime object and format it to the specified MMM, dd, yyyy and return the string.
	 * @param dateTime
	 * @return String
	 */
	public static String formatTransactionDate(LocalDateTime dateTime) {
		if (null != dateTime) {
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM, dd, yyyy");
			String formattedDate = dateTime.format(pattern);
			return formattedDate;
		}
		return null;
	}

}
