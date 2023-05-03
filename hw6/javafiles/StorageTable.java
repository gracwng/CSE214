import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
/**
 * The database of Storages will be stored in a hash table to provide constant time insertion and deletion. Use the id of Storage 
 * objects as the key for hashing. In this assignment, you may provide your own implementation for the StorageTable class, or you 
 * may use the HashTable (or HashMap) implementation provided by the Java API.
 * 
 * Note: If you utilize the Java API classes, you must use inheritance. If you would like to know more about the Java API 
 * implementations, you should read the Oracle documentation for java.util.Hashtable and java.util.HashMap. Hint: a class can 
 * extend one class and implement another interface.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 */
public class StorageTable extends HashMap <Integer, Storage> implements Serializable {

    private static int serialVersionUID;

    /**
     * Manually inserts a Storage object into the table using the specified key.
     * 
     * @param storageID
     * The unique key for the Storage object
     * @param storage
     * the Storage object to insert into the table
     * @custom.preconditions
     * storageId ≥ 0 and does not already exist in the table.
     * Storage ≠ null
     * @custom.postconditions
     * The Storage has been inserted into the table with the indicated key.
     * @throws IllegalArgumentException
     * If any of the preconditions is not met.
     */

     //constructor
     public StorageTable(){
        super();
     }
    public void putStorage(int storageID, Storage storage) throws IllegalArgumentException{
        if (storage == null)
        throw new IllegalArgumentException("Storage can't be null.");
        //check: storageId ≥ 0 and does not already exist in the table.
        if (storageID >=0 && this.containsKey(storageID))
        throw new IllegalArgumentException("There is a storage that already exists with the same ID.");
        this.put(storageID, storage);
    }

    /**
     * Retrieve the Storage from the table having the indicated storageID. If the requested storageID does not exist in the 
     * StorageTable, return null. (AKA OPTION F)
     * @param storageID
     * Key of the Storage to retrieve from the table.
     * @return
     * A Storage object with the given key, null otherwise.
     */
    public Storage getStorage(int storageID){
        //the line below creates a set of each key and their values from the hashmap. This is how we are able to access each element
        //we need a set to help us.
        Set<Entry<Integer, Storage>> storages = this.entrySet();
        for (Entry<Integer, Storage> storage : storages){
            if ((int)(storage.getKey()) == storageID)
            return storage.getValue();
        }
        return null;
    }

    /**
     * gets all of the box information for a certain client
     * @param client
     * the name of the client storing the box with the company
     * @return
     * The string representation of the client's boxes
     */
    public String getStorageForClient(String client){
        String s = "";
        int count = 0;
        s+= String.format("%-7s%-30s%-10s", "Box#", "Contents", "Owner");
        s+= "\n---------------------------------------------------------------- \n";
        Set<Entry<Integer, Storage >> storages = this.entrySet();
        for (Entry<Integer, Storage> storage : storages){
            if (storage.getValue().getClient().equals(client)){
                s+= String.format("%-7s%-30s%-10s", storage.getKey(),storage.getValue().getContent(), storage.getValue().getClient());
                s += "\n";
                count++;
            }
        }
        if (count > 0)
        return s;
        else return client + " does not own any boxes";
    }

    // public boolean removeStorage(int id) throws IllegalArgumentException {
    //     this.remove((Integer)id);

    // }
    /**
     * String representation of the Storage table
     * when iterating through the Hashmap, I have to use a linkedHashmap to ensure the data is printed in order
     * how do we iterate through a hashmap? we have to use the entry interface that is extended by the hashmap class
     * we have to create an entry object that converts the hashtable into an entry that can be iterated through
     * there are different ways to implement the entry. if we use a linkedhashmap it prints the data the way it was inputed in order
     * if we use a treeMap, it prints the data in ascending order
     */
    public String toString(){
        String s = "";
        int count = 0;
        s+= String.format("%-7s%-30s%-10s", "Box#", "Contents", "Owner");
        s+= "\n---------------------------------------------------------------- \n";
        Set<Entry<Integer, Storage>> storage = this.entrySet();
        for (Entry<Integer, Storage> eachStorage: storage){
            s+= String.format("%-7s%-30s%-10s", eachStorage.getKey(),eachStorage.getValue().getContent(), eachStorage.getValue().getClient());
            s += "\n";
            count++;
        }
        if (count > 0)
        return s;
        else return "No data.";
    }
}
