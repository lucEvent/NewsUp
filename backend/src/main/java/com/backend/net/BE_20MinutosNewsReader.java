package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_20MinutosNewsReader extends BE_NewsReader {

    public BE_20MinutosNewsReader() {
        super();

        SECTIONS = new BE_Sections();

        SECTIONS.add(new BE_Section("Titulares", "http://www.20minutos.es/rss/"));
        SECTIONS.add(new BE_Section("Artes", "http://www.20minutos.es/rss/artes/"));
        SECTIONS.add(new BE_Section("Artrend", "http://www.20minutos.es/rss/artrend/"));
        SECTIONS.add(new BE_Section("Ciencia", "http://www.20minutos.es/rss/ciencia/"));
        SECTIONS.add(new BE_Section("Cine", "http://www.20minutos.es/rss/cine"));
        SECTIONS.add(new BE_Section("Comunidad 20", "http://www.20minutos.es/rss/zona20"));
        SECTIONS.add(new BE_Section("Deportes", "http://www.20minutos.es/rss/deportes"));
        SECTIONS.add(new BE_Section("Economía", "http://www.20minutos.es/rss/economia"));
        SECTIONS.add(new BE_Section("Formación y empleo", "http://www.20minutos.es/rss/empleo"));
        SECTIONS.add(new BE_Section("Grastronomía", "http://www.20minutos.es/rss/gastronomia"));
        SECTIONS.add(new BE_Section("Gente y televisión", "http://www.20minutos.es/rss/gente-television"));
        SECTIONS.add(new BE_Section("Internacional", "http://www.20minutos.es/rss/internacional"));
        SECTIONS.add(new BE_Section("Libros", "http://www.20minutos.es/rss/libros"));
        SECTIONS.add(new BE_Section("Motor", "http://www.20minutos.es/rss/motor"));
        SECTIONS.add(new BE_Section("Música", "http://www.20minutos.es/rss/musica"));
        SECTIONS.add(new BE_Section("Nacional", "http://www.20minutos.es/rss/nacional"));
        SECTIONS.add(new BE_Section("Salud", "http://www.20minutos.es/rss/salud"));
        SECTIONS.add(new BE_Section("Tecnología", "http://www.20minutos.es/rss/tecnologia"));
        SECTIONS.add(new BE_Section("Viajes", "http://www.20minutos.es/rss/viajes"));
        SECTIONS.add(new BE_Section("Vivienda y hogar", "http://www.20minutos.es/rss/vivienda"));

        SECTIONS.add(new BE_Section("Comunidades", null));
        SECTIONS.add(new BE_Section("Madrid", "http://www.20minutos.es/rss/madrid"));
        SECTIONS.add(new BE_Section("Cataluña", "http://www.20minutos.es/rss/barcelona-cataluna"));
        SECTIONS.add(new BE_Section("Andalucía", "http://www.20minutos.es/rss/andalucia"));
        SECTIONS.add(new BE_Section("Aragón", "http://www.20minutos.es/rss/aragon"));
        SECTIONS.add(new BE_Section("Asturias", "http://www.20minutos.es/rss/asturias"));
        SECTIONS.add(new BE_Section("Castilla y león", "http://www.20minutos.es/rss/castilla-y-leon"));
        SECTIONS.add(new BE_Section("C. valenciana", "http://www.20minutos.es/rss/comunidad-valenciana"));
        SECTIONS.add(new BE_Section("Galicia", "http://www.20minutos.es/rss/galicia"));
        SECTIONS.add(new BE_Section("País vasco", "http://www.20minutos.es/rss/pais-vasco"));
        SECTIONS.add(new BE_Section("Murcia", "http://www.20minutos.es/rss/region-de-murcia"));

        SECTIONS.add(new BE_Section("Ciudades", null));
        SECTIONS.add(new BE_Section("A coruña", "http://www.20minutos.es/rss/coruna"));
        SECTIONS.add(new BE_Section("Albacete", "http://www.20minutos.es/rss/albacete"));
        SECTIONS.add(new BE_Section("Algeciras", "http://www.20minutos.es/rss/algeciras"));
        SECTIONS.add(new BE_Section("Alicante", "http://www.20minutos.es/rss/alicante"));
        SECTIONS.add(new BE_Section("Almería", "http://www.20minutos.es/rss/almeria"));
        SECTIONS.add(new BE_Section("Avila", "http://www.20minutos.es/rss/avila"));
        SECTIONS.add(new BE_Section("Badajoz", "http://www.20minutos.es/rss/badajoz"));
        SECTIONS.add(new BE_Section("Barcelona", "http://www.20minutos.es/rss/barcelona"));
        SECTIONS.add(new BE_Section("Bilbao", "http://www.20minutos.es/rss/bilbao"));
        SECTIONS.add(new BE_Section("Burgos", "http://www.20minutos.es/rss/burgos"));
        SECTIONS.add(new BE_Section("Cáceres", "http://www.20minutos.es/rss/caceres"));
        SECTIONS.add(new BE_Section("Cádiz", "http://www.20minutos.es/rss/cadiz"));
        SECTIONS.add(new BE_Section("Cartagena", "http://www.20minutos.es/rss/cartagena"));
        SECTIONS.add(new BE_Section("Castellón", "http://www.20minutos.es/rss/castellon"));
        SECTIONS.add(new BE_Section("Ceuta", "http://www.20minutos.es/rss/ceuta"));
        SECTIONS.add(new BE_Section("Ciudad real", "http://www.20minutos.es/rss/ciudadreal"));
        SECTIONS.add(new BE_Section("Córdoba", "http://www.20minutos.es/rss/cordoba"));
        SECTIONS.add(new BE_Section("Cuenca", "http://www.20minutos.es/rss/cuenca"));
        SECTIONS.add(new BE_Section("Elche", "http://www.20minutos.es/rss/elche"));
        SECTIONS.add(new BE_Section("Gijón", "http://www.20minutos.es/rss/gijon"));
        SECTIONS.add(new BE_Section("Girona", "http://www.20minutos.es/rss/girona"));
        SECTIONS.add(new BE_Section("Granada", "http://www.20minutos.es/rss/granada"));
        SECTIONS.add(new BE_Section("Guadalajara", "http://www.20minutos.es/rss/guadalajara"));
        SECTIONS.add(new BE_Section("Huelva", "http://www.20minutos.es/rss/huelva"));
        SECTIONS.add(new BE_Section("Huesca", "http://www.20minutos.es/rss/huesca"));
        SECTIONS.add(new BE_Section("Jaén", "http://www.20minutos.es/rss/jaen"));
        SECTIONS.add(new BE_Section("Jerez de la frontera", "http://www.20minutos.es/rss/jerez"));
        SECTIONS.add(new BE_Section("Las palmas", "http://www.20minutos.es/rss/laspalmas"));
        SECTIONS.add(new BE_Section("León", "http://www.20minutos.es/rss/leon"));
        SECTIONS.add(new BE_Section("Lleida", "http://www.20minutos.es/rss/lleida"));
        SECTIONS.add(new BE_Section("Logroño", "http://www.20minutos.es/rss/logrono"));
        SECTIONS.add(new BE_Section("Lugo", "http://www.20minutos.es/rss/lugo"));
        SECTIONS.add(new BE_Section("Madrid", "http://www.20minutos.es/rss/madrid"));
        SECTIONS.add(new BE_Section("Málaga", "http://www.20minutos.es/rss/malaga"));
        SECTIONS.add(new BE_Section("Marbella", "http://www.20minutos.es/rss/marbella"));
        SECTIONS.add(new BE_Section("Melilla", "http://www.20minutos.es/rss/melilla"));
        SECTIONS.add(new BE_Section("Murcia", "http://www.20minutos.es/rss/murcia"));
        SECTIONS.add(new BE_Section("Ourense", "http://www.20minutos.es/rss/ourense"));
        SECTIONS.add(new BE_Section("Oviedo", "http://www.20minutos.es/rss/oviedo"));
        SECTIONS.add(new BE_Section("Palencia", "http://www.20minutos.es/rss/palencia"));
        SECTIONS.add(new BE_Section("Palma de Mallorca", "http://www.20minutos.es/rss/palma"));
        SECTIONS.add(new BE_Section("Pamplona", "http://www.20minutos.es/rss/pamplona"));
        SECTIONS.add(new BE_Section("Pontevedra", "http://www.20minutos.es/rss/pontevedra"));
        SECTIONS.add(new BE_Section("Salamanca", "http://www.20minutos.es/rss/salamanca"));
        SECTIONS.add(new BE_Section("Santa cruz de Tenerife", "http://www.20minutos.es/rss/tenerife"));
        SECTIONS.add(new BE_Section("San Sebastián", "http://www.20minutos.es/rss/sansebastian"));
        SECTIONS.add(new BE_Section("Santander", "http://www.20minutos.es/rss/santander"));
        SECTIONS.add(new BE_Section("Santiago de Compostela", "http://www.20minutos.es/rss/santiago"));
        SECTIONS.add(new BE_Section("Segovia", "http://www.20minutos.es/rss/segovia"));
        SECTIONS.add(new BE_Section("Sevilla", "http://www.20minutos.es/rss/sevilla"));
        SECTIONS.add(new BE_Section("Soria", "http://www.20minutos.es/rss/soria"));
        SECTIONS.add(new BE_Section("Tarragona", "http://www.20minutos.es/rss/tarragona"));
        SECTIONS.add(new BE_Section("Teruel", "http://www.20minutos.es/rss/teruel"));
        SECTIONS.add(new BE_Section("Toledo", "http://www.20minutos.es/rss/toledo"));
        SECTIONS.add(new BE_Section("Valencia", "http://www.20minutos.es/rss/valencia"));
        SECTIONS.add(new BE_Section("Valladolid", "http://www.20minutos.es/rss/valladolid"));
        SECTIONS.add(new BE_Section("Vigo", "http://www.20minutos.es/rss/vigo"));
        SECTIONS.add(new BE_Section("Vitoria", "http://www.20minutos.es/rss/vitoria"));
        SECTIONS.add(new BE_Section("Zamora", "http://www.20minutos.es/rss/zamora"));
        SECTIONS.add(new BE_Section("Zaragoza", "http://www.20minutos.es/rss/zaragoza"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        doc.select("body > br, body > img, body > a").remove();

        news.description = "";
        news.content = doc.select("body").html();
        return news;
    }

}