package model;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Model {
    // Path to 'data' folder
    private final String dataPath = "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "data" + File.separator;

    // Creates a file at 'filePath' under 'fileName' with 'fileContent'
    private void createFile(String filePath, String fileName, String fileContent) throws IOException{
        String linkPath = filePath + File.separator + fileName;
        // Creates the file at the designated path
        new File(linkPath).createNewFile();

        // Writes to the newly created file
        FileWriter myWriter = new FileWriter(linkPath);
        myWriter.write(fileContent);
        myWriter.close();
    }

    // Returns the data read within the file at 'path'
    private String readFile(String path){
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(path));
            String row = csvReader.readLine();
            csvReader.close();
            return Objects.requireNonNullElse(row, "");
        } catch (IOException e) {
            return "";
        }
    }

    private void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    public TreeMap<String, HashMap<String, String[]>> getAllData(){
        TreeMap<String, HashMap<String, String[]>> data = new TreeMap<>();
        for (String listName : getListNames()){
            HashMap<String, String[]> listData = new HashMap<>();
            for (String itemName : getListItems(listName)) {
                String[] itemData = new String[4];
                itemData[0] = getItemLink(listName, itemName);
                itemData[1] = getItemText(listName, itemName);
                itemData[2] = getItemURL(listName, itemName);
                itemData[3] = getItemFile(listName, itemName)[0];
                listData.put(itemName, itemData);
            }
            data.put(listName, listData);
        }

        return data;
    }

    // Creates a folder under the name 'listName' within the folder 'data'
    public Boolean addList(String listName){
        if (listName == null){
            return false;
        }

        File fileToBeCreated = new File(dataPath + listName);
        if (fileToBeCreated.exists()){
            return false;
        }else{
            return fileToBeCreated.mkdirs();
        }
    }

    // Returns a String[] containing a list of the list names
    public String[] getListNames(){
        File file = new File(dataPath);

        return file.list((current, name) -> new File(current, name).isDirectory());
    }

    // Deletes the folder 'listName'
    public Boolean deleteLists(String[] listNames) {
        try {
            for (String listName : listNames) {
                deleteDirectory(new File(dataPath + listName));
                changeAllInstancesOfItemsLinkedToList(listName, listName);
            }
            return true;
        }catch (IOException e){
            return false;
        }
    }

    // Changes a list name
    public Boolean changeListName(String currentListName, String newListName) {
        // In the case where 'currentListName' is the same as 'newListName', ignoring case
        if (currentListName.equalsIgnoreCase(newListName)){
            // Change list name to something else and then change it again to 'newListName'
            if(currentListName.equalsIgnoreCase("Test")){
                changeListName(currentListName, "Test1");
                currentListName = "Test1";
            }else{
                changeListName(currentListName, "Test");
                currentListName = "Test";
            }
        }

        try {
            // Moves the contents to the new folder
            Path source = Paths.get(dataPath + currentListName);
            Files.move(source, source.resolveSibling(newListName));
            // Items linked to the list are changed to the new named list
            changeAllInstancesOfItemsLinkedToList(currentListName, newListName);
            return true;
        } catch (IOException e){
            return false;
        }
    }

    // Retrieves the names of items within a list
    public String[] getListItems(String listName){
        File list = new File(dataPath + listName);
        // Creates the list in the case it is not present
        list.mkdirs();

        return list.list((current, name) -> new File(current, name).isDirectory());
    }

    // Adds an item to a list and ensures an item contains the required components
    public Boolean addItemToList(String listName, String itemName){
        String itemPath = dataPath + listName + File.separator + itemName;

        try {
            // Creates the item and its necessary components
            new File(itemPath).mkdirs();
            setItemLink(listName, itemName, "");
            setItemText(listName, itemName, "");
            setItemURL(listName, itemName, "");
            new File(itemPath + File.separator + "file").mkdir();

            return true;
        } catch (IOException e){
            return false;
        }
    }

    // Removes an item from a list
    public void deleteItemFromList(String listName, String itemName){
        String itemPath = dataPath + listName + File.separator + itemName;
        deleteDirectory(new File(itemPath));
    }

    // Changes the name of an item
    public Boolean changeItemNameInList(String listName, String itemName, String newItemName) {
        Path sourceDirectory = Paths.get(dataPath + listName + File.separator + itemName);
        try {
            Files.move(sourceDirectory, sourceDirectory.resolveSibling(newItemName));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // When changing a list name or deleting a list, it ensures any items linked to the list are changed to avoid errors
    private void changeAllInstancesOfItemsLinkedToList(String editedListName, String newLink) throws IOException{
        for (String listName : getListNames()){
            for (String itemName : getListItems(listName)){
                if (getItemLink(listName, itemName).equals(editedListName)){
                    // When the list has been deleted
                    if (editedListName.equals(newLink)){
                        setItemLink(listName, itemName, "");
                    }else{
                        // When the list has been renamed
                        setItemLink(listName, itemName, newLink);
                    }
                }
            }
        }
    }

    // Retrieves the lists linked to each item in 'listName'
    public String[] getItemLinksInList(String listName){
        List<String> itemLinksInList = new ArrayList<>();
        String[] listItems = getListItems(listName);
        for (String item : listItems){
            itemLinksInList.add(getItemLink(listName, item));
        }
        return itemLinksInList.toArray(new String[0]);
    }

    // Clears the data corresponding to the item object type
    public void deleteItemFile(String listName, String itemName) {
        deleteDirectory(new File(dataPath + listName + File.separator + itemName + File.separator + "file"));
    }

    // Link an item to a list
    public void setItemLink(String listName, String itemName, String listToLink) throws IOException {
        createFile(dataPath + listName + File.separator + itemName, "link.txt", listToLink);
    }

    // Set the text of an item
    public void setItemText(String listName, String itemName, String itemText) throws IOException {
        createFile(dataPath + listName + File.separator + itemName, "text.txt", itemText);
    }

    // Set the URL of an item
    public void setItemURL(String listName, String itemName, String itemURL) throws IOException {
        createFile(dataPath + listName + File.separator + itemName, "url.txt", itemURL);
    }

    // Saves the file of an item
    public Boolean setItemFile(String listName, String itemName, String fileName, Part filePart) {
        deleteItemFile(listName, itemName);

        String itemPath = dataPath + listName + File.separator + itemName;
        String path = itemPath + File.separator + "file";
        new File(path).mkdirs();
        OutputStream out;
        InputStream fileContent;

        try {
            out = new FileOutputStream(path + File.separator + fileName);
            fileContent = filePart.getInputStream();

            int read;
            final byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.close();
            fileContent.close();
            return true;
        } catch (IOException e){
            return false;
        }
    }

    // Retrieves the list linked to an item
    public String getItemLink(String listName, String itemName){
        String itemPath = dataPath + listName + File.separator + itemName;

        if (!new File(itemPath + File.separator + "link.txt").exists()){
            try {
                setItemLink(listName, itemName, "");
                return "";
            } catch (IOException e){
                return listName;
            }
        }

        return readFile(itemPath + File.separator + "link.txt");
    }

    // Retrieves the text of an item
    public String getItemText(String listName, String itemName){
        String itemPath = dataPath + listName + File.separator + itemName;

        if (!new File(itemPath + File.separator + "text.txt").exists()){
            try {
                setItemText(listName, itemName, "");
            } catch (IOException e){
                return "";
            }
        }

        return readFile(itemPath + File.separator + "text.txt");
    }

    // Retrieves the item's URL
    public String getItemURL(String listName, String itemName){
        String itemPath = dataPath + listName + File.separator + itemName;

        if (!new File(itemPath + File.separator + "url.txt").exists()){
            try {
                setItemURL(listName, itemName, "");
            } catch (IOException e){
                return "";
            }
        }

        return readFile(itemPath + File.separator + "url.txt");
    }

    // Retrieves the item's file name and its path
    public String[] getItemFile(String listName, String itemName){
        String fileFolderPath = dataPath + listName + File.separator + itemName + File.separator + "file";
        File fileFolder = new File(fileFolderPath);
        fileFolder.mkdirs();
        String[] file = fileFolder.list((current, name) -> new File(current, name).isFile());

        // To make sure 'file' is not null or empty
        if (file == null){
            file = new String[]{""};
        }else if(file.length == 0){
            file = new String[]{""};
        }

        String filePath = "data" + File.separator + listName + File.separator + itemName + File.separator + "file" + File.separator + file[0];

        String[] fileData = new String[2];
        fileData[0] = file[0];
        fileData[1] = filePath;

        return fileData;
    }

    // Searches for any items that contains the 'searchQuery' within the String[] 'listsToSearch'
    public String[][] searchFor(String searchQuery, String[] listsToSearch){
        // In the case there are no lists to search
        if (listsToSearch == null){
            return new String[0][0];
        }

        // Stores the itemName as well as the listName it is within if it matches
        List<String[]> results = new ArrayList<>();
        for (String listName : listsToSearch){
            String[] listItems = getListItems(listName);
            for (String itemName : listItems){
                if (itemName.toLowerCase().contains(searchQuery.toLowerCase())){
                    results.add(new String[]{listName, itemName});
                }
            }
        }

        // Convert 'List<String[]> results' to 'String[][] searchResults'
        String[][] searchResults = new String[results.size()][];
        for (int i = 0; i < results.size(); i++) {
            String[] row = results.get(i);
            searchResults[i] = row;
        }

        return searchResults;
    }
}