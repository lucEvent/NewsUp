package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class XatakaSections extends Sections {

    public XatakaSections()
    {
        super();

        add(new Section("Principal", "http://www.xataka.com/index.xml", 0));
        add(new Section("Móvil", "http://www.xatakamovil.com/index.xml", 0));
        add(new Section("Fotografía", "http://www.xatakafoto.com/index.xml", 0));
        add(new Section("Android", "http://www.xatakandroid.com/index.xml", 0));
        add(new Section("Smart home", "http://www.xatakahome.com/index.xml", 0));
        add(new Section("Ciencia", "http://www.xatakaciencia.com/index.xml", 0));
        add(new Section("Windows", "http://www.xatakawindows.com/index.xml", 0));
        add(new Section("Videojuegos", "http://www.vidaextra.com/index.xml", 0));
        add(new Section("AppleSfera", "http://www.applesfera.com/index.xml", 0));
        add(new Section("GenBeta", "http://www.genbeta.com/index.xml", 0));
        add(new Section("Developer", "http://www.genbetadev.com/index.xml", 0));
        add(new Section("Magnet", "http://magnet.xataka.com/index.xml", 0));

    }

}
