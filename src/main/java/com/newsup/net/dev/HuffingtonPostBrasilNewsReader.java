package com.newsup.net.dev;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;


public class HuffingtonPostBrasilNewsReader extends NewsReader {

    public HuffingtonPostBrasilNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Brasil", 0, "http://www.brasilpost.com.br/feeds/verticals/brazil/news.xml"));
        SECTIONS.add(new Section("País", 1, "http://www.brasilpost.com.br/news/pais/feed/"));
        SECTIONS.add(new Section("Mundo", 1, "http://www.brasilpost.com.br/news/brasil-mundo/feed/"));
        SECTIONS.add(new Section("Dinheiro", 1, "http://www.brasilpost.com.br/news/dinheiro/feed/"));
        SECTIONS.add(new Section("Comportamento", 1, "http://www.brasilpost.com.br/news/comportamento/feed/"));
        SECTIONS.add(new Section("Tecnologia", 1, "http://www.brasilpost.com.br/news/brasil-tecnologia/feed/"));
        SECTIONS.add(new Section("Viral", 1, "http://www.brasilpost.com.br/news/viral/feed/"));
        SECTIONS.add(new Section("Esportes", 1, "http://www.brasilpost.com.br/news/esportes/feed/"));
        SECTIONS.add(new Section("Diversão", 1, "http://www.brasilpost.com.br/news/diversao/feed/"));
        SECTIONS.add(new Section("Outra Medida", 1, "http://www.brasilpost.com.br/news/outra-medida/feed/"));
        SECTIONS.add(new Section("Ciência", 1, "http://www.brasilpost.com.br/news/brasil-ciencia/feed/"));
        SECTIONS.add(new Section("Educação", 1, "http://www.brasilpost.com.br/news/educacao/feed/"));
        SECTIONS.add(new Section("Saúde", 1, "http://www.brasilpost.com.br/news/saude/feed/"));
        SECTIONS.add(new Section("Inovação", 1, "http://www.brasilpost.com.br/news/inovacao/feed/"));
        SECTIONS.add(new Section("Família", 1, "http://www.brasilpost.com.br/news/brasil-familia/feed/"));
        SECTIONS.add(new Section("Mulheres", 1, "http://www.brasilpost.com.br/news/mulheres/feed/"));
        SECTIONS.add(new Section("Homem", 1, "http://www.brasilpost.com.br/news/homem/feed/"));
        SECTIONS.add(new Section("Gay", 1, "http://www.brasilpost.com.br/news/brasil-gay/feed/"));
        SECTIONS.add(new Section("Animais", 1, "http://www.brasilpost.com.br/news/animais/feed/"));
        SECTIONS.add(new Section("Meio ambiente", 1, "http://www.brasilpost.com.br/news/meio-ambiente/feed/"));
        SECTIONS.add(new Section("Tem jeito", 1, "http://www.brasilpost.com.br/news/tem-jeito/feed/"));
        SECTIONS.add(new Section("Dinheiro", 1, "http://www.brasilpost.com.br/news/dinheiro/feed/"));
        SECTIONS.add(new Section("Gente", 1, "http://www.brasilpost.com.br/news/gente/feed/"));
        SECTIONS.add(new Section("Arte", 1, "http://www.brasilpost.com.br/news/arte/feed/"));
        SECTIONS.add(new Section("Carnaval", 1, "http://www.brasilpost.com.br/news/carnaval/feed/"));
        SECTIONS.add(new Section("Familia", 1, "http://www.brasilpost.com.br/news/brasil-familia/feed/"));
        SECTIONS.add(new Section("Sexo", 1, "http://www.brasilpost.com.br/news/brasil-sexo/feed/"));
        SECTIONS.add(new Section("Midia", 1, "http://www.brasilpost.com.br/news/midia/feed/"));
        SECTIONS.add(new Section("Videos", 1, "http://www.brasilpost.com.br/news/brasil-post-videos/feed/"));
        SECTIONS.add(new Section("Blogs", 1, "http://www.brasilpost.com.br/feeds/verticals/brazil/blog.xml"));

    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }
}
