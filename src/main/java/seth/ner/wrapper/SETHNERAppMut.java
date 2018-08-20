package seth.ner.wrapper;

import de.hu.berlin.wbi.objects.MutationMention;
import seth.SETH;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import java.util.List;
/**
 * Minimal example to apply {@link SETHNER} (including MutationFinder) on free text
 * @author rockt
 * Date: 11/9/12
 * Time: 11:12 AM
 */

public class SETHNERAppMut {
    public static void main(String[] args) throws IOException {
    System.out.println(args[0]);
    SETH seth = new SETH("resources/mutations.txt", true, true);

    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    while(br.ready()){
        String line = br.readLine();
        List<MutationMention> result = seth.findMutations(line);
        for (MutationMention mutation : result) {
            System.out.println(mutation);
        }
    }
    br.close();
    
    
    }
}