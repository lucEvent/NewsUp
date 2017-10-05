package com.lucevent.newsup.data.event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Server side event
 */
public class SEvent {

    static class EventInfo {
        String lang, title, description;
    }

    static class EventSite {
        int site_code;
        int[] section_codes;
    }

    public int code;

    public long startTime, endTime;

    public EventInfo[] info;

    public String imgSrc;

    public String[] tags;

    public EventSite[] sites;

    public SEvent()
    {
    }

    public static SEvent parse(String json)
    {
        SEvent res = new SEvent();
        try {
            JSONObject parsed = new JSONObject(json);

            res.code = parsed.getInt("code");
            res.startTime = parsed.getLong("start");
            res.endTime = parsed.getLong("end");
            res.imgSrc = parsed.getString("imgsrc");
            res.tags = parsed.getString("tags").split(",");

            JSONArray parsedInfo = parsed.getJSONArray("info");
            res.info = new EventInfo[parsedInfo.length()];
            for (int i = 0; i < parsedInfo.length(); i++) {
                JSONObject p = parsedInfo.getJSONObject(i);

                res.info[i] = new EventInfo();
                res.info[i].lang = p.getString("lang");
                res.info[i].title = p.getString("title");
                res.info[i].description = p.getString("description");
            }

            JSONArray parsedSites = parsed.getJSONArray("info");
            res.sites = new EventSite[parsedSites.length()];
            for (int i = 0; i < parsedSites.length(); i++) {
                JSONObject p = parsedSites.getJSONObject(i);

                res.sites[i] = new EventSite();
                res.sites[i].site_code = p.getInt("sitecode");

                String[] section_codes = p.getString("seccodes").split(",");
                res.sites[i].section_codes = new int[section_codes.length];
                for (int j = 0; j < section_codes.length; j++)
                    res.sites[i].section_codes[j] = Integer.parseInt(section_codes[j]);
            }

        } catch (JSONException e) {
            System.out.println("JSON exeption:" + e.getMessage());
        }
        return res;
    }

}
