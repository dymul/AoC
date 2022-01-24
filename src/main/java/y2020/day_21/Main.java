package y2020.day_21;

import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        Map<String, String> ingred;
        List<Line> lines;
        String[] input = in;

        lines = parse(input);
        ingred = transform(lines);

        String collect = ingred.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .collect(Collectors.joining(","));

        System.out.println(collect);

        int sum = getSum(ingred, lines);


        System.out.println(sum);
    }

    private static int getSum(Map<String, String> ingred, List<Line> lines) {
        Map<String, Integer> ingredOcc = ingredCount(lines);
        for (String ing : ingred.values()) {
            ingredOcc.remove(ing);
        }
        return ingredOcc.values().stream().mapToInt(i -> i).sum();
    }

    static Map<String, Integer> ingredCount(List<Line> lines) {
        Map<String, Integer> result = new HashMap<>();
        for(Line l : lines) {
            l.ingredients.forEach(ingred -> {
                result.compute(ingred, (k,v) -> v==null? 1 : v+1);
            });
        }
        return result;
    }

    static List<Line> parse(String[] input) {
        List<Line> result = new LinkedList<>();
        for (String line : input) {
            Line l  = new Line(line);
            result.add(l);
        }
        return result;
    }

    private static Map<String, String> transform(List<Line> lines) {
        Map<String, String> completed = new HashMap<>();
        Map<String, Set<String>> result = new HashMap<>();

        for (Line line: lines) {
            for (String a : line.allergens) {
                if (!result.containsKey(a)) {
                    result.put(a, new HashSet<>(line.ingredients));
                } else {
                    result.get(a).retainAll(line.ingredients);
                }
            }
        }
        while(!result.isEmpty()) {
            Optional<Map.Entry<String, Set<String>>> any = result.entrySet().stream().filter(e -> e.getValue().size() == 1).findAny();
            if (!any.isPresent()) {
                throw new RuntimeException("Wrong");
            } else {
                Map.Entry<String, Set<String>> entry = any.get();
                String ingredient = entry.getValue().stream().findAny().get();
                completed.put(entry.getKey(), ingredient);
                result.remove(entry.getKey());
                result.values().forEach(v -> v.remove(ingredient));
            }

        }


        return completed;
    }


    static String[] in2 = {
            "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
            "trh fvjkl sbzzf mxmxvkd (contains dairy)",
            "sqjhc fvjkl (contains soy)",
            "sqjhc mxmxvkd sbzzf (contains fish)"
    };
    static String[] in = {
            "rtdhb jxspzl bjgjgb tjtgbf jdmsljx psjq jqktb qjtlsg trlx vpzxk rfccb lccbpx nrz qndq vnml cvzqq bdt sqqr tpfj rczt xdpvd mpms npk tgpv tvcrkml tclx bkgmcsx fbvsh rjdqt hdcj rjjp lggrq vxnd xhdf tgkgzqj dgxrzglk grqfjk kgckc rthjg hbnf clnkzp zqtd vndnsd jspkl rbxklnd cdvjskn vblsdx lxxvb chkqhd vdnnrpc vzqmj (contains wheat)",
            "grqfjk cvzqq tjtgbf vqxxx bkgmcsx rthjg vpzxk vxnd mrt kpvft tclx bbbhk rjdqt jnbq hzrmhn pmsjt lskl ddqfcd sbtpbx chkqhd zlshrr xtkjp nthgnch jknhx blccjb klt kgkjvlrj zqtd gghpn rsfrcrx grndv jmf rlhbd rfccb zfdfjl cxnhz kvjgfp hbnf fxlb sprdmt pzpxz npk dzrdddm cgtrfnb znssxz mskn sljnxj vffs xsjjq vndnsd kkkb hhxh kgckc kxdb jzlzc qbhrz jspkl hdcj hchkn hkrz dzkxl dgxrzglk mqcbh (contains sesame, dairy)",
            "lccbpx hhxh sprdmt xdpvd qbhrz tgkgzqj vstd psjq sttlfn bbbhk dgjct tfklstp vpzxk jknhx kpvft cvzqq jnbq rjdqt ljpv fbrt tpfj fbvsh sdjd thfdx qfzv hbnf kgckc jzlzc jspkl sqqr khjb cfqqmtx cxnhz grlqdjv trlx zxxds tgvbk kns bjgjgb smmxhs vndnsd tgpv kvjgfp tjtgbf bkgmcsx mpkpx hlzt mglxvr mskn lshcgz hqkb (contains eggs, fish, soy)",
            "vzpntm tpfj bkgmcsx nrz nbxzds bdt cxftspv rjdqt kpvft dfxqfz vkzc tgkgzqj zrmddmt gzlm mrt hzrmhn lbs znvfz npgzfv sd zpgk kns jhk kxsrjd kjrk tqph hkrz rthjg zxxds zqtd phm rfccb cgqrb sdjd vdj jdmsljx klt lvxr xlgpb jmf hdcj dgxrzglk bjr vpzxk dmldz vrctht smmxhs kmpvdjj tjtgbf grnv cvzqq tgvbk jspkl grndv zfcvkg qndq rjjp dzkxl mskn blgvh hfffm mfvqxv vzqmj dzrdddm sprdmt nthgnch thd bfd zmns qfzv kgckc sqqr mthtx pssxd jknhx xtkjp xck rbxklnd qqkvgr lxbkzk mqcbh prtnk lzff dxtx hhxh mpms pmsjt vndnsd djlrc (contains sesame, peanuts, dairy)",
            "cvzqq mpkpx jgkvx nhsbhd mqcbh psjq dmldz kll rczt blccjb bjgjgb hbnf tgvbk gzlm sdjd bkgmcsx jspkl dgjct mrjp jknhx znvfz zxxds lggrq mpms qfzv vpzxk pzpxz zrmddmt xsjjq qjtlsg vqxxx gzmt thd bdt chkqhd qbhrz zqtd sjszxk tclx lxxvb kkkb hkrz khjb tjtgbf zfcvkg rjdqt jdmsljx znssxz jqktb kxsrjd hmhvg (contains shellfish, soy)",
            "xdpvd tsrpk mglxvr tjtgbf lzff smmxhs lbdbjhcv dzkxl dgxrzglk npgzfv rthjg hzrmhn nthgnch vrctht cvzqq vdnnrpc tpfj hbnf tclx grnv rjdqt kkkb tgpv pssxd dgjct dmldz svvd tgvbk kmpvdjj kgkjvlrj thfdx bjgjgb rlhbd bdt kvjgfp sttlfn vkzc dknbxb gghpn smbs vpzxk rfccb qjn sljnxj rfrkt mpkpx rtdhb lchcf dfxqfz jspkl sqkhm fbrt zgkg qfzv tvcrkml mrjp vxnd bjr kjrk tgkgzqj lvxr dxtx xsjjq bkgmcsx dzrdddm khjb phm klt (contains peanuts, soy)",
            "znvfz tvcrkml zlbcj mskn kgx ppsnc vdj zrmddmt vzqmj lbdbjhcv cxftspv grlqdjv jdmsljx zfcvkg jxspzl zqzfnn cghh znssxz xhdf kpvft pzdz zfdfjl sprdmt hmhvg hbnf jmf npgzfv fxlb grnv rfccb tclx cgtrfnb vqxxx hchkn hlzt kptksf zmns qfzv rlhbd dvkmm msth xmnn tgpv hdcj blgvh jqktb fbvsh thfdx jspkl jgkvx dgjct rjdqt sdjd rfrkt dmldz xsjjq tpfj tjtgbf qndq tsrpk cxnhz cgqrb bzszdk vnml blccjb vpzxk xtkjp (contains shellfish)",
            "rbxklnd zfcvkg zqzfnn thfdx vpzxk chkqhd mrjp zlshrr jmf npgzfv jspkl cghh cxnhz kvjgfp cxnfh vdnnrpc sskg cvzqq blccjb kpvft ddqfcd znvfz psjq dgxrzglk lvxr lbdbjhcv thd vffs vdj xdpvd rjdqt bfd khjb tvcrkml hmhvg lchcf smbs djlrc dnzmt tgkgzqj hbnf dknbxb qjtlsg lxxvb qbhrz svvd sd bkgmcsx kgckc hdcj kgkjvlrj jzlzc znssxz dvkmm dgjct rczt qfzv hqc vndnsd bzszdk zlbcj mthtx qndq dmldz jknhx (contains fish)",
            "qndq nthgnch fbxgvhc fbvsh qfzv nbxzds cgqrb jknhx bbbhk jspkl rjdqt hdcj cghh lbs rlhbd rfccb mfvqxv qjn jdmsljx cdvjskn bjgjgb sqkhm lshcgz lxxvb jqktb kddxg nrz tgkgzqj jgkvx dgxrzglk vpzxk lvxr vkzc dxtx lrxrmf dgjct jzlzc pssxd xmnn hqc znssxz hhxh zlbcj rczt rthjg ljpv bdt vqxxx tjtgbf vzpntm kxsrjd vndnsd kkkb vffs prtnk tclx tpfj mrt xsjjq kpvft rbxklnd kgkjvlrj kxdb tvcrkml vrctht bkgmcsx xlgpb vxnd vzqmj thfdx kgx grlqdjv lskl svvd (contains fish, dairy, sesame)",
            "tclx dzkxl fxlb mskn kns cxftspv qjtlsg tfklstp sqqr lvxr dfxqfz bfd vffs gghpn kgckc fpkq ddqfcd rtdhb hbnf thfdx cfqqmtx zfcvkg zrmddmt zlshrr xdpvd khcds lbs tgvbk fbvsh bkgmcsx tqph mthtx vpzxk hdcj bjgjgb blgvh sljnxj lxxvb vzpntm lggrq jxspzl rbxklnd vxnd ljpv sbtpbx hmhvg tkrtrr sd jmf dzrdddm kmpvdjj xsjjq cxnhz pssxd qfzv jhk vndnsd lccbpx jspkl kpvft xmnn tjtgbf (contains dairy, soy, peanuts)",
            "ljpv fpkq ddqfcd tjtgbf jhk cxnhz xlgpb grnv rlhbd sprdmt kxsrjd dvkmm cfqqmtx klt dfxqfz thd cgqrb rfccb hbnf lccbpx pmsjt tgpv blccjb ppsnc vblsdx rjjp qbhrz fxlb vpzxk nhsbhd qjtlsg dxtx rthjg lshcgz dgxrzglk vdj djlrc rjdqt fbxgvhc sttlfn bkgmcsx chkqhd hkbk cxnfh qfzv tvcrkml vzpntm pzpxz kptksf vstd lzff smmxhs kpvft tsrpk lskl dzkxl hzrmhn mrjp mpms lvxr msth hchkn sskg rczt lbdbjhcv grqfjk jspkl sljnxj rsfrcrx sbtpbx vzqmj (contains fish)",
            "jqktb zgkg hdcj rjdqt zqzfnn bkgmcsx vdnnrpc lshcgz vblsdx lvxr hhxh msth nbxzds hkrz zpgk xtkjp hfffm qqkvgr mfvqxv tqph thfdx vndnsd hbnf blccjb lxbkzk jxspzl kns tsrpk qbhrz jnbq rjjp xlgpb dgxrzglk dnzmt tpfj trlx lzff kmpvdjj zfcvkg khjb cxnhz pssxd vrctht dmldz mpkpx jknhx cgtrfnb dvkmm chkqhd rsfrcrx hmhvg thd bfd pnnfccs jzlzc jspkl qndq hqkb tvcrkml rfrkt vkzc kgkjvlrj hlzt lrxrmf lccbpx fbrt sqkhm vpzxk tjtgbf dknbxb kkkb blgvh cgqrb bzszdk (contains peanuts, dairy, eggs)",
            "tjtgbf xtkjp jknhx dgjct jspkl vkzc xdpvd npk xsjjq kgkjvlrj sprdmt vzqmj chkqhd dfxqfz jdmsljx djlrc lbdbjhcv cxnhz hkbk jhk sbtpbx pzdz sjszxk hbnf bfd ljpv vnml tpfj vpzxk bkgmcsx rthjg grnv mglxvr xmnn kpvft kptksf khcds sljnxj qjn smbs rjdqt zqtd tgkgzqj vxnd dzkxl rtdhb vndnsd pzpxz xck msth qfzv tgvbk ddqfcd prtnk tfklstp (contains shellfish)",
            "npgzfv klt hbnf phm khjb mpkpx zpgk grlqdjv qqkvgr dgjct trlx vrctht pssxd qjn ljpv mrjp vdnnrpc lxbkzk rjjp mglxvr tclx kpvft nrz lchcf qbhrz qndq mrt vstd bkgmcsx qfzv vndnsd chkqhd kgx pzdz sprdmt xlgpb dnzmt sjszxk zmns hkrz fbrt vkzc dmldz vxnd xck kmpvdjj bjr jqktb vpzxk vdj cdvjskn jspkl cxnfh kxdb kkkb fbvsh smmxhs xdpvd gzlm hkbk lvxr pmsjt nthgnch hdcj lxxvb jzlzc tgvbk gghpn zqzfnn rfrkt vqxxx kgckc hmhvg vzqmj tjtgbf kddxg grndv kns dfxqfz clnkzp (contains eggs, dairy, soy)",
            "jnbq zqtd xdpvd hkrz vkzc fbxgvhc lbs kmpvdjj thd klt pzdz qndq dxtx kjrk rfccb hdcj rjjp mqcbh hbnf lggrq lbdbjhcv rczt dgxrzglk smbs tjtgbf kptksf jhk rbxklnd zxxds hqc djlrc pnnfccs cgqrb khcds mrt kns bzszdk mpms dgjct pbdvxb dnzmt mthtx msth blgvh sbtpbx gzmt tkrtrr vpzxk qqkvgr vxnd qbhrz znssxz lrxrmf sqkhm dvkmm xhdf chkqhd tgpv jspkl smmxhs hhxh rlhbd rsfrcrx bkgmcsx pgdqzn hchkn fpkq qjtlsg kll lzff sdjd rjdqt vblsdx kpvft npgzfv xlgpb (contains shellfish, peanuts)",
            "tgpv psjq qfzv hqc hbnf hmhvg vxnd znssxz jxspzl qqkvgr zpgk kvjgfp vndnsd rjdqt kgx tclx jmf kll jspkl zxxds ljpv vrctht smmxhs tpfj cghh dzkxl vdj kxdb xdpvd lccbpx dgxrzglk tkrtrr pmsjt sdjd zqtd hfffm ppsnc pnnfccs hdcj tgkgzqj grqfjk xlgpb lskl sprdmt bkgmcsx xmnn tjtgbf tfklstp (contains eggs, peanuts, wheat)",
            "rjdqt dxtx sqqr pzdz mqcbh kkkb fpkq bkgmcsx pzpxz rczt xtkjp cgqrb zpgk zfcvkg npgzfv kddxg vpzxk nthgnch pnnfccs vffs lzff fxlb bbbhk dknbxb hkrz mpms clnkzp djlrc hchkn smbs hdcj hfffm jxspzl vdj tvcrkml lggrq jgkvx dfxqfz zxxds sprdmt msth tgpv tjtgbf bjr jspkl klt mpkpx lxxvb jhk vzqmj zmns qndq thfdx sjszxk rlhbd ljpv lvxr dzkxl lshcgz nrz vqxxx zgkg grlqdjv hhxh vndnsd tclx kptksf mfvqxv qfzv mrjp zqzfnn dvkmm sdjd zlshrr bdt npk nhsbhd chkqhd vxnd gzlm (contains peanuts, soy)",
            "sdjd sbtpbx kxsrjd dvkmm nhsbhd kmpvdjj qfzv sqkhm dxtx tqph fbvsh lvxr tjtgbf klt grqfjk jspkl zqtd vzqmj rjdqt pnnfccs mglxvr lshcgz vffs rfccb cxftspv jxspzl vpzxk bkgmcsx thd hbnf rjjp rlhbd jdmsljx tgkgzqj rthjg fxlb bbbhk vnml sd grlqdjv hfffm rbxklnd xtkjp blccjb psjq (contains eggs)",
            "sdjd pzdz grlqdjv nbxzds nthgnch zqzfnn mglxvr lskl vnml tgkgzqj hzrmhn zxxds lbdbjhcv zgkg qqkvgr rfrkt rjdqt cfqqmtx fbxgvhc rczt khcds kll vpzxk bjr vblsdx vstd kvjgfp tkrtrr rthjg dxtx blccjb vkzc sjszxk thd mqcbh dknbxb zpgk grnv xck trlx lshcgz clnkzp phm xlgpb lxxvb hbnf nhsbhd vzqmj qfzv tsrpk sljnxj tfklstp tjtgbf pzpxz ljpv hdcj klt dnzmt sqkhm pbdvxb qndq bkgmcsx vdj zmns kgx kxdb tpfj (contains dairy, fish, eggs)",
            "vblsdx vpzxk jspkl gghpn qndq vzpntm tqph dgxrzglk kmpvdjj cxftspv rfrkt zfdfjl kxdb xdpvd bkgmcsx bjr dvkmm thd blgvh dknbxb znvfz zlshrr mglxvr ppsnc lvxr tgvbk sttlfn lzff lxbkzk hbnf sd kptksf vffs kgkjvlrj qjtlsg tclx npk pnnfccs sqqr mpkpx dgjct ljpv cfqqmtx tvcrkml qbhrz znssxz hdcj svvd grnv vdnnrpc tjtgbf zpgk vnml rthjg nthgnch rjdqt chkqhd fxlb djlrc zfcvkg (contains eggs, peanuts)",
            "rbxklnd hchkn qbhrz khjb cfqqmtx rjjp zrmddmt trlx hbnf pbdvxb qfzv npgzfv kgkjvlrj cgtrfnb dvkmm tfklstp kddxg zgkg hdcj blgvh xsjjq hfffm hmhvg vpzxk rjdqt npk psjq jspkl rthjg ljpv jknhx khcds kjrk gzmt tclx lbdbjhcv zfdfjl lskl mrjp kll bbbhk hqc pzdz lggrq sttlfn hkrz bkgmcsx fbxgvhc tgpv thd tgvbk djlrc xlgpb pgdqzn grnv cdvjskn xhdf rtdhb kxsrjd mthtx fxlb (contains fish)",
            "vdj nbxzds bbbhk rjdqt pnnfccs jknhx vstd chkqhd jspkl hzrmhn tjtgbf dgjct kgckc mthtx jnbq tgkgzqj hhxh rlhbd gzmt rfrkt tfklstp bjr kkkb qfzv znssxz vpzxk djlrc blccjb pssxd mglxvr jxspzl nthgnch zmns fbrt psjq vblsdx bfd phm bkgmcsx fpkq gzlm zqzfnn tgvbk hdcj smmxhs vqxxx vxnd mrt gghpn kns fbvsh thfdx mrjp jdmsljx clnkzp mpkpx sqkhm kgkjvlrj tclx (contains peanuts)",
            "rbxklnd qfzv rsfrcrx dmldz hchkn vqxxx fpkq kxsrjd vffs sqqr kddxg vzpntm lggrq kgx jgkvx hdcj kll zfcvkg rjdqt mrjp bjr smmxhs sprdmt mpms ppsnc gzlm xdpvd bkgmcsx grnv khcds cvzqq hbnf cdvjskn kptksf fbrt vnml jknhx jspkl qbhrz khjb svvd hfffm rfccb cgqrb sjszxk vpzxk dvkmm djlrc chkqhd pbdvxb tkrtrr blgvh (contains soy, dairy, fish)",
            "lchcf rjjp chkqhd zgkg sdjd sttlfn lrxrmf zxxds rjdqt lxxvb vpzxk jxspzl hchkn rfrkt mthtx qfzv jzlzc thd pbdvxb hmhvg vkzc dgjct jspkl bkgmcsx kgckc gzmt gzlm jdmsljx lggrq kxdb tjtgbf hkrz lbs mrjp blccjb vdj cxnfh jhk hbnf dzrdddm kll khjb smmxhs cvzqq (contains sesame, dairy, peanuts)",
            "vblsdx pzdz lggrq kns vstd vndnsd kll djlrc jspkl cxftspv lxbkzk tjtgbf hfffm mskn grnv grndv vqxxx vpzxk hkrz grqfjk hchkn rtdhb dxtx sqkhm lchcf bkgmcsx hbnf kvjgfp kxdb qfzv hhxh xlgpb kxsrjd sskg fpkq kgx vrctht vdnnrpc gzlm lskl rsfrcrx cgqrb pssxd bjgjgb rjdqt gghpn zgkg bjr cghh vzqmj sbtpbx pzpxz vnml jnbq (contains fish, dairy, eggs)",
            "zqtd dgjct npk xck jhk tpfj tgpv kddxg kpvft jnbq sd rjdqt zrmddmt xsjjq smmxhs rtdhb rfrkt bkgmcsx msth tjtgbf pmsjt dzrdddm cghh fbxgvhc dvkmm jspkl pzdz kgx ppsnc lskl tkrtrr cxnhz qqkvgr prtnk kgckc cxftspv vzqmj kvjgfp hbnf jqktb hdcj zqzfnn zfcvkg vdnnrpc svvd lbdbjhcv tvcrkml lxbkzk vnml lrxrmf hkbk mrjp vpzxk sqqr xmnn blccjb grlqdjv (contains sesame)",
            "vkzc grnv vnml xck dknbxb klt chkqhd jspkl vxnd hdcj grndv qqkvgr dgjct lskl tkrtrr vrctht vqxxx thd kxdb xhdf cdvjskn xmnn qbhrz tjtgbf kll zqtd bjr grlqdjv lbs bdt rjdqt zlbcj grqfjk prtnk ddqfcd bkgmcsx blgvh hqc kgckc vpzxk cfqqmtx mpms dfxqfz sljnxj kptksf qfzv hzrmhn tvcrkml msth rbxklnd smmxhs gzlm (contains soy, peanuts, wheat)",
            "tvcrkml prtnk hlzt lxxvb grndv kgx kmpvdjj dgjct lxbkzk dvkmm tgkgzqj cvzqq hfffm xtkjp jspkl kxsrjd lzff lchcf klt kll kjrk tjtgbf jmf zxxds bkgmcsx pmsjt kvjgfp cgqrb tclx hkbk vpzxk zmns qfzv xlgpb dnzmt blccjb hzrmhn mpms vdj hbnf vzqmj fbrt rjdqt thd lbs sbtpbx chkqhd jknhx zlbcj (contains soy, fish, dairy)",
            "tgkgzqj jgkvx mfvqxv rfrkt bkgmcsx kxsrjd gzlm sprdmt cxnhz nthgnch jspkl hkrz kgx lrxrmf zlbcj kvjgfp zfcvkg pzpxz tjtgbf lxbkzk mpms dgjct grndv smmxhs sljnxj qndq hkbk prtnk rczt kgckc znvfz mglxvr xlgpb rjdqt pbdvxb gghpn hbnf hlzt hzrmhn dzkxl qfzv jknhx dgxrzglk hdcj vffs hfffm (contains peanuts, eggs, soy)",
            "kptksf npgzfv jnbq lxxvb fpkq rsfrcrx xck pzdz khjb vzqmj vzpntm rbxklnd hdcj kkkb vblsdx pbdvxb kpvft xlgpb tgkgzqj smbs vqxxx sqqr bdt nbxzds bjr vstd rjdqt tclx qbhrz tjtgbf bbbhk kxsrjd khcds bfd clnkzp xmnn zrmddmt bkgmcsx prtnk tpfj jspkl nthgnch cdvjskn cgqrb thfdx thd gzlm hchkn sskg zfcvkg nhsbhd zlshrr pmsjt jdmsljx cghh lggrq hqc qfzv mqcbh dvkmm hzrmhn tfklstp mrjp gzmt ljpv vrctht kmpvdjj sljnxj vpzxk sdjd znssxz ppsnc kns hfffm zpgk mthtx qqkvgr vdnnrpc pssxd zqtd tgvbk tqph (contains soy, sesame)",
            "fxlb kgx kjrk kgckc vpzxk kkkb tkrtrr hbnf znssxz klt jknhx lzff npgzfv pbdvxb lccbpx jdmsljx jnbq grlqdjv kpvft xck prtnk khcds qqkvgr dknbxb hqkb mpkpx pssxd xmnn mrjp vndnsd zxxds xhdf nthgnch lshcgz jzlzc vffs fbxgvhc sqqr sttlfn tgkgzqj cgqrb pnnfccs thfdx rfrkt dfxqfz znvfz kvjgfp zrmddmt sljnxj khjb kll cgtrfnb jspkl tjtgbf pmsjt mpms grnv bkgmcsx rsfrcrx rtdhb nrz lxbkzk qjn bfd mqcbh zlbcj bjgjgb psjq vzpntm fbvsh hqc rbxklnd bjr kgkjvlrj hdcj rjdqt kmpvdjj hkrz zlshrr zqzfnn cfqqmtx vstd cxftspv sbtpbx mglxvr (contains sesame, peanuts, soy)",
            "npk vndnsd blgvh tgvbk lchcf bkgmcsx khcds cxnhz pmsjt fpkq fbxgvhc rlhbd rfrkt pbdvxb smmxhs hzrmhn lskl khjb rjdqt mskn tjtgbf ddqfcd hlzt xmnn jmf hdcj kll xck xhdf dfxqfz dzkxl qndq sqkhm kpvft tsrpk vpzxk sjszxk jgkvx lshcgz fxlb cfqqmtx jspkl mfvqxv hfffm jdmsljx msth hbnf mrjp sljnxj qqkvgr kgckc (contains peanuts, eggs, wheat)",
            "vkzc hqkb tjtgbf klt kgckc hdcj xlgpb jqktb fxlb sbtpbx lbs zlbcj hkbk fpkq jgkvx cxnhz dzrdddm khcds vndnsd ddqfcd dfxqfz bjr mskn bkgmcsx vzpntm kkkb qbhrz hbnf vrctht qjn msth dzkxl qfzv jmf tgpv gzlm dknbxb dgxrzglk vpzxk thd phm rtdhb hqc jnbq rfrkt zxxds psjq rczt lzff hfffm kpvft npk dvkmm kxdb thfdx jspkl grnv dxtx djlrc kgkjvlrj jdmsljx nrz (contains wheat, eggs)",
            "rfccb jspkl jxspzl vzpntm kpvft vffs hbnf sljnxj pzpxz qbhrz zfdfjl kll cxnhz mfvqxv lccbpx pzdz kgkjvlrj rlhbd thfdx xck sqkhm pgdqzn jnbq phm fbvsh mpms tqph tgvbk blccjb vrctht jdmsljx lshcgz lbdbjhcv qfzv hchkn fbrt bfd zlshrr chkqhd cfqqmtx zpgk qjn khcds msth pnnfccs bzszdk tgpv grlqdjv kxdb vpzxk zmns sd jzlzc xhdf vdj tjtgbf sbtpbx ljpv vstd fpkq prtnk zfcvkg vdnnrpc cvzqq rbxklnd klt tfklstp tclx grqfjk psjq mrt hdcj rfrkt bkgmcsx mskn nrz dgjct hlzt pbdvxb (contains fish, wheat)",
            "pnnfccs psjq fbvsh ljpv rthjg mrjp rfccb bjgjgb bjr rjdqt dknbxb msth xlgpb kmpvdjj vdnnrpc kxsrjd pmsjt hzrmhn sqqr ddqfcd tgvbk rlhbd jmf kkkb lvxr kxdb kpvft xtkjp khcds jspkl dfxqfz kll rjjp rfrkt vstd hmhvg vdj cfqqmtx zqtd jxspzl tjtgbf hchkn cdvjskn qfzv cxftspv vqxxx hdcj sdjd pssxd khjb gghpn zlshrr lskl zmns nbxzds dxtx vpzxk lrxrmf mpkpx hqkb sbtpbx chkqhd zpgk qjtlsg mfvqxv mthtx lggrq bbbhk grqfjk pzpxz tpfj trlx kgx bkgmcsx lccbpx (contains fish, eggs, sesame)",
            "hfffm vblsdx rbxklnd svvd cxnfh smmxhs mskn fxlb rjjp mglxvr dvkmm zfcvkg sljnxj grlqdjv zqtd xsjjq djlrc rczt zfdfjl dnzmt xdpvd trlx tsrpk xhdf mfvqxv blccjb sd zxxds prtnk gzmt klt kkkb kns vpzxk dknbxb pnnfccs sqkhm vzpntm gzlm hdcj thd qfzv grndv lchcf pgdqzn bdt ppsnc jxspzl tjtgbf nrz jspkl lskl vdj dgxrzglk grnv kxdb sdjd cvzqq bkgmcsx clnkzp bbbhk mqcbh cdvjskn vndnsd hbnf kgx dzrdddm kpvft lrxrmf xlgpb tkrtrr kjrk zrmddmt (contains dairy, fish, peanuts)",
            "vnml hdcj dmldz zgkg dvkmm tgkgzqj kxdb bkgmcsx rlhbd kns rjjp dnzmt bdt vrctht cghh hkrz vxnd sdjd zfdfjl xmnn jdmsljx xdpvd rjdqt cgqrb zpgk jhk npgzfv qfzv tpfj jnbq pzpxz mpms blccjb vndnsd pssxd vpzxk dxtx mskn cxnfh lvxr lchcf kjrk jxspzl dzrdddm lbs lskl msth phm vkzc jspkl lrxrmf tvcrkml cfqqmtx hbnf dzkxl dgxrzglk lbdbjhcv rbxklnd pzdz sskg (contains wheat, eggs, shellfish)",
            "sdjd cvzqq dvkmm msth cxftspv xtkjp rbxklnd jxspzl sjszxk qndq dzrdddm lvxr qjn pssxd jmf lggrq tkrtrr cdvjskn smmxhs zrmddmt npgzfv lchcf rczt bfd tpfj prtnk zqtd fpkq thfdx qfzv xlgpb khcds tjtgbf kgx hbnf mskn mpkpx dknbxb bbbhk zlbcj mrjp kll hdcj sd lskl tvcrkml jzlzc zqzfnn mrt blgvh jqktb tgkgzqj hfffm qjtlsg xsjjq cgqrb chkqhd ppsnc sqqr fbrt dxtx mqcbh vzpntm dfxqfz lxbkzk lshcgz bkgmcsx cgtrfnb rjdqt dgxrzglk zgkg tsrpk dnzmt zfcvkg cghh vpzxk zlshrr kgkjvlrj (contains peanuts)"
    };



}
