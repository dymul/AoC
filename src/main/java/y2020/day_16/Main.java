package y2020.day_16;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static List<Rule> rules = new ArrayList<>();
    static Ticket myTicket;
    static List<Ticket> othTickets = new ArrayList<>();

    public static void main(String[] args) {
        String[] in = input;
        int i = 0;
        for (i = 0; i < in.length; i++) {
            if (in[i].equals("")) break;
            Rule r = new Rule(in[i]);
            rules.add(r);
        }
        myTicket = new Ticket(in[i+2]);
        i+=5;
        for (;i < in.length; i++ ) {
            othTickets.add(new Ticket(in[i]));
        }
        List<Integer> invalid = new LinkedList<>();
        for (Ticket t : othTickets) {
            for (Integer val : t.list) {
                boolean valid = false;
                for(Rule rule : rules) {
                    valid = rule.isValid(val);
                    if (valid) break;
                }
                if(!valid) invalid.add(val);
            }
        }
        int sum = invalid.stream().mapToInt(x -> x).sum();
        System.out.println(sum);

    }

    static String[] in2 = {
            "class: 1-3 or 5-7"
            ,"row: 6-11 or 33-44"
            ,"seat: 13-40 or 45-50"
            ,""
            ,"your ticket:"
            ,"7,1,14"
            ,""
            ,"nearby tickets:"
            ,"7,3,47"
            ,"40,4,50"
            ,"55,2,20"
            ,"38,6,12"
    };

    static String[] input = {
            "departure location: 40-261 or 279-955"
            ,"departure station: 33-375 or 394-963"
            ,"departure platform: 39-863 or 877-970"
            ,"departure track: 30-237 or 256-955"
            ,"departure date: 47-731 or 741-950"
            ,"departure time: 38-301 or 317-954"
            ,"arrival location: 26-598 or 623-969"
            ,"arrival station: 50-835 or 854-971"
            ,"arrival platform: 44-535 or 549-958"
            ,"arrival track: 36-672 or 685-967"
            ,"class: 34-217 or 236-974"
            ,"duration: 29-469 or 483-970"
            ,"price: 45-111 or 120-965"
            ,"route: 32-751 or 760-954"
            ,"row: 25-321 or 339-954"
            ,"seat: 38-423 or 438-958"
            ,"train: 45-798 or 813-954"
            ,"type: 40-487 or 503-954"
            ,"wagon: 46-916 or 938-949"
            ,"zone: 25-160 or 184-957"
            ,""
            ,"your ticket:"
            ,"73,59,83,127,137,151,71,139,67,53,89,79,61,109,131,103,149,97,107,101"
            ,""
            ,"nearby tickets:"
            ,"782,297,512,592,171,360,774,483,653,294,299,519,448,916,939,293,535,63,54,648"
            ,"650,284,299,520,890,792,654,774,639,887,655,654,313,662,83,650,137,570,700,505"
            ,"12,126,760,151,298,484,782,149,141,416,285,450,747,294,111,660,562,780,776,93"
            ,"159,671,77,361,577,363,74,459,725,217,504,738,456,817,453,454,207,789,124,880"
            ,"464,856,729,116,581,666,189,938,595,65,369,189,691,939,132,365,446,370,689,895"
            ,"400,64,422,509,95,138,724,701,444,199,360,828,95,776,465,551,348,640,837,372"
            ,"452,699,70,946,596,891,828,77,352,503,395,794,595,310,817,690,419,111,88,413"
            ,"439,54,839,626,462,452,653,138,715,152,596,508,405,822,370,916,217,527,723,817"
            ,"167,827,522,284,693,101,204,206,60,507,785,628,460,741,438,530,447,726,884,259"
            ,"455,96,703,813,419,402,760,371,523,403,949,94,202,107,343,699,834,556,547,771"
            ,"60,350,55,131,110,949,686,831,648,577,685,50,420,94,289,899,305,67,237,530"
            ,"368,880,883,215,52,351,819,656,296,706,636,400,586,193,623,492,652,744,667,283"
            ,"450,647,439,862,780,668,878,859,570,779,694,833,786,444,781,116,259,438,78,584"
            ,"670,519,830,205,647,520,694,202,597,508,281,456,670,343,444,733,889,520,138,98"
            ,"356,343,157,441,419,731,195,398,704,442,350,295,128,97,856,155,116,881,533,554"
            ,"637,590,412,576,187,553,51,516,730,505,215,728,342,396,658,632,67,408,114,446"
            ,"292,685,883,833,450,728,748,574,85,769,300,86,654,89,591,554,886,697,549,988"
            ,"151,407,286,697,195,649,521,375,710,418,982,643,353,215,290,350,439,365,407,154"
            ,"528,484,504,594,202,86,506,517,670,130,641,691,366,828,298,65,379,349,402,535"
            ,"457,193,447,857,553,442,532,516,777,395,824,707,352,301,201,86,417,583,232,55"
            ,"451,657,518,759,237,356,831,634,660,716,80,358,64,372,781,297,941,901,517,891"
            ,"346,408,438,140,702,194,505,319,658,760,509,650,352,683,667,357,256,404,50,568"
            ,"321,749,750,198,369,107,13,79,896,60,63,91,830,80,886,140,644,103,448,704"
            ,"144,199,65,0,63,366,896,58,292,369,318,206,862,581,712,574,701,947,411,408"
            ,"287,416,326,895,483,418,50,577,102,187,666,826,640,591,438,831,82,353,128,747"
            ,"376,824,657,746,318,625,286,190,204,559,359,633,766,553,791,150,444,216,484,104"
            ,"793,198,62,554,217,818,832,158,90,130,422,555,446,149,914,778,644,421,197,983"
            ,"137,454,649,438,417,467,571,72,696,863,789,372,913,741,74,216,75,835,434,356"
            ,"321,412,581,818,50,558,566,750,396,360,642,765,112,586,320,258,780,717,442,764"
            ,"509,846,197,441,399,139,718,572,87,370,598,402,109,135,349,715,658,863,148,858"
            ,"567,514,123,525,88,403,96,508,751,520,723,774,940,658,716,761,421,949,414,22"
            ,"565,120,319,284,578,192,375,653,685,849,719,637,82,186,912,879,108,154,109,76"
            ,"747,139,947,774,357,88,939,725,581,748,130,504,593,452,465,63,752,580,127,62"
            ,"763,411,130,576,430,710,108,56,878,154,894,833,52,463,405,68,723,751,711,791"
            ,"903,555,527,670,574,506,111,557,731,913,126,60,451,128,216,978,237,70,580,880"
            ,"883,134,100,597,652,657,721,747,756,109,554,57,571,886,259,751,82,523,408,649"
            ,"522,760,776,749,551,121,942,756,710,692,451,670,58,72,814,259,319,287,259,120"
            ,"464,516,77,883,885,87,580,690,624,714,791,668,160,460,289,435,521,295,940,70"
            ,"211,415,835,452,99,528,422,549,927,817,664,750,765,503,628,566,626,705,287,787"
            ,"641,402,899,526,121,707,58,197,147,586,794,766,755,514,187,408,350,769,779,825"
            ,"550,765,915,529,212,687,522,153,15,438,149,207,534,903,878,369,792,198,96,583"
            ,"150,912,76,635,355,766,654,442,515,646,197,446,204,195,507,685,192,726,163,133"
            ,"892,205,144,588,640,216,721,419,781,60,904,711,523,227,286,557,569,417,726,760"
            ,"835,458,195,770,212,714,521,660,703,685,983,54,213,146,653,588,460,464,570,760"
            ,"878,648,295,892,652,563,51,92,770,550,827,75,396,758,899,774,860,726,534,786"
            ,"796,651,84,528,227,667,451,217,154,587,320,794,359,61,773,793,664,352,339,260"
            ,"420,976,643,558,57,346,191,298,407,552,786,699,749,125,50,214,342,362,700,654"
            ,"643,141,689,823,914,896,846,891,668,939,507,702,260,415,584,503,412,520,598,345"
            ,"901,551,746,568,577,576,147,349,581,206,288,515,231,58,509,301,92,729,787,668"
            ,"558,782,630,103,713,290,746,948,556,84,591,514,464,893,123,184,540,580,513,791"
            ,"666,484,719,397,623,644,587,828,90,768,655,405,374,202,894,151,822,256,361,987"
            ,"320,465,798,902,406,321,509,882,81,343,209,779,357,418,637,676,184,644,505,449"
            ,"705,949,187,467,887,663,357,56,216,65,729,255,566,416,145,879,650,702,948,76"
            ,"565,145,85,504,517,665,504,628,466,72,628,454,137,821,898,579,770,459,980,568"
            ,"644,95,585,531,452,818,283,89,564,396,724,366,297,871,126,280,666,284,565,63"
            ,"321,236,750,881,393,703,194,781,786,259,824,507,892,668,559,321,292,578,521,156"
            ,"221,279,401,146,58,368,487,356,515,54,144,215,916,706,910,129,916,566,139,71"
            ,"522,59,698,780,419,726,318,77,432,575,591,409,419,657,709,367,635,71,127,366"
            ,"79,592,449,203,591,358,982,571,565,349,720,633,748,353,505,857,345,815,945,260"
            ,"947,319,783,285,204,696,94,770,913,549,136,701,408,820,848,94,519,521,904,814"
            ,"59,523,784,557,927,292,217,131,647,197,655,441,128,638,54,708,290,282,915,236"
            ,"190,448,773,70,91,462,369,552,526,981,713,185,207,553,193,413,53,729,587,746"
            ,"360,728,361,365,765,863,401,636,155,185,751,397,144,702,686,577,101,709,996,465"
            ,"91,160,660,394,363,585,143,146,484,565,370,517,980,59,73,712,124,916,279,913"
            ,"700,883,582,213,81,820,484,701,285,100,517,729,740,555,729,762,452,794,83,626"
            ,"703,784,449,657,77,88,822,770,64,58,506,691,695,733,341,899,632,363,280,655"
            ,"136,749,595,405,510,719,518,370,187,351,718,586,200,694,895,160,286,425,896,704"
            ,"100,355,907,815,725,721,349,813,93,522,280,469,725,613,550,375,691,949,350,143"
            ,"354,269,716,125,645,671,765,292,469,186,706,503,574,727,420,484,824,361,88,672"
            ,"900,659,452,85,204,743,199,397,518,210,413,786,705,882,900,163,670,915,212,534"
            ,"483,641,635,189,725,229,65,486,661,821,468,882,237,356,813,77,101,901,889,861"
            ,"344,447,890,71,353,627,984,588,53,535,830,597,652,126,286,98,781,484,877,721"
            ,"416,534,626,784,299,516,103,772,902,554,360,340,634,104,722,68,823,716,785,996"
            ,"569,120,793,69,899,928,884,746,60,769,705,702,409,395,906,286,458,291,291,695"
            ,"712,344,400,404,626,689,404,858,215,822,55,65,508,53,854,712,181,108,697,784"
            ,"343,422,363,709,114,69,884,657,763,596,835,855,374,913,77,533,793,450,459,647"
            ,"487,91,152,663,209,894,486,770,280,79,590,185,503,210,791,403,300,984,634,642"
            ,"350,356,596,137,323,519,762,855,414,88,626,764,283,415,464,909,197,584,406,726"
            ,"401,693,439,436,663,81,102,717,407,79,79,150,578,258,526,208,397,373,465,74"
            ,"824,522,726,153,893,525,579,939,894,713,80,208,86,712,671,935,695,642,594,411"
            ,"764,821,555,188,721,507,442,559,554,707,510,145,625,419,128,744,515,911,756,789"
            ,"653,108,857,124,93,442,720,101,189,948,447,1,624,92,291,191,462,63,731,202"
            ,"360,76,125,763,139,648,668,561,64,148,940,75,138,994,902,781,629,374,363,555"
            ,"367,223,110,693,751,717,895,214,532,830,639,406,370,856,524,906,712,668,415,74"
            ,"792,462,207,394,560,949,790,441,529,105,400,546,688,137,900,417,90,503,569,395"
            ,"89,122,949,153,762,554,288,948,527,398,317,643,870,762,578,771,396,770,714,712"
            ,"124,886,722,126,150,510,569,938,914,187,290,625,348,912,690,515,832,139,912,12"
            ,"849,765,486,139,909,904,444,357,211,564,532,345,581,297,813,130,629,892,350,859"
            ,"398,560,211,367,75,643,411,651,256,943,184,135,570,444,152,895,11,662,566,915"
            ,"856,829,182,367,208,75,719,75,905,572,195,671,486,888,355,760,535,318,351,400"
            ,"565,701,519,623,914,581,176,521,256,575,938,791,560,596,583,689,892,775,530,905"
            ,"815,395,944,88,366,445,703,455,748,649,776,124,820,652,208,66,807,882,916,797"
            ,"375,655,401,354,888,144,464,358,720,835,354,817,547,292,656,503,503,719,362,421"
            ,"126,939,189,129,943,167,896,319,422,92,106,422,135,885,588,295,901,207,132,483"
            ,"935,259,766,125,946,213,412,421,186,139,213,780,452,527,62,299,893,258,97,92"
            ,"142,2,697,84,258,724,446,196,194,638,563,896,687,585,192,342,60,320,468,301"
            ,"861,23,78,894,590,70,636,520,319,185,746,103,595,299,863,110,145,122,144,635"
            ,"722,643,861,912,515,322,292,656,750,110,350,129,159,289,462,589,193,724,127,910"
            ,"522,77,783,58,889,453,861,116,466,828,710,107,523,647,783,691,887,208,102,579"
            ,"375,586,644,862,572,835,787,504,728,588,364,519,121,921,572,402,403,665,75,141"
            ,"371,903,726,578,128,321,714,197,559,783,576,743,342,763,297,72,667,824,376,124"
            ,"593,176,83,84,104,236,635,938,783,566,397,402,75,298,370,591,821,792,197,883"
            ,"589,896,879,582,779,944,595,701,487,175,486,440,946,710,834,184,413,773,205,458"
            ,"215,706,421,71,197,598,830,854,198,411,632,645,403,528,90,815,678,146,122,690"
            ,"854,570,191,423,279,798,816,821,293,585,202,717,108,146,627,23,822,372,413,708"
            ,"659,536,289,595,832,507,280,777,794,857,461,444,443,354,348,686,150,652,361,741"
            ,"708,526,660,354,105,412,410,93,402,947,859,131,636,418,829,246,101,697,665,465"
            ,"751,400,190,300,153,98,721,77,412,329,286,911,884,767,98,296,407,525,366,859"
            ,"661,886,645,583,774,724,521,53,453,632,89,876,74,624,765,104,95,749,418,201"
            ,"779,686,76,363,368,207,790,821,298,415,780,100,83,728,408,831,448,294,706,850"
            ,"691,505,796,158,623,55,595,79,157,349,702,112,661,706,693,550,527,144,69,483"
            ,"487,939,399,222,777,97,198,297,689,50,422,743,361,196,158,410,830,521,750,341"
            ,"894,729,798,555,287,369,760,191,587,650,9,319,829,135,576,948,370,707,106,888"
            ,"100,728,626,467,656,780,466,257,412,688,372,503,561,880,722,782,853,638,588,101"
            ,"555,377,447,98,196,130,486,785,947,139,624,104,79,294,900,772,440,486,205,895"
            ,"50,828,624,750,291,454,200,324,653,647,577,157,636,859,637,783,344,156,364,877"
            ,"582,776,148,337,79,210,813,564,483,532,661,53,54,583,525,770,558,899,761,458"
            ,"626,570,796,710,203,339,401,885,419,639,731,671,450,212,353,841,726,466,89,792"
            ,"559,80,197,554,152,577,769,821,695,237,787,460,280,687,441,725,534,166,80,192"
            ,"282,414,289,133,551,319,771,182,340,716,584,714,149,519,357,944,688,409,592,143"
            ,"398,332,585,103,127,553,907,576,213,97,157,948,774,526,559,639,81,823,131,888"
            ,"589,512,370,878,768,716,198,718,416,986,564,76,551,445,58,202,741,891,105,449"
            ,"504,633,421,353,528,903,137,343,775,506,560,56,571,843,770,741,420,662,187,461"
            ,"580,723,395,906,777,758,65,71,441,369,567,144,416,730,721,905,285,943,558,289"
            ,"871,193,941,725,128,827,285,67,592,449,772,317,363,749,445,879,452,469,64,644"
            ,"580,184,190,585,294,858,444,291,838,726,832,573,448,398,190,943,414,92,516,188"
            ,"764,786,743,131,89,343,827,751,707,344,725,454,583,211,159,300,590,550,627,181"
            ,"201,282,281,792,751,568,142,729,440,699,550,274,81,729,449,291,786,826,135,730"
            ,"108,289,350,710,91,66,666,623,698,586,760,468,819,391,445,746,881,821,457,280"
            ,"745,573,977,585,368,556,879,108,586,419,726,592,204,911,773,236,796,358,731,461"
            ,"625,706,148,782,465,856,660,653,405,123,667,671,190,437,92,782,911,882,909,128"
            ,"112,944,111,190,577,301,415,710,816,884,688,826,574,466,214,587,695,463,76,880"
            ,"373,938,56,906,708,643,776,301,593,116,77,715,550,293,190,301,686,531,629,787"
            ,"439,457,339,744,850,287,898,598,361,257,718,395,371,215,863,630,411,552,408,727"
            ,"662,821,916,707,149,728,317,593,319,95,892,781,236,652,699,365,632,344,797,920"
            ,"462,790,290,578,688,428,97,140,939,236,77,632,881,257,518,764,359,909,291,672"
            ,"665,788,579,931,461,108,591,630,819,289,743,108,410,339,257,258,534,550,375,207"
            ,"542,625,661,97,342,198,628,468,820,257,108,459,823,909,556,830,797,690,193,638"
            ,"792,661,507,861,251,623,137,718,85,296,665,830,375,783,209,449,726,81,650,197"
            ,"527,113,63,727,79,345,349,687,153,820,835,146,422,577,696,343,788,451,761,699"
            ,"666,904,439,860,670,583,364,708,648,639,154,509,131,240,444,88,132,512,186,459"
            ,"138,258,76,860,740,155,200,59,916,550,406,710,572,825,463,659,633,197,192,891"
            ,"659,189,582,707,666,890,340,140,288,256,892,690,894,697,349,783,876,448,467,110"
            ,"531,236,743,788,291,295,745,763,154,923,353,896,261,731,949,148,945,527,765,707"
            ,"533,99,782,195,743,949,596,632,438,910,558,218,647,549,633,256,530,857,646,943"
            ,"704,742,373,577,949,340,771,787,399,50,824,764,484,4,89,207,690,132,941,628"
            ,"360,525,81,899,453,706,578,456,422,68,60,937,144,120,590,140,694,657,564,359"
            ,"772,244,652,486,369,72,80,353,91,83,149,508,52,949,769,135,911,531,72,585"
            ,"909,713,299,657,191,584,120,645,485,829,642,318,139,877,15,879,903,292,93,395"
            ,"791,517,582,628,744,915,941,912,300,485,637,385,103,158,781,66,818,136,318,905"
            ,"818,281,568,365,530,438,412,946,897,107,835,516,743,634,823,756,191,629,185,940"
            ,"630,160,195,78,519,99,207,949,862,901,374,560,627,536,817,624,55,120,194,160"
            ,"444,722,456,666,643,122,576,720,351,515,595,56,571,496,762,419,66,722,592,411"
            ,"556,535,761,187,80,547,785,453,397,558,86,663,750,410,941,829,370,747,61,773"
            ,"756,52,719,206,668,136,237,214,416,92,147,943,798,624,397,446,881,763,465,341"
            ,"944,831,943,770,79,217,692,750,879,90,160,122,72,317,813,450,224,724,366,822"
            ,"632,785,0,749,445,412,698,813,294,822,505,366,796,407,360,551,413,400,439,150"
            ,"625,913,914,68,66,290,991,372,401,362,300,342,53,777,576,186,916,686,884,742"
            ,"900,708,943,155,403,772,944,67,728,711,215,591,351,624,697,318,620,580,77,592"
            ,"820,700,877,718,822,788,552,596,744,356,832,96,557,374,761,939,241,711,401,580"
            ,"344,23,813,791,260,724,397,73,510,596,202,281,570,579,534,523,95,413,160,359"
            ,"122,398,659,297,215,386,257,147,910,708,627,941,662,237,69,357,766,61,883,629"
            ,"586,320,357,943,408,649,140,924,815,199,82,74,284,793,774,291,628,319,776,700"
            ,"439,59,407,857,143,369,468,825,641,110,395,506,432,656,349,893,901,629,319,770"
            ,"566,552,60,650,140,394,203,158,592,727,404,355,449,332,790,744,520,107,363,403"
            ,"777,138,448,949,883,915,368,628,575,89,418,62,915,116,146,138,590,145,454,704"
            ,"913,193,400,257,517,813,888,461,284,625,484,947,595,775,813,880,937,106,783,751"
            ,"915,398,409,783,483,855,523,685,66,761,396,261,854,630,524,110,338,258,669,532"
            ,"288,560,549,556,516,293,141,140,534,215,23,655,508,785,321,443,670,135,793,143"
            ,"597,448,523,401,938,258,747,368,672,915,583,833,692,82,550,896,71,754,701,598"
            ,"421,132,510,520,903,897,761,910,98,485,517,568,986,565,283,76,410,713,464,407"
            ,"503,554,483,71,859,59,560,625,110,453,551,469,362,59,64,560,447,928,301,906"
            ,"562,436,798,141,819,831,783,457,293,778,418,486,450,777,699,888,688,412,236,656"
            ,"772,627,510,343,663,777,141,638,597,828,280,65,935,150,582,561,103,775,576,949"
            ,"87,513,369,693,699,743,781,186,468,569,74,291,262,194,948,99,84,596,762,658"
            ,"731,933,463,361,200,365,891,414,579,689,150,184,633,587,90,408,101,650,63,550"
            ,"625,454,781,598,143,454,691,347,908,549,518,795,180,900,155,564,644,592,821,775"
            ,"688,293,96,137,504,142,653,447,87,762,583,560,103,745,258,994,450,696,204,347"
            ,"745,868,783,403,817,938,100,354,695,749,878,292,457,485,139,910,355,552,565,410"
            ,"148,690,449,299,655,348,354,517,141,708,214,407,408,816,534,263,61,640,906,944"
            ,"468,462,514,662,947,516,946,319,91,893,406,518,255,81,81,204,347,88,569,632"
            ,"887,826,50,514,857,794,69,513,320,790,259,438,939,257,784,123,277,649,695,257"
            ,"764,302,633,762,341,355,630,712,595,824,54,638,366,582,516,726,349,339,484,655"
            ,"945,692,787,162,158,624,88,587,821,416,148,525,888,565,579,372,346,797,193,908"
            ,"338,766,549,566,659,659,341,125,298,464,905,298,783,633,348,417,52,580,130,789"
            ,"731,753,896,92,790,731,415,558,65,862,882,731,105,133,693,781,854,90,287,152"
            ,"750,782,796,510,92,893,788,764,689,185,467,574,216,671,998,102,287,206,648,107"
            ,"583,562,553,530,902,699,698,258,557,370,341,344,636,179,145,651,580,505,772,528"
            ,"665,408,794,68,647,506,205,342,232,366,298,516,790,279,298,523,361,659,70,440"
            ,"283,406,190,763,156,520,148,662,712,794,196,149,379,912,527,67,557,193,155,574"
            ,"152,342,665,420,350,157,731,653,593,361,594,514,206,511,671,383,905,84,555,191"
            ,"855,700,148,624,666,521,749,402,334,460,750,198,64,409,100,588,196,824,487,550"
            ,"348,739,578,444,362,855,375,699,110,484,137,193,214,372,558,577,879,236,771,341"
            ,"825,285,368,829,468,55,512,652,637,575,583,137,107,120,260,537,579,766,340,259"
            ,"439,729,454,440,947,701,939,596,640,655,193,515,843,771,688,532,656,653,551,686"
            ,"720,414,484,297,772,745,217,749,58,857,685,461,207,137,364,222,701,80,468,129"
            ,"510,194,465,285,73,420,727,900,105,87,597,161,764,83,297,706,721,589,901,50"
            ,"342,887,205,73,942,214,394,534,889,628,746,283,360,50,566,907,440,328,533,66"
            ,"647,13,556,532,346,644,946,62,454,515,187,561,664,340,513,762,900,823,190,367"
            ,"649,692,625,290,359,915,780,483,117,348,467,908,357,365,416,411,507,880,407,579"
            ,"213,109,565,339,783,423,283,522,536,442,340,903,818,654,909,764,577,443,556,156"
            ,"293,822,422,469,687,160,664,704,528,741,597,430,394,422,441,566,290,298,794,348"
            ,"156,692,831,375,692,852,504,570,938,581,595,158,110,297,184,796,192,856,909,593"
            ,"717,912,793,644,419,73,454,890,258,649,136,137,596,512,149,308,485,710,197,339"
            ,"103,893,794,892,355,773,731,206,596,346,283,151,892,198,103,716,738,884,450,415"
            ,"484,816,687,785,726,144,566,257,795,66,360,725,566,819,61,687,834,625,332,688"
            ,"721,394,761,67,575,747,419,633,698,730,187,790,569,209,465,781,237,118,193,552"
            ,"65,634,98,773,287,636,444,362,945,286,404,208,74,449,344,409,424,510,413,559"
            ,"771,716,982,862,535,416,862,55,509,158,893,817,764,198,416,815,774,50,127,720"
            ,"360,637,528,691,628,720,156,367,394,257,880,752,350,909,54,156,562,664,817,65"
            ,"665,207,597,647,753,659,361,741,763,503,509,627,948,511,788,258,597,905,296,417"
            ,"139,98,908,63,890,213,217,847,530,554,795,649,83,78,690,777,122,769,786,406"
            ,"820,570,885,321,659,245,514,881,779,418,698,827,699,484,638,947,293,661,554,688"
            ,"375,825,764,409,659,57,638,526,101,568,815,998,296,938,359,770,881,770,148,83"
            ,"658,771,148,68,938,445,701,797,597,236,694,51,741,270,560,854,85,747,787,891"
            ,"134,792,462,73,92,748,453,560,415,55,626,195,833,513,107,508,259,256,659,326"
            ,"946,747,654,817,858,404,624,96,892,696,369,576,914,129,592,326,861,360,205,102"
            ,"446,69,210,565,688,484,815,686,152,63,202,714,569,712,368,834,577,842,797,728"
            ,"127,862,343,637,547,624,205,530,422,210,792,584,140,825,532,122,456,92,419,692"
            ,"122,420,657,399,486,120,518,290,698,726,654,357,892,130,530,105,987,817,411,400"
            ,"728,495,256,636,559,947,571,77,881,770,549,189,597,442,783,797,63,236,60,624"
            ,"724,549,355,394,208,400,699,527,585,258,371,998,144,749,94,727,412,592,415,634"
            ,"125,645,666,853,667,780,96,125,96,191,185,93,64,642,941,522,661,813,217,454"
            ,"796,216,513,945,786,653,212,780,774,897,635,512,783,503,900,916,145,529,931,57"
            ,"518,945,129,945,552,712,557,451,152,67,856,727,338,831,880,519,131,144,690,396"
            ,"125,155,897,405,876,697,59,463,345,137,404,698,638,576,528,155,727,790,154,394"
            ,"551,442,776,280,415,869,696,156,766,345,150,653,151,939,814,448,561,579,645,133"
            ,"279,98,54,287,630,899,145,128,512,295,747,857,897,96,73,64,691,207,3,64"
            ,"103,885,419,857,824,623,349,666,524,454,132,423,111,79,821,204,490,821,861,419"
            ,"434,350,205,723,321,741,596,296,830,344,582,715,404,452,857,63,511,816,640,764"
            ,"146,62,743,949,635,279,883,57,165,529,885,570,217,558,147,782,121,355,347,94"
            ,"788,141,397,916,463,149,444,209,131,727,126,841,318,506,650,120,825,98,449,290"
            ,"318,594,126,708,143,648,657,459,820,832,68,994,203,320,786,521,668,566,699,533"
            ,"360,886,148,560,353,112,856,696,109,194,359,95,891,700,819,80,203,941,785,577"
            ,"798,108,418,696,69,699,590,126,402,792,460,813,657,287,559,871,150,578,561,129"
            ,"527,144,404,632,81,580,855,283,700,744,883,327,518,832,660,206,189,397,662,295"
    };




}
