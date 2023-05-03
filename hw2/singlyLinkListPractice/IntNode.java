public class IntNode {
    private int data;
    private IntNode link;

    public IntNode(){
        data = 0;
        link = null;
    }

    public IntNode(int data){
        this.data = data;
        this.link = null;
    }
    
    //getter methods
    public int getData(){
        return data;
    }

    public IntNode getLink(){
        return link;
    }

    //setter methods
    public void setData(int data){
        this.data = data;
    }

    public void setLink(IntNode link){
        this.link = link;
    }
    
}
