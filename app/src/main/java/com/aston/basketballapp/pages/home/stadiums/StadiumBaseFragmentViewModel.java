package com.aston.basketballapp.pages.home.stadiums;

import java.util.ArrayList;
import com.aston.basketballapp.R;


//Locally Store the Stadium information as the information is not provided by the API.
public class StadiumBaseFragmentViewModel {

    public static ArrayList<StadiumInformation> getStadiumsInfo() {
        ArrayList<StadiumInformation> info = new ArrayList<>();

        info.add(new StadiumInformation(
                "hawks",
                "State Farm Arena",
                "21,000",
                "https://www.nba.com/hawks/tickets",
                "https://cdn.wegow.com/media/venues/state-farm-arena/state-farm-arena-1634221655.8113344.jpg",
                33.7573f,
                84.3963f,
                R.drawable.ic_atlantahawksmarker
        ));

        info.add(new StadiumInformation(
                "celtics",
                "TD Garden",
                "19,156",
                "https://www.nba.com/celtics/tickets",
                "https://upload.wikimedia.org/wikipedia/commons/6/6a/TD_Garden_%28crop%29.JPG",
                42.3662f,
                71.0621f,
                R.drawable.ic_bostoncelticsmarker

        ));

        info.add(new StadiumInformation(
                "nets",
                "Barclays Center",
                "19,000",
                "https://www.nba.com/nets/tickets",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/BarclayCenter-2_%2848034233762%29.jpg/1200px-BarclayCenter-2_%2848034233762%29.jpg",
                40.6826f,
                73.9754f,
                R.drawable.ic_brooklynnetsmarker

        ));

        info.add(new StadiumInformation(
                "hornets",
                "Spectrum Center",
                "20,200",
                "https://www.nba.com/hornets/tickets",
                "https://www.spectrumcentercharlotte.com/assets/img/Spectrum_Center_TradeSt_Slide-b25a4c749a.jpg",
                35.2251f,
                80.8394f,
                R.drawable.ic_charlottehornetsmarker

        ));


        info.add(new StadiumInformation(
                "bulls",
                "United Center",
                "23,500",
                "https://www.nba.com/bulls/tickets",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/United_Center_1.jpg/330px-United_Center_1.jpg",
                41.8807f,
                87.6742f,
                R.drawable.ic_chicagobullsmarker

        ));

        info.add(new StadiumInformation(
                "cavs",
                "Rocket Mortgage FieldHouse",
                "19,432",
                "https://www.nba.com/cavaliers/tickets",
                "https://upload.wikimedia.org/wikipedia/commons/f/fd/Rocket_Mortgage_FieldHouse_%282%29.jpg",
                41.4965f,
                81.6881f,
                R.drawable.ic_clevelandcavaliersmarker

        ));

        info.add(new StadiumInformation(
                "mavericks",
                "American Airlines Center",
                "20,000",
                "https://www.nba.com/mavericks/tickets",
                "https://upload.wikimedia.org/wikipedia/commons/3/3d/American_Airlines_Center_%286246886325%29_cropped.jpg",
                32.790527f,
                96.810883f,
                R.drawable.ic_dallasmavericksmarker

        ));

        info.add(new StadiumInformation(
                "nuggets",
                "Ball Arena",
                "20,000",
                "https://www.nba.com/nuggets/tickets",
                "https://upload.wikimedia.org/wikipedia/commons/9/98/Pepsi_Center_2013.jpg",
                39.7439f,
                105.0201f,
                R.drawable.ic_denvernuggetsmarker

        ));

        info.add(new StadiumInformation(
                "pistons",
                "Little Caesars Arena",
                "20,332",
                "https://www.nba.com/pistons/tickets",
                "https://cms.nhl.bamgrid.com/images/photos/290630558/1024x576/cut.jpg",
                42.3411f,
                83.0553f,
                R.drawable.ic_detroitpistons

        ));

        info.add(new StadiumInformation(
                "warriors",
                "Chase Center",
                "18,064",
                "https://www.nba.com/warriors/tickets",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Chase_Center.jpg/330px-Chase_Center.jpg",
                37.7680f,
                122.3877f,
                R.drawable.ic_goldenstatewarriorsmarker

        ));

        info.add(new StadiumInformation(
                "rockets",
                "Toyota Center",
                "18,300",
                "https://www.nba.com/rockets/tickets",
                "https://populous.com/wp-content/uploads/2018/01/POP_00_0653_00_N56_medium.jpg",
                29.7508f,
                95.3621f,
                R.drawable.ic_houstonrocketsmarker

        ));

        info.add(new StadiumInformation(
                "pacers",
                "Gainbridge Fieldhouse",
                "20,000",
                "https://www.nba.com/pacers/tickets",
                "https://www.gannett-cdn.com/presto/2021/09/28/PIND/dd91186f-cb8a-42e3-953d-2763c5304019-2021_0922_Penn_GAINBRIDGE.jpeg?crop=3823,2151,x0,y0&width=3200&height=1801&format=pjpg&auto=webp",
                39.7640f,
                86.1555f,
                R.drawable.ic_indianapacersmarker
        ));

        info.add(new StadiumInformation(
                "clippers",
                "Crypto.com Arena",
                "20,000",
                "https://www.nba.com/clippers/tickets",
                "https://upload.wikimedia.org/wikipedia/commons/2/24/Staples_Center_2012.jpg",
                34.0430f,
                118.2673f,
                R.drawable.ic_laclippers
        ));

        info.add(new StadiumInformation(
                "lakers",
                "Crypto.com Arena",
                "20,000",
                "https://www.nba.com/lakers/tickets",
                "https://upload.wikimedia.org/wikipedia/commons/2/24/Staples_Center_2012.jpg",
                34.0430f,
                118.27f,
                R.drawable.ic_lalakers

        ));

        info.add(new StadiumInformation(
                "grizzles",
                "FedExForum",
                "18,119",
                "https://www.nba.com/grizzlies/tickets",
                "https://upload.wikimedia.org/wikipedia/commons/3/37/FedExForum.jpg",
                35.1381f,
                90.0506f,
                R.drawable.ic_memphisgrizzlesmarker
        ));

        info.add(new StadiumInformation(
                "heat",
                "FTX Arena",
                "21,000",
                "https://www.nba.com/heat/tickets",
                "https://upload.wikimedia.org/wikipedia/commons/4/44/American_Airlines_Arena%2C_Miami%2C_FL%2C_jjron_29.03.2012.jpg",
                25.7814f,
                80.1870f,
                R.drawable.ic_miamiheatmarker

        ));

        info.add(new StadiumInformation(
                "bucks",
                "Fiserv Forum",
                "17,341",
                "https://www.nba.com/bucks/tickets",
                "https://www.nba.com/bucks/sites/bucks/files/ff-ext-0526-4.jpg?w=756&h=425",
                43.0451f,
                87.9174f,
                R.drawable.ic_milwaukeebucksmarker
        ));

        info.add(new StadiumInformation(
                "timberwolves",
                "Target Center",
                "19,356",
                "https://www.nba.com/timberwolves/tickets",
                "https://cdn.saffire.com/images.ashx?t=ig&rid=ASMGlobal&i=target-center-arena-minneapolis-mn-usa-asm-global.jpg",
                44.9795f,
                93.2761f,
                R.drawable.ic_minnasotatimberwolvesmarker

        ));

        info.add(new StadiumInformation(
                "pelicans",
                "Smoothie King Center",
                "17,791",
                "https://www.nba.com/pelicans/tickets",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/00/Smoothie_King_Center.jpg/280px-Smoothie_King_Center.jpg",
                29.9490f,
                90.0821f,
                R.drawable.ic_neworleanspelicans

        ));

        info.add(new StadiumInformation(
                "knicks",
                "Madison Square Garden",
                "20,789",
                "https://www.nba.com/knicks/tickets",
                "https://img.msg.com/wp-content/uploads/2021/02/MSG_Welcome-Fans_VLP.jpg?w=500",
                40.7505f,
                73.9934f,
                R.drawable.ic_newyorkknicksmarker

        ));

        info.add(new StadiumInformation(
                "okc",
                "Paycom Center",
                "18,203",
                "https://www.nba.com/thunder/tickets",
                "https://3gz8cg829c.execute-api.us-west-2.amazonaws.com/prod/image-renderer/16x9/full/1015/center/80/53e25380-dfc0-4f2e-9c61-252abe29fa7b-large16x9_9.jpg",
                35.4634f,
                97.5151f,
                R.drawable.ic_oklahomacitythundermarker

        ));

        info.add(new StadiumInformation(
                "magic",
                "Amway Center",
                "20,000",
                "https://www.nba.com/magic/tickets",
                "https://populous.com/wp-content/uploads/2018/01/ORLANDOAR_0209_Tanner.jpg",
                28.5392f,
                81.3839f,
                R.drawable.ic_orlandomagicmarker

        ));

        info.add(new StadiumInformation(
                "76ers",
                "Wells Fargo Center",
                "19,500",
                "https://www.nba.com/sixers/tickets",
                "https://upload.wikimedia.org/wikipedia/commons/6/61/Wells_Fargo_Center.jpg",
                39.9012f,
                75.1720f,
                R.drawable.ic_philadelphiamarker

        ));

        info.add(new StadiumInformation(
                "suns",
                "Footprint Center",
                "18,422",
                "https://www.nba.com/suns/tickets",
                "https://basketballforever.nyc3.cdn.digitaloceanspaces.com/wp-content/uploads/2021/10/19113004/FPC.jpeg",
                33.4457f,
                112.0712f,
                R.drawable.ic_phoenixsunsmarker
        ));

        info.add(new StadiumInformation(
                "kings",
                "Golden 1 Center",
                "17,608",
                "https://www.nba.com/kings/tickets",
                "https://aecom.com/uk/wp-content/uploads/2015/10/golden_1_center_6.jpg",
                38.5802f,
                121.4997f,
                R.drawable.ic_sacramentokingsmarker
        ));

        info.add(new StadiumInformation(
                "spurs",
                "AT&T Center",
                "18,581",
                "https://www.nba.com/spurs/tickets",
                "https://static.wikia.nocookie.net/nba/images/d/d9/AT%26T_Center.jpg/revision/latest?cb=20160214194951",
                29.4270f,
                98.4375f,
                R.drawable.ic_sanantoniospursmarker
        ));

        info.add(new StadiumInformation(
                "raptors",
                "Scotiabank Arena",
                "19,800",
                "https://www.nba.com/raptors/tickets",
                "https://mediaim.expedia.com/destination/2/0eac53acf36796a89cc6b7e43b9994a1.jpg?impolicy=fcrop&w=536&h=384&q=high",
                43.6435f,
                79.3791f,
                R.drawable.ic_torontoraptorsmarker
        ));

        info.add(new StadiumInformation(
                "trail blazers",
                "Moda Center",
                "19,980",
                "https://www.nba.com/blazers/tickets",
                "https://mediaim.expedia.com/destination/1/2f76b65ec13a0e93e33e2749e3e3223e.jpg?impolicy=fcrop&w=536&h=384&q=high",
                45.5316f,
                122.6668f,
                R.drawable.ic_trailblazersmarker
        ));

        info.add(new StadiumInformation(
                "jazz",
                "Vivint Arena",
                "18,300",
                "https://www.nba.com/jazz/tickets",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7t03uWtUI6i10FS_1cRNe8gNT95rDW8QOFgHMsHWJ9dh_ksiV4dzoRnEi9PAnqaWr_mM&usqp=CAU",
                40.7683f,
                111.9011f,
                R.drawable.ic_utahjazzmarker

        ));

        info.add(new StadiumInformation(
                "wizards",
                "Capital One Arena",
                "20,356",
                "https://www.nba.com/wizards/tickets",
                "https://monumentalsports.com/wp-content/uploads/2019/09/COA-Exterior-SM.jpg",
                38.8982f,
                77.0209f,
                R.drawable.ic_washingtonwizardsmarker

        ));

        return info;
    }
}
