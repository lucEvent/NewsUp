package com.lucevent.newsup.development;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.reader.ElImparcial;
import com.lucevent.newsup.data.section.ElImparcialSections;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsSet;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.SiteCategory;
import com.lucevent.newsup.data.util.SiteCountry;
import com.lucevent.newsup.data.util.SiteLanguage;
import com.lucevent.newsup.data.util.Tags;
import com.lucevent.newsup.development.utils.FullTest;
import com.lucevent.newsup.development.utils.HardDrive;
import com.lucevent.newsup.development.utils.NewsReader;
import com.lucevent.newsup.development.utils.SectionsTest;
import com.lucevent.newsup.development.utils.ServerReader;
import com.lucevent.newsup.development.utils.Test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

@SuppressWarnings("ALL")
public class Main {

	enum Process {
		TITLES, NEWS, DATES, TAGS, EXTRACT_CONTENT, FULL_TEST, COUNT_NEWS,
		SECTIONS_TEST, DOWNLOAD_ERRORS, GOOGLE_PLAY_SITES_TEXT
	}

	public static void main(String[] args)
	{
		Site s = null;
		s = new Site(0, "El Imparcial", 0, "", 0, 0, 0, ElImparcialSections.class, ElImparcial.class);
//		s = new Site(0, "Life Science Sweden", 0, "", 0, 0, 0, LifeScienceSwedenSections.class, LifeScienceSweden.class);
// [October]
//		s = new Site(0, "ElPeriodicoExtremadura", 0, "", 0, 0, 0, ElPeriodicoExtremaduraSections.class, ElPeriodicoExtremadura.class);
//		s = new Site(0, "CounterPunch", 0, "", 0, 0, 0, CounterPunchSections.class, CounterPunch.class);
//		s = new Site(0, "Andro4all", 0, "", 0, 0, 0, Andro4allSections.class, Andro4all.class);
//		s = new Site(0, "ElMundo", 0, "", 0, 0, 0, ElMundoSections.class, ElMundo.class);
//		s = new Site(0, "TheNational", 0, "", 0, 0, 0, TheNationalSections.class, TheNational.class);
// [November]
//		s = new Site(0, "Time", 0, "", 0, 0, 0, TimeSections.class, Time.class);
//		s = new Site(0, "Topes de Gama", 0, "", 0, 0, 0, TopesDeGamaSections.class, TopesDeGama.class);
//		s = new Site(0, "ElPuntAvui", 0, "", 0, 0, 0, ElPuntAvuiSections.class, ElPuntAvui.class);
// [February]
//		s = new Site(1080, "SportsIllustrated", 0, "", 0, 0, 0, SportsIllustratedSections.class, SportsIllustrated.class);
// [March]
//		s = new Site(0, "HelsinkiSanomat", 0, "", 0, 0, 0, HelsinkiSanomatSections.class, HelsinkiSanomat.class);
//		s = new Site(0, "Xataka", 0, "", 0, 0, 0, XatakaSections.class, Xataka.class);
//		s = new Site(0, "DiarioCordoba", 0, "", 0, 0, 0, DiarioCordobaSections.class, DiarioCordoba.class);
//		s = new Site(0, "Sport", 0, "", 0, 0, 0, SportSections.class, Sport.class);
//		s = new Site(0, "Mashable", 0, "", 0, 0, 0, MashableSections.class, Mashable.class);
//		s = new Site(0, "_20Minutos", 0, "", 0, 0, 0, _20MinutosSections.class, _20Minutos.class);
//		s = new Site(0, "As", 0, "", 0, 0, 0, AsSections.class, As.class);
//		s = new Site(0, "ElConfidencial", 0, "", 0, 0, 0, ElConfidencialSections.class, ElConfidencial.class);
//		s = new Site(0, "HuffingtonPostSpain", 0, "", 0, 0, 0, HuffingtonPostSpainSections.class, HuffingtonPostSpain.class);
//		s = new Site(0, "ElNacional", 0, "", 0, 0, 0, ElNacionalSections.class, ElNacional.class);
//		s = new Site(0, "Aftonbladet", 0, "", 0, 0, 0, AftonbladetSections.class, Aftonbladet.class);
//		s = new Site(0, "SvenskaDagbladet", 0, "", 0, 0, 0, SvenskaDagbladetSections.class, SvenskaDagbladet.class);
//		s = new Site(0, "The Atlantic", 0, "", 0, 0, 0, TheAtlanticSections.class, TheAtlantic.class);
//		s = new Site(0, "The Intercept", 0, "", 0, 0, 0, TheInterceptSections.class, TheIntercept.class);
//		s = new Site(0, "PokemonGo", 0, "", 0, 0, 0, PokemonGoSections.class, PokemonGo.class);
//		s = new Site(0, "Gizmodo Australia", 0, "", 0, 0, 0, GizmodoAuSections.class, GizmodoAu.class);
//		s = new Site(0, "TED", 0, "", 0, 0, 0, TEDSections.class, TED.class);
//		s = new Site(0, "Android Authority", 0, "", 0, 0, 0, AndroidAuthoritySections.class, AndroidAuthority.class);
//		s = new Site(0, "Make", 0, "", 0, 0, 0, MakeSections.class, Make.class);
//		s = new Site(0, "TheLocal", 0, "", 0, 0, 0, TheLocalSections.class, TheLocal.class);
//		s = new Site(0, "Gizmodo en Español", 0, "", 0, 0, 0, GizmodoEsSections.class, GizmodoEs.class);
//		s = new Site(0, "Verne", 0, "", 0, 0, 0, VerneSections.class, Verne.class);
//		s = new Site(0, "Hipertextual", 0, "", 0, 0, 0, HipertextualSections.class, Hipertextual.class);
// [April]
//		s = new Site(0, "EveningStandard", 0, "", 0, 0, 0, EveningStandardSections.class, EveningStandard.class);
//		s = new Site(0, "DailyMirror", 0, "", 0, 0, 0, DailyMirrorSections.class, DailyMirror.class);
// [May]
//		s = new Site(0, "TheTelegraph", 0, "", 0, 0, 0, TheTelegraphSections.class, TheTelegraph.class);
// [June]
//		s = new Site(0, "Nintenderos", 0, "", 0, 0, 0, NintenderosSections.class, Nintenderos.class);
//		s = new Site(0, "EuropaPress", 0, "", 0, 0, 0, EuropaPressSections.class, EuropaPress.class);
// [July]
//		s = new Site(0, "RollingStone", 0, "", 0, 0, 0, RollingStoneSections.class, RollingStone.class);
//		s = new Site(600, "CNN", 0, "", 0, 0, 0, CNNSections.class, CNN.class);
//		s = new Site(0, "TheIndependent", 0, "", 0, 0, 0, TheIndependentSections.class, TheIndependent.class);
//		s = new Site(0, "Yle", 0, "", 0, 0, 0, YleSections.class, Yle.class);
// [August]
//		s = new Site(0, "El Jueves", 0, "", 0, 0, 0, ElJuevesSections.class, ElJueves.class);
//		s = new Site(0, "Abc", 0, "", 0, 0, 0, AbcSections.class, Abc.class);
//		s = new Site(0, "CataloniaToday", 0, "", 0, 0, 0, CataloniaTodaySections.class, CataloniaToday.class);
//		s = new Site(0, "L'Esportiu", 0, "", 0, 0, 0, LEsportiuSections.class, LEsportiu.class);
//		s = new Site(0, "MetroCoUK", 0, "", 0, 0, 0, MetroCoUKSections.class, MetroCoUK.class);
//		s = new Site(0, "Publico", 0, "", 0, 0, 0, PublicoSections.class, Publico.class);
// [September]
//		s = new Site(0, "TheNewYorkTimes", 0, "", 0, 0, 0, TheNewYorkTimesSections.class, TheNewYorkTimes.class);
//		s = new Site(0, "TheBolivarCommercial", 0, "", 0, 0, 0, TheBolivarCommercialSections.class, TheBolivarCommercial.class);
//		s = new Site(0, "Chicago Sun-Times", 0, "", 0, 0, 0, ChicagoSunTimesSections.class, ChicagoSunTimes.class);
//		s = new Site(0, "Swedroid", 0, "", 0, 0, 0, SwedroidSections.class, Swedroid.class);
//		s = new Site(0, "VogueEs", 0, "", 0, 0, 0, VogueEsSections.class, VogueEs.class);
//		s = new Site(0, "Sky and Telescope", 0, "", 0, 0, 0, SkyAndTelescopeSections.class, SkyAndTelescope.class);
//		s = new Site(0, "Space News", 0, "", 0, 0, 0, SpaceNewsSections.class, SpaceNews.class);
//		s = new Site(0, "Codigo Nuevo", 0, "", 0, 0, 0, CodigoNuevoSections.class, CodigoNuevo.class);
//		s = new Site(0, "ElPeriodicoCa", 0, "", 0, 0, 0, ElPeriodicoCaSections.class, ElPeriodicoCa.class);
//		s = new Site(0, "ElPeriodicoEs", 0, "", 0, 0, 0, ElPeriodicoEsSections.class, ElPeriodicoEs.class);
//		s = new Site(510, "HuffingtonPostUK", 0, "", 0, 0, 0, HuffingtonPostUKSections.class, HuffingtonPostUK.class);
//		s = new Site(0, "The Conversation Australia", 0, "", 0, 0, 0, TheConversationAustraliaSections.class, TheConversation.class);
//		s = new Site(0, "The Conversation US", 0, "", 0, 0, 0, TheConversationUSSections.class, TheConversation.class);
//		s = new Site(0, "The Conversation France", 0, "", 0, 0, 0, TheConversationFranceSections.class, TheConversation.class);
//		s = new Site(0, "The Conversation Africa", 0, "", 0, 0, 0, TheConversationAfricaSections.class, TheConversation.class);
//		s = new Site(0, "The Conversation UK", 0, "", 0, 0, 0, TheConversationUKSections.class, TheConversation.class);
//		s = new Site(0, "MetroUK", 0, "", 0, 0, 0, MetroUKSections.class, MetroUK.class);
//		s = new Site(0, "HuffingtonPostCanada", 0, "", 0, 0, 0, HuffingtonPostCanadaSections.class, HuffingtonPostCanada.class);
//		s = new Site(725, "TheTimesOfIndia", 0, "", 0, 0, 0, TheTimesOfIndiaSections.class, TheTimesOfIndia.class);
//		s = new Site(730, "LaPatilla", 0, "", 0, 0, 0, LaPatillaSections.class, LaPatilla.class);
//		s = new Site(0, "LifeHacker", 0, "", 0, 0, 0, LifeHackerSections.class, LifeHacker.class);
//		s = new Site(0, "ComputerHoy", 0, "", 0, 0, 0, ComputerHoySections.class, ComputerHoy.class);
//		s = new Site(0, "The Verge", 0, "", 0, 0, 0, TheVergeSections.class, TheVerge.class);
//		s = new Site(0, "Clipset", 0, "", 0, 0, 0, ClipsetSections.class, Clipset.class);
//		s = new Site(0, "TechCrunch", 0, "", 0, 0, 0, TechCrunchSections.class, TechCrunch.class);
//		s = new Site(0, "DigitalInspiration", 0, "", 0, 0, 0, DigitalInspirationSections.class, DigitalInspiration.class);
//		s = new Site(0, "GoogleEarthBlog", 0, "", 0, 0, 0, GoogleEarthBlogSections.class, GoogleEarthBlog.class);
//		s = new Site(0, "Digital Camera", 0, "", 0, 0, 0, DigitalCameraSections.class, DigitalCamera.class);
//		s = new Site(0, "Dogster", 0, "", 0, 0, 0, DogsterSections.class, Dogster.class);
//		s = new Site(0, "CosmoNoticias", 0, "", 0, 0, 0, CosmoNoticiasSections.class, CosmoNoticias.class);
//		s = new Site(0, "Astronomy Now", 0, "", 0, 0, 0, AstronomyNowSections.class, AstronomyNow.class);
//		s = new Site(0, "Vandal", 0, "", 0, 0, 0, VandalSections.class, Vandal.class);
//		s = new Site(0, "PokemonGoHub", 0, "", 0, 0, 0, PokemonGoHubSections.class, PokemonGoHub.class);
//		s = new Site(0, "Teknikens Värld", 0, "", 0, 0, 0, TeknikensVarldSections.class, TeknikensVarld.class);
//		s = new Site(0, "Coming Soon", 0, "", 0, 0, 0, ComingSoonSections.class, ComingSoon.class);
//		s = new Site(0, "Gizmodo", 0, "", 0, 0, 0, GizmodoSections.class, Gizmodo.class);
//		s = new Site(0, "The A.V. Club", 0, "", 0, 0, 0, TheAVClubSections.class, TheAVClub.class);
//		s = new Site(0, "Deadspin", 0, "", 0, 0, 0, DeadspinSections.class, Deadspin.class);
//		s = new Site(0, "Jalopnik", 0, "", 0, 0, 0, JalopnikSections.class, Jalopnik.class);
//		s = new Site(0, "Jezebel", 0, "", 0, 0, 0, JezebelSections.class, Jezebel.class);
//		s = new Site(0, "Kotaku", 0, "", 0, 0, 0, KotakuSections.class, Kotaku.class);
//		s = new Site(0, "Splinter", 0, "", 0, 0, 0, SplinterSections.class, Splinter.class);
//		s = new Site(0, "The Takeout", 0, "", 0, 0, 0, TheTakeoutSections.class, TheTakeout.class);
//		s = new Site(0, "The Root", 0, "", 0, 0, 0, TheRootSections.class, TheRoot.class);
//		s = new Site(0, "The Onion", 0, "", 0, 0, 0, TheOnionSections.class, TheOnion.class);
//		s = new Site(0, "ClickHole", 0, "", 0, 0, 0, ClickHoleSections.class, ClickHole.class);
//		s = new Site(0, "The Inventory", 0, "", 0, 0, 0, TheInventorySections.class, TheInventory.class);
//		s = new Site(0, "UniverseToday", 0, "", 0, 0, 0, UniverseTodaySections.class, UniverseToday.class);
//		s = new Site(0, "Gizmodo UK", 0, "", 0, 0, 0, GizmodoUkSections.class, GizmodoUk.class);
// [October]
//		s = new Site(0, "IGN España", 0, "", 0, 0, 0, IGNEsSections.class, IGNEs.class);
//		s = new Site(0, "IGN", 0, "", 0, 0, 0, IGNSections.class, IGN.class);
//		s = new Site(0, "El Economista", 0, "", 0, 0, 0, ElEconomistaSections.class, ElEconomista.class);
//		s = new Site(0, "FriaTider", 0, "", 0, 0, 0, FriaTiderSections.class, FriaTider.class);
//		s = new Site(0, "ElDiario", 0, "", 0, 0, 0, ElDiarioSections.class, ElDiario.class);
//		s = new Site(0, "ElAndroideLibre", 0, "", 0, 0, 0, ElAndroideLibreSections.class, ElAndroideLibre.class);
//		s = new Site(0, "Elle", 0, "", 0, 0, 0, ElleSections.class, Elle.class);
//		s = new Site(1085, "WIRED", 0, "", 0, 0, 0, WiredSections.class, Wired.class);
// [November]
//		s = new Site(0, "Iltalehti", 0, "", 0, 0, 0, IltalehtiSections.class, Iltalehti.class);
//		s = new Site(0, "Omicrono", 0, "", 0, 0, 0, OmicronoSections.class, Omicrono.class);
//		s = new Site(1015, "People", 0, "", 0, 0, 0, PeopleSections.class, People.class);
//		s = new Site(0, "EUObserver", 0, "", 0, 0, 0, EUObserverSections.class, EUObserver.class);
//		s = new Site(0, "NationalGeographicEs", 0, "", 0, 0, 0, NationalGeographicEsSections.class, NationalGeographicEs.class);
//		s = new Site(0, "Motherboard", 0, "", 0, 0, 0, MotherboardSections.class, Motherboard.class);
//		s = new Site(0, "DigitalTrendsEs", 0, "", 0, 0, 0, DigitalTrendsEsSections.class, DigitalTrendsEs.class);
//		s = new Site(0, "Ara", 0, "", 0, 0, 0, AraSections.class, Ara.class);
//		s = new Site(0, "TheJapanTimes", 0, "", 0, 0, 0, TheJapanTimesSections.class, TheJapanTimes.class);
//		s = new Site(0, "ElPais", 0, "", 0, 0, 0, ElPaisSections.class, ElPais.class);
//		s = new Site(0, "TheHeraldScotland", 0, "", 0, 0, 0, TheHeraldScotlandSections.class, TheHeraldScotland.class);
//		s = new Site(0, "DagensNyheter", 0, "", 0, 0, 0, DagensNyheterSections.class, DagensNyheter.class);
//		s = new Site(0, "Republica", 0, "", 0, 0, 0, RepublicaSections.class, Republica.class);
//		s = new Site(0, "TheRinger", 0, "", 0, 0, 0, TheRingerSections.class, TheRinger.class);
//		s = new Site(0, "FinlandToday", 0, "", 0, 0, 0, FinlandTodaySections.class, FinlandToday.class);
//		s = new Site(0, "Discover", 0, "", 0, 0, 0, DiscoverSections.class, Discover.class);
//		s = new Site(0, "TheGuardian", 0, "", 0, 0, 0, TheGuardianSections.class, TheGuardian.class);
//		s = new Site(0, "Digital Trends", 0, "", 0, 0, 0, DigitalTrendsSections.class, DigitalTrends.class);
//		s = new Site(0, "TheChive", 0, "", 0, 0, 0, TheChiveSections.class, TheChive.class);
//		s = new Site(0, "Vice", 0, "", 0, 0, 0, ViceSections.class, Vice.class);
//		s = new Site(0, "DailyExpress", 0, "", 0, 0, 0, DailyExpressSections.class, DailyExpress.class);
//		s = new Site(0, "DailyMail", 0, "", 0, 0, 0, DailyMailSections.class, DailyMail.class);
//		s = new Site(0, "Medium", 0, "", 0, 0, 0, MediumSections.class, Medium.class);
//		s = new Site(0, "LaVanguardia", 0, "", 0, 0, 0, LaVanguardiaSections.class, LaVanguardia.class);
//		s = new Site(545, "The Sun", 0, "", 0, 0, 0, TheSunSections.class, TheSun.class);
//		s = new Site(0, "Nació Digital", 0, "", 0, 0, 0, NacioDigitalSections.class, NacioDigital.class);
//		s = new Site(0, "VilaWeb", 0, "", 0, 0, 0, VilaWebSections.class, VilaWeb.class);
//		s = new Site(0, "LaRazon", 0, "", 0, 0, 0, LaRazonSections.class, LaRazon.class);
//		s = new Site(0, "WwwhatsNew", 0, "", 0, 0, 0, WwwhatsNewSections.class, WwwhatsNew.class);
//		s = new Site(0, "LuckyPuppy", 0, "", 0, 0, 0, LuckyPuppySections.class, LuckyPuppy.class);
//		s = new Site(0, "HuffingtonPostUSA", 0, "", 0, 0, 0, HuffingtonPostUSASections.class, HuffingtonPostUSA.class);
//		s = new Site(0, "OttawaCitizen", 0, "", 0, 0, 0, OttawaCitizenSections.class, OttawaCitizen.class);
//		s = new Site(0, "iFixit", 0, "", 0, 0, 0, iFixitSections.class, iFixit.class);
//		s = new Site(0, "TheCanary", 0, "", 0, 0, 0, TheCanarySections.class, TheCanary.class);
//		s = new Site(0, "Göteborgs-Posten", 0, "", 0, 0, 0, GoteborgsPostenSections.class, GoteborgsPosten.class);
//[January]
//		s = new Site(0, "TheNewYorkTimes", 0, "", 0, 0, 0, TheNewYorkTimesSections.class, TheNewYorkTimes.class);
//		s = new Site(0, "MundoDeportivo", 0, "", 0, 0, 0, MundoDeportivoSections.class, MundoDeportivo.class);
//		s = new Site(0, "PC World", 0, "", 0, 0, 0, PCWorldSections.class, PCWorld.class);
//		s = new Site(0, "Expressen", 0, "", 0, 0, 0, ExpressenSections.class, Expressen.class);

		/*
		 */
		// TITLES, NEWS, DATES, TAGS, FULL_TEST, SECTIONS_TEST, COUNT_NEWS
		// EXTRACT_CONTENT, DOWNLOAD_ERRORS, GOOGLE_PLAY_SITES_TEXT
		Process p = Process.FULL_TEST;
		int[] sections = new int[]{0};
		int position = 0;
		int lenght = -1;
		boolean showTitle = false;
		boolean copyToFile = !true;
		boolean queryServer = !true;

		// Readers to check
//		s = new Site(610, "USAToday", 0, "", 0, 0, 0, USATodaySections.class, USAToday.class);
//		s = new Site(0, "BBC", 0, "", 0, 0, 0, BBCSections.class, BBC.class);
//		s = new Site(120, "Marca", 0, "", 0, 0, 0, MarcaSections.class, Marca.class);
//		s = new Site(0, "The Siberian Times", 0, "", 0, 0, 0, SiberianTimesSections.class, SiberianTimes.class);
		long t_start = System.currentTimeMillis();
		try {
			String siteName = s == null ? "NONE" : s.getSections().getClass().getSimpleName().replace("Sections", "");
			switch (p) {
				case TITLES:
					System.out.println("## Titles - " + siteName + " ##");
					for (int i : sections) {
						System.out.println(s.getSections().get(i).name);
					}
					int ti = 0;
					NewsSet news = new NewsSet(s.readNewsHeaders(sections));
					for (News n : news) {
						System.out.println("[" + ti++ + "] " + n.title);
					}
					break;
				case TAGS:
					System.out.println("## Tags - " + siteName + " ##");
					checkTags(s, showTitle);
					break;
				case NEWS:
					System.out.println("## News - " + siteName + " ##");
					boolean[] print = new boolean[]{
							true, //title
							true, //link
							false, //date
							true, //description
							false, //content
							true, //tags
							true, //enclosure
					};
					read(s, sections, position, lenght, queryServer, print, copyToFile);
					break;
				case COUNT_NEWS:
					System.out.println("## News Count - " + siteName + " ##");
					countNews(s, queryServer);
					break;
				case DATES:
					System.out.println("## Dates - " + siteName + " ##");
					checkDates(s);
					break;
				case FULL_TEST:
					System.out.println("## Full test - " + siteName + " ##");
					Test[] tests = new Test[]{FullTest.WITHCONTENT, FullTest.TH2, FullTest.SCRIPTS, FullTest.STYLES, FullTest.LINKS, FullTest.COMMENTS, FullTest.A_WITH_OBJECT};
					int[] testResults = new int[tests.length];
					int numNews = queryServer
							? FullTest.testSiteInServer(s, tests, testResults)
							: FullTest.testSite(s, tests, testResults);
					System.out.println("\n\tResultados:");
					System.out.println("\t\t" + numNews + " noticias");
					for (int i = 0; i < testResults.length; i++) {
						String dscr = tests[i].testDescription();
						int res = testResults[i];
						int negRes = numNews - res;
						System.out.println("\t\t" + res + " " + dscr + " (" + negRes + " no)");
					}
					System.out.println("_________________________________________________\n");
					break;
				case SECTIONS_TEST:
					System.out.println("## Sections test - " + siteName + " ##");
					new SectionsTest().doTest(s);
					break;
				case EXTRACT_CONTENT:
					System.out.println("## Extract content - " + siteName + " ##");
					extractcontent(s);
					break;
				case DOWNLOAD_ERRORS:
					downloadErrors();
					break;
				case GOOGLE_PLAY_SITES_TEXT:
					printGooglePlaySitesText();
					break;
			}
		} catch (Exception e) {
			System.out.println("############ ERROR ###########");
			System.out.println(" error: " + e.toString());
			e.printStackTrace();
		}
		long t_end = System.currentTimeMillis();
		double t = ((double) (t_end - t_start)) / 1000.0;
		System.out.println("..in " + Double.toString(t) + " seconds.");
	}

	private static void printNews(News n,
	                              boolean title, boolean link, boolean date, boolean description,
	                              boolean content, boolean tags, boolean imageSrc)
	{
		System.out.println(" #################################### ");
		if (title) {
			System.out.println("\t" + n.title);
		}
		if (link) {
			System.out.println("\t" + n.link);
		}
		if (date) {
			System.out.println("\t" + Date.getAge(n.date));
		}
		if (description) {
			System.out.println("\tDscr:[" + n.description + "]");
		}
		if (content) {
			System.out.println("\tCnt:[" + n.content + "]");
		}
		if (tags) {
			System.out.println("\tTags:" + n.tags.toString());
		}
		if (imageSrc) {
			System.out.println("\tImage:" + (n.imgSrc != null ? n.imgSrc : ""));
		}
		System.out.println(" #################################### ");
	}

	private static void read(Site s, int[] sections, int startPosition, int lenght, boolean queryServer, boolean[] print, boolean copyToFile)
	{
		NewsArray news;

		ServerReader server = null;
		if (queryServer) {

			int[] section_codes = new int[sections.length];
			for (int i = 0; i < section_codes.length; i++) {
				section_codes[i] = s.getSections().get(sections[i]).code;
			}

			server = new ServerReader();
			news = server.readNewsHeaders(s.code, section_codes);

		} else {
			news = new NewsArray();
			for (int i : sections) {
				Section sec = s.getSections().get(i);
				System.out.println("[" + sec.name + "]");
				if (sec.url != null) {
					news.addAll(s.readNewsHeaders(new int[]{sec.code}));
				}
			}
		}

		NewsSet ns = new NewsSet(news);
		news.clear();
		news.addAll(ns);

		System.out.println("Nnews:" + news.size());
		if (lenght == -1) {
			lenght = news.size() - startPosition;
		}
		if (lenght > news.size()) {
			lenght = news.size();
		}

		for (int i = startPosition; i < (startPosition + lenght); ++i) {
            /*   try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }*/
			News N = news.get(i);
			System.out.print("[" + (i + 1) + "]");
			printNews(N, print[0], print[1], print[2], print[3], print[4], print[5], print[6]);

			if (N.content == null || N.content.isEmpty()) {
				if (queryServer) {
					server.readNewsContent(4, N);
				} else {
					String content = s.readNewsContent(N.link);

					if (content != null)
						N.content = content;
				}
			}
			if (copyToFile) {
				HardDrive.copyTo(s, N);
			}
		}
	}

	private static void downloadErrors()
	{
		Test[] tests = new Test[]{FullTest.WITHCONTENT, FullTest.TH2, FullTest.SCRIPTS, FullTest.STYLES, FullTest.LINKS, FullTest.COMMENTS, FullTest.A_WITH_OBJECT};

		try {
			JSONObject json = new JSONObject(fetch("https://newsup-debug.appspot.com/dev?bugs&withContent"));
			JSONArray jsonItems = json.getJSONArray("bugs");
			for (int i = 0; i < jsonItems.length(); i++) {
				JSONObject jsonItem = (JSONObject) jsonItems.get(i);

				int site_code = jsonItem.getInt("sc");
				String site_name = jsonItem.getString("sn");
				String dscr = URLDecoder.decode(jsonItem.getString("d"), "utf-8");
				String link = jsonItem.getString("l");
				String content = URLDecoder.decode(jsonItem.getString("c"), "utf-8");

				News aux = new News("", link, "", 0, "", new Tags(), site_code, 0, 0);
				aux.content = "<a href='" + link + "'>Original Content</a>" + content;

				HardDrive.copy(aux, site_code + "-" + i);

				System.out.println("[" + (i + 1) + "/" + jsonItems.length() + "] # " + site_code + " # " + site_name + " # #");
				for (Test t : tests) {
					boolean b = t.evaluate(null, aux);
					System.out.print((b ? t.testDescription() : "") + ",");
				}
				System.out.println("\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void checkTags(Site site, boolean showTitle)
	{

		for (Section sec : site.getSections()) {
			if (sec.url == null) {
				continue;
			}

			if (showTitle) {
				System.out.print("Section " + sec.name + "\t\t");
			}

			org.jsoup.nodes.Document doc = ((NewsReader) site.getReader()).read(sec.url);
			if (doc == null) {
				System.out.println("");
				continue;
			}

			TreeSet<String> tagSet = new TreeSet<>();

			Elements items = doc.select("item,entry");
			for (org.jsoup.nodes.Element item : items) {
				for (org.jsoup.nodes.Element prop : item.getAllElements()) {
					tagSet.add(prop.tagName());
				}
			}
			System.out.println(tagSet.toString());
		}
	}

	private static void checkDates(Site s)
	{
		Date.setTitles(new String[]{"%d seconds ago", "%d minutes ago", "%d hours ago", "%d days ago", "%d MONTHS ago", "%d YEARS ago"});
		NewsArray news;

		int isec = 0;
		for (Section sec : s.getSections()) {
			if (sec.url == null) {
				isec++;
				continue;
			}

			news = s.readNewsHeaders(new int[]{sec.code});

			TreeSet<News> nmap = new TreeSet<>(new Comparator<News>() {
				@Override
				public int compare(News o1, News o2)
				{
					return Long.compare(o2.date, o1.date);
				}
			});
			nmap.addAll(news);

			if (!nmap.isEmpty()) {
				News first = nmap.first();
				System.out.println(Date.getAge(first.date) + "\t[Section " + sec.name + "]");
			} else {
				System.out.println("EMPTY!!!!\t[Section " + sec.name + "] " + sec.url);
			}
			isec++;
		}
	}

	private static void countNews(Site s, boolean queryServer)
	{
		ServerReader server = queryServer ? new ServerReader() : null;
		int isec = 0;
		for (Section section : s.getSections()) {
			if (section.url == null) {
				isec++;
				continue;
			}
			NewsArray news = queryServer
					? server.readNewsHeaders(s.code, new int[]{section.code})
					: s.readNewsHeaders(new int[]{section.code});

			if (!news.isEmpty()) {
				System.out.println(news.size() + "\t[Section " + section.name + "]");
			} else {
				System.out.println("EMPTY!!!!\t[Section " + section.name + "] " + section.url);
			}
			isec++;
		}
	}

	private static void extractcontent(Site site)
	{
		NewsArray ns = site.readNewsHeaders(new int[]{0});
		News n = ns.get(0);

		System.out.println("[Number of news:" + ns.size());

		System.out.println("[Title]" + n.title);
		//  System.out.println("" + n.content);

		Document d = Jsoup.parse(n.content);

		System.out.println("Contando nodos:" + d.childNodeSize());
		for (int i = 0; i < d.childNodeSize(); ++i) {
			Node e = d.childNode(i);
			System.out.println("" + e.nodeName());

			for (int j = 0; j < e.childNodeSize(); ++j) {
				Node e2 = e.childNode(j);
				System.out.println("__" + e2.nodeName());
			}
		}

		Elements cs = d.children();
		System.out.println("\nContando elementos:" + cs.size());
		for (int i = 0; i < cs.size(); ++i) {
			Element e = cs.get(i);

			System.out.println("" + e.tagName());
		}

		ArrayList<DataNode> dns = new ArrayList<>(d.dataNodes());
		System.out.println("\nContando DataNodes:" + dns.size());
		for (int i = 0; i < dns.size(); ++i) {
			DataNode dn = dns.get(i);

			System.out.println("" + dn.nodeName());
		}

		printTree(d.children(), 0);

	}

	private static void printTree(Elements elements, int level)
	{
		for (Element e : elements) {

			for (int i = 0; i < level; ++i) {
				System.out.print("_");
			}

			System.out.println("" + e.tagName());

			printTree(e.children(), level + 1);
		}

	}

	private static String fetch(String url) throws Exception
	{
		URL oracle = new URL(url);
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream(), "utf-8"));

		char[] buffer = new char[4096];
		StringBuilder sb = new StringBuilder(1000);
		int len;
		while ((len = in.read(buffer, 0, buffer.length)) > 0) {
			sb.append(buffer, 0, len);
		}
		in.close();

		return sb.toString();
	}

	private static void sectionsTest(int from)
	{
		Sites sites = Sites.getDefault(true);
		SectionsTest test = new SectionsTest();
		for (int i = from; i < sites.size(); i++) {
			Site s = sites.get(i);

			System.out.print(i + " -- " + s.name + " -- ");
			test.doTest(s);

			System.out.println("\n");
		}
	}

	private static void printGooglePlaySitesText()
	{
		Sites sites = Sites.getDefault(true);

		TreeSet<com.lucevent.newsup.data.util.Site> remaining = new TreeSet<com.lucevent.newsup.data.util.Site>(new Comparator<com.lucevent.newsup.data.util.Site>() {
			@Override
			public int compare(com.lucevent.newsup.data.util.Site o1, com.lucevent.newsup.data.util.Site o2)
			{
				return Integer.compare(o1.code, o2.code);
			}
		});
		remaining.addAll(sites);

		// España
		System.out.println("[Noticias - Espa\u00f1a]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.SPAIN}, null);

		System.out.println("\n");
		System.out.println("[Noticias - Reino Unido]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.UK}, null);

		System.out.println("\n");
		System.out.println("[Noticias - USA]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.USA}, null);

		System.out.println("\n");
		System.out.println("[Suecia]");
		list(sites, remaining, null, new int[]{SiteCountry.SWEDEN}, null);

		System.out.println("\n");
		System.out.println("[Tecnolog\u00eda]");
		list(sites, remaining, new int[]{SiteCategory.TECHNOLOGY}, null, null);

		System.out.println("\n");
		System.out.println("[Astronom\u00eda]");
		list(sites, remaining, new int[]{SiteCategory.ASTRONOMY}, null, null);

		System.out.println("\n");
		System.out.println("[Videojuegos]");
		list(sites, remaining, new int[]{SiteCategory.VIDEOGAMES}, null, null);

		System.out.println("\n");
		System.out.println("[Otros]");
		list(sites, remaining, new int[]{SiteCategory.BLOG, SiteCategory.BUSINESS, SiteCategory.CONSUMPTION, SiteCategory.CULTURE, SiteCategory.DIY, SiteCategory.ECONOMY, SiteCategory.ENTERTAINMENT, SiteCategory.FASHION, SiteCategory.GASTRONOMY, SiteCategory.GOSSIP, SiteCategory.HEALTH_AND_FITNESS, SiteCategory.LIFESTYLE, SiteCategory.MOTOR, SiteCategory.MUSIC, SiteCategory.PETS, SiteCategory.POLITICS, SiteCategory.SATIRICAL, SiteCategory.SCIENCE, SiteCategory.TRAVEL}, null, new int[]{SiteLanguage.SPANISH});
		System.out.print(", ");
		listRemaining(remaining);

		System.out.println("\n\n\n###############################\n\n\n");
		remaining.addAll(sites);

		// Catalunya
		System.out.println("[Not\u00edcies - Espanya]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, null, new int[]{SiteLanguage.CATALAN});
		System.out.print(", ");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.SPAIN}, new int[]{SiteLanguage.SPANISH});

		System.out.println("\n");
		System.out.println("[Not\u00edcies - Regne Unit]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.UK}, null);

		System.out.println("\n");
		System.out.println("[Not\u00edcies - USA]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.USA}, null);

		System.out.println("\n");
		System.out.println("[Su\u00e8cia]");
		list(sites, remaining, null, new int[]{SiteCountry.SWEDEN}, null);

		System.out.println("\n");
		System.out.println("[Tecnologia]");
		list(sites, remaining, new int[]{SiteCategory.TECHNOLOGY}, null, null);

		System.out.println("\n");
		System.out.println("[Astronomia]");
		list(sites, remaining, new int[]{SiteCategory.ASTRONOMY}, null, null);

		System.out.println("\n");
		System.out.println("[Videojocs]");
		list(sites, remaining, new int[]{SiteCategory.VIDEOGAMES}, null, null);

		System.out.println("\n");
		System.out.println("[Altres]");
		list(sites, remaining, new int[]{SiteCategory.BLOG, SiteCategory.BUSINESS, SiteCategory.CONSUMPTION, SiteCategory.CULTURE, SiteCategory.DIY, SiteCategory.ECONOMY, SiteCategory.ENTERTAINMENT, SiteCategory.FASHION, SiteCategory.GASTRONOMY, SiteCategory.GOSSIP, SiteCategory.HEALTH_AND_FITNESS, SiteCategory.LIFESTYLE, SiteCategory.MOTOR, SiteCategory.MUSIC, SiteCategory.PETS, SiteCategory.POLITICS, SiteCategory.SATIRICAL, SiteCategory.SCIENCE, SiteCategory.TRAVEL}, null, new int[]{SiteLanguage.SPANISH, SiteLanguage.CATALAN});
		System.out.print(", ");
		listRemaining(remaining);

		System.out.println("\n\n\n###############################\n\n\n");

		// U.K.
		System.out.println("[News - UK]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.UK}, null);

		System.out.println("\n");
		System.out.println("[News - USA]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.USA}, null);

		System.out.println("\n");
		System.out.println("[News - Spain]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.SPAIN}, new int[]{SiteLanguage.SPANISH});

		System.out.println("\n");
		System.out.println("[Sweden]");
		list(sites, remaining, null, new int[]{SiteCountry.SWEDEN}, null);

		System.out.println("\n");
		System.out.println("[Technology]");
		list(sites, remaining, new int[]{SiteCategory.TECHNOLOGY}, null, null);

		System.out.println("\n");
		System.out.println("[Astronomy]");
		list(sites, remaining, new int[]{SiteCategory.ASTRONOMY}, null, null);

		System.out.println("\n");
		System.out.println("[Videogames]");
		list(sites, remaining, new int[]{SiteCategory.VIDEOGAMES}, null, null);

		System.out.println("\n");
		System.out.println("[Other]");
		list(sites, remaining, new int[]{SiteCategory.BLOG, SiteCategory.BUSINESS, SiteCategory.CONSUMPTION, SiteCategory.CULTURE, SiteCategory.DIY, SiteCategory.ECONOMY, SiteCategory.ENTERTAINMENT, SiteCategory.FASHION, SiteCategory.GASTRONOMY, SiteCategory.GOSSIP, SiteCategory.HEALTH_AND_FITNESS, SiteCategory.LIFESTYLE, SiteCategory.MOTOR, SiteCategory.MUSIC, SiteCategory.PETS, SiteCategory.POLITICS, SiteCategory.SATIRICAL, SiteCategory.SCIENCE, SiteCategory.TRAVEL}, new int[]{SiteCountry.UK}, null);
		System.out.print(", ");
		list(sites, remaining, new int[]{SiteCategory.BLOG, SiteCategory.BUSINESS, SiteCategory.CONSUMPTION, SiteCategory.CULTURE, SiteCategory.DIY, SiteCategory.ECONOMY, SiteCategory.ENTERTAINMENT, SiteCategory.FASHION, SiteCategory.GASTRONOMY, SiteCategory.GOSSIP, SiteCategory.HEALTH_AND_FITNESS, SiteCategory.LIFESTYLE, SiteCategory.MOTOR, SiteCategory.MUSIC, SiteCategory.PETS, SiteCategory.POLITICS, SiteCategory.SATIRICAL, SiteCategory.SCIENCE, SiteCategory.TRAVEL}, null, new int[]{SiteLanguage.ENGLISH});
		System.out.print(", ");
		listRemaining(remaining);

		System.out.println("\n\n\n###############################\n\n\n");

		// U.S.A.
		System.out.println("[News - USA]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.USA}, null);

		System.out.println("\n");
		System.out.println("[News - UK]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.UK}, null);

		System.out.println("\n");
		System.out.println("[News - Spain]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.SPAIN}, new int[]{SiteLanguage.SPANISH});

		System.out.println("\n");
		System.out.println("[Sweden]");
		list(sites, remaining, null, new int[]{SiteCountry.SWEDEN}, null);

		System.out.println("\n");
		System.out.println("[Technology]");
		list(sites, remaining, new int[]{SiteCategory.TECHNOLOGY}, null, null);

		System.out.println("\n");
		System.out.println("[Astronomy]");
		list(sites, remaining, new int[]{SiteCategory.ASTRONOMY}, null, null);

		System.out.println("\n");
		System.out.println("[Videogames]");
		list(sites, remaining, new int[]{SiteCategory.VIDEOGAMES}, null, null);

		System.out.println("\n");
		System.out.println("[Other]");
		list(sites, remaining, new int[]{SiteCategory.BLOG, SiteCategory.BUSINESS, SiteCategory.CONSUMPTION, SiteCategory.CULTURE, SiteCategory.DIY, SiteCategory.ECONOMY, SiteCategory.ENTERTAINMENT, SiteCategory.FASHION, SiteCategory.GASTRONOMY, SiteCategory.GOSSIP, SiteCategory.HEALTH_AND_FITNESS, SiteCategory.LIFESTYLE, SiteCategory.MOTOR, SiteCategory.MUSIC, SiteCategory.PETS, SiteCategory.POLITICS, SiteCategory.SATIRICAL, SiteCategory.SCIENCE, SiteCategory.TRAVEL}, new int[]{SiteCountry.USA}, null);
		System.out.print(", ");
		list(sites, remaining, new int[]{SiteCategory.BLOG, SiteCategory.BUSINESS, SiteCategory.CONSUMPTION, SiteCategory.CULTURE, SiteCategory.DIY, SiteCategory.ECONOMY, SiteCategory.ENTERTAINMENT, SiteCategory.FASHION, SiteCategory.GASTRONOMY, SiteCategory.GOSSIP, SiteCategory.HEALTH_AND_FITNESS, SiteCategory.LIFESTYLE, SiteCategory.MOTOR, SiteCategory.MUSIC, SiteCategory.PETS, SiteCategory.POLITICS, SiteCategory.SATIRICAL, SiteCategory.SCIENCE, SiteCategory.TRAVEL}, null, new int[]{SiteLanguage.ENGLISH});
		System.out.print(", ");
		listRemaining(remaining);

		System.out.println("\n\n\n###############################\n\n\n");

		// Sweden
		System.out.println("[Sverige]");
		list(sites, remaining, null, new int[]{SiteCountry.SWEDEN}, null);

		System.out.println("\n");
		System.out.println("[Nyheter - USA]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.USA}, null);

		System.out.println("\n");
		System.out.println("[Nyheter - Storbritannien]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.UK}, null);

		System.out.println("\n");
		System.out.println("[Nyheter - Spanien]");
		list(sites, remaining, new int[]{SiteCategory.NEWS, SiteCategory.SPORTS}, new int[]{SiteCountry.SPAIN}, new int[]{SiteLanguage.SPANISH});

		System.out.println("\n");
		System.out.println("[Teknik]");
		list(sites, remaining, new int[]{SiteCategory.TECHNOLOGY}, null, null);

		System.out.println("\n");
		System.out.println("[Astronomi]");
		list(sites, remaining, new int[]{SiteCategory.ASTRONOMY}, null, null);

		System.out.println("\n");
		System.out.println("[Videospel]");
		list(sites, remaining, new int[]{SiteCategory.VIDEOGAMES}, null, null);

		System.out.println("\n");
		System.out.println("[Mer]");
		list(sites, remaining, new int[]{SiteCategory.BLOG, SiteCategory.BUSINESS, SiteCategory.CONSUMPTION, SiteCategory.CULTURE, SiteCategory.DIY, SiteCategory.ECONOMY, SiteCategory.ENTERTAINMENT, SiteCategory.FASHION, SiteCategory.GASTRONOMY, SiteCategory.GOSSIP, SiteCategory.HEALTH_AND_FITNESS, SiteCategory.LIFESTYLE, SiteCategory.MOTOR, SiteCategory.MUSIC, SiteCategory.PETS, SiteCategory.POLITICS, SiteCategory.SATIRICAL, SiteCategory.SCIENCE, SiteCategory.TRAVEL}, null, new int[]{SiteLanguage.SWEDISH});
		System.out.print(", ");
		listRemaining(remaining);

		System.out.println("\n\n\n###############################\n\n\n");
	}

	private static void list(Sites sites, TreeSet<com.lucevent.newsup.data.util.Site> remaining, int[] categories, int[] countries, int[] languages)
	{
		boolean needsComma = false;
		for (com.lucevent.newsup.data.util.Site s : sites) {
			if (match(s, categories, countries, languages)) {
				if (needsComma)
					System.out.print(", ");
				else
					needsComma = true;

				System.out.print(s.name);
				remaining.remove(s);
			}
		}
	}

	private static void listRemaining(TreeSet<com.lucevent.newsup.data.util.Site> remaining)
	{
		boolean needsComma = false;
		for (com.lucevent.newsup.data.util.Site s : remaining) {
			if (needsComma)
				System.out.print(", ");
			else
				needsComma = true;

			System.out.print(s.name);
		}
	}

	private static boolean match(com.lucevent.newsup.data.util.Site site, int[] categories, int[] countries, int[] languages)
	{
		boolean match = false;
		if (categories != null) {
			for (int i : categories)
				if (i == site.getCategory()) {
					match = true;
					break;
				}

			if (!match)
				return false;
		}

		match = false;
		if (countries != null) {
			for (int i : countries)
				if (i == site.getCountry()) {
					match = true;
					break;
				}

			if (!match)
				return false;
		}

		match = false;
		if (languages != null) {
			for (int i : languages)
				if (i == site.getLanguage()) {
					match = true;
					break;
				}

			if (!match)
				return false;
		}
		return true;
	}

}
