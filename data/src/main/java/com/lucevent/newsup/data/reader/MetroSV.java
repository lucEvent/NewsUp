package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsStylist;

import org.jsoup.nodes.Element;

@Deprecated
public class MetroSV extends com.lucevent.newsup.data.util.NewsReader {

    public MetroSV()
    {
        super(TAG_ITEM_ITEMS,
                new int[]{},
                new int[]{},
                new int[]{},
                new int[]{},
                new int[]{},
                new int[]{},
                new int[]{});
    }

}
