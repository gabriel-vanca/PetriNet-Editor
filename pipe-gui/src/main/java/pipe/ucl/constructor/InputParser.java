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

    private ArrayList<TransAssertion> TransAssertionList = new ArrayList<>();

    public ArrayList<TransAssertion> getTransAssertionList() {
        return TransAssertionList;
    }

    public void emptyTransAssertionList() {
        TransAssertionList = new ArrayList<>();
    }

    public InputParser() {

        List<String> tranAssertionList =  ReadFile();
        for (String tranAssertionString:tranAssertionList) {
            TransAssertion transAssertion = ParseTranAssertion(tranAssertionString);
            TransAssertionList.add(transAssertion);
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

    private TransAssertion ParseTranAssertion(String transAssertionString) {

        /* Note: String instructions on input should be made */

        TransAssertion newTransAssertion = new TransAssertion();

        try {
            //.replaceAll("\\s", "")

            /* Initialisations */
            transAssertionString = transAssertionString.replace("'", "");
            String[] tranAssertionSplit = transAssertionString.split(Pattern.quote("("));
            String[] currentSubsectionSplit;
            String[] currentUnderSubSectionSplit;
            String function;
            int currentSectionIndex = 2;

            function = null;
            if(tranAssertionSplit[currentSectionIndex].toLowerCase().contains("default")) {
                function = "default";
                currentSectionIndex++;
            }

            currentSubsectionSplit = tranAssertionSplit[currentSectionIndex].split(Pattern.quote(","));
            if(function != null) {
                newTransAssertion.setStartStateName(function + "( " + currentSubsectionSplit[0]);
            } else {
                newTransAssertion.setStartStateName(currentSubsectionSplit[0]);
            }
            newTransAssertion.setStartStateDate(currentSubsectionSplit[1].substring(0, currentSubsectionSplit[1].lastIndexOf(")")));
            currentSectionIndex++;

            function = null;
            if(tranAssertionSplit[currentSectionIndex].toLowerCase().contains("default")) {
                function = "default";
                currentSectionIndex++;
            }

            currentSubsectionSplit = tranAssertionSplit[currentSectionIndex].split(Pattern.quote(","));
            if(function != null) {
                newTransAssertion.setEndStateName(function + "( " + currentSubsectionSplit[0]);
            } else {
                newTransAssertion.setEndStateName(currentSubsectionSplit[0]);
            }
            newTransAssertion.setEndStateDate(currentSubsectionSplit[1].substring(0, currentSubsectionSplit[1].lastIndexOf(")")));
            currentUnderSubSectionSplit= currentSubsectionSplit[2].split(Pattern.quote(":"));
            if (currentUnderSubSectionSplit.length == 1) {
                if (currentUnderSubSectionSplit[0].toLowerCase().contains("true")) {
                    newTransAssertion.setSign(Boolean.TRUE);
                } else {
                    if (currentUnderSubSectionSplit[0].toLowerCase().contains("false")) {
                        newTransAssertion.setSign(Boolean.FALSE);
                    } else {
                        throw new NotImplementedException();
                    }
                }
            }
            else {
                newTransAssertion.setAuthor(currentUnderSubSectionSplit[0]);
                String time = currentUnderSubSectionSplit[1] + " ((";

                currentSectionIndex+=2;

                currentSubsectionSplit = tranAssertionSplit[currentSectionIndex].split(Pattern.quote(":"));
                time += currentSubsectionSplit[0];
                newTransAssertion.setTime(time);
                function = null;
                int indexOfSign = currentSubsectionSplit[1].indexOf("~");
                if (indexOfSign == -1) {
                    newTransAssertion.setSign(Boolean.FALSE);
                    function = currentSubsectionSplit[1].substring(indexOfSign + 1);
                } else {
                    newTransAssertion.setSign(Boolean.TRUE);
                    function = currentSubsectionSplit[1];
                }

                function += " (";
                function += tranAssertionSplit[currentSectionIndex].substring(0, tranAssertionSplit[currentSectionIndex].lastIndexOf(")"));
                newTransAssertion.setAction(function);
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
            return null;
        }

        finally {
            return newTransAssertion;
        }
    };

}
