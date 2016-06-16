package com.lucevent.newsup.data.sports.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class FootballSections extends Sections {

    public FootballSections()
    {
        super();

        add(new Section("España", null, -1));
        add(new Section("Liga BBVA", "http://www.resultados-futbol.com/primera/grupo1", 1));
        add(new Section("Liga Adelante", "http://www.resultados-futbol.com/segunda/grupo1", 1));
        add(new Section("Segunda B - Grupo A", "http://www.resultados-futbol.com/segundab/grupo1", 1));
        add(new Section("Segunda B - Grupo B", "http://www.resultados-futbol.com/segundab/grupo2", 1));
        add(new Section("Segunda B - Grupo C", "http://www.resultados-futbol.com/segundab/grupo3", 1));
        add(new Section("Segunda B - Grupo D", "http://www.resultados-futbol.com/segundab/grupo4", 1));

        add(new Section("Inglaterra", null, -1));
        add(new Section("Premier League", "http://www.resultados-futbol.com/premier/grupo1", 1));
        add(new Section("Football League Championship", "http://www.resultados-futbol.com/championship/grupo1", 1));
        add(new Section("League one", "http://www.resultados-futbol.com/league_one/grupo1", 1));
        add(new Section("League two", "http://www.resultados-futbol.com/league_two/grupo1", 1));

        add(new Section("Alemania", null, -1));
        add(new Section("Bundesliga", "http://www.resultados-futbol.com/bundesliga/grupo1", 1));
        add(new Section("2. Bundesliga - Grupo 1", "http://www.resultados-futbol.com/bundesliga_2/grupo1", 1));
        add(new Section("2. Bundesliga - Grupo 2", "http://www.resultados-futbol.com/bundesliga_2/grupo2", 1));
        add(new Section("3. Liga", "http://www.resultados-futbol.com/bundesliga_3/grupo1", 1));

        add(new Section("Francia", null, -1));
        add(new Section("Ligue 1", "http://www.resultados-futbol.com/ligue_1/grupo1", 1));
        add(new Section("Ligue 2", "http://www.resultados-futbol.com/ligue_2/grupo1", 1));
        add(new Section("Championnat National", "http://www.resultados-futbol.com/ligue_3/grupo1", 1));

        add(new Section("Italia", null, -1));
        add(new Section("Serie A", "http://www.resultados-futbol.com/serie_a/grupo1", 1));
        add(new Section("Serie B", "http://www.resultados-futbol.com/serie_b/grupo1", 1));
        add(new Section("Lega Pro - Grupo 1", "http://www.resultados-futbol.com/serie_c1/grupo1", 1));
        add(new Section("Lega Pro - Grupo 2", "http://www.resultados-futbol.com/serie_c1/grupo2", 1));
        add(new Section("Lega Pro - Grupo 3", "http://www.resultados-futbol.com/serie_c1/grupo3", 1));
        add(new Section("Lega Pro - Grupo 3", "http://www.resultados-futbol.com/serie_c1/grupo4", 1));

        add(new Section("Portugal", null, -1));
        add(new Section("Primeira Liga", "http://www.resultados-futbol.com/portugal/grupo1", 1));
        add(new Section("Liga de Honra", "http://www.resultados-futbol.com/vitalis/grupo1", 1));

        add(new Section("Bélgica", null, -1));
        add(new Section("Jupiler Pro League", "http://www.resultados-futbol.com/belgica/grupo1", 1));
        add(new Section("Jupiler Pro League Play-off", "http://www.resultados-futbol.com/belgica/grupo2", 1));

        add(new Section("Holanda", null, -1));
        add(new Section("Eredivisie", "http://www.resultados-futbol.com/holanda/grupo1", 1));
        add(new Section("Eerste Divisie", "http://www.resultados-futbol.com/eerste_divise/grupo1", 1));

    }

}
