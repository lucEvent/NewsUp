package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class XatakaSections extends Sections {

	public XatakaSections()
	{
		super();

		add(new Section("Xataka", "http://feeds.weblogssl.com/xataka2", 0));
		add(new Section("M\u00F3vil", "http://feeds.weblogssl.com/xatakamovil", 0));
		add(new Section("Fotograf\u00EDa", "http://feeds.weblogssl.com/xatakafoto", 0));
		add(new Section("Android", "http://feeds.weblogssl.com/xatakandroid", 0));
		add(new Section("Smart home", "http://feeds.weblogssl.com/xatakahome.com", 0));
		add(new Section("Windows", "http://feeds.weblogssl.com/xatakawindows.com", 0));
		add(new Section("Ciencia", "http://feeds.weblogssl.com/xatakaciencia.com", 0));
		add(new Section("AppleSfera", "http://feeds.weblogssl.com/applesfera.com", 0));
		add(new Section("Vida Extra", "http://feeds.weblogssl.com/vidaextra", 0));
		add(new Section("GenBeta", "http://feeds.weblogssl.com/genbeta.com", 0));
		add(new Section("Genbeta Dev", "http://feeds.weblogssl.com/genbetadev", 0));
		add(new Section("Magnet", "http://feeds.weblogssl.com/xatakamagnet2", 0));
		add(new Section("Compradicci\u00F3n", "http://feeds.weblogssl.com/compradiccion", 0));
		add(new Section("eSPORTS", "http://feeds.weblogssl.com/xatakaesports", 0));

		add(new Section("Estilo de vida", null, -1));
		add(new Section("Trendencias", "http://feeds.weblogssl.com/trendencias", 1));
		add(new Section("Trendencias Moda", "http://feeds.weblogssl.com/modatrendencias", 1));
		add(new Section("Trendencias Belleza", "http://feeds.weblogssl.com/trendenciasbelleza", 1));
		add(new Section("Trendencias Hombre", "http://feeds.weblogssl.com/trendenciashombre", 1));
		add(new Section("Directo al Paladar", "http://feeds.weblogssl.com/directoalpaladar", 1));
		add(new Section("Beb\u00E9s y m\u00E1s", "http://feeds.weblogssl.com/bebesymas", 1));
		add(new Section("Vit\u00F3nica", "http://feeds.weblogssl.com/vitonica", 1));
		add(new Section("Decoesfera", "http://feeds.weblogssl.com/decoesfera", 1));

		add(new Section("Motor", null, -1));
		add(new Section("Motorpasi\u00F3n", "http://feeds.weblogssl.com/motorpasion", 1));
		add(new Section("Motorpasi\u00F3n Moto", "http://feeds.weblogssl.com/motorpasionmoto", 1));

		add(new Section("Econom\u00EDa", null, -1));
		add(new Section("El Blog Salm\u00F3n", "http://feeds.weblogssl.com/elblogsalmon2", 1));
		add(new Section("Pymes y Aut\u00F3nomos", "http://feeds.weblogssl.com/pymesyautonomos", 1));

		add(new Section("Ocio", null, -1));
		add(new Section("Espinof", "http://feeds.weblogssl.com/espinof", 1));
		add(new Section("Diario del Viajero", "http://feeds.weblogssl.com/diariodelviajero", 1));

	}

}
