package com.lucevent.newsup.data.sports.util;

public class LeagueTableRow {

    public String badgeUrl;
    public String teamName;
    public String points;
    public String games;
    public String won;
    public String draw;
    public String lost;
    public String scored;
    public String received;

    public LeagueTableRow()
    {
    }

    public String wrap()
    {
        return badgeUrl + "," + teamName + "," + points + "," + games + "," + won + "," + draw + "," + lost + "," + scored + "," + received;
    }

    public void unWrap(String wrapper)
    {
        String[] data = wrapper.split(",");
        badgeUrl = data[0];
        teamName = data[1];
        points = data[2];
        games = data[3];
        won = data[4];
        draw = data[5];
        lost = data[6];
        scored = data[7];
        received = data[8];
    }

}
