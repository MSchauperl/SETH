package seth;

import de.hu.berlin.wbi.objects.MutationMention;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import seth.ner.wrapper.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Detect dnSNP mentions in text (e.g., rs2345)
 * @author  Philippe Thomas
 */
class dbSNPRecognizer {

    final private Logger logger = LoggerFactory.getLogger(dbSNPRecognizer.class);

    final private static String prefix="(^|[\\s\\(\\)\\[\\'\"/,\\-:_])"; //>
    final private static String midfix="((?<wild>[ATGC])\\s?[/\\\\>]\\s?(?<mut>[ATGC]))?";
    final private static String suffix="(?=([\\.,\\s\\)\\(\\]\\'\":;_\\-/]|$))";
    //(?!0) = Not starting with 0
    //(\d{1,3}(,\d{3})+) -> Matches decimals with thousand separators
    //\d{2,} matches decimals without thousand separator which are at least 2 long
    final private static Pattern dbSNP = Pattern.compile(prefix +"(rs(?!0)((\\d{1,3}(,\\d{3})+)|\\d{2,}))" +midfix +suffix);

    /**
     * FInd dbSNP mentions in a text
     * @param text Text to perform Named Entity Recognition
     * @return A list of dbSNP mentions found in the provided text
     */
    public List<MutationMention> extractMutations(String text) {
        List<MutationMention> result = new ArrayList<MutationMention>();

        Matcher matcher = dbSNP.matcher(text);
        while (matcher.find()){

            int begin = matcher.start(2);
            int end = matcher.group("mut") == null ? matcher.end(2) : matcher.end("mut");

            String wild= matcher.group("wild");
            String mutated=matcher.group("mut");

            MutationMention mm = new MutationMention(begin, end, text.substring(begin, end), null, null, wild, mutated, Type.DBSNP_MENTION, MutationMention.Tool.DBSNP);
            try{
                int rsId = Integer.parseInt(matcher.group(2).substring(2).replaceAll(",",""));
                mm.normalizeSNP(rsId); //TODO: In general we could check if this rs-id is known in dbSNP?
            }catch(NumberFormatException nfe){
                logger.warn("Cannot parse dbSNP Mention {}", matcher.group(2));
            }
            result.add(mm);
        }

        return result;
    }
}
