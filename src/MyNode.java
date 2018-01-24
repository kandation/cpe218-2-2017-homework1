package components;

public class MyNode {
    String value;
    public MyNode left, right;


    public MyNode(String key){
        value = key;
        left = right = null;
    }

    public MyNode(MyNode left, String key, MyNode right){
        this.value = key;
        this.left = left;
        this.right = right;
    }

    public String getValue(){
        return this.value;
    }

    public int getIntValue(){
        String op = "+-*/";
        if( op.indexOf(value) == -1){
            return Integer.parseInt(this.value);
        }return 0;
    }

    public String toString() {
        return value;
    }


}
