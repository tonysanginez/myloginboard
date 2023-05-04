package com.tasm.exceptions;

import java.util.Locale;

import javax.validation.constraints.NotNull;

import com.tasm.util.MensajesUtil;

public class UnauthorizedException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final Locale localeDefault = new Locale("es", "EC");
	private String codeMessage;
	private Object[] messageParametersValues;
	private Object data;

	public UnauthorizedException(String codeMessage, Throwable cause) {
		super(MensajesUtil.getMensaje(codeMessage, localeDefault), cause);
		this.codeMessage = codeMessage;
	}

	public UnauthorizedException(String codeMessage) {
		super(MensajesUtil.getMensaje(codeMessage, localeDefault));
		this.codeMessage = codeMessage;
	}

	public UnauthorizedException(Throwable cause) {
		super(cause);
	}

	public UnauthorizedException(String codeMessage, @NotNull Object data) {
		super(MensajesUtil.getMensaje(codeMessage, localeDefault));
		this.codeMessage = codeMessage;
		this.data = data;
	}

	public UnauthorizedException(String codeMessage, @NotNull Object[] messageParametersValues, Throwable cause) {
		super(MensajesUtil.getMensaje(codeMessage, messageParametersValues, localeDefault), cause);
		this.codeMessage = codeMessage;
		this.messageParametersValues = messageParametersValues;
	}

	public UnauthorizedException(String codeMessage, @NotNull Object[] messageParametersValues) {
		super(MensajesUtil.getMensaje(codeMessage, messageParametersValues, localeDefault));
		this.codeMessage = codeMessage;
		this.messageParametersValues = messageParametersValues;
	}

	public UnauthorizedException(String codeMessage, @NotNull Object[] messageParametersValues, @NotNull Object data) {
		super(MensajesUtil.getMensaje(codeMessage, messageParametersValues, localeDefault));
		this.codeMessage = codeMessage;
		this.messageParametersValues = messageParametersValues;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getTranslatedMessage(String strLanguage) {
		Locale locale = MensajesUtil.validateSupportedLocale(strLanguage);
		if (localeDefault.equals(locale)) {
			return super.getMessage();
		} else {
			if (messageParametersValues != null && messageParametersValues.length > 0)
				return MensajesUtil.getMensaje(codeMessage, messageParametersValues, locale);
			else
				return MensajesUtil.getMensaje(codeMessage, locale);
		}
	}

}