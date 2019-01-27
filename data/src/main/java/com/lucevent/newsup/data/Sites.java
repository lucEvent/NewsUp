package com.lucevent.newsup.data;

import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.SiteCategory;
import com.lucevent.newsup.data.util.SiteCountry;
import com.lucevent.newsup.data.util.SiteLanguage;

import java.util.ArrayList;
import java.util.Collection;

public class Sites extends ArrayList<Site> {

	public Sites()
	{
		super();
	}

	public Sites(Collection<Site> c)
	{
		super(c);
	}

	public Sites(int capacity)
	{
		super(capacity);
	}

	public static Sites getDefault(boolean finnishSites)
	{
		Sites res = new Sites(100);

		// Spanish news
		res.add(new Site(100, "El Pa\u00EDs", 0xffffffff, "https://elpais.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.ElPaisSections.class, com.lucevent.newsup.data.reader.ElPais.class));
		res.add(new Site(105, "20 Minutos", 0xff0057a3, "https://www.20minutos.es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section._20MinutosSections.class, com.lucevent.newsup.data.reader._20Minutos.class));
		res.add(new Site(110, "El Mundo", 0xffffffff, "http://www.elmundo.es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.ElMundoSections.class, com.lucevent.newsup.data.reader.ElMundo.class));
		res.add(new Site(115, "As", 0xffba0202, "https://as.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.SPORTS,
				com.lucevent.newsup.data.section.AsSections.class, com.lucevent.newsup.data.reader.As.class));
		res.add(new Site(120, "Marca", 0xff04394a, "http://www.marca.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.SPORTS,
				com.lucevent.newsup.data.section.MarcaSections.class, com.lucevent.newsup.data.reader.Marca.class));
		res.add(new Site(125, "El Confidencial", 0xff0a374a, "https://www.elconfidencial.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.ElConfidencialSections.class, com.lucevent.newsup.data.reader.ElConfidencial.class));
		res.add(new Site(130, "El Diario", 0xff0061ab, "https://www.eldiario.es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.ElDiarioSections.class, com.lucevent.newsup.data.reader.ElDiario.class));
		res.add(new Site(135, "La Raz\u00F3n", 0xffc7c7c7, "https://www.larazon.es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.LaRazonSections.class, com.lucevent.newsup.data.reader.LaRazon.class));
		res.add(new Site(140, "Huffington Post Espa\u00F1a", 0xff0dbe98, "https://www.huffingtonpost.es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.HuffingtonPostSpainSections.class, com.lucevent.newsup.data.reader.HuffingtonPostSpain.class));
		res.add(new Site(145, "Europa press", 0xffffffff, "http://www.europapress.es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.EuropaPressSections.class, com.lucevent.newsup.data.reader.EuropaPress.class));
		res.add(new Site(150, "Diario C\u00F3rdoba", 0xffde2632, "http://www.diariocordoba.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.DiarioCordobaSections.class, com.lucevent.newsup.data.reader.DiarioCordoba.class));
		res.add(new Site(155, "Abc", 0xff21435c, "https://www.abc.es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.AbcSections.class, com.lucevent.newsup.data.reader.Abc.class));
		res.add(new Site(160, "El Peri\u00F3dico Extremadura", 0xff357d7c, "http://www.elperiodicoextremadura.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.ElPeriodicoExtremaduraSections.class, com.lucevent.newsup.data.reader.ElPeriodicoExtremadura.class));
		res.add(new Site(165, "P\u00FAblico", 0xffc91435, "https://www.publico.es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.PublicoSections.class, com.lucevent.newsup.data.reader.Publico.class));
		res.add(new Site(170, "Republica", 0xffffffff, "https://www.republica.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.RepublicaSections.class, com.lucevent.newsup.data.reader.Republica.class));

		// Catalan news
		res.add(new Site(200, "El Peri\u00F3dico (Cat)", 0xff477db6, "https://www.elperiodico.cat/ca",
				SiteCountry.SPAIN, SiteLanguage.CATALAN, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.ElPeriodicoCaSections.class, com.lucevent.newsup.data.reader.ElPeriodicoCa.class));
		res.add(new Site(205, "El Peri\u00F3dico (Esp)", 0xfff04d4d, "https://www.elperiodico.com/es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.ElPeriodicoEsSections.class, com.lucevent.newsup.data.reader.ElPeriodicoEs.class));
		res.add(new Site(210, "La Vanguardia", 0xff1a4970, "https://www.lavanguardia.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.LaVanguardiaSections.class, com.lucevent.newsup.data.reader.LaVanguardia.class));
		res.add(new Site(215, "Sport", 0xffd61a1a, "https://www.sport.es/es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.SPORTS,
				com.lucevent.newsup.data.section.SportSections.class, com.lucevent.newsup.data.reader.Sport.class));
		res.add(new Site(220, "Mundo Deportivo", 0xff242424, "https://www.mundodeportivo.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.SPORTS,
				com.lucevent.newsup.data.section.MundoDeportivoSections.class, com.lucevent.newsup.data.reader.MundoDeportivo.class));
		res.add(new Site(225, "Rac\u00F3 Catal\u00E0", 0xffff6347, "https://www.racocatala.cat",
				SiteCountry.SPAIN, SiteLanguage.CATALAN, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.RacoCatalaSections.class, com.lucevent.newsup.data.reader.RacoCatala.class));
		res.add(new Site(230, "VilaWeb", 0xfffd6300, "https://www.vilaweb.cat",
				SiteCountry.SPAIN, SiteLanguage.CATALAN, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.VilaWebSections.class, com.lucevent.newsup.data.reader.VilaWeb.class));
		res.add(new Site(235, "El Punt Avui", 0xff851111, "http://www.elpuntavui.cat",
				SiteCountry.SPAIN, SiteLanguage.CATALAN, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.ElPuntAvuiSections.class, com.lucevent.newsup.data.reader.ElPuntAvui.class));
		res.add(new Site(240, "Catalonia Today", 0xff000000, "https://www.cataloniatoday.cat",
				SiteCountry.SPAIN, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.CataloniaTodaySections.class, com.lucevent.newsup.data.reader.CataloniaToday.class));
		res.add(new Site(245, "L'Esportiu", 0xff9dbc02, "https://www.lesportiudecatalunya.cat",
				SiteCountry.SPAIN, SiteLanguage.CATALAN, SiteCategory.SPORTS,
				com.lucevent.newsup.data.section.LEsportiuSections.class, com.lucevent.newsup.data.reader.LEsportiu.class));
		res.add(new Site(250, "Naci\u00F3 Digital", 0xffe0565a, "https://www.naciodigital.cat",
				SiteCountry.SPAIN, SiteLanguage.CATALAN, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.NacioDigitalSections.class, com.lucevent.newsup.data.reader.NacioDigital.class));
		res.add(new Site(255, "El Nacional", 0xffffd529, "https://www.elnacional.cat",
				SiteCountry.SPAIN, SiteLanguage.VARIOUS, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.ElNacionalSections.class, com.lucevent.newsup.data.reader.ElNacional.class));
		res.add(new Site(260, "Ara", 0xffffffff, "https://www.ara.cat",
				SiteCountry.SPAIN, SiteLanguage.CATALAN, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.AraSections.class, com.lucevent.newsup.data.reader.Ara.class));

		// Swedish news
		res.add(new Site(300, "Aftonbladet", 0xffffffff, "https://www.aftonbladet.se",
				SiteCountry.SWEDEN, SiteLanguage.SWEDISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.AftonbladetSections.class, com.lucevent.newsup.data.reader.Aftonbladet.class));
		res.add(new Site(305, "Expressen", 0xffdb2727, "https://www.expressen.se",
				SiteCountry.SWEDEN, SiteLanguage.SWEDISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.ExpressenSections.class, com.lucevent.newsup.data.reader.Expressen.class));
		res.add(new Site(310, "Dagens Nyheter", 0xffeb1c2a, "https://www.dn.se",
				SiteCountry.SWEDEN, SiteLanguage.SWEDISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.DagensNyheterSections.class, com.lucevent.newsup.data.reader.DagensNyheter.class));
		res.add(new Site(315, "Svenska Dagbladet", 0xffffffff, "https://www.svd.se",
				SiteCountry.SWEDEN, SiteLanguage.SWEDISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.SvenskaDagbladetSections.class, com.lucevent.newsup.data.reader.SvenskaDagbladet.class));
		res.add(new Site(320, "G\u00F6teborgs-Posten", 0xff005a9a, "http://www.gp.se",
				SiteCountry.SWEDEN, SiteLanguage.SWEDISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.GoteborgsPostenSections.class, com.lucevent.newsup.data.reader.GoteborgsPosten.class));
		res.add(new Site(325, "Fria Tider", 0xffffffff, "http://www.friatider.se",
				SiteCountry.SWEDEN, SiteLanguage.SWEDISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.FriaTiderSections.class, com.lucevent.newsup.data.reader.FriaTider.class));

		// Finnish news
		if (finnishSites) {
			res.add(new Site(400, "Helsinki times", 0xff32c8fa, "http://www.helsinkitimes.fi",
					SiteCountry.FINLAND, SiteLanguage.ENGLISH, SiteCategory.NEWS,
					com.lucevent.newsup.data.section.HelsinkiTimesSections.class, com.lucevent.newsup.data.reader.HelsinkiTimes.class));
			res.add(new Site(405, "Helsingin Sanomat", 0xff01133d, "https://www.hs.fi",
					SiteCountry.FINLAND, SiteLanguage.FINNISH, SiteCategory.NEWS,
					com.lucevent.newsup.data.section.HelsinkiSanomatSections.class, com.lucevent.newsup.data.reader.HelsinkiSanomat.class));
			res.add(new Site(410, "Iltalehti", 0xffff0000, "http://www.iltalehti.fi",
					SiteCountry.FINLAND, SiteLanguage.FINNISH, SiteCategory.NEWS,
					com.lucevent.newsup.data.section.IltalehtiSections.class, com.lucevent.newsup.data.reader.Iltalehti.class));
			res.add(new Site(415, "Yle", 0xff00b4c4, "https://yle.fi",
					SiteCountry.FINLAND, SiteLanguage.FINNISH, SiteCategory.NEWS,
					com.lucevent.newsup.data.section.YleSections.class, com.lucevent.newsup.data.reader.Yle.class));
		}
		res.add(new Site(420, "Finland Today", 0xffffffff, "https://finlandtoday.fi",
				SiteCountry.FINLAND, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.FinlandTodaySections.class, com.lucevent.newsup.data.reader.FinlandToday.class));

		// British news
		res.add(new Site(500, "BBC", 0xffa62e30, "http://www.bbc.com",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.BBCSections.class, com.lucevent.newsup.data.reader.BBC.class));
		res.add(new Site(505, "The Telegraph", 0xffffffff, "https://www.telegraph.co.uk",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheTelegraphSections.class, com.lucevent.newsup.data.reader.TheTelegraph.class));
		res.add(new Site(510, "The Huffington Post UK", 0xff0dbe98, "https://www.huffingtonpost.co.uk",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.HuffingtonPostUKSections.class, com.lucevent.newsup.data.reader.HuffingtonPostUK.class));
		res.add(new Site(515, "Metro.co.uk", 0xfff78b26, "https://metro.co.uk",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.MetroCoUKSections.class, com.lucevent.newsup.data.reader.MetroCoUK.class));
		res.add(new Site(520, "The Guardian", 0xff005689, "https://www.theguardian.com",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheGuardianSections.class, com.lucevent.newsup.data.reader.TheGuardian.class));
		res.add(new Site(525, "The Herald Scotland", 0xffffffff, "http://www.heraldscotland.com",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheHeraldScotlandSections.class, com.lucevent.newsup.data.reader.TheHeraldScotland.class));
		res.add(new Site(530, "The Conversation UK", 0xffe43a3c, "https://theconversation.com/uk",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS, /*Analysis, Commentary, Research, News*/
				com.lucevent.newsup.data.section.TheConversationUKSections.class, com.lucevent.newsup.data.reader.TheConversation.class));
		res.add(new Site(535, "The Independent", 0xffe41e2b, "https://www.independent.co.uk",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheIndependentSections.class, com.lucevent.newsup.data.reader.TheIndependent.class));
		res.add(new Site(540, "Daily Mail", 0xff004db3, "http://www.dailymail.co.uk",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.DailyMailSections.class, com.lucevent.newsup.data.reader.DailyMail.class));
		res.add(new Site(545, "The Sun", 0xffcc0001, "https://www.thesun.co.uk",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheSunSections.class, com.lucevent.newsup.data.reader.TheSun.class));
		res.add(new Site(550, "Metro UK", 0xff04326d, "https://www.metro.news",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.MetroUKSections.class, com.lucevent.newsup.data.reader.MetroUK.class));
		res.add(new Site(555, "Evening Standard", 0xffff970d, "https://www.standard.co.uk",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.EveningStandardSections.class, com.lucevent.newsup.data.reader.EveningStandard.class));
		res.add(new Site(560, "Daily Mirror", 0xffe90e0e, "https://www.mirror.co.uk",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.DailyMirrorSections.class, com.lucevent.newsup.data.reader.DailyMirror.class));
		res.add(new Site(565, "The National", 0xffffffff, "http://www.thenational.scot",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheNationalSections.class, com.lucevent.newsup.data.reader.TheNational.class));
		res.add(new Site(570, "Daily Express", 0xffffffff, "https://www.express.co.uk",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.DailyExpressSections.class, com.lucevent.newsup.data.reader.DailyExpress.class));
		res.add(new Site(575, "The Canary", 0xff000000, "https://www.thecanary.co",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheCanarySections.class, com.lucevent.newsup.data.reader.TheCanary.class));

		// American news
		res.add(new Site(600, "CNN", 0xffc20000, "https://edition.cnn.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.CNNSections.class, com.lucevent.newsup.data.reader.CNN.class));
		res.add(new Site(605, "The Huffington Post USA", 0xff0dbe98, "https://www.huffingtonpost.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.HuffingtonPostUSASections.class, com.lucevent.newsup.data.reader.HuffingtonPostUSA.class));
		res.add(new Site(610, "USA Today", 0xff009bff, "https://www.usatoday.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.USATodaySections.class, com.lucevent.newsup.data.reader.USAToday.class));
		res.add(new Site(615, "The New York Times", 0xffffffff, "https://www.nytimes.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheNewYorkTimesSections.class, com.lucevent.newsup.data.reader.TheNewYorkTimes.class));
		res.add(new Site(620, "The Bolivar Commercial", 0xffffffff, "https://bolivarcommercial.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheBolivarCommercialSections.class, com.lucevent.newsup.data.reader.TheBolivarCommercial.class));
		res.add(new Site(625, "Chicago Sun-Times", 0xff333333, "https://chicago.suntimes.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.ChicagoSunTimesSections.class, com.lucevent.newsup.data.reader.ChicagoSunTimes.class));
		res.add(new Site(630, "The Conversation US", 0xffe43a3c, "https://theconversation.com/us",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheConversationUSSections.class, com.lucevent.newsup.data.reader.TheConversation.class));

		//Other newspapers or news websites
		res.add(new Site(700, "The Local", 0xfff76e05, "https://www.thelocal.com",
				SiteCountry.VARIOUS, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheLocalSections.class, com.lucevent.newsup.data.reader.TheLocal.class));
		res.add(new Site(715, "The Huffington Post Canada", 0xff0dbe98, "https://www.huffingtonpost.ca",
				SiteCountry.CANADA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.HuffingtonPostCanadaSections.class, com.lucevent.newsup.data.reader.HuffingtonPostCanada.class));
		res.add(new Site(720, "The Siberian Times", 0xff0271b9, "http://siberiantimes.com",
				SiteCountry.RUSSIA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.SiberianTimesSections.class, com.lucevent.newsup.data.reader.SiberianTimes.class));
		res.add(new Site(725, "The Times of India", 0xff8e1e1d, "https://timesofindia.indiatimes.com",
				SiteCountry.INDIA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheTimesOfIndiaSections.class, com.lucevent.newsup.data.reader.TheTimesOfIndia.class));
		res.add(new Site(730, "La Patilla", 0xff4B8E40, "https://www.lapatilla.com/site",
				SiteCountry.VENEZUELA, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.LaPatillaSections.class, com.lucevent.newsup.data.reader.LaPatilla.class));
		res.add(new Site(735, "The Conversation Australia", 0xffe43a3c, "https://theconversation.com/au",
				SiteCountry.AUSTRALIA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheConversationAustraliaSections.class, com.lucevent.newsup.data.reader.TheConversation.class));
		res.add(new Site(740, "The Conversation Africa", 0xffe43a3c, "https://theconversation.com/africa",
				SiteCountry.VARIOUS, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheConversationAfricaSections.class, com.lucevent.newsup.data.reader.TheConversation.class));
		res.add(new Site(745, "The Conversation France", 0xffe43a3c, "https://theconversation.com/fr",
				SiteCountry.FRANCE, SiteLanguage.FRENCH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheConversationFranceSections.class, com.lucevent.newsup.data.reader.TheConversation.class));
		res.add(new Site(750, "El Imparcial", 0xff00adef, "http://www.elimparcial.com",
				SiteCountry.MEXICO, SiteLanguage.SPANISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.ElImparcialSections.class, com.lucevent.newsup.data.reader.ElImparcial.class));
		res.add(new Site(755, "The Japan Times", 0xffffffff, "https://www.japantimes.co.jp",
				SiteCountry.JAPAN, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheJapanTimesSections.class, com.lucevent.newsup.data.reader.TheJapanTimes.class));
		res.add(new Site(760, "Ottawa Citizen", 0xffffffff, "https://ottawacitizen.com",
				SiteCountry.CANADA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.OttawaCitizenSections.class, com.lucevent.newsup.data.reader.OttawaCitizen.class));

		// Technology sites
		res.add(new Site(800, "El Androide Libre", 0xffa3c23e, "https://elandroidelibre.elespanol.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.ElAndroideLibreSections.class, com.lucevent.newsup.data.reader.ElAndroideLibre.class));
		res.add(new Site(805, "Digital Trends", 0xff0095da, "https://www.digitaltrends.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.DigitalTrendsSections.class, com.lucevent.newsup.data.reader.DigitalTrends.class));
		res.add(new Site(810, "Lifehacker", 0xff94b330, "https://lifehacker.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.LifeHackerSections.class, com.lucevent.newsup.data.reader.LifeHacker.class));
		res.add(new Site(815, "Xataka", 0xff212a34, "https://www.xataka.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.XatakaSections.class, com.lucevent.newsup.data.reader.Xataka.class));
		res.add(new Site(830, "Android Authority", 0xff8cc234, "https://www.androidauthority.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.AndroidAuthoritySections.class, com.lucevent.newsup.data.reader.AndroidAuthority.class));
		res.add(new Site(835, "Computer Hoy", 0xff1a1a1a, "https://computerhoy.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.ComputerHoySections.class, com.lucevent.newsup.data.reader.ComputerHoy.class));
		res.add(new Site(840, "Swedroid", 0xff485366, "https://swedroid.se",
				SiteCountry.SWEDEN, SiteLanguage.SWEDISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.SwedroidSections.class, com.lucevent.newsup.data.reader.Swedroid.class));
		res.add(new Site(845, "Hipertextual", 0xff2799d7, "https://hipertextual.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.HipertextualSections.class, com.lucevent.newsup.data.reader.Hipertextual.class));
		res.add(new Site(850, "Mashable", 0xff01aef0, "https://mashable.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.MashableSections.class, com.lucevent.newsup.data.reader.Mashable.class));
		res.add(new Site(855, "PC World", 0xff941622, "https://www.pcworld.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.PCWorldSections.class, com.lucevent.newsup.data.reader.PCWorld.class));
		res.add(new Site(860, "The Verge", 0xfffa4b2a, "https://www.theverge.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.TheVergeSections.class, com.lucevent.newsup.data.reader.TheVerge.class));
		res.add(new Site(865, "Clipset", 0xff000000, "https://clipset.20minutos.es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.ClipsetSections.class, com.lucevent.newsup.data.reader.Clipset.class));
		res.add(new Site(870, "TechCrunch", 0xff1a9711, "https://techcrunch.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.TechCrunchSections.class, com.lucevent.newsup.data.reader.TechCrunch.class));
		res.add(new Site(875, "Andro4all", 0xffffffff, "https://andro4all.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.Andro4allSections.class, com.lucevent.newsup.data.reader.Andro4all.class));
		res.add(new Site(880, "Topes de Gama", 0xff1B303E, "https://topesdegama.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.TopesDeGamaSections.class, com.lucevent.newsup.data.reader.TopesDeGama.class));
		res.add(new Site(890, "Omicrono", 0xff323232, "https://omicrono.elespanol.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.OmicronoSections.class, com.lucevent.newsup.data.reader.Omicrono.class));
		res.add(new Site(895, "WWWhat's new?", 0xff3d7bb8, "https://wwwhatsnew.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.WwwhatsNewSections.class, com.lucevent.newsup.data.reader.WwwhatsNew.class));

		// Blog sites
		res.add(new Site(900, "Medium", 0xffffffff, "https://medium.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.BLOG,
				com.lucevent.newsup.data.section.MediumSections.class, com.lucevent.newsup.data.reader.Medium.class));
		res.add(new Site(905, "Verne", 0xff02b283, "https://verne.elpais.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.BLOG,
				com.lucevent.newsup.data.section.VerneSections.class, com.lucevent.newsup.data.reader.Verne.class));
		res.add(new Site(910, "Digital Inspiration", 0xff000000, "https://www.labnol.org",
				SiteCountry.INDIA, SiteLanguage.ENGLISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.DigitalInspirationSections.class, com.lucevent.newsup.data.reader.DigitalInspiration.class));
		res.add(new Site(915, "Google Earth Blog", 0xffffffff, "https://www.gearthblog.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.BLOG,
				com.lucevent.newsup.data.section.GoogleEarthBlogSections.class, com.lucevent.newsup.data.reader.GoogleEarthBlog.class));

		// Magazines sites (1000)
		res.add(new Site(1010, "Rolling Stone", 0xff1c202b, "https://www.rollingstone.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.MUSIC,
				com.lucevent.newsup.data.section.RollingStoneSections.class, com.lucevent.newsup.data.reader.RollingStone.class));
		res.add(new Site(1015, "People", 0xff20b3e8, "https://people.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.GOSSIP,
				com.lucevent.newsup.data.section.PeopleSections.class, com.lucevent.newsup.data.reader.People.class));
		res.add(new Site(1020, "Time", 0xffe60000, "http://time.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TimeSections.class, com.lucevent.newsup.data.reader.Time.class));
		res.add(new Site(1025, "The Atlantic", 0xff030202, "https://www.theatlantic.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.CULTURE,
				com.lucevent.newsup.data.section.TheAtlanticSections.class, com.lucevent.newsup.data.reader.TheAtlantic.class));
		res.add(new Site(1000, "Make:", 0xff4ecbf5, "https://makezine.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.DIY,
				com.lucevent.newsup.data.section.MakeSections.class, com.lucevent.newsup.data.reader.Make.class));
		res.add(new Site(1005, "Discover", 0xff171717, "http://discovermagazine.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.SCIENCE,
				com.lucevent.newsup.data.section.DiscoverSections.class, com.lucevent.newsup.data.reader.Discover.class));
		res.add(new Site(1045, "Digital Camera", 0xffffffff, "http://www.digitalcamera.es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.DigitalCameraSections.class, com.lucevent.newsup.data.reader.DigitalCamera.class));
		res.add(new Site(1060, "Elle", 0xffffffff, "https://www.elle.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.FASHION,
				com.lucevent.newsup.data.section.ElleSections.class, com.lucevent.newsup.data.reader.Elle.class));
		res.add(new Site(1065, "The Intercept", 0xff000000, "https://theintercept.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.POLITICS,
				com.lucevent.newsup.data.section.TheInterceptSections.class, com.lucevent.newsup.data.reader.TheIntercept.class));
		res.add(new Site(1075, "Vogue Espa\u00F1a", 0xff000000, "https://www.vogue.es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.FASHION,
				com.lucevent.newsup.data.section.VogueEsSections.class, com.lucevent.newsup.data.reader.VogueEs.class));

		res.add(new Site(1035, "Dogster", 0xff547a94, "https://www.dogster.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.PETS,
				com.lucevent.newsup.data.section.DogsterSections.class, com.lucevent.newsup.data.reader.Dogster.class));
		res.add(new Site(1040, "El Jueves", 0xffcb1f1f, "http://www.eljueves.es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.SATIRICAL,
				com.lucevent.newsup.data.section.ElJuevesSections.class, com.lucevent.newsup.data.reader.ElJueves.class));
		res.add(new Site(1055, "CounterPunch", 0xffdb0c0c, "https://www.counterpunch.org",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.POLITICS,
				com.lucevent.newsup.data.section.CounterPunchSections.class, com.lucevent.newsup.data.reader.CounterPunch.class));
		res.add(new Site(1070, "Lucky Puppy Mag", 0xff8224e3, "https://www.luckypuppymag.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.PETS,
				com.lucevent.newsup.data.section.LuckyPuppySections.class, com.lucevent.newsup.data.reader.LuckyPuppy.class));
		res.add(new Site(1080, "Sports Illustrated", 0xff09132a, "https://www.si.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.SPORTS,
				com.lucevent.newsup.data.section.SportsIllustratedSections.class, com.lucevent.newsup.data.reader.SportsIllustrated.class));
		res.add(new Site(1085, "WIRED", 0xff000000, "https://www.wired.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.WiredSections.class, com.lucevent.newsup.data.reader.Wired.class));

		// Astronomy (1200)
		res.add(new Site(1030, "Sky and Telescope", 0xffd92326, "https://www.skyandtelescope.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.ASTRONOMY,
				com.lucevent.newsup.data.section.SkyAndTelescopeSections.class, com.lucevent.newsup.data.reader.SkyAndTelescope.class));
		res.add(new Site(1050, "Space News", 0xffffffff, "https://spacenews.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.ASTRONOMY,
				com.lucevent.newsup.data.section.SpaceNewsSections.class, com.lucevent.newsup.data.reader.SpaceNews.class));
		res.add(new Site(1215, "Cosmo Noticias", 0xff181818, "http://www.cosmonoticias.org",
				SiteCountry.CHILE, SiteLanguage.SPANISH, SiteCategory.ASTRONOMY,
				com.lucevent.newsup.data.section.CosmoNoticiasSections.class, com.lucevent.newsup.data.reader.CosmoNoticias.class));
		res.add(new Site(1220, "Universe Today", 0xff6963ac, "https://www.universetoday.com",
				SiteCountry.CANADA, SiteLanguage.ENGLISH, SiteCategory.ASTRONOMY,
				com.lucevent.newsup.data.section.UniverseTodaySections.class, com.lucevent.newsup.data.reader.UniverseToday.class));
		res.add(new Site(1225, "Astronomy Now", 0xffdd3333, "https://astronomynow.com",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.ASTRONOMY,
				com.lucevent.newsup.data.section.AstronomyNowSections.class, com.lucevent.newsup.data.reader.AstronomyNow.class));

		// Videogames (1300)
		res.add(new Site(1305, "Vandal", 0xffffffff, "https://vandal.elespanol.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.VIDEOGAMES,
				com.lucevent.newsup.data.section.VandalSections.class, com.lucevent.newsup.data.reader.Vandal.class));
		res.add(new Site(1310, "Pokemon Go", 0xff152bd5, "https://pokemongolive.com",
				SiteCountry.USA, SiteLanguage.VARIOUS, SiteCategory.VIDEOGAMES,
				com.lucevent.newsup.data.section.PokemonGoSections.class, com.lucevent.newsup.data.reader.PokemonGo.class));
		res.add(new Site(1315, "Pokemon GO Hub", 0xff1cbc7c, "https://pokemongohub.net",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.VIDEOGAMES,
				com.lucevent.newsup.data.section.PokemonGoHubSections.class, com.lucevent.newsup.data.reader.PokemonGoHub.class));
		res.add(new Site(1320, "Nintenderos", 0xffff0000, "http://www.nintenderos.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.VIDEOGAMES,
				com.lucevent.newsup.data.section.NintenderosSections.class, com.lucevent.newsup.data.reader.Nintenderos.class));
		res.add(new Site(1325, "IGN Espa\u00F1a", 0xffd3222a, "https://es.ign.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.VIDEOGAMES,
				com.lucevent.newsup.data.section.IGNEsSections.class, com.lucevent.newsup.data.reader.IGNEs.class));
		res.add(new Site(1330, "IGN", 0xffd3222a, "https://www.ign.com",
				SiteCountry.VARIOUS, SiteLanguage.VARIOUS, SiteCategory.VIDEOGAMES,
				com.lucevent.newsup.data.section.IGNSections.class, com.lucevent.newsup.data.reader.IGN.class));

		// LifeStyle (1400)
		res.add(new Site(1400, "Vice", 0xff000000, "https://www.vice.com",
				SiteCountry.VARIOUS, SiteLanguage.VARIOUS, SiteCategory.LIFESTYLE,
				com.lucevent.newsup.data.section.ViceSections.class, com.lucevent.newsup.data.reader.Vice.class));
		res.add(new Site(1405, "C\u00F3digo Nuevo", 0xff000000, "https://www.codigonuevo.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.LIFESTYLE,
				com.lucevent.newsup.data.section.CodigoNuevoSections.class, com.lucevent.newsup.data.reader.CodigoNuevo.class));

		// Science (1500)
		res.add(new Site(820, "TED", 0xffffffff, "https://www.ted.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.SCIENCE,
				com.lucevent.newsup.data.section.TEDSections.class, com.lucevent.newsup.data.reader.TED.class));
		res.add(new Site(1500, "Life Science Sweden", 0xff962D31, "https://www.lifesciencesweden.se",
				SiteCountry.SWEDEN, SiteLanguage.SWEDISH, SiteCategory.SCIENCE,
				com.lucevent.newsup.data.section.LifeScienceSwedenSections.class, com.lucevent.newsup.data.reader.LifeScienceSweden.class));
		res.add(new Site(1505, "National Geographic en Espa\u00f1ol", 0xffffffff, "https://www.ngenespanol.com",
				SiteCountry.MEXICO, SiteLanguage.SPANISH, SiteCategory.SCIENCE,
				com.lucevent.newsup.data.section.NationalGeographicEsSections.class, com.lucevent.newsup.data.reader.NationalGeographicEs.class));

		// Motor (1600)
		res.add(new Site(1600, "Teknikens V\u00E4rld", 0xffEE1D23, "https://teknikensvarld.se",
				SiteCountry.SWEDEN, SiteLanguage.SWEDISH, SiteCategory.MOTOR,
				com.lucevent.newsup.data.section.TeknikensVarldSections.class, com.lucevent.newsup.data.reader.TeknikensVarld.class));

		// Entertainment (1700)
		res.add(new Site(1700, "Coming Soon", 0xff000000, "http://www.comingsoon.net",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.ENTERTAINMENT,
				com.lucevent.newsup.data.section.ComingSoonSections.class, com.lucevent.newsup.data.reader.ComingSoon.class));
		res.add(new Site(1705, "The Chive", 0xff000000, "http://thechive.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.ENTERTAINMENT,
				com.lucevent.newsup.data.section.TheChiveSections.class, com.lucevent.newsup.data.reader.TheChive.class));

		// Fitness (1800)

		// Other
		res.add(new Site(825, "Gizmodo", 0xff9c9c9c, "http://gizmodo.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.SCIENCE,
				com.lucevent.newsup.data.section.GizmodoSections.class, com.lucevent.newsup.data.reader.Gizmodo.class));
		res.add(new Site(826, "Gizmodo UK", 0xff212631, "http://www.gizmodo.co.uk",
				SiteCountry.UK, SiteLanguage.ENGLISH, SiteCategory.SCIENCE,
				com.lucevent.newsup.data.section.GizmodoUkSections.class, com.lucevent.newsup.data.reader.GizmodoUk.class));
		res.add(new Site(827, "Gizmodo en Espa\u00F1ol", 0xff9c9c9c, "https://es.gizmodo.com",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.SCIENCE,
				com.lucevent.newsup.data.section.GizmodoEsSections.class, com.lucevent.newsup.data.reader.GizmodoEs.class));
		res.add(new Site(828, "Gizmodo Australia", 0xff7db2d2, "https://www.gizmodo.com.au",
				SiteCountry.AUSTRALIA, SiteLanguage.ENGLISH, SiteCategory.SCIENCE,
				com.lucevent.newsup.data.section.GizmodoAuSections.class, com.lucevent.newsup.data.reader.GizmodoAu.class));

		// More (2000-2999)
		res.add(new Site(2000, "The A.V. Club", 0xff1C2733, "https://www.avclub.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.MUSIC,
				com.lucevent.newsup.data.section.TheAVClubSections.class, com.lucevent.newsup.data.reader.TheAVClub.class));
		res.add(new Site(2005, "Deadspin", 0xff1B3A4E, "https://deadspin.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.SPORTS,
				com.lucevent.newsup.data.section.DeadspinSections.class, com.lucevent.newsup.data.reader.Deadspin.class));
		res.add(new Site(2010, "Jalopnik", 0xffFE4D00, "https://jalopnik.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.MOTOR,
				com.lucevent.newsup.data.section.JalopnikSections.class, com.lucevent.newsup.data.reader.Jalopnik.class));
		res.add(new Site(2015, "Jezebel", 0xffED1238, "https://jezebel.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.GOSSIP,
				com.lucevent.newsup.data.section.JezebelSections.class, com.lucevent.newsup.data.reader.Jezebel.class));
		res.add(new Site(2020, "Kotaku", 0xffFBC000, "https://kotaku.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.VIDEOGAMES,
				com.lucevent.newsup.data.section.KotakuSections.class, com.lucevent.newsup.data.reader.Kotaku.class));
		res.add(new Site(2025, "Splinter", 0xffF95637, "https://splinternews.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.SplinterSections.class, com.lucevent.newsup.data.reader.Splinter.class));
		res.add(new Site(2030, "The Takeout", 0xffE5371A, "https://thetakeout.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.GASTRONOMY,
				com.lucevent.newsup.data.section.TheTakeoutSections.class, com.lucevent.newsup.data.reader.TheTakeout.class));
		res.add(new Site(2035, "The Root", 0xff59A624, "https://www.theroot.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheRootSections.class, com.lucevent.newsup.data.reader.TheRoot.class));
		res.add(new Site(2040, "The Onion", 0xff006B3A, "https://www.theonion.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.NEWS,
				com.lucevent.newsup.data.section.TheOnionSections.class, com.lucevent.newsup.data.reader.TheOnion.class));
		res.add(new Site(2045, "ClickHole", 0xffFE6700, "https://www.clickhole.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.SATIRICAL,
				com.lucevent.newsup.data.section.ClickHoleSections.class, com.lucevent.newsup.data.reader.ClickHole.class));
		res.add(new Site(2050, "The Inventory", 0xff8F085E, "https://theinventory.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.CONSUMPTION,
				com.lucevent.newsup.data.section.TheInventorySections.class, com.lucevent.newsup.data.reader.TheInventory.class));
		res.add(new Site(2055, "EUobserver", 0xff27303f, "https://euobserver.com",
				SiteCountry.VARIOUS, SiteLanguage.ENGLISH, SiteCategory.POLITICS,
				com.lucevent.newsup.data.section.EUObserverSections.class, com.lucevent.newsup.data.reader.EUObserver.class));
		res.add(new Site(2060, "Motherboard", 0xffffffff, "https://motherboard.vice.com",
				SiteCountry.VARIOUS, SiteLanguage.VARIOUS, SiteCategory.CULTURE,
				com.lucevent.newsup.data.section.MotherboardSections.class, com.lucevent.newsup.data.reader.Motherboard.class));
		res.add(new Site(2065, "Digital Trends Espa\u00F1ol", 0xff0095da, "https://es.digitaltrends.com",
				SiteCountry.USA, SiteLanguage.SPANISH, SiteCategory.TECHNOLOGY,
				com.lucevent.newsup.data.section.DigitalTrendsEsSections.class, com.lucevent.newsup.data.reader.DigitalTrendsEs.class));
		res.add(new Site(2070, "The Ringer", 0xff000000, "https://www.theringer.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.CULTURE,
				com.lucevent.newsup.data.section.TheRingerSections.class, com.lucevent.newsup.data.reader.TheRinger.class));
		res.add(new Site(2075, "iFixit", 0xffffffff, "https://www.ifixit.com",
				SiteCountry.USA, SiteLanguage.ENGLISH, SiteCategory.DIY,
				com.lucevent.newsup.data.section.iFixitSections.class, com.lucevent.newsup.data.reader.iFixit.class));

		// Economy (3000-3099)
		res.add(new Site(3000, "El Economista", 0xffff6600, "https://www.eleconomista.es",
				SiteCountry.SPAIN, SiteLanguage.SPANISH, SiteCategory.ECONOMY,
				com.lucevent.newsup.data.section.ElEconomistaSections.class, com.lucevent.newsup.data.reader.ElEconomista.class));

		return res;
	}

	public Site getSiteByCode(int code)
	{
		for (Site site : this)
			if (site.code == code)
				return site;

		return null;
	}

}