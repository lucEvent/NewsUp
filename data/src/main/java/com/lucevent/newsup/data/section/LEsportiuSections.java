package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class LEsportiuSections extends Sections {

	public LEsportiuSections()
	{
		super();

		add(new Section("Portada", "http://www.lesportiudecatalunya.cat/(x)/nacional.feed?type=rss", 0));
		add(new Section("Bar\u00E7a", "http://www.lesportiudecatalunya.cat/barca.feed?type=rss", 0));
		add(new Section("Espanyol", "http://www.lesportiudecatalunya.cat/espanyol.feed?type=rss", 0));
		add(new Section("Girona", "http://www.lesportiudecatalunya.cat/girona.feed?type=rss", 0));

		add(new Section("Futbol", "http://www.lesportiudecatalunya.cat/futbol/mes-futbol.feed?type=rss", 0));
		add(new Section("1a divisi\u00F3", "http://www.lesportiudecatalunya.cat/futbol/1adivisio.feed?type=rss", 1));
		add(new Section("Internacional", "http://www.lesportiudecatalunya.cat/futbol/internacional.feed?type=rss", 1));
		add(new Section("M\u00E9s futbol", "http://www.lesportiudecatalunya.cat/futbol/mesfutbol.feed?type=rss", 1));

		add(new Section("Futbol catal\u00E0", "http://www.lesportiudecatalunya.cat/futbolcat/futbol-catala.feed?type=rss", 0));
		add(new Section("N\u00E0stic", "http://www.lesportiudecatalunya.cat/futbolcat/nastic.feed?type=rss", 1));
		add(new Section("Reus", "http://www.lesportiudecatalunya.cat/futbolcat/reus.feed?type=rss", 1));
		add(new Section("Bar\u00E7a B", "http://www.lesportiudecatalunya.cat/futbolcat/barca-b.feed?type=rss", 1));
		add(new Section("2a divisi\u00F3 A", "http://www.lesportiudecatalunya.cat/futbolcat/2a-divisio-a.feed?type=rss", 1));
		add(new Section("2a divisi\u00F3 B", "http://www.lesportiudecatalunya.cat/futbolcat/2a-divisio-b.feed?type=rss", 1));
		add(new Section("3a divisi\u00F3", "http://www.lesportiudecatalunya.cat/futbolcat/3a-divisio.feed?type=rss", 1));
		add(new Section("Territorial", "http://www.lesportiudecatalunya.cat/futbolcat/territorial.feed?type=rss", 1));
		add(new Section("Femen\u00ED", "http://www.lesportiudecatalunya.cat/futbolcat/femeni.feed?type=rss", 1));

		add(new Section("B\u00E0squet", "http://www.lesportiudecatalunya.cat/basquet.feed?type=rss", 0));
		add(new Section("ACB", "http://www.lesportiudecatalunya.cat/basquet/acb.feed?type=rss", 1));
		add(new Section("FEB", "http://www.lesportiudecatalunya.cat/basquet/feb.feed?type=rss", 1));
		add(new Section("FCBQ", "http://www.lesportiudecatalunya.cat/basquet/fcbq.feed?type=rss", 1));
		add(new Section("NBA", "http://www.lesportiudecatalunya.cat/basquet/nba.feed?type=rss", 1));
		add(new Section("Eurolliga", "http://www.lesportiudecatalunya.cat/basquet/eurolliga.feed?type=rss", 1));
		add(new Section("Eurocopa", "http://www.lesportiudecatalunya.cat/basquet/eurocopa.feed?type=rss", 1));
		add(new Section("WNBA", "http://www.lesportiudecatalunya.cat/basquet/wnba.feed?type=rss", 1));
		add(new Section("M\u00E9s b\u00E0squet", "http://www.lesportiudecatalunya.cat/basquet/mes-basquet.feed?type=rss", 1));

		add(new Section("Hoquei", null, -1));
		add(new Section("OK lliga", "http://www.lesportiudecatalunya.cat/hoquei/ok-lliga.feed?type=rss", 0));
		add(new Section("Lliga europea", "http://www.lesportiudecatalunya.cat/hoquei/lliga-europea.feed?type=rss", 1));
		add(new Section("Copa CERS", "http://www.lesportiudecatalunya.cat/hoquei/copa-cers.feed?type=rss", 1));
		add(new Section("Copa", "http://www.lesportiudecatalunya.cat/hoquei/copa.feed?type=rss", 1));
		add(new Section("Copa Europa", "http://www.lesportiudecatalunya.cat/hoquei/copa-europa.feed?type=rss", 1));
		add(new Section("M\u00E9s hoquei", "http://www.lesportiudecatalunya.cat/hoquei/mes-hoquei.feed?type=rss", 1));

		add(new Section("Motor", "http://www.lesportiudecatalunya.cat/motor/motor.feed?type=rss", 0));
		add(new Section("F\u00F2rmula 1", "http://www.lesportiudecatalunya.cat/motor/formula1.feed?type=rss", 1));
		add(new Section("Motociclisme", "http://www.lesportiudecatalunya.cat/motor/moto-gp.feed?type=rss", 1));
		add(new Section("Ral·lis", "http://www.lesportiudecatalunya.cat/motor/rallis.feed?type=rss", 1));
		add(new Section("Trial", "http://www.lesportiudecatalunya.cat/motor/trial.feed?type=rss", 1));
		add(new Section("Dakar", "http://www.lesportiudecatalunya.cat/motor/dakar.feed?type=rss", 1));
		add(new Section("M\u00E9s motor", "http://www.lesportiudecatalunya.cat/motor/mes-motor.feed?type=rss", 1));

		add(new Section("Pol\u00EDtica esportiva", "http://www.lesportiudecatalunya.cat/mes-esport/politica-esportiva.feed?type=rss", 0));
		add(new Section("Handbol", "http://www.lesportiudecatalunya.cat/mes-esport/handbol.feed?type=rss", 0));
		add(new Section("Tennis", "http://www.lesportiudecatalunya.cat/mes-esport/tennis.feed?type=rss", 0));
		add(new Section("Atletisme", "http://www.lesportiudecatalunya.cat/mes-esport/atletisme.feed?type=rss", 0));
		add(new Section("Nataci\u00F3", "http://www.lesportiudecatalunya.cat/mes-esport/natacio.feed?type=rss", 0));
		add(new Section("Voleibol", "http://www.lesportiudecatalunya.cat/mes-esport/voleibol.feed?type=rss", 0));
		add(new Section("Waterpolo", "http://www.lesportiudecatalunya.cat/mes-esport/waterpolo.feed?type=rss", 0));
		add(new Section("Rugbi", "http://www.lesportiudecatalunya.cat/mes-esport/rugbi.feed?type=rss", 0));
		add(new Section("Hoquei herba", "http://www.lesportiudecatalunya.cat/mes-esport/hoquei-herba.feed?type=rss", 0));
		add(new Section("Ciclisme", "http://www.lesportiudecatalunya.cat/mes-esport/ciclisme.feed?type=rss", 0));
		add(new Section("Futbol sala", "http://www.lesportiudecatalunya.cat/mes-esport/futbol-sala.feed?type=rss", 0));
		add(new Section("Hoquei sobre gel", "http://www.lesportiudecatalunya.cat/mes-esport/hoquei-gel.feed?type=rss", 0));
		add(new Section("Tennis Taula", "http://www.lesportiudecatalunya.cat/mes-esport/tennis-taula.feed?type=rss", 0));
		add(new Section("Patinatge", "http://www.lesportiudecatalunya.cat/mes-esport/patinatge.feed?type=rss", 0));
		add(new Section("Golf", "http://www.lesportiudecatalunya.cat/mes-esport/golf.feed?type=rss", 0));
		add(new Section("Taekwondo", "http://www.lesportiudecatalunya.cat/mes-esport/taekwondo.feed?type=rss", 0));
		add(new Section("Esports de muntanya", "http://www.lesportiudecatalunya.cat/mes-esport/esports-muntanya.feed?type=rss", 0));
		add(new Section("Esqu\u00ED", "http://www.lesportiudecatalunya.cat/mes-esport/esqui.feed?type=rss", 0));
		add(new Section("Surf de neu", "http://www.lesportiudecatalunya.cat/mes-esport/surf-neu.feed?type=rss", 0));
		add(new Section("M\u00E9s esports", "http://www.lesportiudecatalunya.cat/mes-esport/mes-esports.feed?type=rss", 0));

	}

}
