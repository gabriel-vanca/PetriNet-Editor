package pipe.ucl.constructor;

import pipe.ucl.models.TransAssertion;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputParser {

    public InputParser() {

        List<String> tranAssertionList =  ReadFile();
        for (String tranAssertion:tranAssertionList) {
            ParseTranAssertion(tranAssertion);
        }
    }

    private List<String> ReadFile() {

        String fileName = "input.txt";
        List<String> tranAssertionList = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(fileName)
                .toURI()))) {

            tranAssertionList = stream
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        tranAssertionList.forEach(System.out::println);

        return tranAssertionList;
    }

    private TransAssertion ParseTranAssertion(String transAssertion) {

        /* Note: String instructions on input should be made */

        TransAssertion newTransAssertion = new TransAssertion();

        try {
            //.replaceAll("\\s", "")
            transAssertion = transAssertion.replace("'", "");
            String[] tranAssertionSplit = transAssertion.split(Pattern.quote("("));

            for (String partSPlit : tranAssertionSplit) {
                System.out.println(partSPlit);
            }
            ;

            String[] section2 = tranAssertionSplit[2].split(Pattern.quote(","));
            newTransAssertion.setStartStateName(section2[0]);
            newTransAssertion.setStartStateDate(section2[1].substring(0, section2[1].lastIndexOf(")")));

            String[] section3 = tranAssertionSplit[3].split(Pattern.quote(","));
            newTransAssertion.setEndStateName(section3[0]);
            newTransAssertion.setEndStateDate(section3[1].substring(0, section3[1].lastIndexOf(")")));
            String[] section3_section2 = section3[2].split(Pattern.quote(":"));
            if (section3_section2.length == 1) {
                if (section3_section2[0].toLowerCase().contains("true")) {
                    newTransAssertion.setSign(Boolean.TRUE);
                } else {
                    if (section3_section2[0].toLowerCase().contains("false")) {
                        newTransAssertion.setSign(Boolean.FALSE);
                    } else {
                        throw new NotImplementedException();
                    }
                }
            }
            else {
                newTransAssertion.setAuthor(section3_section2[0]);
                String time = section3_section2[1] + " ((";

                String[] section5 = tranAssertionSplit[5].split(Pattern.quote(":"));
                time += section5[0];
                newTransAssertion.setTime(time);
                String action;
                int indexOfSign = section5[1].indexOf("~");
                if (indexOfSign == -1) {
                    newTransAssertion.setSign(Boolean.FALSE);
                    action = section5[1].substring(indexOfSign + 1);
                } else {
                    newTransAssertion.setSign(Boolean.TRUE);
                    action = section5[1];
                }

                action += " (";
                action += tranAssertionSplit[6].substring(0, tranAssertionSplit[6].lastIndexOf(")"));
                newTransAssertion.setAction(action);
            }

        } catch (Exception e) {
            System.out.println("ERROR: e");
            return null;
        }

        finally {
            return newTransAssertion;
        }
    };

}
