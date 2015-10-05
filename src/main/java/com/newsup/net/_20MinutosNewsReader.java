package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class _20MinutosNewsReader extends NewsReader {

    public _20MinutosNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();

        SECTIONS.add(new Section("TITULARES", 0, "http://www.20minutos.es/rss/"));
        SECTIONS.add(new Section("ARTES", 0, "http://www.20minutos.es/rss/artes/"));
        SECTIONS.add(new Section("ARTREND", 0, "http://www.20minutos.es/rss/artrend/"));
        SECTIONS.add(new Section("CIENCIA", 0, "http://www.20minutos.es/rss/ciencia/"));
        SECTIONS.add(new Section("CINE", 0, "http://www.20minutos.es/rss/cine"));
        SECTIONS.add(new Section("COMUNIDAD 20", 0, "http://www.20minutos.es/rss/zona20"));
        SECTIONS.add(new Section("DEPORTES", 0, "http://www.20minutos.es/rss/deportes"));
        SECTIONS.add(new Section("ECONOMÍA", 0, "http://www.20minutos.es/rss/economia"));
        SECTIONS.add(new Section("FORMACIÓN Y EMPLEO", 0, "http://www.20minutos.es/rss/empleo"));
        SECTIONS.add(new Section("GRASTRONOMÍA", 0, "http://www.20minutos.es/rss/gastronomia"));
        SECTIONS.add(new Section("GENTE Y TELEVISIÓN", 0, "http://www.20minutos.es/rss/gente-television"));
        SECTIONS.add(new Section("INTERNACIONAL", 0, "http://www.20minutos.es/rss/internacional"));
        SECTIONS.add(new Section("LIBROS", 0, "http://www.20minutos.es/rss/libros"));
        SECTIONS.add(new Section("MOTOR", 0, "http://www.20minutos.es/rss/motor"));
        SECTIONS.add(new Section("MÚSICA", 0, "http://www.20minutos.es/rss/musica"));
        SECTIONS.add(new Section("NACIONAL", 0, "http://www.20minutos.es/rss/nacional"));
        SECTIONS.add(new Section("SALUD", 0, "http://www.20minutos.es/rss/salud"));
        SECTIONS.add(new Section("TECNOLOGÍA", 0, "http://www.20minutos.es/rss/tecnologia"));
        SECTIONS.add(new Section("VIAJES", 0, "http://www.20minutos.es/rss/viajes"));
        SECTIONS.add(new Section("VIVIENDA Y HOGAR", 0, "http://www.20minutos.es/rss/vivienda"));
        SECTIONS.add(new Section("MADRID", 0, "http://www.20minutos.es/rss/madird"));
        SECTIONS.add(new Section("CATALUÑA", 0, "http://www.20minutos.es/rss/barcelona-cataluna"));
        SECTIONS.add(new Section("ANDALUCÍA", 0, "http://www.20minutos.es/rss/andalucia"));
        SECTIONS.add(new Section("ARAGON", 0, "http://www.20minutos.es/rss/aragon"));
        SECTIONS.add(new Section("ASTURIAS", 0, "http://www.20minutos.es/rss/asturias"));
        SECTIONS.add(new Section("CASTILLA Y LEÓN", 0, "http://www.20minutos.es/rss/castilla-y-leon"));
        SECTIONS.add(new Section("C. VALENCIANA", 0, "http://www.20minutos.es/rss/comunidad-valenciana"));
        SECTIONS.add(new Section("GALICIA", 0, "http://www.20minutos.es/rss/galicia"));
        SECTIONS.add(new Section("PAÍS VASCO", 0, "http://www.20minutos.es/rss/pais-vasco"));
        SECTIONS.add(new Section("MURCIA", 0, "http://www.20minutos.es/rss/region-de-murcia"));
        SECTIONS.add(new Section("A CORUÑA", 0, "http://www.20minutos.es/rss/coruna"));
        SECTIONS.add(new Section("ALBACETE", 0, "http://www.20minutos.es/rss/albacete"));
        SECTIONS.add(new Section("ALGECIRAS", 0, "http://www.20minutos.es/rss/algeciras"));
        SECTIONS.add(new Section("ALICANTE", 0, "http://www.20minutos.es/rss/alicante"));
        SECTIONS.add(new Section("ALMERÍA", 0, "http://www.20minutos.es/rss/almeria"));
        SECTIONS.add(new Section("ÁVILA", 0, "http://www.20minutos.es/rss/avila"));
        SECTIONS.add(new Section("BADAJOZ", 0, "http://www.20minutos.es/rss/badajoz"));
        SECTIONS.add(new Section("BARCELONA", 0, "http://www.20minutos.es/rss/barcelona"));
        SECTIONS.add(new Section("BILBAO", 0, "http://www.20minutos.es/rss/bilbao"));
        SECTIONS.add(new Section("BURGOS", 0, "http://www.20minutos.es/rss/burgos"));
        SECTIONS.add(new Section("CÁCERES", 0, "http://www.20minutos.es/rss/caceres"));
        SECTIONS.add(new Section("CÁDIZ", 0, "http://www.20minutos.es/rss/cadiz"));
        SECTIONS.add(new Section("CARTAGENA", 0, "http://www.20minutos.es/rss/cartagena"));
        SECTIONS.add(new Section("CASTELLÓN", 0, "http://www.20minutos.es/rss/castellon"));
        SECTIONS.add(new Section("CEUTA", 0, "http://www.20minutos.es/rss/ceuta"));
        SECTIONS.add(new Section("CIUDAD REAL", 0, "http://www.20minutos.es/rss/ciudadreal"));
        SECTIONS.add(new Section("CÓRDOBA", 0, "http://www.20minutos.es/rss/cordoba"));
        SECTIONS.add(new Section("CUENCA", 0, "http://www.20minutos.es/rss/cuenca"));
        SECTIONS.add(new Section("ELCHE", 0, "http://www.20minutos.es/rss/elche"));
        SECTIONS.add(new Section("GIJÓN", 0, "http://www.20minutos.es/rss/gijon"));
        SECTIONS.add(new Section("GIRONA", 0, "http://www.20minutos.es/rss/girona"));
        SECTIONS.add(new Section("GRANADA", 0, "http://www.20minutos.es/rss/granada"));
        SECTIONS.add(new Section("GUADALAJARA", 0, "http://www.20minutos.es/rss/guadalajara"));
        SECTIONS.add(new Section("HUELVA", 0, "http://www.20minutos.es/rss/huelva"));
        SECTIONS.add(new Section("HUESCA", 0, "http://www.20minutos.es/rss/huesca"));
        SECTIONS.add(new Section("JAÉN", 0, "http://www.20minutos.es/rss/jaen"));
        SECTIONS.add(new Section("JEREZ DE LA FRONTERA", 0, "http://www.20minutos.es/rss/jerez"));
        SECTIONS.add(new Section("LAS PALMAS", 0, "http://www.20minutos.es/rss/laspalmas"));
        SECTIONS.add(new Section("LEÓN", 0, "http://www.20minutos.es/rss/leon"));
        SECTIONS.add(new Section("LLEIDA", 0, "http://www.20minutos.es/rss/lleida"));
        SECTIONS.add(new Section("LOGROÑO", 0, "http://www.20minutos.es/rss/logrono"));
        SECTIONS.add(new Section("LUGO", 0, "http://www.20minutos.es/rss/lugo"));
        SECTIONS.add(new Section("MADRID", 0, "http://www.20minutos.es/rss/madrid"));
        SECTIONS.add(new Section("MÁLAGA", 0, "http://www.20minutos.es/rss/malaga"));
        SECTIONS.add(new Section("MARBELLA", 0, "http://www.20minutos.es/rss/marbella"));
        SECTIONS.add(new Section("MELILLA", 0, "http://www.20minutos.es/rss/melilla"));
        SECTIONS.add(new Section("MURCIA", 0, "http://www.20minutos.es/rss/murcia"));
        SECTIONS.add(new Section("OURENSE", 0, "http://www.20minutos.es/rss/ourense"));
        SECTIONS.add(new Section("OVIEDO", 0, "http://www.20minutos.es/rss/oviedo"));
        SECTIONS.add(new Section("PALENCIA", 0, "http://www.20minutos.es/rss/palencia"));
        SECTIONS.add(new Section("PALMA DE MALLORCA", 0, "http://www.20minutos.es/rss/palma"));
        SECTIONS.add(new Section("PAMPLONA", 0, "http://www.20minutos.es/rss/pamplona"));
        SECTIONS.add(new Section("PONTEVEDRA", 0, "http://www.20minutos.es/rss/pontevedra"));
        SECTIONS.add(new Section("SALAMANCA", 0, "http://www.20minutos.es/rss/salamanca"));
        SECTIONS.add(new Section("SANTA CRUZ DE TENERIFE", 0, "http://www.20minutos.es/rss/tenerife"));
        SECTIONS.add(new Section("SAN SEBASTIÁN", 0, "http://www.20minutos.es/rss/sansebastian"));
        SECTIONS.add(new Section("SANTANDER", 0, "http://www.20minutos.es/rss/santander"));
        SECTIONS.add(new Section("SANTIAGO DE COMPOSTELA", 0, "http://www.20minutos.es/rss/santiago"));
        SECTIONS.add(new Section("SEGOVIA", 0, "http://www.20minutos.es/rss/segovia"));
        SECTIONS.add(new Section("SEVILLA", 0, "http://www.20minutos.es/rss/sevilla"));
        SECTIONS.add(new Section("SORIA", 0, "http://www.20minutos.es/rss/soria"));
        SECTIONS.add(new Section("TARRAGONA", 0, "http://www.20minutos.es/rss/tarragona"));
        SECTIONS.add(new Section("TERUEL", 0, "http://www.20minutos.es/rss/teruel"));
        SECTIONS.add(new Section("TOLEDO", 0, "http://www.20minutos.es/rss/toledo"));
        SECTIONS.add(new Section("VALENCIA", 0, "http://www.20minutos.es/rss/valencia"));
        SECTIONS.add(new Section("VALLADOLID", 0, "http://www.20minutos.es/rss/valladolid"));
        SECTIONS.add(new Section("VIGO", 0, "http://www.20minutos.es/rss/vigo"));
        SECTIONS.add(new Section("VITORIA", 0, "http://www.20minutos.es/rss/vitoria"));
        SECTIONS.add(new Section("ZAMORA", 0, "http://www.20minutos.es/rss/zamora"));
        SECTIONS.add(new Section("ZARAGOZA", 0, "http://www.20minutos.es/rss/zaragoza"));

    }

    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        News result = new News(title, link, "", date, categories);
        result.content = description;
        return result;
    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##20M_NewsReader##", text);
    }

} 