package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

public class _20MinutosNewsReader extends NewsReader {

    public _20MinutosNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();

        SECTIONS.add(new Section("Titulares", 0, "http://www.20minutos.es/rss/"));
        SECTIONS.add(new Section("Artes", 0, "http://www.20minutos.es/rss/artes/"));
        SECTIONS.add(new Section("Artrend", 0, "http://www.20minutos.es/rss/artrend/"));
        SECTIONS.add(new Section("Ciencia", 0, "http://www.20minutos.es/rss/ciencia/"));
        SECTIONS.add(new Section("Cine", 0, "http://www.20minutos.es/rss/cine"));
        SECTIONS.add(new Section("Comunidad 20", 0, "http://www.20minutos.es/rss/zona20"));
        SECTIONS.add(new Section("Deportes", 0, "http://www.20minutos.es/rss/deportes"));
        SECTIONS.add(new Section("Economía", 0, "http://www.20minutos.es/rss/economia"));
        SECTIONS.add(new Section("Formación y empleo", 0, "http://www.20minutos.es/rss/empleo"));
        SECTIONS.add(new Section("Grastronomía", 0, "http://www.20minutos.es/rss/gastronomia"));
        SECTIONS.add(new Section("Gente y televisión", 0, "http://www.20minutos.es/rss/gente-television"));
        SECTIONS.add(new Section("Internacional", 0, "http://www.20minutos.es/rss/internacional"));
        SECTIONS.add(new Section("Libros", 0, "http://www.20minutos.es/rss/libros"));
        SECTIONS.add(new Section("Motor", 0, "http://www.20minutos.es/rss/motor"));
        SECTIONS.add(new Section("Música", 0, "http://www.20minutos.es/rss/musica"));
        SECTIONS.add(new Section("Nacional", 0, "http://www.20minutos.es/rss/nacional"));
        SECTIONS.add(new Section("Salud", 0, "http://www.20minutos.es/rss/salud"));
        SECTIONS.add(new Section("Tecnología", 0, "http://www.20minutos.es/rss/tecnologia"));
        SECTIONS.add(new Section("Viajes", 0, "http://www.20minutos.es/rss/viajes"));
        SECTIONS.add(new Section("Vivienda y hogar", 0, "http://www.20minutos.es/rss/vivienda"));

        SECTIONS.add(new Section("Comunidades", 0, null));
        SECTIONS.add(new Section("Madrid", 1, "http://www.20minutos.es/rss/madrid"));
        SECTIONS.add(new Section("Cataluña", 1, "http://www.20minutos.es/rss/barcelona-cataluna"));
        SECTIONS.add(new Section("Andalucía", 1, "http://www.20minutos.es/rss/andalucia"));
        SECTIONS.add(new Section("Aragón", 1, "http://www.20minutos.es/rss/aragon"));
        SECTIONS.add(new Section("Asturias", 1, "http://www.20minutos.es/rss/asturias"));
        SECTIONS.add(new Section("Castilla y león", 1, "http://www.20minutos.es/rss/castilla-y-leon"));
        SECTIONS.add(new Section("C. valenciana", 1, "http://www.20minutos.es/rss/comunidad-valenciana"));
        SECTIONS.add(new Section("Galicia", 1, "http://www.20minutos.es/rss/galicia"));
        SECTIONS.add(new Section("País vasco", 1, "http://www.20minutos.es/rss/pais-vasco"));
        SECTIONS.add(new Section("Murcia", 1, "http://www.20minutos.es/rss/region-de-murcia"));

        SECTIONS.add(new Section("Ciudades", 0, null));
        SECTIONS.add(new Section("A coruña", 1, "http://www.20minutos.es/rss/coruna"));
        SECTIONS.add(new Section("Albacete", 1, "http://www.20minutos.es/rss/albacete"));
        SECTIONS.add(new Section("Algeciras", 1, "http://www.20minutos.es/rss/algeciras"));
        SECTIONS.add(new Section("Alicante", 1, "http://www.20minutos.es/rss/alicante"));
        SECTIONS.add(new Section("Almería", 1, "http://www.20minutos.es/rss/almeria"));
        SECTIONS.add(new Section("Ávila", 1, "http://www.20minutos.es/rss/avila"));
        SECTIONS.add(new Section("Badajoz", 1, "http://www.20minutos.es/rss/badajoz"));
        SECTIONS.add(new Section("Barcelona", 1, "http://www.20minutos.es/rss/barcelona"));
        SECTIONS.add(new Section("Bilbao", 1, "http://www.20minutos.es/rss/bilbao"));
        SECTIONS.add(new Section("Burgos", 1, "http://www.20minutos.es/rss/burgos"));
        SECTIONS.add(new Section("Cáceres", 1, "http://www.20minutos.es/rss/caceres"));
        SECTIONS.add(new Section("Cádiz", 1, "http://www.20minutos.es/rss/cadiz"));
        SECTIONS.add(new Section("Cartagena", 1, "http://www.20minutos.es/rss/cartagena"));
        SECTIONS.add(new Section("Castellón", 1, "http://www.20minutos.es/rss/castellon"));
        SECTIONS.add(new Section("Ceuta", 1, "http://www.20minutos.es/rss/ceuta"));
        SECTIONS.add(new Section("Ciudad real", 1, "http://www.20minutos.es/rss/ciudadreal"));
        SECTIONS.add(new Section("Córdoba", 1, "http://www.20minutos.es/rss/cordoba"));
        SECTIONS.add(new Section("Cuenca", 1, "http://www.20minutos.es/rss/cuenca"));
        SECTIONS.add(new Section("Elche", 1, "http://www.20minutos.es/rss/elche"));
        SECTIONS.add(new Section("Gijón", 1, "http://www.20minutos.es/rss/gijon"));
        SECTIONS.add(new Section("Girona", 1, "http://www.20minutos.es/rss/girona"));
        SECTIONS.add(new Section("Granada", 1, "http://www.20minutos.es/rss/granada"));
        SECTIONS.add(new Section("Guadalajara", 1, "http://www.20minutos.es/rss/guadalajara"));
        SECTIONS.add(new Section("Huelva", 1, "http://www.20minutos.es/rss/huelva"));
        SECTIONS.add(new Section("Huesca", 1, "http://www.20minutos.es/rss/huesca"));
        SECTIONS.add(new Section("Jaén", 1, "http://www.20minutos.es/rss/jaen"));
        SECTIONS.add(new Section("Jerez de la frontera", 1, "http://www.20minutos.es/rss/jerez"));
        SECTIONS.add(new Section("Las palmas", 1, "http://www.20minutos.es/rss/laspalmas"));
        SECTIONS.add(new Section("León", 1, "http://www.20minutos.es/rss/leon"));
        SECTIONS.add(new Section("Lleida", 1, "http://www.20minutos.es/rss/lleida"));
        SECTIONS.add(new Section("Logroño", 1, "http://www.20minutos.es/rss/logrono"));
        SECTIONS.add(new Section("Lugo", 1, "http://www.20minutos.es/rss/lugo"));
        SECTIONS.add(new Section("Madrid", 1, "http://www.20minutos.es/rss/madrid"));
        SECTIONS.add(new Section("Málaga", 1, "http://www.20minutos.es/rss/malaga"));
        SECTIONS.add(new Section("Marbella", 1, "http://www.20minutos.es/rss/marbella"));
        SECTIONS.add(new Section("Melilla", 1, "http://www.20minutos.es/rss/melilla"));
        SECTIONS.add(new Section("Murcia", 1, "http://www.20minutos.es/rss/murcia"));
        SECTIONS.add(new Section("Ourense", 1, "http://www.20minutos.es/rss/ourense"));
        SECTIONS.add(new Section("Oviedo", 1, "http://www.20minutos.es/rss/oviedo"));
        SECTIONS.add(new Section("Palencia", 1, "http://www.20minutos.es/rss/palencia"));
        SECTIONS.add(new Section("Palma de Mallorca", 1, "http://www.20minutos.es/rss/palma"));
        SECTIONS.add(new Section("Pamplona", 1, "http://www.20minutos.es/rss/pamplona"));
        SECTIONS.add(new Section("Pontevedra", 1, "http://www.20minutos.es/rss/pontevedra"));
        SECTIONS.add(new Section("Salamanca", 1, "http://www.20minutos.es/rss/salamanca"));
        SECTIONS.add(new Section("Santa cruz de Tenerife", 1, "http://www.20minutos.es/rss/tenerife"));
        SECTIONS.add(new Section("San Sebastián", 1, "http://www.20minutos.es/rss/sansebastian"));
        SECTIONS.add(new Section("Santander", 1, "http://www.20minutos.es/rss/santander"));
        SECTIONS.add(new Section("Santiago de Compostela", 1, "http://www.20minutos.es/rss/santiago"));
        SECTIONS.add(new Section("Segovia", 1, "http://www.20minutos.es/rss/segovia"));
        SECTIONS.add(new Section("Sevilla", 1, "http://www.20minutos.es/rss/sevilla"));
        SECTIONS.add(new Section("Soria", 1, "http://www.20minutos.es/rss/soria"));
        SECTIONS.add(new Section("Tarragona", 1, "http://www.20minutos.es/rss/tarragona"));
        SECTIONS.add(new Section("Teruel", 1, "http://www.20minutos.es/rss/teruel"));
        SECTIONS.add(new Section("Toledo", 1, "http://www.20minutos.es/rss/toledo"));
        SECTIONS.add(new Section("Valencia", 1, "http://www.20minutos.es/rss/valencia"));
        SECTIONS.add(new Section("Valladolid", 1, "http://www.20minutos.es/rss/valladolid"));
        SECTIONS.add(new Section("Vigo", 1, "http://www.20minutos.es/rss/vigo"));
        SECTIONS.add(new Section("Vitoria", 1, "http://www.20minutos.es/rss/vitoria"));
        SECTIONS.add(new Section("Zamora", 1, "http://www.20minutos.es/rss/zamora"));
        SECTIONS.add(new Section("Zaragoza", 1, "http://www.20minutos.es/rss/zaragoza"));

    }

    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        News res = new News(title, link, "", date, categories);

        org.jsoup.nodes.Element doc = org.jsoup.Jsoup.parse(description).select("body").get(0);

        org.jsoup.select.Elements eelasts = doc.select("[clear=all]");
        if (!eelasts.isEmpty()) {
            org.jsoup.select.Elements elements = doc.children();

            int last = elements.indexOf(eelasts.get(0));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < last; ++i) {
                sb.append(elements.get(i).outerHtml());
            }
            res.content = sb.toString();
        } else {
            res.content = doc.html();
        }

        return res;
    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }

} 