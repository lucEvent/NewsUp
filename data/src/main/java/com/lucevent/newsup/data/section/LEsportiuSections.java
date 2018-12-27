package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class LEsportiuSections extends Sections {

	public LEsportiuSections()
	{
		super();

		add(new Section("Portada", "https://www.lesportiudecatalunya.cat/(x)/nacional.feed?type=rss", 0));
		add(new Section("Bar\u00E7a", "https://www.lesportiudecatalunya.cat/barca.feed?type=rss", 0));
		add(new Section("Espanyol", "https://www.lesportiudecatalunya.cat/espanyol.feed?type=rss", 0));
		add(new Section("Girona", "https://www.lesportiudecatalunya.cat/girona.feed?type=rss", 0));

		add(new Section("Futbol", "https://www.lesportiudecatalunya.cat/futbol/mes-futbol.feed?type=rss", 0));
		add(new Section("1a divisi\u00F3", "https://www.lesportiudecatalunya.cat/futbol/1adivisio.feed?type=rss", 1));
		add(new Section("Internacional", "https://www.lesportiudecatalunya.cat/futbol/internacional.feed?type=rss", 1));
		add(new Section("M\u00E9s futbol", "https://www.lesportiudecatalunya.cat/futbol/mesfutbol.feed?type=rss", 1));

		add(new Section("Futbol catal\u00E0", "https://www.lesportiudecatalunya.cat/futbolcat/futbol-catala.feed?type=rss", 0));
		add(new Section("N\u00E0stic", "https://www.lesportiudecatalunya.cat/futbolcat/nastic.feed?type=rss", 1));
		add(new Section("Reus", "https://www.lesportiudecatalunya.cat/futbolcat/reus.feed?type=rss", 1));
		add(new Section("Bar\u00E7a B", "https://www.lesportiudecatalunya.cat/futbolcat/barca-b.feed?type=rss", 1));
		add(new Section("2a divisi\u00F3 A", "https://www.lesportiudecatalunya.cat/futbolcat/2a-divisio-a.feed?type=rss", 1));
		add(new Section("2a divisi\u00F3 B", "https://www.lesportiudecatalunya.cat/futbolcat/2a-divisio-b.feed?type=rss", 1));
		add(new Section("3a divisi\u00F3", "https://www.lesportiudecatalunya.cat/futbolcat/3a-divisio.feed?type=rss", 1));
		add(new Section("Territorial", "https://www.lesportiudecatalunya.cat/futbolcat/territorial.feed?type=rss", 1));
		add(new Section("Femen\u00ED", "https://www.lesportiudecatalunya.cat/futbolcat/femeni.feed?type=rss", 1));

		add(new Section("B\u00E0squet", "https://www.lesportiudecatalunya.cat/basquet.feed?type=rss", 0));
		add(new Section("ACB", "https://www.lesportiudecatalunya.cat/basquet/acb.feed?type=rss", 1));
		add(new Section("FEB", "https://www.lesportiudecatalunya.cat/basquet/feb.feed?type=rss", 1));
		add(new Section("FCBQ", "https://www.lesportiudecatalunya.cat/basquet/fcbq.feed?type=rss", 1));
		add(new Section("NBA", "https://www.lesportiudecatalunya.cat/basquet/nba.feed?type=rss", 1));
		add(new Section("Eurolliga", "https://www.lesportiudecatalunya.cat/basquet/eurolliga.feed?type=rss", 1));
		add(new Section("Eurocopa", "https://www.lesportiudecatalunya.cat/basquet/eurocopa.feed?type=rss", 1));
		add(new Section("WNBA", "https://www.lesportiudecatalunya.cat/basquet/wnba.feed?type=rss", 1));
		add(new Section("M\u00E9s b\u00E0squet", "https://www.lesportiudecatalunya.cat/basquet/mes-basquet.feed?type=rss", 1));

		add(new Section("Hoquei", null, -1));
		add(new Section("OK lliga", "https://www.lesportiudecatalunya.cat/hoquei/ok-lliga.feed?type=rss", 1));
		add(new Section("Lliga europea", "https://www.lesportiudecatalunya.cat/hoquei/lliga-europea.feed?type=rss", 1));
		add(new Section("Copa CERS", "https://www.lesportiudecatalunya.cat/hoquei/copa-cers.feed?type=rss", 1));
		add(new Section("Copa", "https://www.lesportiudecatalunya.cat/hoquei/copa.feed?type=rss", 1));
		add(new Section("Copa Europa", "https://www.lesportiudecatalunya.cat/hoquei/copa-europa.feed?type=rss", 1));
		add(new Section("M\u00E9s hoquei", "https://www.lesportiudecatalunya.cat/hoquei/mes-hoquei.feed?type=rss", 1));

		add(new Section("Motor", "https://www.lesportiudecatalunya.cat/motor/motor.feed?type=rss", 0));
		add(new Section("F\u00F2rmula 1", "https://www.lesportiudecatalunya.cat/motor/formula1.feed?type=rss", 1));
		add(new Section("Motociclisme", "https://www.lesportiudecatalunya.cat/motor/moto-gp.feed?type=rss", 1));
		add(new Section("Ral·lis", "https://www.lesportiudecatalunya.cat/motor/rallis.feed?type=rss", 1));
		add(new Section("Trial", "https://www.lesportiudecatalunya.cat/motor/trial.feed?type=rss", 1));
		add(new Section("Dakar", "https://www.lesportiudecatalunya.cat/motor/dakar.feed?type=rss", 1));
		add(new Section("M\u00E9s motor", "https://www.lesportiudecatalunya.cat/motor/mes-motor.feed?type=rss", 1));

		add(new Section("Pol\u00EDtica esportiva", "https://www.lesportiudecatalunya.cat/mes-esport/politica-esportiva.feed?type=rss", 0));
		add(new Section("Handbol", "https://www.lesportiudecatalunya.cat/mes-esport/handbol.feed?type=rss", 0));
		add(new Section("Tennis", "https://www.lesportiudecatalunya.cat/mes-esport/tennis.feed?type=rss", 0));
		add(new Section("Atletisme", "https://www.lesportiudecatalunya.cat/mes-esport/atletisme.feed?type=rss", 0));
		add(new Section("Nataci\u00F3", "https://www.lesportiudecatalunya.cat/mes-esport/natacio.feed?type=rss", 0));
		add(new Section("Voleibol", "https://www.lesportiudecatalunya.cat/mes-esport/voleibol.feed?type=rss", 0));
		add(new Section("Waterpolo", "https://www.lesportiudecatalunya.cat/mes-esport/waterpolo.feed?type=rss", 0));
		add(new Section("Rugbi", "https://www.lesportiudecatalunya.cat/mes-esport/rugbi.feed?type=rss", 0));
		add(new Section("Hoquei herba", "https://www.lesportiudecatalunya.cat/mes-esport/hoquei-herba.feed?type=rss", 0));
		add(new Section("Ciclisme", "https://www.lesportiudecatalunya.cat/mes-esport/ciclisme.feed?type=rss", 0));
		add(new Section("Futbol sala", "https://www.lesportiudecatalunya.cat/mes-esport/futbol-sala.feed?type=rss", 0));
		add(new Section("Hoquei sobre gel", "https://www.lesportiudecatalunya.cat/mes-esport/hoquei-gel.feed?type=rss", 0));
		add(new Section("Tennis Taula", "https://www.lesportiudecatalunya.cat/mes-esport/tennis-taula.feed?type=rss", 0));
		add(new Section("Patinatge", "https://www.lesportiudecatalunya.cat/mes-esport/patinatge.feed?type=rss", 0));
		add(new Section("Golf", "https://www.lesportiudecatalunya.cat/mes-esport/golf.feed?type=rss", 0));
		add(new Section("Taekwondo", "https://www.lesportiudecatalunya.cat/mes-esport/taekwondo.feed?type=rss", 0));
		add(new Section("Esports de muntanya", "https://www.lesportiudecatalunya.cat/mes-esport/esports-muntanya.feed?type=rss", 0));
		add(new Section("Esqu\u00ED", "https://www.lesportiudecatalunya.cat/mes-esport/esqui.feed?type=rss", 0));
		add(new Section("Surf de neu", "https://www.lesportiudecatalunya.cat/mes-esport/surf-neu.feed?type=rss", 0));
		add(new Section("M\u00E9s esports", "https://www.lesportiudecatalunya.cat/mes-esport/mes-esports.feed?type=rss", 0));

	}

}
