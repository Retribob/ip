package parser;

import customexceptions.EmptyListException;
import customexceptions.IncompleteTaskException;
import customexceptions.NoSuchTaskException;

import customexceptions.UnknownInputException;
import gui.Main;
import javafx.application.Application;
import listmanager.ListManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Parser is responsible for decoding Strings for user input as well as file input.
 *
 */
public class Parser {


    /**
     * Scans nextline to obtain userInput.
     * Breaks down user input into chunks to perform certain actions:
     * Can end chatbot conversation. "bye"
     * Can display list. "list"
     * Can mark task as complete/incomplete. "mark/unmark <>list number</>"
     * Can delete task. "delete <>list number</>"
     * Can store task in <code>ListManager</code> object.
     *
     * @param listManager ListManager instance that stores tasks
     * @return False if the user wants to end the conversation, otherwise True.
     * @throws NoSuchTaskException If taskDescriptor in input does not match any known format.
     * @throws IncompleteTaskException If taskDescriptor in input matches known format but is incomplete.
     * @throws EmptyListException If user wants to display taskList but there are no tasks.
     */
    public String parseInput(ListManager listManager, String input)
            throws NoSuchTaskException, IncompleteTaskException, EmptyListException{
        List<String> wordSegments = stringSplitter(input, " ");
        String keyword = wordSegments.get(0);

        if (keyword.equals("bye")) {
            return keyword;
        } else if (keyword.equals("list")) {
            return listManager.displayList();
        } else  if (keyword.equals("find")) {
            return listManager.findTasks(input);
        } else if (keyword.equals("unmark")) {
            int index = Integer.parseInt(wordSegments.get(1)) - 1;
            return listManager.updateTask(false, index);
        } else if (keyword.equals("mark")) {
            int index = Integer.parseInt(wordSegments.get(1)) - 1;
            return listManager.updateTask(true, index);
        } else if (keyword.equals("delete")) {
            int index = Integer.parseInt(wordSegments.get(1)) - 1;
            return listManager.deleteTasks(index);
        } else if (keyword.equals("tag")) {
            List<String> temp = stringSplitter(wordSegments.get(1), " ");
            int index = Integer.parseInt(temp.get(0)) - 1;
            String tagName = temp.get(1);
            return listManager.addTagToTask(tagName, index);

        }

        return listManager.add(input);


    }


    /**
     * This method breaks down the words into segments based on the specified splitPoints
     *
     * @param input is the String the caller wishes to break apart
     * @param splitPoints each split point will break the remaining string in half based on the first occurrence
     *                    of each splitPoint
     * @return a List of Strings that correspond to each segment of the input.
     */
    public List<String> stringSplitter(String input, String... splitPoints) {
        List<String> stringSegments = new ArrayList<String>();
        String temp = input;
        boolean completeSplit = true;

        for (String splitPoint : splitPoints) {
            String[] words = temp.split(splitPoint, 2);
            stringSegments.add(words[0]);
            if (words.length <= 1) {
                completeSplit = false;
                break;
            }
            temp = words[1];
        }

        if (completeSplit) {
            stringSegments.add(temp);
        }

        return stringSegments;
    }

}
