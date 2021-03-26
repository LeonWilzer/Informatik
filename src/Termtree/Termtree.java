package Termtree;

import lib.BinaryTree;
import lib.Queue;
import lib.TIO;
import lib.TreeHelper;

public class Termtree {

    private TreeHelper<String> treeBuilder;
    private Queue<String> treeQueue;
    final private BinaryTree<String> mainTree;

    public Termtree()
    {
        treeBuilder = new TreeHelper<String>();
        treeQueue = new Queue<String>();
        treeQueue.enqueue("*");
        treeQueue.enqueue("/");
        treeQueue.enqueue("4");
        treeQueue.enqueue("4");
        treeQueue.enqueue("\u0000");
        treeQueue.enqueue("\u0000");
        treeQueue.enqueue("\u0000");
        treeQueue.enqueue("\u0000");
        treeQueue.enqueue("4");
        treeQueue.enqueue("3");

        treeBuilder.setTreeQueue(treeQueue);
        treeBuilder.setBreakCondition("\u0000");
        mainTree = treeBuilder.buildBinTree("+");
        TIO.prt(ToPreorder(mainTree));
        TIO.prt(ToInorder(mainTree));
        TIO.prt(ToPostorder(mainTree));
        TIO.prt(SolveTree(mainTree).toString());
    }

    public String ToPreorder(BinaryTree<String> pTree)
    {
        String outString = "";

        if(!pTree.isEmpty())
            outString += pTree.getContent();
        if(!pTree.getLeftTree().isEmpty())
            outString += ToPreorder(pTree.getLeftTree());
        if(!pTree.getRightTree().isEmpty())
            outString += ToPreorder(pTree.getRightTree());

        return outString;
    }

    public String ToInorder(BinaryTree<String> pTree)
    {
        String outString = "";

        if(!pTree.getLeftTree().isEmpty())
            outString += ToPreorder(pTree.getLeftTree());
        if(!pTree.isEmpty())
            outString += pTree.getContent();
        if(!pTree.getRightTree().isEmpty())
            outString += ToPreorder(pTree.getRightTree());

        return outString;
    }

    public String ToPostorder(BinaryTree<String> pTree)
    {
        String outString = "";

        if(!pTree.getLeftTree().isEmpty())
            outString += ToPreorder(pTree.getLeftTree());
        if(!pTree.getRightTree().isEmpty())
            outString += ToPreorder(pTree.getRightTree());
        if(!pTree.isEmpty())
            outString += pTree.getContent();

        return outString;
    }

    public Double SolveTree(BinaryTree<String> pTree)
    {
        if(!pTree.isEmpty())
        {
            Double number = null;

            try{
            number = Double.parseDouble(pTree.getContent());
            }
            catch(NumberFormatException e)
            {
                // e.printStackTrace();
            }

            if(number != null)
            {
                return Double.parseDouble(pTree.getContent());
            }
            else
            {
               switch(pTree.getContent())
               {
                    case "*":
                        return SolveTree(pTree.getLeftTree()) * SolveTree(pTree.getRightTree());
                    case "/":
                        return SolveTree(pTree.getLeftTree()) / SolveTree(pTree.getRightTree());
                    case "+":
                        return SolveTree(pTree.getLeftTree()) + SolveTree(pTree.getRightTree());
                    case "-":
                        return SolveTree(pTree.getLeftTree()) - SolveTree(pTree.getRightTree());
               }
            }
        }
        return null;
    }

    public BinaryTree<String> PrefixToTree(String pPrefix)
    {
        return new BinaryTree<String>();
    }

    public BinaryTree<String> postfixToTree(String pPostfix)
    {
        return new BinaryTree<String>();
    }
}
