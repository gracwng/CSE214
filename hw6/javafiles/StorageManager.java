import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Write a fully-documented class named StorageManager. This class will allow
 * the user to interact with the storage
 * database by listing the storage boxes occupied, allowing the user to add or
 * remove storage boxes, searching for a
 * box by id, and listing all the boxes for a user. In addition, the class
 * should provide the functionality to load
 * a saved (serialized) StorageTable or create a new one if a saved table does
 * not exist.
 * 
 * On startup, the StorageManager should check to see if the file storage.obj
 * exists in the current directory. If it does,
 * then the file should be loaded and deserialized into a StorageTable. If the
 * file does not exist, an empty StorageTable
 * object should be created and used instead. In either case, the user should be
 * allowed to fully interact with the storage
 * table, inserting, removing, selecting, and reading entries.
 * 
 * When the user enters 'Q' to quit the program, the storage table should be
 * serialized to the file storage.obj. That way,
 * the next time the program is run, the storages will remain in the database
 * and allow different users to manipulate the
 * storage records. If you would like to 'reset' the storage table, use the "X"
 * command to delete the file, if it exists,
 * when the program quits (you must first check if the file exists, delete it if
 * it does; and do not serialize the current
 * StorageTable upon exit).
 * 
 * @author Grace Wang
 *         Stony Brook ID: 115083013
 *         Recitation: CSE 214.R01
 **/
public class StorageManager {
    private static StorageTable storageTable;
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // check to see if the file storage.obj exists in the current directory
        /**On startup, the StorageManager should check to see if the file storage.obj exists in the current directory. If it does, 
         * then the file should be loaded and deserialized into a StorageTable. If the file does not exist, an empty StorageTable 
         * object should be created and used instead. In either case, the user should be allowed to fully interact with the storage 
         * table, inserting, removing, selecting, and reading entries. */
        try{
            File fileExists = new File("storage.obj");
            if (fileExists.exists()){
                FileInputStream file = new FileInputStream("storage.obj");
                ObjectInputStream inStream = new ObjectInputStream(file);

                storageTable = (StorageTable) inStream.readObject();
                inStream.close();
            }
            else
            storageTable = new StorageTable();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        String userInput = ""; 
        
        
        do {
            System.out.print(
                    "Hello, and welcome to Rocky Stream Storage Manager\n\n" +
                            "P - Print all storage boxes\n" +
                            "A - Insert into storage box\n" +
                            "R - Remove contents from a storage box\n" +
                            "C - Select all boxes owned by a particular client\n" +
                            "F - Find a box by ID and display its owner and contents\n" +
                            "Q - Quit and save workspace\n" +
                            "X - Quit and delete workspace\n\n" +
                            "Please select an option: ");
            userInput = input.nextLine().toUpperCase();

            switch (userInput) {
                // ---------------------------------------------------------------------------------------------------------//
                case ("P"):
                    // Print all storage boxes
                    // if (storageTable == null){
                    //     System.out.println("Storage table is null.");
                    //     break;
                    // }
                    System.out.println(storageTable.toString());
                    break;
                // ---------------------------------------------------------------------------------------------------------//
                case ("A"):
                    // Insert into storage box
                    int id;
                    String client;
                    String content;
                    System.out.print("Please enter id: ");
                    id = input.nextInt();
                    input.nextLine();
                    System.out.print("Please enter client: ");
                    client = input.nextLine();
                    System.out.print("Please enter contents: ");
                    content = input.nextLine();
                    System.out.println();
                    try{
                        Storage storage = new Storage(id, client, content);
                        storageTable.putStorage(id, storage);
                        System.out.println("Storage " + id + " set!");
                    }
                    catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                // ---------------------------------------------------------------------------------------------------------//
                case ("R"):
                    // Remove contents from a storage box
                    System.out.print("Please enter ID: ");
                    int storageID = input.nextInt();
                    input.nextLine();
                    Storage removedStorage = storageTable.remove((Integer)storageID);
                    if (removedStorage == null)
                    System.out.println("Box " + storageID + " was not found.");
                    else
                    System.out.println("Box " + storageID + " is now removed.");
                    break;
                // ---------------------------------------------------------------------------------------------------------//
                case ("C"):
                    // Select all boxes owned by a particular client
                    String clientName;
                    System.out.print("Please enter the name of the client: ");
                    clientName = input.nextLine();
                    System.out.println(storageTable.getStorageForClient(clientName));
                    break;
                // ---------------------------------------------------------------------------------------------------------//
                case ("F"):
                    // Find a box by ID and display its owner and contents
                    int ID;
                    System.out.print("Please enter ID: ");
                    ID = input.nextInt();
                    input.nextLine();
                    Storage storage = storageTable.getStorage(ID);
                    if (storage == null)
                    System.out.println("The storage you're looking for doesn't exist.");
                    else{
                        System.out.print(
                            "Box " + storage.getID() +
                            "\nContents: " + storage.getContent() +
                            "\nOwner: " + storage.getClient()
                        );
                        System.out.println();
                    }
                    break;
                // ---------------------------------------------------------------------------------------------------------//
                case ("Q"):
                    // Quit and save workspace
                    /**
                     * When the user enters 'Q' to quit the program, the storage table should be
                     * serialized to the file storage.obj.
                     * That way, the next time the program is run, the storages will remain in the
                     * database and allow different users
                     * to manipulate the storage records.
                     */
                    try {
                        // Create a new FileOutputStream object to write binary data to a file named
                        // "storage.obj"
                        // fileoutputstream is a file with binary data
                        FileOutputStream file = new FileOutputStream("storage.obj");

                        // Create a new ObjectOutputStream object to write serialized objects to the
                        // FileOutputStream
                        // this class is able to convert text to binary
                        ObjectOutputStream outStream = new ObjectOutputStream(file);

                        // Write the object "storage" to the output stream. This serializes the object
                        // and writes its binary representation to the file.
                        // this method actually converts all text to binary. very powerful method
                        outStream.writeObject(storageTable);

                        // Close the output stream to free up system resources.
                        outStream.close();
                        System.out.println("Storage Manager is quitting, all data is being saved.");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                // ---------------------------------------------------------------------------------------------------------//
                case ("X"):
                    // Quit and delete workspace
                    /*
                     * If you would like to 'reset' the storage table, use the "X" command to delete
                     * the file, if it exists, when the program quits (you must first check if the file exists, 
                     * delete it if it does; and do not serialize the current StorageTable upon exit).
                     */
                    // Check if the file exists and delete it if it does
                    File file = new File("storage.obj");
                    if (file.exists())
                    file.delete();
                    System.out.println("Storage Manager is quitting, all data is being erased.");
                    break;
                // ---------------------------------------------------------------------------------------------------------//
                default:
                    System.out.println("Invalid option.");
            }
            System.out.println();
        } while (!(userInput.equals("X") || userInput.equals("Q")));

    }
}
