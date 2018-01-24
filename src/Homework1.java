import components.MyNode;

import javax.swing.*;

public class Homework1 extends JPanel  {
    private JTree tree;
    static MyNode root;
    static Tree treeTraveling;


    public static void main(String[] args) {
        if (args.length <= 0) {
            System.exit(0);
        }
        String input = args[0];
        //String input = "251-*32*+";
        treeTraveling = new Tree(input);
        treeTraveling.printResult(treeTraveling.root);
        root = treeTraveling.root;


        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(root);
            }
        });
    }

    private static void createAndShowGUI(MyNode root) {

        //Create and set up the window.
        JFrame frame = new JFrame("Binary Tree Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new ProgramGUIA(root));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static String infix(MyNode n){
        Tree t = new Tree(n);
        return t.infix(t.root);
    }


    public static void inorder(MyNode n){
        Tree t = new Tree(n);
        t.inorder(t.root);
    }

    public static String calculator(MyNode n){
        Tree t = new Tree(n);
        return t.calNode(t.root).toString();
    }

}

