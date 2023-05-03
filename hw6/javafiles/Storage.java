import java.io.Serializable;
/**
 * Write a fully documented class named Storage that contains three private data fields: int id, String client, String contents, 
 * along with public getter and setter methods for each of these fields. This class will be used to represent a storage box 
 * registered with the company. This class should implement the Serializable interface
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 **/
public class Storage implements Serializable {
    private static long serialVersionUID;
    //the unique ID of the storage box
    private int id;
    //the name of the client storing the box with the company
    private String client;
    //a brief description of the contents of the box
    private String content;

    /**
     * Default constructor for storage
     */
    public Storage(){
        id = 0;
        client = null;
        content = null;
    }

    /**
     * Arg Constructor for storage
     * @param id
     * the unique ID of the storage box
     * @param client
     * the name of the client storing the box with the company
     * @param content
     * a brief description of the contents of the box
     */
    public Storage(int id, String client, String content){
        this.id = id;
        this.client = client;
        this.content = content;
    }

    //public getters/setters

    /**
     * gets the unique ID of the storage box
     * @return
     * the unique ID of the storage box
     */
    public int getID(){
        return id;
    }

    /**
     * gets the name of the client storing the box with the company
     * @return
     * the name of the client storing the box with the company
     */
    public String getClient(){
        return client;
    }

    /**
     * gets a brief description of the contents of the box
     * @return
     * a brief description of the contents of the box
     */
    public String getContent(){
        return content;
    }

    /**
     * sets the unique ID of the storage box
     * @param id
     * the new unique ID of the storage box
     */
    public void setID(int id){
        this.id = id;
    }

    /**
     * sets the name of the client storing the box with the company
     * @param client
     * the name of the new client storing the box with the company
     */
    public void setClient(String client){
        this.client = client;
    }

    /**
     * sets a brief description of the contents of the box
     * @param content
     * a brief description of the contents of the box
     */
    public void setContent(String content){
        this.content = content;
    }
}