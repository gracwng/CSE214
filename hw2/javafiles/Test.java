public class Test {
    public static void main(String[]args){
        Slide a = new Slide();
        a.setTitle("Welcome to my Presentation");
        System.out.println(a.getTitle());
        System.out.println(a);
        a.setBullet("My name is Grace", 1);
        a.setBullet("I live in NYC", 2);
        a.setBullet("I have a brother", 3);
        a.setBullet("I go to Stony Brook University", 4);
        a.setBullet("I am studying computer science", 5);

        System.out.println(a.getNumBullets());

        System.out.println(a);
        SlideListNode node = new SlideListNode(a);
        //node.slideshowSummary();


    }
}
