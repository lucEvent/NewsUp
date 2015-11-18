package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;


public class HuffingtonPostBrasilNewsReader extends NewsReader {

    public HuffingtonPostBrasilNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new SectionDeprecated("Brasil", 0, "http://www.brasilpost.com.br/feeds/verticals/brazil/news.xml"));
        SECTIONS.add(new SectionDeprecated("País", 1, "http://www.brasilpost.com.br/news/pais/feed/"));
        SECTIONS.add(new SectionDeprecated("Mundo", 1, "http://www.brasilpost.com.br/news/brasil-mundo/feed/"));
        SECTIONS.add(new SectionDeprecated("Dinheiro", 1, "http://www.brasilpost.com.br/news/dinheiro/feed/"));
        SECTIONS.add(new SectionDeprecated("Comportamento", 1, "http://www.brasilpost.com.br/news/comportamento/feed/"));
        SECTIONS.add(new SectionDeprecated("Tecnologia", 1, "http://www.brasilpost.com.br/news/brasil-tecnologia/feed/"));
        SECTIONS.add(new SectionDeprecated("Viral", 1, "http://www.brasilpost.com.br/news/viral/feed/"));
        SECTIONS.add(new SectionDeprecated("Esportes", 1, "http://www.brasilpost.com.br/news/esportes/feed/"));
        SECTIONS.add(new SectionDeprecated("Diversão", 1, "http://www.brasilpost.com.br/news/diversao/feed/"));
        SECTIONS.add(new SectionDeprecated("Outra Medida", 1, "http://www.brasilpost.com.br/news/outra-medida/feed/"));
        SECTIONS.add(new SectionDeprecated("Ciência", 1, "http://www.brasilpost.com.br/news/brasil-ciencia/feed/"));
        SECTIONS.add(new SectionDeprecated("Educação", 1, "http://www.brasilpost.com.br/news/educacao/feed/"));
        SECTIONS.add(new SectionDeprecated("Saúde", 1, "http://www.brasilpost.com.br/news/saude/feed/"));
        SECTIONS.add(new SectionDeprecated("Inovação", 1, "http://www.brasilpost.com.br/news/inovacao/feed/"));
        SECTIONS.add(new SectionDeprecated("Família", 1, "http://www.brasilpost.com.br/news/brasil-familia/feed/"));
        SECTIONS.add(new SectionDeprecated("Mulheres", 1, "http://www.brasilpost.com.br/news/mulheres/feed/"));
        SECTIONS.add(new SectionDeprecated("Homem", 1, "http://www.brasilpost.com.br/news/homem/feed/"));
        SECTIONS.add(new SectionDeprecated("Gay", 1, "http://www.brasilpost.com.br/news/brasil-gay/feed/"));
        SECTIONS.add(new SectionDeprecated("Animais", 1, "http://www.brasilpost.com.br/news/animais/feed/"));
        SECTIONS.add(new SectionDeprecated("Meio ambiente", 1, "http://www.brasilpost.com.br/news/meio-ambiente/feed/"));
        SECTIONS.add(new SectionDeprecated("Tem jeito", 1, "http://www.brasilpost.com.br/news/tem-jeito/feed/"));
        SECTIONS.add(new SectionDeprecated("Dinheiro", 1, "http://www.brasilpost.com.br/news/dinheiro/feed/"));
        SECTIONS.add(new SectionDeprecated("Gente", 1, "http://www.brasilpost.com.br/news/gente/feed/"));
        SECTIONS.add(new SectionDeprecated("Arte", 1, "http://www.brasilpost.com.br/news/arte/feed/"));
        SECTIONS.add(new SectionDeprecated("Carnaval", 1, "http://www.brasilpost.com.br/news/carnaval/feed/"));
        SECTIONS.add(new SectionDeprecated("Familia", 1, "http://www.brasilpost.com.br/news/brasil-familia/feed/"));
        SECTIONS.add(new SectionDeprecated("Sexo", 1, "http://www.brasilpost.com.br/news/brasil-sexo/feed/"));
        SECTIONS.add(new SectionDeprecated("Midia", 1, "http://www.brasilpost.com.br/news/midia/feed/"));
        SECTIONS.add(new SectionDeprecated("Videos", 1, "http://www.brasilpost.com.br/news/brasil-post-videos/feed/"));
        SECTIONS.add(new SectionDeprecated("Blogs", 1, "http://www.brasilpost.com.br/feeds/verticals/brazil/blog.xml"));

    }

}
