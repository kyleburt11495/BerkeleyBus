package com.example.bus.utilities;

import android.test.ActivityInstrumentationTestCase;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Kyle on 8/27/2017.
 */

public class NetworkUtils
{


    public static List<String> getArrivalTimes(String urlString)
    {
        List<String> nextPredictionsTextList = null;


        try {
            //connect to website
            org.jsoup.nodes.Document doc = Jsoup.connect(urlString).get();
            //check if any predictions exist
            if(doc.getElementsByClass("noPrediction").isEmpty() == false)
                return null;
            //get first prediction
            String firstPrediction = doc.getElementsByClass("firstPrediction").text();
            Elements nextPredictions = doc.getElementsByClass("secondaryPredictions");
            nextPredictionsTextList = nextPredictions.eachText();
            nextPredictionsTextList.add(0,firstPrediction);


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return nextPredictionsTextList;

    }

    public static String getAddress(String route, String direction, int position)
    {
        //Perimeter stop urls
        String downtownBerkeleyPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=berkbart_d&ts=berkbart_d";
        String oxfordPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=oxfouniv&ts=berkbart_d";
        String tolmanHallPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=tolmhall&ts=berkbart_d";
        String northGatePerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=nortgate&ts=berkbart_d";
        String coryHallPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=coryhall&ts=berkbart_d";
        String evansHallPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=evanhall&ts=berkbart_d";
        String gayleyPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=gaylstad&ts=berkbart_d";
        String haasPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=haasbusi&ts=berkbart_d";
        String internationalPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=intehous&ts=berkbart_d";
        String piedmontPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=piedchan&ts=berkbart_d";
        String collegePerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=collast&ts=berkbart_d";
        String kroeberPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=krobhall&ts=berkbart_d";
        String hearstGymPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=hrstmemo&ts=berkbart_d";
        String sproulPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=sprouhall&ts=berkbart_d";
        String recreationalPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=bancells&ts=berkbart_d";
        String banwayPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=bancshat&ts=berkbart_d";
        String shattuckPerimeter = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=shatkitt&ts=berkbart_d";

        //hill downhill urls
        String spaceSciencesDownhill = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=hill&d=down&s=spacelab_d&ts=lawrhall_ib";
        String lawrenceDownhill = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=hill&d=down&s=lawrhall_ib&ts=botagard_ib";
        String botanicalDownhill = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=hill&d=down&s=botagard_ib&ts=strawcany_ib";
        String strawberryDownhill = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=hill&d=down&s=strawcany_ib&ts=evanhall_a";
        String evansDownhill = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=hill&d=down&s=evanhall_a&ts=evanhall_a";

        //uphill urls
        String downtownUphill = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=hill&d=up&s=berkbart_d&ts=evanhall";
        String evansUphill = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=hill&d=up&s=evanhall&ts=strawcany";
        String strawberryUphill = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=hill&d=up&s=strawcany&ts=botagard";
        String botanicalUphill = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=hill&d=up&s=botagard&ts=lawrhall";
        String lawrenceUphill = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=hill&d=up&s=lawrhall&ts=spacelab_a";
        String spaceSciencesUphill = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=hill&d=up&s=spacelab_a&ts=spacelab_a";

        //central campus urls
        String downtownCentral = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=cent&d=loop&s=berkbart_d&ts=berkbart_d";
        String oxfordCentral = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=cent&d=loop&s=oxfouniv&ts=berkbart_d";
        String tolmanCentral = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=cent&d=loop&s=tolmhall&ts=berkbart_d";
        String northCentral = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=cent&d=loop&s=nortgate&ts=berkbart_d";
        String coryCentral = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=cent&d=loop&s=coryhall&ts=berkbart_d";
        String evansCentral = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=cent&d=loop&s=evanhall&ts=berkbart_d";
        String moffittCentral = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=cent&d=loop&s=mofflibr&ts=berkbart_d";
        String westCircleCentral = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=cent&d=loop&s=westcirc&ts=berkbart_d";
        String liKaShingCentral = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=cent&d=loop&s=likashin&ts=berkbart_d";

        //RFS urls
        String elCerritoRFS = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=rfs&d=loop&s=ecerbart_d&ts=ecerbart_d";
        String richmondRFS = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=rfs&d=loop&s=richfsta&ts=ecerbart_d";
        String regattaRFS = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=rfs&d=loop&s=regafacl_d&ts=ecerbart_d";
        String buchananRFS = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=rfs&d=loop&s=buchjack&ts=ecerbart_d";
        String hopkinsRFS = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=rfs&d=loop&s=hopkmlk&ts=ecerbart_d";
        String downtonwnRFS = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=rfs&d=loop&s=berkbart_d&ts=ecerbart_d";
        String evansRFS = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=rfs&d=loop&s=evanhall&ts=ecerbart_d";
        String oxfordRFS = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=rfs&d=loop&s=oxfouniv&ts=ecerbart_d";
        String hopkinsRFS2 = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=rfs&d=loop&s=hopkmlk_ib&ts=ecerbart_d";
        String buchananRFS2 = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=rfs&d=loop&s=buchjack_ib&ts=ecerbart_d";
        String richmondRFS2 = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=rfs&d=loop&s=richfsta_ib&ts=ecerbart_d";
        String regattaRFS2 = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=rfs&d=loop&s=regafacl_a&ts=ecerbart_d";

        //night safety north urls
        String moffittNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=mofflibr_d&ts=mofflibr_d";
        String lifeScienceNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=lifescie&ts=mofflibr_d";
        String liKaShingNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=likashin&ts=mofflibr_d";
        String downtownNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=berkbart_d&ts=mofflibr_d";
        String universityNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=univshack&ts=mofflibr_d";
        String tolmanNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=tolmhall&ts=mofflibr_d";
        String northGateNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=nortgate&ts=mofflibr_d";
        String coryNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=coryhall&ts=mofflibr_d";
        String foothillHearstNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=footstud&ts=mofflibr_d";
        String highlandNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=highridg&ts=mofflibr_d";
        String eastGateNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=eastgate&ts=mofflibr_d";
        String bowlesNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=bowlhall&ts=mofflibr_d";
        String haasNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=haasbusi&ts=mofflibr_d";
        String internationalNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=intehous&ts=mofflibr_d";
        String kroeberNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=krobhall&ts=mofflibr_d";
        String hearstNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=hrstmemo&ts=mofflibr_d";
        String sproulNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=sprouhall&ts=mofflibr_d";
        String recreationalNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=bancells&ts=mofflibr_d";
        String banwayNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=bancshat&ts=mofflibr_d";
        String bancroftNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=banckitt&ts=mofflibr_d";
        String downtownBARTNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=berkbart_ib&ts=mofflibr_d";
        String liKaShingWestCrescentSideNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=lkshincr&ts=mofflibr_d";
        String lifeNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=lifescie_ib&ts=mofflibr_d";
        String evansNorth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafens&d=loop&s=evanhall&ts=mofflibr_d";

        //night safety south urls
        String moffittSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=mofflibr_d&ts=mofflibr_d";
        String lifeScienceSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=lifescie&ts=mofflibr_d";
        String liKaShingWestCrescentSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=likashin&ts=mofflibr_d";
        String downtownBerkeleySouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=berkbart_d&ts=mofflibr_d";
        String shattuckAtAlstonSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=shatalls&ts=mofflibr_d";
        String shattuckAtKittredgeSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=shatkitt&ts=mofflibr_d";
        String shattuckAtDurantSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=shatdura&ts=mofflibr_d";
        String dwightSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=dwigfult&ts=mofflibr_d";
        String ellsworthSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=ellsstru&ts=mofflibr_d";
        String residence3South = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=chantele&ts=mofflibr_d";
        String channingSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=chanbowd&ts=mofflibr_d";
        String residence1South = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=chancoll&ts=mofflibr_d";
        String collegeSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=collhast&ts=mofflibr_d";
        String warringAtPiedmontSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=warrpied&ts=mofflibr_d";
        String clarkSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=clarkerr&ts=mofflibr_d";
        String warringAtChanningSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=warrbanc&ts=mofflibr_d";
        String warringAtBancroftSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=intehous&ts=mofflibr_d";
        String internationalSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=intehous&ts=mofflibr_d";
        String haasSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=haasbusn&ts=mofflibr_d";
        String gayleySouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=gaylstad&ts=mofflibr_d";
        String greekSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=greethea&ts=mofflibr_d";
        String foothillSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=footstud&ts=mofflibr_d";
        String highlandSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=highridg&ts=mofflibr_d";
        String evansSouth = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=ntsafess&d=loop&s=evanhall&ts=mofflibr_d";

        //shared services urls
        String northBerkeleyBartSharedServices = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=shrdserv&d=loop&s=nberbart_d&ts=nberbart_d";
        String cssSharedServices = "http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=shrdserv&d=loop&s=css_d&ts=nberbart_d";

        if(route.equals("Perimeter"))
        {
            switch(position)
            {
                case 0: return downtownBerkeleyPerimeter;
                case 1: return oxfordPerimeter;
                case 2: return tolmanHallPerimeter;
                case 3: return northGatePerimeter;
                case 4: return coryHallPerimeter;
                case 5: return evansHallPerimeter;
                case 6: return gayleyPerimeter;
                case 7: return haasPerimeter;
                case 8: return internationalPerimeter;
                case 9: return piedmontPerimeter;
                case 10: return collegePerimeter;
                case 11: return kroeberPerimeter;
                case 12: return hearstGymPerimeter;
                case 13: return sproulPerimeter;
                case 14: return recreationalPerimeter;
                case 15: return banwayPerimeter;
                case 16: return shattuckPerimeter;
            }
        }
        else if(route.equals("Hill") && direction.equals("Uphill"))
        {
            switch(position)
            {
                case 0: return downtownUphill;
                case 1: return evansUphill;
                case 2: return strawberryUphill;
                case 3: return botanicalUphill;
                case 4: return lawrenceUphill;
                case 5: return spaceSciencesUphill;
            }
        }
        else if(route.equals("Hill") && direction.equals("Downhill"))
        {
            switch (position)
            {
                case 0: return spaceSciencesDownhill;
                case 1: return lawrenceDownhill;
                case 2: return botanicalDownhill;
                case 3: return strawberryDownhill;
                case 4: return evansDownhill;
            }
        }
        else if(route.equals("Central Campus"))
        {
            switch (position)
            {
                case 0: return downtownCentral;
                case 1: return oxfordCentral;
                case 2: return tolmanCentral;
                case 3: return northCentral;
                case 4: return coryCentral;
                case 5: return evansCentral;
                case 6: return moffittCentral;
                case 7: return westCircleCentral;
                case 8: return liKaShingCentral;
            }
        }
        else if(route.equals("RFS"))
        {
            switch ((position))
            {
                case 0: return elCerritoRFS;
                case 1: return richmondRFS;
                case 2: return regattaRFS;
                case 3: return buchananRFS;
                case 4: return hopkinsRFS;
                case 5: return downtonwnRFS;
                case 6: return evansRFS;
                case 7: return oxfordRFS;
                case 8: return hopkinsRFS2;
                case 9: return buchananRFS2;
                case 10: return richmondRFS2;
                case 11: return regattaRFS2;
            }
        }
        else if(route.equals("Night Safety North"))
        {
            switch((position))
            {
                case 0: return moffittNorth;
                case 1: return lifeScienceNorth;
                case 2: return liKaShingNorth;
                case 3: return downtownNorth;
                case 4: return universityNorth;
                case 5: return tolmanNorth;
                case 6: return northGateNorth;
                case 7: return coryNorth;
                case 8: return foothillHearstNorth;
                case 9: return highlandNorth;
                case 10: return eastGateNorth;
                case 11: return bowlesNorth;
                case 12: return haasNorth;
                case 13: return internationalNorth;
                case 14: return kroeberNorth;
                case 15: return hearstNorth;
                case 16: return sproulNorth;
                case 17: return recreationalNorth;
                case 18: return banwayNorth;
                case 19: return bancroftNorth;
                case 20: return downtownBARTNorth;
                case 21: return liKaShingWestCrescentSideNorth;
                case 22: return lifeNorth;
                case 23: return evansNorth;
            }
        }
        else if(route.equals("Night Safety South"))
        {
            switch(position)
            {
                case 0: return moffittSouth;
                case 1: return lifeScienceSouth;
                case 2: return liKaShingWestCrescentSouth;
                case 3: return downtownBerkeleySouth;
                case 4: return shattuckAtAlstonSouth;
                case 5: return shattuckAtKittredgeSouth;
                case 6: return shattuckAtDurantSouth;
                case 7: return dwightSouth;
                case 8: return ellsworthSouth;
                case 9: return residence3South;
                case 10: return channingSouth;
                case 11: return residence1South;
                case 12: return collegeSouth;
                case 13: return warringAtPiedmontSouth;
                case 14: return clarkSouth;
                case 15: return warringAtChanningSouth;
                case 16: return warringAtBancroftSouth;
                case 17: return internationalSouth;
                case 18: return haasSouth;
                case 19: return gayleySouth;
                case 20: return greekSouth;
                case 21: return foothillSouth;
                case 22: return highlandSouth;
                case 23: return evansSouth;

            }
        }
        else //shared services selected
        {
            switch (position)
            {
                case 0: return northBerkeleyBartSharedServices;
                case 1: return cssSharedServices;
            }
        }
        return null;



    }

}
