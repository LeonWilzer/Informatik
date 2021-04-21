package Termtree;

import java.util.Stack;

import lib.BinaryTree;
import lib.Queue;
import lib.TIO;

public class Termtree {

    static public void Demo()
    {            
        int choice = TIO.AskInt(
            "What would you like to try out? \n" +
            "0. Cancel \n" +
            "1. Reverse Polish Calculator \n" +
            "2. Regular Polish Notation Calculator \n" +
            "3. Translate Regular Polish to Reverse Polish Notation \n" +
            "4. Translate Reverse Polish to Regular Polish Notation"
        );

        switch(choice)
        {
            case 1: 
                TIO.prt(SolveTree(PostfixToTree(StringToStack(TIO.AskString("Please provide an expression in reverse polish notation:")))).toString());
                break;
            case 2: 
                TIO.prt(SolveTree(PrefixToTree(StringToQueue(TIO.AskString("Please provide an expression in the regular Polish notation:")))).toString());
                break;
            case 3:
                TIO.prt(ToPostorder(PrefixToTree(StringToQueue(TIO.AskString("Please provide some regular Polish notation:")))));
                break;
            case 4:
                TIO.prt(ToPreorder(PostfixToTree(StringToStack(TIO.AskString("Please provide some reverse Polish notation:")))));
                break;
            default:
                return;
        }
    }

    static public String ToPreorder(BinaryTree<String> pTree)
    {
        String outString = "";

        if(!pTree.isEmpty())
            outString += pTree.getContent();
        if(!pTree.getLeftTree().isEmpty())
            outString += ToPreorder(pTree.getLeftTree());
        if(!pTree.getRightTree().isEmpty())
            outString += ToPreorder(pTree.getRightTree());

        return " " + outString;
    }

    static public String ToInorder(BinaryTree<String> pTree)
    {
        String outString = "";

        if(!pTree.getLeftTree().isEmpty())
            outString += ToInorder(pTree.getLeftTree());
        if(!pTree.isEmpty())
            outString += pTree.getContent();
        if(!pTree.getRightTree().isEmpty())
            outString += ToInorder(pTree.getRightTree());

        return " " + outString + " ";
    }

    static public String ToPostorder(BinaryTree<String> pTree)
    {
        String outString = "";

        if(!pTree.getLeftTree().isEmpty())
            outString += ToPostorder(pTree.getLeftTree());
        if(!pTree.getRightTree().isEmpty())
            outString += ToPostorder(pTree.getRightTree());
        if(!pTree.isEmpty())
            outString += pTree.getContent();

        return outString + " ";
    }

    static public Double SolveTree(BinaryTree<String> pTree)
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

    static public Queue<String> StringToQueue(String pPostfix)
    {
        char[] inputArray = pPostfix.toCharArray();
        String curNum = "";
        Queue<String> treeQueue = new Queue<String>();

        for (char i : inputArray){
            if(Character.isDigit(i))
            {
                curNum += i;
            }
            else
            {
                if(curNum != "")
                {
                    treeQueue.enqueue(curNum);
                    curNum = "";
                }
                if(!Character.isWhitespace(i))
                {
                    treeQueue.enqueue(Character.toString(i));
                }
            }
        }
        if(curNum != "")
            treeQueue.enqueue(curNum);

        return treeQueue;
    }

    static public BinaryTree<String> PrefixToTree(Queue<String> pTreeQueue)
    {
        if(!pTreeQueue.isEmpty())
        {
           BinaryTree<String> outTree = new BinaryTree<String>(pTreeQueue.front());
           pTreeQueue.dequeue();
        try{
            Double.parseDouble(outTree.getContent());
        }
        catch(NumberFormatException e)
        {
            outTree.setLeftTree(PrefixToTree(pTreeQueue));
            outTree.setRightTree(PrefixToTree(pTreeQueue));
        }
        return outTree;
        }
        else{
            return null;
        }
    }

    static public Stack<String> StringToStack(String pPostfix)
    {
        char[] inputArray = pPostfix.toCharArray();
        String curNum = "";
        Stack<String> treeStack = new Stack<String>();
        boolean hasDigit = false;
        boolean hasPoint = false;

        for (char i : inputArray){
            if(Character.isDigit(i)){
                curNum += i;
                hasDigit = true;
            }
            else if (i == '.')
            {
                if(hasDigit)
                {
                    if(hasPoint)
                    {
                        TIO.prt("Invalid floating point syntax!");
                        return null;
                    }
                    else
                    {
                        hasPoint = true;
                        curNum += '.';
                    }
                    }
                else if(!hasPoint)
                {
                    curNum += "0.";
                    hasDigit = false;
                }
                else
                {

                    TIO.prt("Invalid floating point syntax!");
                    return null;
                }
            }
            else
            {
                if(!curNum.isEmpty())
                {
                    treeStack.push(curNum);
                    curNum = "";
                    hasPoint = false;
                    hasDigit = false;
                }
                if(!Character.isWhitespace(i))
                {
                    treeStack.push(Character.toString(i));
                }
            }
        }
        return treeStack;
    }

    static public BinaryTree<String> PostfixToTree(Stack<String> pTreeStack)
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