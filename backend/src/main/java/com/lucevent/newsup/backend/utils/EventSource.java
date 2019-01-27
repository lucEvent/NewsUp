package com.lucevent.newsup.backend.utils;

public class EventSource {

	public static final String ID_ES_SOURCES = "es_es";
	public static final String ID_UK_SOURCES = "en_gb";
	public static final String ID_US_SOURCES = "en_us";
	public static final String ID_SV_SOURCES = "sv_se";
	public static final String ID_CAT_SOURCES = "ca_es";

	private static final int[][] ES_SOURCES = {
			{100, -306162022},
			{105, 132737269},
			{110, 1272519773},
			{125, 2085053711},
			{130, 132737269},
			{135, -2141651978},
			{140, 2085053711},
			{145, 1272519773},
			{155, -778380837},
			{165, 1272519773},
			{205, 1272519773},
			{210, 191926286}
	};
	private static final int[][] UK_SOURCES = {
			{500, 816398440}, // BBC
			{505, 2424563}, // The Telegraph
			{510, -420727794}, // The Huffington Post UK
			{515, -369395939}, // Metro.co.uk
			{520, 307565117}, // The Guardian
			{535, -420727794}, // The Independent
			{550, -420727794}, // Metro UK
			{555, -420727794}, // Evening Standard
			{570, 2424563}, // Daily Express
	};
	private static final int[][] CAT_SOURCES = {
			{200, 1272519773}, // El Periódico (Cat)
			{225, 1771822894}, // Racó Català
			{230, 191926286}, // VilaWeb
			{235, 1272519773}, // El Punt Avui
			{250, -890380364}, // Nació Digital
	};
	private static final int[][] US_SOURCES = {
			{600, -1813915576}, // CNN
			{605, 536598325}, // The Huffington Post USA
			{610, 1796885759}, // USA Today
			{615, -232053456}, // The New York Times
	};
	private static final int[][] SV_SOURCES = {
			{300, 438456953}, // Aftonbladet
			{305, -225859847}, // Expressen
			{310, -1290138452}, // Dagens Nyheter
			{315, -1226593567}, // Svenska Dagbladet
			{320, 80204866}, // Göteborgs-Posten
			{325, -673985891}, // Fria Tider
	};

	public static int[][] getSources(String region_code)
	{
		switch (region_code) {
			case ID_ES_SOURCES:
				return ES_SOURCES;
			case ID_CAT_SOURCES:
				return CAT_SOURCES;
			default:
			case ID_UK_SOURCES:
				return UK_SOURCES;
			case ID_US_SOURCES:
				return US_SOURCES;
			case ID_SV_SOURCES:
				return SV_SOURCES;
		}
	}

}
