package com.tasm.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.PhoneNumberToTimeZonesMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;

public class NumeroTelefonicoUtil {

	/**
	 * Valida numero telefonico utilizando libphonenumber de Google.
	 * 
	 * @param strNumeroTelefonico Numero telefonico.
	 * @param strDefaultCountry   Codigo ISO del pais
	 * @param locGeocodingLocale  Lenguaje
	 * @return
	 */
	public static Map<String, Object> validarNumeroTelefonico(String strNumeroTelefonico, String strDefaultCountry, Locale locGeocodingLocale) {
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		PhoneNumber objPhoneNumber = null;
		try {
			objPhoneNumber = phoneUtil.parseAndKeepRawInput(strNumeroTelefonico, strDefaultCountry);
		} catch (NumberParseException e) {
			e.printStackTrace();
			return null;
		}
		
		Map<String, Object> mapValidacion = new HashMap<String, Object>();
		mapValidacion.put("countryCode", Integer.toString(objPhoneNumber.getCountryCode()));
		PhoneNumberType objNumberType = phoneUtil.getNumberType(objPhoneNumber);
		mapValidacion.put("numberType", objNumberType);
		mapValidacion.put("region", phoneUtil.getRegionCodeForNumber(objPhoneNumber));
		boolean isNumberValid = phoneUtil.isValidNumber(objPhoneNumber);
		mapValidacion.put("isValidNumber", isNumberValid);
		mapValidacion.put("isValidNumberForRegion", (isNumberValid ? phoneUtil.isValidNumberForRegion(objPhoneNumber, strDefaultCountry) : false));
		mapValidacion.put("nationalNumber", Long.toString(objPhoneNumber.getNationalNumber()));
		mapValidacion.put("E164Format", (isNumberValid ? phoneUtil.format(objPhoneNumber, PhoneNumberFormat.E164) : "invalid"));
		mapValidacion.put("originalFormat", phoneUtil.formatInOriginalFormat(objPhoneNumber, strDefaultCountry));
		mapValidacion.put("nationalFormat", phoneUtil.format(objPhoneNumber, PhoneNumberFormat.NATIONAL));
		mapValidacion.put("internationalFormat", (isNumberValid ? phoneUtil.format(objPhoneNumber, PhoneNumberFormat.INTERNATIONAL) : "invalid"));
		mapValidacion.put("outOfcountryFormatFromUS", (isNumberValid ? phoneUtil.formatOutOfCountryCallingNumber(objPhoneNumber, "US") : "invalid"));
		mapValidacion.put("outOfcountryFormatFromCH", (isNumberValid ? phoneUtil.formatOutOfCountryCallingNumber(objPhoneNumber, "CH") : "invalid"));
		mapValidacion.put("location", PhoneNumberOfflineGeocoder.getInstance().getDescriptionForNumber(objPhoneNumber, locGeocodingLocale));
		mapValidacion.put("timeZones", PhoneNumberToTimeZonesMapper.getInstance().getTimeZonesForNumber(objPhoneNumber).toString());
		
		if (objNumberType == PhoneNumberType.MOBILE || objNumberType == PhoneNumberType.FIXED_LINE_OR_MOBILE || objNumberType == PhoneNumberType.PAGER) {
			mapValidacion.put("carrier", PhoneNumberToCarrierMapper.getInstance().getNameForNumber(objPhoneNumber, locGeocodingLocale));
		}
		return mapValidacion;
	}
	
	public static void main(String args[]) {
		System.out.println(NumeroTelefonicoUtil.validarNumeroTelefonico("0988940231", "EC", new Locale("es")).toString());
	}

}