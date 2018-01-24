import java.util.Stack;
import components.MyNode;

public class Tree {
    MyNode root;
    public Tree(String input){
        root = new MyNode(input);
        root = textPaser(root);
    }

    public Tree(MyNode n){
        this.root = n;
    }

    public void printResult(MyNode root){
        System.out.print(infix(root));
        System.out.print("=");
        System.out.println(calculator(root));
    }

    public static MyNode textPaser(MyNode rootEmp){
        rootEmp = readStack(rootEmp.getValue());
        return rootEmp;
    }

    public String infix(MyNode n){
        return inOrderTree(n,0);
    }


    public static void inorder(MyNode n){
        inorderTraveling(n,0);
    }

    public String calculator(MyNode n){
        return calNode(n).toString();
    }

    private static MyNode readStack(String p){
        MyNode root = new MyNode("0");
        Stack<MyNode> stack  = new Stack<MyNode>();
        for (int i = 0; i < p.length(); i++ ){
            Character nowCh = p.charAt(i);
            //System.out.println(p.charAt(i));
            //System.out.println(isOperand(p.charAt(i)));
            if(!isOperand(nowCh)){
                stack.push(new MyNode(nowCh.toString()));
            }else{
                MyNode tmp = new MyNode(nowCh.toString());
                tmp.left = stack.pop();
                tmp.right = stack.pop();
                stack.push(tmp);
                //
            }

        }
        return stack.peek();
    }



    public static Integer calNode(MyNode n) {
        if (n == null) {
            return 0;
        }
        if(n.left == null && n.right == null){
            return n.getIntValue();
        }
        Integer sumLeft = calNode(n.right);
        Integer sumRight = calNode(n.left);
        Integer sum = 0;


        if(n.getValue().equals("+")){
            sum = sumLeft + sumRight;
        }else if(n.getValue().equals("-")){
            sum = sumLeft - sumRight;
        }else if(n.getValue().equals("*")){
            sum = sumLeft * sumRight;
        }else if(n.getValue().equals("/")){
            sum = sumLeft / sumRight;
        }
        return sum;
    }

    private static boolean isOperand(Character op){
        return op == '+' || op == '-' || op == '*' || op =='/';
    }


    private static String inOrderTree(MyNode n, int c) {
        String result = "";
        // Print Infix by tree
        if (n.left == null && n.right == null) {
            result += n.getValue().toString();

        }else {
            // internal node - an operator
            if( c > 0){
                result += "(";
            }
            result += inOrderTree(n.right, c + 1).toString();
            result += n.getValue().toString();
            result += inOrderTree(n.left, c + 1).toString();
            if(c >0) {
                result += ")";
            }
        }
        return result;
    }

    private static void inorderTraveling(MyNode n, int c){
        // Travel in tree with PreOrder
        if (n.left == null && n.right == null) {
            System.out.print(n.getValue());
        }else {
            inorderTraveling(n.right, c + 1);
            System.out.print(n.getValue());
            inorderTraveling(n.left, c + 1);
        }
    }
}
