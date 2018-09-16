/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication29;
import java.util.*;
/**
 *
 * @author henok
 */
public class JavaApplication29 {
     // Current input Lisp expression
    private String currentExpr;

    // Main expression stack & current operation stack, see algorithm in evaluate()
    private Stack<Object> exprStack;
    private Stack<Double> computeStack;

    // default constructor
    // set currentExpr to "" 
    // create stack objects
    public JavaApplication29()
    {
        currentExpr = "";
        exprStack = new Stack<Object>();
        computeStack = new Stack<Double>();        
	// add statements
    }

    // constructor with an input expression 
    // set currentExpr to currentExpression 
    // create stack objects
    public JavaApplication29(String currentExpression) 
    {
        currentExpr = currentExpression;
        exprStack = new Stack<Object>();
        computeStack = new Stack<Double>();   
	// add statements
    }

    // set currentExpr to currentExpression 
    // clear stack objects
    public void reset(String currentExpression) 
    {
         currentExpr = currentExpression;
         exprStack.clear();
         computeStack.clear();
	// add statements
    }


    // This function evaluates current operator with its operands
    // See complete algorithm in evaluate()
    //
    // Main Steps:
    // 		Pop operands from exprStack and push them onto 
    // 			computeStack until you find an operator
    //  	Apply the operator to the operands on computeStack
    //          Push the result into exprStack
    //
    
    private void evaluateCurrentOperation()
    {   
        if (exprStack.isEmpty()) {
			throw new LispExprEvaluatorException("The expression is empty");
		}

		double result;
		Object aOperation = exprStack.pop();
                
                if (aOperation == "+" && computeStack.size() == 0)
                    {
                        result = computeStack.push(0.0);
                        exprStack.push(result);
                    }
                        else if (aOperation == "*" && computeStack.size() == 0)
                    {
                        result = computeStack.push(1.0);
                        exprStack.push(result);
             
                    }
		try {
			while (aOperation.equals(aOperation.toString())) {
				computeStack.push(Double.parseDouble(aOperation.toString()));
				aOperation = exprStack.pop();
			}
		} catch (EmptyStackException e) {
			throw new LispExprEvaluatorException("The stack is empty");
		}
		String item = aOperation.toString();
                
        switch (item) {
            
            case "+":
                result = 0;
                while(!computeStack.isEmpty())
                    {
                        result += computeStack.pop();
                    }
                    exprStack.push(Double.toString(result));
                break;
                
            case "-": 
                
                if (computeStack == null || computeStack.isEmpty())
                    {
                        throw new LispExprEvaluatorException("Subtraction error, at least one operand required!"); 
                    }
                    else
                    {
                        result = computeStack.pop();
                        if(computeStack.isEmpty())
                            {
                                result = -result;
                            }
                             else
                            {
                                while(!computeStack.isEmpty())
                                    {
                                        result -= computeStack.pop();
                                    }
                            }           
                    exprStack.push(Double.toString(result));
                    }
              
                break; 
                
            case "*":
                
                result = 1;
                    while(!computeStack.isEmpty())
                        {
                            result *= computeStack.pop();
                        }
                    exprStack.push(Double.toString(result));
                break;
                
            case "/":
                
                 if (computeStack == null || computeStack.isEmpty())
                    {
                        throw new LispExprEvaluatorException("Division error, at least one operand required "); 
                    }
                    else
                    {
                        result = computeStack.pop();
                        
                        if (computeStack.isEmpty() && result != 0.0)
                        {
                            result = 1.0/result;
                        } 
                        else
                        {    
                            while(!computeStack.isEmpty())
                                {
                                result /= computeStack.pop();
                                }
                        }
                     exprStack.push(Double.toString(result));
                    }
                break;
                
            case "(":
          
            default:
                
                throw new LispExprEvaluatorException(item + " is not a legal expression operator");
           } 
           // add statements
        }
       
    /**
     * This function evaluates current Lisp expression in currentExpr
     * It return result of the expression 
     *
     * The algorithm:  
     *
     * Step 1   Scan the tokens in the string.
     * Step 2		If you see an operand, push operand object onto the exprStack
     * Step 3  	    	If you see "(", next token should be an operator
     * Step 4  		If you see an operator, push operator object onto the exprStack
     * Step 5		If you see ")"  // steps in evaluateCurrentOperation() :
     * Step 6			Pop operands and push them onto computeStack 
     * 					until you find an operator
     * Step 7			Apply the operator to the operands on computeStack
     * Step 8			Push the result into exprStack
     * Step 9    If you run out of tokens, the value on the top of exprStack is
     *           is the result of the expression.
     */
    public double evaluate()
    {
        //double result = 0.00;
        // only outline is given...
        // you need to add statements/local variables
        // you may delete or modify any statements in this method


        // use scanner to tokenize currentExpr
        Scanner currentExprScanner = new Scanner(currentExpr);
        
        // Use zero or more white space as delimiter,
        // which breaks the string into single character tokens
        currentExprScanner = currentExprScanner.useDelimiter("\\s*");
            
        // Step 1: Scan the tokens in the string.
        while (currentExprScanner.hasNext())
        {
		
     	    // Step 2: If you see an operand, push operand object onto the exprStack
            if (currentExprScanner.hasNextInt())
            {
                // This force scanner to grab all of the digits
                // Otherwise, it will just get one char
                String dataString = currentExprScanner.findInLine("\\d+");
                
                // more ...
                 //exprStack.push(Double.parseDouble(dataString));
                 exprStack.push(dataString);
            }
            else
            {
                // Get next token, only one char in string token
                String aToken = currentExprScanner.next();
                char item = aToken.charAt(0);
                String nextToken;
                char nextItem;
                
                switch (item)
                {
     		    // Step 3: If you see "(", next token shoube an operator
                    case '(':
                    nextToken = currentExprScanner.next();
                    nextItem = nextToken.charAt(0);
     		    // Step 4: If you see an operator, push operator object onto the exprStack
                     if(nextItem == '+')
                        {
                            exprStack.push(nextItem);  
                        }
                     else if (nextItem == '-')
                        {
                            exprStack.push(nextItem);
                        }
                     else if (nextItem == '*')
                        {
                           exprStack.push(nextItem); 
                        }
                     else if (nextItem == '/')
                        {
                            exprStack.push(nextItem);
                        }
                       else
                        {
                            exprStack.push(nextItem);
                        }
                        break; 
                        
                    default: 
                        throw new LispExprEvaluatorException(item + " is not a legal expression operator");
     		    // Step 5: If you see ")"  // steps in evaluateCurrentOperation() :
                     case ')':
                             System.out.println(exprStack);
                             evaluateCurrentOperation();
                } // end switch
            } // end else
        } // end while
        
        // Step 9: If you run out of tokens, the value on the top of exprStack is
        //         is the result of the expression.
        //
        //         return result
	//return 0.0;  // change this statement
       return Double.parseDouble(exprStack.pop().toString());
    }

    //=============================================================
    // DO NOT MODIFY ANY STATEMENTS BELOW
    //=============================================================
    
    // This static method is used by main() only
    private static void evaluateExprTest(String s, JavaApplication29 expr, String expect)
    {
        Double result;
        System.out.println("Expression " + s);
        System.out.printf("Expected result : %s\n", expect);
	expr.reset(s);
	try {
          result = expr.evaluate();
          System.out.printf("Evaluated result : %.2f\n", result);
	} 
	catch (LispExprEvaluatorException e) {
          System.out.println("Evaluated result : "+e);
	}
        System.out.println("-----------------------------");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         JavaApplication29 expr= new JavaApplication29();
        String test1 = "(+ (- 6) (* 2 3 4) (/ (+ 3) (* 1) (- 2 3 1)) (+))";
        String test2 = "(+ (- 632) (* 21 3 4) (/ (+ 32) (* 1) (- 21 3 1)) (+))";
        String test3 = "(+ (/ 2) (* 2) (/ (+ 1) (+ 1) (- 2 1 ))(*))";
        String test4 = "(+ (/2)(+))";
        String test5 = "(+ (/2 3 0))";
        String test6 = "(+ (/ 2) (* 2) (/ (+ 1) (+ 3) (- 2 1 ))))";
        String test7 = "(+ (/) )";
        String test8 = "(+ (- 6) (* 2 3 4) (/ (+ 3) (* 1) (-)) (+1))";
	evaluateExprTest(test1, expr, "16.50");
	evaluateExprTest(test2, expr, "-378.12");
	evaluateExprTest(test3, expr, "4.50");
	evaluateExprTest(test4, expr, "0.50");
	evaluateExprTest(test5, expr, "Infinity or LispExprEvaluatorException");
	evaluateExprTest(test6, expr, "LispExprEvaluatorException");
	evaluateExprTest(test7, expr, "LispExprEvaluatorException");
	evaluateExprTest(test8, expr, "LispExprEvaluatorException");
    }
    
}
