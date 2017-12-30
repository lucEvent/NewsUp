package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class DailyExpressSections extends Sections {

    public DailyExpressSections()
    {
        super();

        add(new Section("News", "http://www.express.co.uk/posts/rss/77/news", 0));
        add(new Section("UK", "http://www.express.co.uk/posts/rss/1/uk", 1));
        add(new Section("World", "http://www.express.co.uk/posts/rss/78/world", 1));
        add(new Section("Politics", "http://www.express.co.uk/posts/rss/139/politics", 1));
        add(new Section("Royal", "http://www.express.co.uk/posts/rss/106/royal", 1));
        add(new Section("Weather", "http://www.express.co.uk/posts/rss/153/weather", 1));
        add(new Section("Nature", "http://www.express.co.uk/posts/rss/128/nature", 1));
        add(new Section("History", "http://www.express.co.uk/posts/rss/141/history", 1));
        add(new Section("Science", "http://www.express.co.uk/posts/rss/151/science", 1));
        add(new Section("Obituaries", "http://www.express.co.uk/posts/rss/140/obituaries", 1));
        add(new Section("Weird", "http://www.express.co.uk/posts/rss/80/weird", 1));
        add(new Section("Sunday", "http://www.express.co.uk/posts/rss/54/sunday", 1));
        add(new Section("Scotland", "http://www.express.co.uk/posts/rss/57/scotland", 1));
        add(new Section("Clarifications and Corrections", "http://www.express.co.uk/posts/rss/149/clarificationsandcorrections", 1));

        add(new Section("Showbiz & TV", "http://www.express.co.uk/posts/rss/152/showbiztv", 0));
        add(new Section("Celebrity News", "http://www.express.co.uk/posts/rss/79/celebritynews", 1));
        add(new Section("TV & Radio", "http://www.express.co.uk/posts/rss/20/tvradio", 1));

        add(new Section("Sport", "http://www.express.co.uk/posts/rss/65/sport", 0));
        add(new Section("Football", "http://www.express.co.uk/posts/rss/67/football", 1));
        add(new Section("Tennis", "http://www.express.co.uk/posts/rss/72/tennis", 1));
        add(new Section("Boxing", "http://www.express.co.uk/posts/rss/102/boxing", 1));
        add(new Section("F1", "http://www.express.co.uk/posts/rss/73/f1", 1));
        add(new Section("Cricket", "http://www.express.co.uk/posts/rss/68/cricket", 1));
        add(new Section("Golf", "http://www.express.co.uk/posts/rss/71/golf", 1));
        add(new Section("Rugby Union", "http://www.express.co.uk/posts/rss/69/rugbyunion", 1));
        add(new Section("Racing", "http://www.express.co.uk/posts/rss/74/racing", 1));
        add(new Section("Other", "http://www.express.co.uk/posts/rss/4/other", 1));

        add(new Section("Comment", "http://www.express.co.uk/posts/rss/16/comment", 0));
        add(new Section("Express Comment", "http://www.express.co.uk/posts/rss/41/expresscomment", 1));
        add(new Section("Beachcomber", "http://www.express.co.uk/posts/rss/107/beachcomber", 1));

        add(new Section("Finance", "http://www.express.co.uk/posts/rss/21/finance", 0));
        add(new Section("City & Business", "http://www.express.co.uk/posts/rss/22/citybusiness", 1));
        add(new Section("Personal Finance", "http://www.express.co.uk/posts/rss/23/personalfinance", 1));
        add(new Section("Retirement", "http://www.express.co.uk/posts/rss/50/retirement", 1));
        add(new Section("The Crusader", "http://www.express.co.uk/posts/rss/17/thecrusader", 1));

        add(new Section("Travel", "http://www.express.co.uk/posts/rss/132/travel", 0));
        add(new Section("Cruise", "http://www.express.co.uk/posts/rss/135/cruise", 1));
        add(new Section("Activity Holidays", "http://www.express.co.uk/posts/rss/133/activityholidays", 1));
        add(new Section("Beach Holidays", "http://www.express.co.uk/posts/rss/134/beachholidays", 1));
        add(new Section("Short & City breaks", "http://www.express.co.uk/posts/rss/136/shortcitybreaks", 1));

        add(new Section("Entertainment", "http://www.express.co.uk/posts/rss/18/entertainment", 0));
        add(new Section("Films", "http://www.express.co.uk/posts/rss/36/films", 1));
        add(new Section("Gaming", "http://www.express.co.uk/posts/rss/143/gaming", 1));
        add(new Section("Theatre", "http://www.express.co.uk/posts/rss/38/theatre", 1));
        add(new Section("Music", "http://www.express.co.uk/posts/rss/35/music", 1));
        add(new Section("Books", "http://www.express.co.uk/posts/rss/39/books", 1));

        add(new Section("Life & Style", "http://www.express.co.uk/posts/rss/8/lifestyle", 0));
        add(new Section("Style", "http://www.express.co.uk/posts/rss/12/style", 1));
        add(new Section("Life", "http://www.express.co.uk/posts/rss/130/life", 1));
        add(new Section("Health", "http://www.express.co.uk/posts/rss/11/health", 1));
        add(new Section("Cars", "http://www.express.co.uk/posts/rss/24/cars", 1));
        add(new Section("Garden", "http://www.express.co.uk/posts/rss/13/garden", 1));
        add(new Section("Food", "http://www.express.co.uk/posts/rss/14/food", 1));
        add(new Section("Property", "http://www.express.co.uk/posts/rss/51/property", 1));
        add(new Section("Diets", "http://www.express.co.uk/posts/rss/126/diets", 1));
        add(new Section("Tech", "http://www.express.co.uk/posts/rss/59/tech", 1));

        add(new Section("Top 10 Facts", "http://www.express.co.uk/posts/rss/109/top10facts", 0));

        add(new Section("Premier League", null, -1));
        add(new Section("Arsenal", "http://www.express.co.uk/posts/rss/67.3/arsenal", 1));
        add(new Section("Bournemouth", "http://www.express.co.uk/posts/rss/67.75/bournemouth", 1));
        add(new Section("Brighton & Hove Albion", "http://www.express.co.uk/posts/rss/67.62/brighton-hove-albion", 1));
        add(new Section("Burnley", "http://www.express.co.uk/posts/rss/67.23/burnley", 1));
        add(new Section("Chelsea", "http://www.express.co.uk/posts/rss/67.17/chelsea", 1));
        add(new Section("Crystal Palace", "http://www.express.co.uk/posts/rss/67.44/crystal-palace", 1));
        add(new Section("Everton", "http://www.express.co.uk/posts/rss/67.5/everton", 1));
        add(new Section("Huddersfield Town", "http://www.express.co.uk/posts/rss/67.66/huddersfield-town", 1));
        add(new Section("Leicester City", "http://www.express.co.uk/posts/rss/67.56/leicester-city", 1));
        add(new Section("Liverpool", "http://www.express.co.uk/posts/rss/67.15/liverpool", 1));
        add(new Section("Manchester City", "http://www.express.co.uk/posts/rss/67.20/manchester-city", 1));
        add(new Section("Manchester United", "http://www.express.co.uk/posts/rss/67.8/manchester-united", 1));
        add(new Section("Newcastle United", "http://www.express.co.uk/posts/rss/67.18/newcastle-united", 1));
        add(new Section("Southampton", "http://www.express.co.uk/posts/rss/67.35/southampton", 1));
        add(new Section("Stoke City", "http://www.express.co.uk/posts/rss/67.11/stoke-city", 1));
        add(new Section("Swansea City", "http://www.express.co.uk/posts/rss/67.24/swansea-city", 1));
        add(new Section("Tottenham Hotspur", "http://www.express.co.uk/posts/rss/67.12/tottenham-hotspur", 1));
        add(new Section("Watford", "http://www.express.co.uk/posts/rss/67.30/watford", 1));
        add(new Section("West Bromwich Albion", "http://www.express.co.uk/posts/rss/67.4/west-bromwich-albion", 1));
        add(new Section("West Ham", "http://www.express.co.uk/posts/rss/67.6/west-ham", 1));

        add(new Section("Championship", null, -1));
        add(new Section("Aston Villa", "http://www.express.co.uk/posts/rss/67.7/aston-villa", 1));
        add(new Section("Barnsley", "http://www.express.co.uk/posts/rss/67.39/barnsley", 1));
        add(new Section("Birmingham City", "http://www.express.co.uk/posts/rss/67.34/birmingham-city", 1));
        add(new Section("Bolton Wanderers", "http://www.express.co.uk/posts/rss/67.10/bolton-wanderers", 1));
        add(new Section("Brentford", "http://www.express.co.uk/posts/rss/67.88/brentford", 1));
        add(new Section("Cardiff City", "http://www.express.co.uk/posts/rss/67.31/cardiff-city", 1));
        add(new Section("Derby County", "http://www.express.co.uk/posts/rss/67.41/derby-county", 1));
        add(new Section("Fulham", "http://www.express.co.uk/posts/rss/67.21/fulham", 1));
        add(new Section("Ipswich Town", "http://www.express.co.uk/posts/rss/67.29/ipswich-town", 1));
        add(new Section("Leeds United", "http://www.express.co.uk/posts/rss/67.48/leeds-united", 1));
        add(new Section("Middlesbrough", "http://www.express.co.uk/posts/rss/67.13/middlesbrough", 1));
        add(new Section("Millwall", "http://www.express.co.uk/posts/rss/67.67/millwall", 1));
        add(new Section("Norwich City", "http://www.express.co.uk/posts/rss/67.40/norwich-city", 1));
        add(new Section("Nottingham Forest", "http://www.express.co.uk/posts/rss/67.25/nottingham-forest", 1));
        add(new Section("Queens Park Rangers", "http://www.express.co.uk/posts/rss/67.36/queens-park-rangers", 1));
        add(new Section("Reading", "http://www.express.co.uk/posts/rss/67.33/reading", 1));
        add(new Section("Sheffield Wednesday", "http://www.express.co.uk/posts/rss/67.42/sheffield-wednesday", 1));
        add(new Section("Sunderland", "http://www.express.co.uk/posts/rss/67.19/sunderland", 1));

        add(new Section("Scottish Premiership", null, -1));
        add(new Section("Aberdeen", "http://www.express.co.uk/posts/rss/67.103/aberdeen", 1));
        add(new Section("Celtic", "http://www.express.co.uk/posts/rss/67.99/celtic", 1));
        add(new Section("Dundee", "http://www.express.co.uk/posts/rss/67.110/dundee", 1));
        add(new Section("Hamilton Academical", "http://www.express.co.uk/posts/rss/67.96/hamilton-academical", 1));
        add(new Section("Hearts", "http://www.express.co.uk/posts/rss/67.102/hearts", 1));
        add(new Section("Hibernian", "http://www.express.co.uk/posts/rss/67.100/hibernian", 1));
        add(new Section("Kilmarnock", "http://www.express.co.uk/posts/rss/67.98/kilmarnock", 1));
        add(new Section("Motherwell", "http://www.express.co.uk/posts/rss/67.105/motherwell", 1));
        add(new Section("Partick Thistle", "http://www.express.co.uk/posts/rss/67.116/partick-thistle", 1));
        add(new Section("Rangers", "http://www.express.co.uk/posts/rss/67.94/rangers", 1));
        add(new Section("Ross County", "http://www.express.co.uk/posts/rss/67.111/ross-county", 1));
        add(new Section("St Johnstone", "http://www.express.co.uk/posts/rss/67.106/st-johnstone", 1));

        add(new Section("Columnists", "http://www.express.co.uk/posts/rss/40/columnists", 0));
        add(new Section("Adam Helliker", "http://www.express.co.uk/columnistsrss/40.237/adam-helliker", 1));
        add(new Section("Alan Titchmarsh", "http://www.express.co.uk/columnistsrss/40.278/alan-titchmarsh", 1));
        add(new Section("Ann Widdecombe", "http://www.express.co.uk/columnistsrss/40.277/ann-widdecombe", 1));
        add(new Section("Camilla Tominey", "http://www.express.co.uk/columnistsrss/40.269/camilla-tominey", 1));
        add(new Section("Carole Ann Rice", "http://www.express.co.uk/columnistsrss/40.73150/carole-ann-rice", 1));
        add(new Section("Dr Rosemary Leonard", "http://www.express.co.uk/columnistsrss/40.279/dr-rosemary-leonard", 1));
        add(new Section("Fergus Kelly", "http://www.express.co.uk/columnistsrss/40.73152/fergus-kelly", 1));
        add(new Section("Frederick Forsyth", "http://www.express.co.uk/columnistsrss/40.274/frederick-forsyth", 1));
        add(new Section("Jennifer Selway", "http://www.express.co.uk/columnistsrss/40.240/jennifer-selway", 1));
        add(new Section("John Ingham", "http://www.express.co.uk/columnistsrss/40.264/john-ingham", 1));
        add(new Section("Leo McKinstry", "http://www.express.co.uk/columnistsrss/40.275/leo-mckinstry", 1));
        add(new Section("Macer Hall", "http://www.express.co.uk/columnistsrss/40.16368/macer-hall", 1));
        add(new Section("Martin Townsend", "http://www.express.co.uk/columnistsrss/40.238/martin-townsend", 1));
        add(new Section("Mindy Hammond", "http://www.express.co.uk/columnistsrss/40.73154/mindy-hammond", 1));
        add(new Section("Nick Ferrari", "http://www.express.co.uk/columnistsrss/40.55514/nick-ferrari", 1));
        add(new Section("Peter Hill", "http://www.express.co.uk/columnistsrss/40.10637/peter-hill", 1));
        add(new Section("Richard and Judy", "http://www.express.co.uk/columnistsrss/40.272/richard-and-judy", 1));
        add(new Section("Stuart Winter", "http://www.express.co.uk/columnistsrss/40.268/stuart-winter", 1));
        add(new Section("Vanessa Feltz", "http://www.express.co.uk/columnistsrss/40.236/vanessa-feltz", 1));
        add(new Section("Virginia Blackburn", "http://www.express.co.uk/columnistsrss/40.273/virginia-blackburn", 1));

    }

}
