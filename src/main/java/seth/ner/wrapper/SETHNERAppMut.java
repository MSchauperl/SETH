package seth.ner.wrapper;

import de.hu.berlin.wbi.objects.MutationMention;
import seth.SETH;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;


import java.util.List;
/**
 * Minimal example to apply {@link SETHNER} (including MutationFinder) on free text
 * @author rockt
 * Date: 11/9/12
 * Time: 11:12 AM
 */

public class SETHNERAppMut {
    public static void main(String[] args) throws IOException {
    //new String FILENAME = args[0];
    System.out.println(args[0]);
    SETH seth = new SETH("resources/mutations.txt", true, true);

    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(new File("muts_extr_seth.txt")));
    bw.write("pubmed_id" + "\t" + "section" + "\t" + "start" + "\t" + "end" + "\t" + "mutant_residue"
    	                 + "\t" + "wildtype_residue" + "\t" + "position" + "\t" + "mutation_type" + "\t" + "found_term"
        			     + "\t" + "tool");
    bw.write('\n');
    while(br.ready()){
        String[] line_split = br.readLine().split("\\|");
        String pmid = line_split[0];
        String section = line_split[1];
        String line = line_split[2];
        List<MutationMention> result = seth.findMutations(line);
        for (MutationMention mutation : result) {
        	String res = pmid + "\t" + section + "\t" + Integer.toString(mutation.getLocation().getStart()) + "\t"
        					  + Integer.toString(mutation.getLocation().getStop()) + "\t" + mutation.getMutResidue() + "\t"
        					  + mutation.getWtResidue() + "\t" + mutation.getPosition() + "\t" + mutation.getType() + "\t" + mutation.getText() + "\t" + mutation.getTool();
        	bw.write(res);
			bw.newLine();
        	//MutationMention [span=104-108, mutResidue=P, location=90, wtResidue=L, text=L90P, type=SUBSTITUTION, tool=MUTATIONFINDER]
        	//new String mut_string = pmid + "\t" + section + "\t" + mutation.span + "\t" + mutation.location + "\t" + mutation.mutResidue + "\t" + mutation.wtResidue + "\t" + mutation.type + "\t" + mutation.text + "\t" + mutation.tool;
            //System.out.println("mutation.span");
            //System.out.println(mut_string);
        	}
        }
        bw.close();
        br.close();
    }

    }
