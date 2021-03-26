package Termtree;

import java.util.LinkedList;
import java.util.Stack;

import lib.BinaryTree;
import lib.Queue;
import lib.TIO;
import lib.TreeHelper;

public class Termtree {

    private Queue<String> treeQueue;
    final private BinaryTree<String> mainTree;

    public Termtree()
    {

        mainTree = PostfixToTree(StringToStack("4 4 * 4 3 / +"));
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

    public Stack<String> StringToStack(String pPostfix)
    {
        char[] inputArray = pPostfix.toCharArray();
        String curNum = "";
        Stack<String> treeStack = new Stack<String>();

        for (char i : inputArray){
            if(Character.isDigit(i)){
                curNum += i;
            }
            else
            {
                if(curNum != "")
                {
                    treeStack.push(curNum);
                    curNum = "";
                }
                if(!Character.isWhitespace(i))
                {
                    treeStack.push(Character.toString(i));
                }
            }
        }
        return treeStack;
    }

    public BinaryTree<String> PostfixToTree(Stack<String> pTreeStack)
    {
        if(!pTreeStack.isEmpty())
        {
        BinaryTree<String> outTree = new BinaryTree<String>(pTreeStack.pop());
        try{
            Double.parseDouble(outTree.getContent());
        }
        catch(NumberFormatException e)
        {
            outTree.setRightTree(PostfixToTree(pTreeStack));
            outTree.setLeftTree(PostfixToTree(pTreeStack));
        }
        return outTree;
        }
        else{
            return null;
        }
    }
}