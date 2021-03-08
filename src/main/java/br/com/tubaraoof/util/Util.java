package br.com.tubaraoof.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	private static final SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
	
	public static String getParcialStr(int maxLength, String str) {
		StringBuilder retorno = new StringBuilder("");
		String _str = str != null ? str : "";
		if (_str.length() > maxLength) {
			retorno.append(_str.subSequence(0, maxLength - 1));
			retorno.append(" ");
		} else {
			retorno.append(_str);
			
			int j = 0;
			for (j = _str.length(); j < maxLength; j++) {
				retorno.append(" ");
			}
		}

		return retorno.toString();
	}
	
	public static String formatarData(Date data) {
		if (data != null) {
			return formatadorData.format(data);
		}
		
		return null;
	}
}