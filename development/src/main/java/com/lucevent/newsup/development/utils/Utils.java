package com.lucevent.newsup.development.utils;

public final class Utils {

	/**
	 * @param data      String the substring will be looked up
	 * @param start     Starting of the substring
	 * @param end       Ending of the substring
	 * @param inclusive Whether or not include the start and end
	 * @return the substring delimited by start and end (including start and end if inclusive=true), null if not found
	 */
	public static String findSubstringBetween(String data, String start, String end, boolean inclusive)
	{
		try {
			int istart = data.indexOf(start);
			if (istart == -1)
				return null;
			int iend = data.indexOf(end, istart + start.length()) + (inclusive ? end.length() : 0);
			return data.substring(istart + (inclusive ? 0 : start.length()), iend);
		} catch (Exception ignored) {
			ignored.printStackTrace();
		}
		return null;
	}

}
