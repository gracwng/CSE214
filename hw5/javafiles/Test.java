import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
public class Test {
            /*the Scanner constructor expects a File or InputStream object to read data from. */
            //File file = new File(fileName);
            public static void main(String[]args) throws FileNotFoundException {
                StoryTree tree = new StoryTree();
                System.out.println(tree.getRoot());
                File file = new File("hw5-SampleStory.txt");
                Scanner fileIn = new Scanner (file);
                while (fileIn.hasNextLine()){
                    String oneLine = fileIn.nextLine();
                    //first for each line, parse it in into an array, with 0th index = position, 1st index = option, 2nd index = message
                    /*The split() method takes a regular expression as its argument, and the "|" character is a special character in 
                    regular expressions that denotes "or". To split a string on a literal "|" character, you need to escape it using 
                    backslashes, like this: String[] oneNode = oneLine.split("\\|"); */
                    String [] oneNodeString = oneLine.split("\\| ");
                    for (int i = 0; i < oneNodeString.length; i++){
                        System.out.print(oneNodeString[i] + " ");
                    }
                    System.out.println();
                // if (oneNodeString.length != 3){
                //     throw new DataFormatException ("The file contained data inconsistent with the expected data format");
                // }
                //create story tree node object
                StoryTreeNode oneNode = new StoryTreeNode(oneNodeString[0], oneNodeString[1], oneNodeString[2]);
                //then begin parsing position into correct part of tree by using the position
                String position = oneNodeString[0];
                //but how?
                //what I know: 1 represents leftchild, 2 represents middle child, 3 represents right child

                //test: maybe use a while loop?
                //when we check each position, we start at the root node, and traverse, for every single new node
                tree.setCursor(tree.getRoot());
                int i = 0;
                while (position.charAt(i+1) != ' '){
                    //but when we reach the last number, that is when we add a new child
                    switch(position.charAt(i)){
                        case('1'):  //if position is at 1, go into left child
                        tree.setCursor(tree.getCursor().getLeftChild());
                        break;
                        case('2'): //if position is at 2, go into middle child
                        tree.setCursor(tree.getCursor().getMiddleChild());

                        break;
                        case('3'):  //if position is at 3, go into right child
                        tree.setCursor(tree.getCursor().getRightChild());
                        break;
                        default: 
                    }
                    i++;
                }
                //when we reach the last element of the position, set the current cursor (which is still the previous cursor) to the position
                //of the corresponding number/child
                switch(position.charAt(i)){
                    case('1'):  //if last position is at 1, set cursor's left child to the new node
                    tree.getCursor().setLeftChild(oneNode);
                    break;
                    case('2'): //if last position is at 2, set cursor's middle child to the new node
                    tree.getCursor().setMiddleChild(oneNode);
                    break;
                    case('3'):  //if last position is at 3, set cursor's right child to the new node
                    tree.getCursor().setRightChild(oneNode);
                    break;
                    default: 
                }

            }

            
            System.out.println(tree.getCursor());
            tree.getRoot().traverse();
            fileIn.close();
            // String[][] options = tree.getOptions();
            // for (int i = 0; i < options.length; i++){
            //     for (int j = 0; j < options[i].length; j++){
            //         System.out.print(options[i][j] + " ");
            //     }
            //     System.out.println();

            // }
            //why isnt this working??
            //tree.findChild("111", tree.getRoot());

            try{
              // tree.selectChild("1-1-1");
              System.out.println("Tree before removal of 1-2-1");
              tree.resetCursor();
              tree.getCursor().traverse();
                tree.findAndRemoveChild("1-2-1");
                System.out.println("Tree after removal of 1-2-1");

                tree.getCursor().traverse();

            }
            // catch(InvalidArgumentException e){
            //     System.out.println(e.getMessage());
            // }
            catch(NodeNotPresentException e){
                System.out.println(e.getMessage());
            }
            System.out.println();
            
            //  System.out.println("saving tree");

            //QUESTION: this method isn't working
            //  StoryTree.saveTree("SaveSampleStory.txt", tree);

            tree.resetCursor();
            System.out.println("*" +tree.getCursor().getPosition() + "*");
            System.out.println(tree.getCursorPosition().length());

    }

    //method to traverse the tree in preorder
   

}

    
        
            
