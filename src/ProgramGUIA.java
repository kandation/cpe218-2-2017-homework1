import components.MyNode;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

public class ProgramGUIA extends JPanel implements TreeSelectionListener {
    private JEditorPane htmlPane;
    private JTree tree;

    private static String lineStyle = "Horizontal";
    private static boolean playWithLineStyle = false;

    public ProgramGUIA(MyNode n){
        super(new GridLayout(1,0));

        //createNodes(n);


        tree = new JTree(createNodesRecv(n));
        ImageIcon tutorialIcon = createImageIcon("middle.gif");
        if (tutorialIcon != null) {
            tree.setCellRenderer(new MyRenderer(tutorialIcon));
        } else {
            System.err.println("Tutorial icon missing; using default.");
        }


        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        //Create the scroll pane and add the tree to it.
        JScrollPane treeView = new JScrollPane(tree);

        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);


        JScrollPane htmlView = new JScrollPane(htmlPane);

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100);
        splitPane.setPreferredSize(new Dimension(500, 300));

        //Add the split pane to this panel.
        add(splitPane);

    }

    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Homework1.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private void createNodes(MyNode top) {
        createNodesRecv(top);
    }

    private DefaultMutableTreeNode createNodesRecv(MyNode n){
        DefaultMutableTreeNode treeTop = new DefaultMutableTreeNode(n);
        if (n.left != null && n.right != null){
            treeTop.add(createNodesRecv(n.right));
            treeTop.add(createNodesRecv(n.left));
        }return treeTop;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node == null) return;
        Object nodeInfo = node.getUserObject();
        MyNode n = (MyNode) nodeInfo;
        Tree tmpTree = new Tree(n);
        String infix = tmpTree.infix(tmpTree.root);
        String cal = tmpTree.calculator(tmpTree.root);
        if (node.isLeaf()) {
            htmlPane.setText(infix);
        } else {
           htmlPane.setText(infix+"="+cal);

        }
    }

    private class MyRenderer extends DefaultTreeCellRenderer {
        Icon tutorialIcon;

        public MyRenderer(Icon icon) {
            tutorialIcon = icon;
        }

        public Component getTreeCellRendererComponent(
                JTree tree,
                Object value,
                boolean sel,
                boolean expanded,
                boolean leaf,
                int row,
                boolean hasFocus) {

            super.getTreeCellRendererComponent(
                    tree, value, sel,
                    expanded, leaf, row,
                    hasFocus);
            if(!leaf){
                setIcon(tutorialIcon);
            }


            return this;
        }
    }
}
